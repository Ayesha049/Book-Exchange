package com.ayeshaapp.android.bookexchange1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by l on 1/2/18.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>  {
    private Context context;
    private static RecyclerViewClickListener itemListener;
    ArrayList<String> BookNameList;
    ArrayList<String> BookAuthorList;
    ArrayList<String> BookPicList;


    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView profileImage;
        TextView full_name, user_name;

        public SearchViewHolder(View itemView) {
            super(itemView);
            profileImage = (ImageView) itemView.findViewById(R.id.image_item);
            full_name = (TextView) itemView.findViewById(R.id.bookName);
            user_name = (TextView) itemView.findViewById(R.id.bookPrice);
            TextView tt = itemView.findViewById(R.id.noti);
            tt.setVisibility(TextView.INVISIBLE);
            itemView.setOnClickListener(this);
            //user_name.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if (itemListener != null) itemListener.recyclerViewListClicked(v, getAdapterPosition());

            /*Context cc = itemView.getContext();
            Intent intent = new Intent(context, Advertise.class);
            context.startActivity(intent);*/

        }
    }


    public void setClickListener(RecyclerViewClickListener itemClickListener) {
        this.itemListener = itemClickListener;
    }



    public SearchAdapter(Context context, ArrayList<String> BookNameList, ArrayList<String> BookAuthorList, ArrayList<String> BookPicList) {
        this.context = context;
        this.BookNameList = BookNameList;
        this.BookAuthorList = BookAuthorList;
        this.BookPicList = BookPicList;
        this.context = context;

    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        return new SearchAdapter.SearchViewHolder(view);


    }


    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.full_name.setText(BookNameList.get(position));
        holder.user_name.setText(BookAuthorList.get(position));
        Glide.with(context).load(BookPicList.get(position)).asBitmap().placeholder(R.mipmap.ic_launcher_round).into(holder.profileImage);

    }

    @Override
    public int getItemCount() {
        return BookNameList.size();
    }
}
