package com.omar_al_bukhari.realmandbinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerviewNormal)RecyclerView rec;
    private RecyclerView.Adapter adapter;

    private List<ListItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rec.setHasFixedSize(true);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        layoutmanager.setReverseLayout(false);
        rec.setLayoutManager(layoutmanager);
        list= new ArrayList<>();

        for(int i=0; i<1000;i++) {
            Random random = new Random(100);
            ListItem listitemObject = new ListItem("Person"+i,random.toString() );
            list.add(listitemObject);
        }
        adapter=new MyAdapter(list,this);
        rec.setAdapter(adapter);

    }
}
