package com.caiomcg.es;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.caiomcg.es.Controllers.NoAccountActivity;
import com.caiomcg.es.Controllers.RegionsNavigation;
import com.caiomcg.es.Models.Ad;
import com.caiomcg.es.Models.User;
import com.caiomcg.es.Utils.Requests;
import com.caiomcg.es.Utils.UserFactory;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PostAdFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PostAdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostAdFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final  int FETCH_IMAGE = 2;
    public static final String TAG = "PostAdFragment";

    private ImageView imageView;
    private EditText title;
    private EditText description;
    private RadioGroup group;

    private User user;

    private OnFragmentInteractionListener mListener;

    public PostAdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PostAdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostAdFragment newInstance(User user) {
        PostAdFragment fragment = new PostAdFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", user);
        Log.e(TAG, "User " + user.toString());
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "create");
        if (getArguments() != null) {
            Log.e(TAG, "Got user");
            user = ((User)getArguments().getSerializable("User"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_ad, container, false);

        imageView = view.findViewById(R.id.image_post_fragment);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Selecione a imagem do anuncio"), FETCH_IMAGE);
        });

        title = view.findViewById(R.id.title_post_fragment);
        description = view.findViewById(R.id.description_post_fragment);
        group = view.findViewById(R.id.ragio_group_post_fragment);

        view.findViewById(R.id.create_post_fragment).setOnClickListener(v -> {
            Ad ad = new Ad();
            ad.user = this.user;
            ad.urlImage = "";
            ad.registerDate = "xx/xx/xxxx";//TODO: get date
            ad.title = title.getText().toString();
            ad.description = description.getText().toString();
            switch (group.getCheckedRadioButtonId()) {
                case R.id.radioButton1:
                    ad.regiao = 1;
                    break;
                case R.id.radioButton2:
                    ad.regiao = 2;
                    break;
                case R.id.radioButton3:
                    ad.regiao = 3;
                    break;
                case R.id.radioButton4:
                    ad.regiao = 4;
                    break;
            }

            //TODO: Send post

            JSONObject jsonObject = null;

            try {
                jsonObject = ad.toJsonObject();
                Log.e(TAG, "Object: " + jsonObject.toString(4));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.e(TAG, "Posting");
            Requests.getInstance().asJsonObject(Request.Method.POST, C.adsURI().toString(),
                    jsonObject, response -> {
                        Log.e(TAG, "All good");
                    }, error -> {
                        Log.e(TAG, "An error occured: " + error.toString());
                        error.printStackTrace();
                    });

        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FETCH_IMAGE && data != null) {
            imageView.setImageURI(data.getData());
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
