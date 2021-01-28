package com.example.recylerviewtest;

import android.view.View;
import android.widget.TextView;

/**
 * 类描述
 *
 * @author HB.yangzuozhe
 * @date 2021-01-27
 */
public interface IAdapterLister {
    void onAdapterItemClick(TextView view, int position);
}
