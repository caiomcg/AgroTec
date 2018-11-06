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
import com.caiomcg.es.Utils.Share;
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
        if (!mValues.get(position).urlImage.isEmpty() && !mValues.get(position).urlImage.equals("null")) {
            Picasso.with(AgroTecApplication.getContext()).load(mValues.get(position).urlImage).into(holder.mPostImage);
        }
        holder.mTitle.setText(mValues.get(position).title);
        holder.mDescription.setText(mValues.get(position).description);

        holder.mUserName.setText(mValues.get(position).user.userName);
        if (!mValues.get(position).user.urlImage.isEmpty()) {
            Picasso.with(AgroTecApplication.getContext()).load(mValues.get(position).user.urlImage).into(holder.mUserImageProfile);
        }
        holder.mUserDate.setText(mValues.get(position).registerDate);

        holder.mView.setOnClickListener(v -> {
            AgroTecApplication.getContext().startActivity(Share.sendText(String.format("%s\n%s\n\n%s",
                    holder.mTitle.getText().toString(),holder.mDescription.getText().toString(),
                    holder.mUserName.getText().toString())));
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mTitle;
        final TextView mDescription;
        final TextView mUserName;
        final TextView mUserDate;
        final ImageView mUserImageProfile;
        final ImageView mPostImage;

        ViewHolder(View view) {
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
