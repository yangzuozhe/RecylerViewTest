package com.example.recylerviewtest.swap;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recylerviewtest.R;

import java.util.List;

/**
 * 类描述
 *
 * @author HB.yangzuozhe
 * @date 2021-02-03
 */
public class SwapAdapter extends RecyclerView.Adapter<DataViewHolder> {
    List<String> mStringList;


    public SwapAdapter(List<String> stringList) {
        mStringList = stringList;
    }
    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flex, null);
        DataViewHolder viewHolder = new DataViewHolder(view);
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
    public void onViewDetachedFromWindow(@NonNull DataViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.i("Demo1", "onViewDetachedFromWindow: ");
    }

    @Override
    public void onViewAttachedToWindow(@NonNull DataViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        Log.i("Demo1", "onViewAttachedToWindow: ");
    }
}
