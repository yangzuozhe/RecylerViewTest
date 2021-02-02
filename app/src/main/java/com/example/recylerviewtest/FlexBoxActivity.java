package com.example.recylerviewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.AlignSelf;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class FlexBoxActivity extends AppCompatActivity {
    RecyclerView mRlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexbox);
        mRlView = findViewById(R.id.rlView);
        List<String> list = new ArrayList<>();
        list.add("哈哈哈");
        list.add("撒大苏打");
        list.add("阿萨德瓦达");
        list.add("245");
        list.add("啊");
        list.add("啊圣诞袜");
        list.add("萨达萨达萨达萨达萨达");
        MyAdapter adapter = new MyAdapter(list);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        mRlView.setLayoutManager(layoutManager);
        mRlView.setAdapter(adapter);

    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<String> mStringList;

        class ItemViewHolder extends RecyclerView.ViewHolder {
            TextView mTitle;
            public ItemViewHolder(View view) {
                super(view);
                mTitle = (TextView) view.findViewById(R.id.tvFlex);
            }


        }

        public MyAdapter(List<String> stringList) {
            mStringList = stringList;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flex, null);
            ItemViewHolder viewHolder = new ItemViewHolder(view);
            return viewHolder;
        }

        @SuppressLint("WrongConstant")
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ItemViewHolder){
                ((ItemViewHolder) holder).mTitle.setText(mStringList.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return mStringList.size();
        }

    }
}