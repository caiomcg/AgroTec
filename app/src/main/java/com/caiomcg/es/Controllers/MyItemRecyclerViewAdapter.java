package com.caiomcg.es.Controllers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caiomcg.es.Controllers.MataFragment.OnListFragmentInteractionListener;
import com.caiomcg.es.Models.Ad;
import com.caiomcg.es.R;
import com.caiomcg.es.Utils.Share;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
