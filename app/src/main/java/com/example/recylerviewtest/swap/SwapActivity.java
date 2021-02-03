package com.example.recylerviewtest.swap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recylerviewtest.R;

import java.util.ArrayList;
import java.util.List;

public class SwapActivity extends AppCompatActivity {
    RecyclerView rvDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
        rvDemo = findViewById(R.id.rvDemo);
        List<String> list = new ArrayList<>();
        list.add("啊哈哈");
        list.add("啊哈哈");
        list.add("啊哈哈");
        list.add("啊哈哈");
        MyAdapter adapter = new MyAdapter(list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvDemo.setLayoutManager(manager);
        rvDemo.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        List<String> list = new ArrayList<>();
        list.add("欸嘿嘿");
        list.add("欸嘿嘿");
        list.add("欸嘿嘿");
        list.add("欸嘿嘿");
        list.add("欸嘿嘿");
        rvDemo.swapAdapter(new SwapAdapter(list),false);
    }
}