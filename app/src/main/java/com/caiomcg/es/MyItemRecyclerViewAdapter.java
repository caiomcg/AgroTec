package com.caiomcg.es;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caiomcg.es.MataFragment.OnListFragmentInteractionListener;
import com.caiomcg.es.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<String> mValues;

    public MyItemRecyclerViewAdapter(ArrayList<String> arrayList) {
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
        holder.mTitle.setText(mValues.get(position));
        holder.mDescription.setText(mValues.get(position));

        holder.mUserName.setText(mValues.get(position));
        holder.mUserDate.setText(mValues.get(position));
        //holder.mUserImageProfile.setImageURI(Uri.parse("https://avatars3.githubusercontent.com/u/11435231?s=400&u=55b9178d434edb72fc13a8b2f1bf46b47d48326e&v=4"));

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

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.item_mata_title);
            mDescription = (TextView) view.findViewById(R.id.item_mata_description);
            mUserName = (TextView) view.findViewById(R.id.item_mata_userName);
            mUserDate = (TextView) view.findViewById(R.id.item_mata_date);
            mUserImageProfile = (ImageView) view.findViewById(R.id.item_mata_userImageProfile);
        }
    }
}
