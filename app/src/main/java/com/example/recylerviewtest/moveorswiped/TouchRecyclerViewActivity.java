package com.example.recylerviewtest.moveorswiped;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recylerviewtest.R;

import java.util.ArrayList;
import java.util.List;

public class TouchRecyclerViewActivity extends AppCompatActivity {
    RecyclerView mRvTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_recyclerview);
        initData();
    }

    private void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("我是" + i);
        }
        initRecyclerView(list);
    }

    public void initRecyclerView(List<String> list) {
        mRvTouch = findViewById(R.id.rvTouch);
        MyGridAdapter adapter = new MyGridAdapter(list);
        ItemTouchCallBack callBack = new ItemTouchCallBack();
        callBack.setOnItemMoveOrSwipedListener(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBack);
        itemTouchHelper.attachToRecyclerView(mRvTouch);
//        mRvTouch.setLayoutManager(new GridLayoutManager(this,3));
        mRvTouch.setLayoutManager(new LinearLayoutManager(this));
        mRvTouch.setAdapter(adapter);

    }

}