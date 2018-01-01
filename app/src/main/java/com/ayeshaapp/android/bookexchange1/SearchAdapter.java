package com.ayeshaapp.android.bookexchange1;

import android.content.Context;
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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> BookNameList;
    ArrayList<String> BookAuthorList;
    ArrayList<String> BookPicList;

    class SearchViewHolder extends RecyclerView.ViewHolder{
        ImageView profileImage;
        TextView full_name, user_name;

        public SearchViewHolder(View itemView) {
            super(itemView);
            profileImage = (ImageView) itemView.findViewById(R.id.image_item);
            full_name = (TextView) itemView.findViewById(R.id.bookName);
            user_name = (TextView) itemView.findViewById(R.id.bookPrice);
        }


    }

    public SearchAdapter(Context context, ArrayList<String> BookNameList, ArrayList<String> BookAuthorList, ArrayList<String> BookPicList) {
        this.context = context;
        this.BookNameList = BookNameList;
        this.BookAuthorList = BookAuthorList;
        this.BookPicList = BookPicList;
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

        holder.full_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Full Name Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return BookNameList.size();
    }
}
