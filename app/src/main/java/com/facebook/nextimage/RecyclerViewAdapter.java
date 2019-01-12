package com.facebook.nextimage;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {
    private static final String TAG = "RecyclerViewAdapter";

    private Context context;
    private ArrayList<String> mList;

    public RecyclerViewAdapter(Context context, ArrayList<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Log.d(TAG, "onCreateViewHolder: InflatingLayout");

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.snippet_image_view_single, viewGroup, false);

        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {


        Log.d(TAG, "onBindViewHolder: LoadingPicaso");

        Picasso.get().load(mList.get(i))
                .resize(350, 350)
                .centerCrop()
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("image", mList.get(i));
                intent.putExtra("position", i);
                intent.putExtra("list", mList);

                context.startActivity(intent
                );
            }
        });
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ImageView image;

        public Holder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.snippetImageView);
        }
    }
}
