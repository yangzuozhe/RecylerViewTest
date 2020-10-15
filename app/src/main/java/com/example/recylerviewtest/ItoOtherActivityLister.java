package com.example.recylerviewtest;

import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView.Adapter中viewHolder的点击事件
 *
 * @author HB.yangzuozhe
 * @date 2020-10-15
 */
public interface ItoOtherActivityLister {

      void clickName(RecyclerView.ViewHolder viewHolder);

      void toOtherActivity(RecyclerView.ViewHolder viewHolder);
}
