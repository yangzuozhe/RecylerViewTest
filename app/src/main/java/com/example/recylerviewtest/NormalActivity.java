package com.example.recylerviewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.recylerviewtest.NormalActivity.*;
import static com.example.recylerviewtest.NormalActivity.NormalAdapter.*;

public class NormalActivity extends AppCompatActivity implements IAdapterLister {
    RecyclerView rvNormal;
    List<String> mList;
    public static final String DATA_ONE = "DATA_ONE";
    public static final String DATA_TWO = "DATA_TWO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        initData();
        initRecyclerView();

    }

    private void initData() {
        mList = new ArrayList<>();
        mList.add("notifyDataSetChanged : 刷新全部可见的item");
        mList.add("没有用到");
        mList.add("notifyItemChanged:局部刷新");
        mList.add("notifyItemInserted: 添加一条数据时可以调用");
        mList.add("notifyItemMoved: 列表fromPosition位置的数据移到toPosition位置时调用");
        mList.add("notifyItemRemoved: 列表position位置移除一条数据时调用");
        mList.add("notifyItemRangeChanged: 列表从positionStart位置到itemCount数量的列表项进行数据刷新");
        mList.add("notifyItemRangeInserted: 列表从positionStart位置到itemCount数量的列表项批量添加数据时调用");
        mList.add("notifyItemRangeRemoved:  列表从positionStart位置到itemCount数量的列表项批量删除数据时调用");
        mList.add("notifyItemChanged： 使用局部刷新的方法刷新textview");
        mList.add("notifyItemChanged： 使用局部刷新的方法刷新背景颜色backGround");
    }


    private void initRecyclerView() {
        rvNormal = findViewById(R.id.rvNormal);
        rvNormal.setLayoutManager(new LinearLayoutManager(this));
        rvNormal.setAdapter(new NormalAdapter(this, mList));
    }

    @Override
    public void onAdapterItemClick(TextView textView, int position) {
        NormalAdapter adapter;
        List<String> list;
        if (rvNormal.getAdapter() instanceof NormalAdapter) {
            adapter = (NormalAdapter) rvNormal.getAdapter();
            list = adapter.getList();
        } else {
            return;
        }

        if (position == 0) {
            //使用这个notifyDataSetChanged不仅可以用 set 替换当前的数据，还可以通过 add 添加数据， remove 删除数据
            //但是使用这个方法刷新的话，add remove set 是不会出现动画效果的
            //总之就是可以全局刷新数据
            list.add(0, "notifyDataSetChanged 全局刷新了 position = 0");
            adapter.notifyDataSetChanged();
        } else if (position == 1) {
            //刷新某一 个 item 的数据，有动画效果
            list.set(1, "notifyItemChanged : 改变了position = 1");
            adapter.notifyItemChanged(1);
        } else if (position == 2) {
            //nothing to do
        } else if (position == 3) {
            //添加某一个item到指定的位置，这里要注意比如在position = 3 的位置添加数据，那么 notifyItemInserted 也要 = 3
            //否则就会产生刷新的数据错误，比如notifyItemInserted(0)那么刷新的数据就为集合 position = 0 的数据，并且放在了 position = 0 的位置。
            list.add(3, "notifyItemInserted : 在position = 3 这里听添加了一条数据");
            adapter.notifyItemInserted(3);
        } else if (position == 4) {
            moveData(adapter, position, 0);
        } else if (position == 5) {
            //这里移除 position 为 5 的数据，这里的 notifyItemRemoved 和 集合的 remove 缺一不可
            list.remove(5);
            adapter.notifyItemRemoved(5);
        } else if (position == 6) {
            //批量改变item的值
            list.set(5, "刷新5");
            list.set(6, "刷新6");
            adapter.notifyItemRangeChanged(5, position);
        } else if (position == 7) {
            //批量插入item的数据
            list.add(0, "批量添加了 0");
            list.add(1, "批量添加了 1");
            list.add(2, "批量添加了 2");
            adapter.notifyItemRangeInserted(0, 2);
        } else if (position == 8) {
            //批量删除item的数据
            list.remove(0);
            list.remove(1);
            list.remove(2);
            adapter.notifyItemRangeRemoved(0, 2);
        } else if (position == 9) {
            list.set(position, "局部控件的刷新1");
            adapter.notifyItemChanged(position, DATA_ONE);
        } else if (position == 10) {
            list.set(position,"局部控件的刷新2");
            adapter.notifyItemChanged(position,DATA_TWO);
        }

    }

    private void moveData(NormalAdapter adapter, int position, int toPosition) {
        //可以直接调用这个方法将 position 为 4 的数据跳转到 position 为 0 的位置，伴随有动画
        adapter.notifyItemMoved(position, toPosition);
        //为了数据的真实性，我们可以在集合中转换数据
        mList.add(toPosition, "notifyItemMoved : 将position = 4 的数据跳到 position = 0");
        mList.remove(position + 1);
    }


    class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.NormalViewHolder> {
        private IAdapterLister mLister;
        private List<String> mList;

        class NormalViewHolder extends RecyclerView.ViewHolder {
            TextView mTvFull;

            public NormalViewHolder(@NonNull View itemView) {
                super(itemView);
                mTvFull = itemView.findViewById(R.id.tvFull);

            }
        }

        public NormalAdapter(IAdapterLister lister, List<String> list) {
            mList = list;
            mLister = lister;
        }

        public List<String> getList() {
            return mList;
        }

        public void setList(List<String> list) {
            mList = list;
        }

        @NonNull
        @Override
        public NormalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_full_img, parent, false);
            return new NormalViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final NormalViewHolder holder, final int position) {
            holder.mTvFull.setText(mList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLister.onAdapterItemClick(holder.mTvFull, position);
                }
            });
        }

        @Override
        public void onBindViewHolder(@NonNull NormalViewHolder holder, int position, @NonNull List<Object> payloads) {
            if (payloads.isEmpty()) {
                onBindViewHolder(holder, position);
            } else {
                holder.mTvFull.setText(mList.get(position));
            }

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }


}