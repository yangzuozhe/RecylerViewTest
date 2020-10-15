package com.example.recylerviewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NormalActivity extends AppCompatActivity {
    RecyclerView rvNormal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        rvNormal=findViewById(R.id.rvNormal);
        rvNormal.setLayoutManager(new LinearLayoutManager(this));
        rvNormal.setAdapter(new NormalAdapter());

    }

    class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.NormalViewHolder>{


        class NormalViewHolder extends RecyclerView.ViewHolder{

            public NormalViewHolder(@NonNull View itemView) {
                super(itemView);

            }
        }

        @NonNull
        @Override
        public NormalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_full_img,parent,false);
            NormalViewHolder viewHolder=new NormalViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull NormalViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 1;
        }






    }


}