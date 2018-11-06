package com.caiomcg.es.Controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.Volley;
import com.caiomcg.es.C;
import com.caiomcg.es.Controllers.MataFragment.OnListFragmentInteractionListener;
import com.caiomcg.es.Models.Ad;
import com.caiomcg.es.Models.User;
import com.caiomcg.es.R;
import com.caiomcg.es.Utils.Requests;
import com.caiomcg.es.Utils.UserFactory;
import com.caiomcg.es.dummy.DummyContent.DummyItem;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Ad> mValues;
    public static final String TAG = "RecyclerView";

    public MyItemRecyclerViewAdapter(ArrayList<Ad> arrayList) {
        mValues = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTitle.setText(mValues.get(position).title);
        holder.mDescription.setText(mValues.get(position).description);

        holder.mUserDate.setText(mValues.get(position).registerDate);

        Picasso.with(AgroTecApplication.getContext()).load(mValues.get(position).urlImage).into(holder.mPostImage);

        holder.mUserName.setText(mValues.get(position).user.userName);
        Picasso.with(AgroTecApplication.getContext()).load(mValues.get(position).user.urlImage).into(holder.mUserImageProfile);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
            }
        });
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "Error getting bitmap", e);
        }
        return bm;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final TextView mDescription;
        public final TextView mUserName;
        public final TextView mUserDate;
        public final ImageView mUserImageProfile;
        public final ImageView mPostImage;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.item_mata_title);
            mDescription = (TextView) view.findViewById(R.id.item_mata_description);
            mUserName = (TextView) view.findViewById(R.id.item_mata_userName);
            mUserDate = (TextView) view.findViewById(R.id.item_mata_date);
            mUserImageProfile = (ImageView) view.findViewById(R.id.item_mata_userImageProfile);
            mPostImage = (ImageView) view.findViewById(R.id.post_image);
        }
    }
}
