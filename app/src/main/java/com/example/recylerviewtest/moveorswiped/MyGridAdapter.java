package com.example.recylerviewtest.moveorswiped;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recylerviewtest.R;

import java.util.Collections;
import java.util.List;

/**
 * RecyclerView 的 Adapter
 *
 * @author HB.yangzuozhe
 * @date 2021-02-02
 */
public class MyGridAdapter extends RecyclerView.Adapter<MyGridAdapter.DataViewHolder> implements OnItemMoveOrSwipedListener {
    List<String> mStringList;
    RecyclerView mRecyclerView;

    public MyGridAdapter(List<String> stringList) {
        mStringList = stringList;
    }


    class DataViewHolder extends RecyclerView.ViewHolder {
        TextView mTvFlex;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvFlex = itemView.findViewById(R.id.tvFlex);
        }
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flex, null);
        final DataViewHolder viewHolder = new DataViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.mTvFlex.setText(mStringList.get(position));
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }


    @Override
    public void onMove(int fromPosition, int toPosition) {
        Log.i("drag", "onMove");
        Collections.swap(mStringList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onSwiped(int position) {
        Log.i("drag", "onSwiped");
        mStringList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }
}
