package com.codepath.bestsellerlistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.bestsellerlistapp.models.BestSellerBook;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link BestSellerBook} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class BestSellerBooksRecyclerViewAdapter extends RecyclerView.Adapter<BestSellerBooksRecyclerViewAdapter.BookViewHolder> {

    private final List<BestSellerBook> books;
    private final OnListFragmentInteractionListener mListener;

    public BestSellerBooksRecyclerViewAdapter(List<BestSellerBook> items, OnListFragmentInteractionListener listener) {
        books = items;
        mListener = listener;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_best_seller_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BookViewHolder holder, int position) {
        holder.mItem = books.get(position);
        holder.mBookTitle.setText(books.get(position).title);
        holder.mBookAuthor.setText(books.get(position).author);
        holder.mRanking.setText(String.valueOf(books.get(position).rank));
        holder.mBookDescription.setText(books.get(position).description);
        holder.mBookDescription.setText(books.get(position).description);
        //holder.mAmazonLink.setLinksClickable(books.get(position).amazonUrl);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemClick(holder.mItem);
                }
            }
        });

        BestSellerBook bestSellerBook = books.get(position);
        Glide.with(holder.mView)
                .load(bestSellerBook.bookImageUrl)
                .centerInside()
                .into(holder.mBookImage);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mRanking;
        public final ImageView mBookImage;
        public final TextView mBookTitle;
        public final TextView mBookAuthor;
        public final TextView mBookDescription;
        public final Button mAmazonLink;
        public BestSellerBook mItem;

        public BookViewHolder(View view) {
            super(view);
            mView = view;
            mRanking = view.findViewById(R.id.ranking);
            mBookImage = view.findViewById(R.id.book_image);
            mBookTitle = view.findViewById(R.id.book_title);
            mBookAuthor = view.findViewById(R.id.book_author);
            mBookDescription = view.findViewById(R.id.book_description);
            mAmazonLink = view.findViewById(R.id.buy_button);
        }

        @Override
        public String toString() {
            return mBookTitle.toString() + " '" + mBookAuthor.getText() + "'";
        }
    }
}
