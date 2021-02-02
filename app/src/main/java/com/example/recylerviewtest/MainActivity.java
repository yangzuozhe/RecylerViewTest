package com.example.recylerviewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvYang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvYang = findViewById(R.id.rvYang);
        initAdapter();
    }

    public void initAdapter() {
        //添加数据
        List<MyBean> listBeans = new ArrayList<>();
        listBeans.add(new MyBean(1, "yang"));
        listBeans.add(new MyBean(2, "wang"));
        listBeans.add(new MyBean(0, "nihao"));
        listBeans.add(new MyBean(1, "yang"));
        listBeans.add(new MyBean(2, "wang"));
        listBeans.add(new MyBean(0, "nihao"));
        listBeans.add(new MyBean(1, "yang"));
        listBeans.add(new MyBean(2, "wang"));
        listBeans.add(new MyBean(0, "nihao"));
        listBeans.add(new MyBean(1, "yang"));
        listBeans.add(new MyBean(2, "wang"));
        listBeans.add(new MyBean(0, "nihao"));
        listBeans.add(new MyBean(1, "yang"));
        listBeans.add(new MyBean(2, "wang"));
        listBeans.add(new MyBean(0, "nihao"));
        listBeans.add(new MyBean(1, "yang"));
        listBeans.add(new MyBean(2, "wang"));
        listBeans.add(new MyBean(0, "nihao"));
        listBeans.add(new MyBean(1, "yang"));
        listBeans.add(new MyBean(2, "wang"));
        listBeans.add(new MyBean(0, "nihao"));
        listBeans.add(new MyBean(1, "yang"));
        listBeans.add(new MyBean(2, "wang"));
        listBeans.add(new MyBean(0, "nihao"));
        MyAdapter adapter = new MyAdapter(listBeans);

        adapter.setLister(new ItoOtherActivityLister() {
            /**
             * viewHolder点击事件的回调，可以获取到某个viewHolder中某个控件，比如这里的TextView
             * 这里我在clickName（）方法里的参数里面写的参数为RecyclerView.ViewHolder，这里只是为了表示可以这样写表示，其实可以直接写TextView
             *
             * @param viewHolder
             */
            @Override
            public void clickName(final RecyclerView.ViewHolder viewHolder) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    TextView myTextView;

                    @Override
                    public void onClick(View v) {
                        //这里判断当前你传进来的值是哪个viewHolder
                        if (viewHolder instanceof MyAdapter.RightImageHolder) {
                            myTextView = ((MyAdapter.RightImageHolder) viewHolder).mTvRight;
                        } else if (viewHolder instanceof MyAdapter.ThreeImageHolder) {
                            myTextView = ((MyAdapter.ThreeImageHolder) viewHolder).mTvThree;
                        }
                        myTextView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String name = myTextView.getText().toString();
                                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }

            /**
             * viewHolder点击事件的回调，可以跳转到另一个页面
             */
            @Override
            public void toOtherActivity(RecyclerView.ViewHolder viewHolder) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), NormalActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
        FlexboxLayoutManager manager = new FlexboxLayoutManager(this);
//        manager.setOrientation(RecyclerView.VERTICAL);
        rvYang.setLayoutManager(manager);
        rvYang.setAdapter(adapter);
    }


    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        /**
         * 定义三种常量  表示三种条目类型
         */
        public static final int TYPE_PULL_IMAGE = 0;
        public static final int TYPE_RIGHT_IMAGE = 1;
        public static final int TYPE_THREE_IMAGE = 2;
        /**
         * 数据源
         */
        List<MyBean> mList;
        ItoOtherActivityLister mLister;

        public MyAdapter(List<MyBean> list) {
            mList = list;
        }

        public void setLister(ItoOtherActivityLister lister) {
            mLister = lister;
        }

        /**
         * 这里我们通过MyBean里的id作为区分
         *
         * @param position
         * @return
         */
        @Override
        public int getItemViewType(int position) {
            int id = mList.get(position).id;
            if (id == 0) {
                return TYPE_PULL_IMAGE;
            } else if (id == 1) {
                return TYPE_RIGHT_IMAGE;
            } else {
                return TYPE_THREE_IMAGE;
            }
        }


        private class FullImageHolder extends RecyclerView.ViewHolder {
            TextView mTvFull;

            public FullImageHolder(@NonNull View itemView) {
                super(itemView);
                mTvFull = itemView.findViewById(R.id.tvFull);
            }


        }

        private class RightImageHolder extends RecyclerView.ViewHolder {
            TextView mTvRight;

            public RightImageHolder(@NonNull View itemView) {
                super(itemView);
                mTvRight = itemView.findViewById(R.id.tvRight);
            }
        }

        private class ThreeImageHolder extends RecyclerView.ViewHolder {
            TextView mTvThree;

            public ThreeImageHolder(@NonNull View itemView) {
                super(itemView);
                mTvThree = itemView.findViewById(R.id.tvThree);
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            if (viewType == TYPE_PULL_IMAGE) {
                view = View.inflate(parent.getContext(), R.layout.item_full_img, null);
                return new FullImageHolder(view);
            } else if (viewType == TYPE_RIGHT_IMAGE) {
                view = View.inflate(parent.getContext(), R.layout.item_right_img, null);
                return new RightImageHolder(view);
            } else {
                view = View.inflate(parent.getContext(), R.layout.item_three_img, null);
                return new ThreeImageHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

            if (holder instanceof FullImageHolder) {
                ((FullImageHolder) holder).mTvFull.setText(mList.get(position).name);
                mLister.toOtherActivity(holder);
            }
            if (holder instanceof RightImageHolder) {
                ((RightImageHolder) holder).mTvRight.setText(mList.get(position).name);
                mLister.clickName(holder);
            }
            if (holder instanceof ThreeImageHolder) {
                ((ThreeImageHolder) holder).mTvThree.setText(mList.get(position).name);
                mLister.clickName(holder);
            }
        }

        @Override
        public int getItemCount() {
            //这里是数据的大小
            return mList.size();
        }
    }

}