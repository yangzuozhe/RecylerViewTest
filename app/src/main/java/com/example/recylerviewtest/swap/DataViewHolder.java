package com.example.recylerviewtest.swap;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recylerviewtest.R;

/**
 * 类描述
 *
 * @author HB.yangzuozhe
 * @date 2021-02-03
 */
class DataViewHolder extends RecyclerView.ViewHolder {
    TextView mTvFlex;

    public DataViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvFlex = itemView.findViewById(R.id.tvFlex);
    }
}
