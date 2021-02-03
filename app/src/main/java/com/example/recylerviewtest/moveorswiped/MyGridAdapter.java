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

    /**
     * 拖动 item 时会做的事情
     *
     * @param fromPosition 开始的viewHolder 的position
     * @param toPosition   结束的 viewHolder 的 position
     */
    @Override
    public void onMove(int fromPosition, int toPosition) {
        Log.i("drag", "onMove");
        Collections.swap(mStringList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * 滑动 item 时会做的事情
     *
     * @param position 当前滑动的 viewHolder 的 position
     */
    @Override
    public void onSwiped(int position) {
        Log.i("drag", "onSwiped");
        mStringList.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 在调用 setAdapter 的时候调用这个方法
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Log.i("Demo", "onAttachedToRecyclerView");
        mRecyclerView = recyclerView;
    }


    /**
     * 当 adpter 改变，旧的 adapter 回调 onDetachedFromRecyclerView，新的 adapter 回调 onAttachedToRecyclerView。
     *
     * @param recyclerView
     */
    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        Log.i("Demo", "onDetachedFromRecyclerView");
        mRecyclerView = null;
    }
}
