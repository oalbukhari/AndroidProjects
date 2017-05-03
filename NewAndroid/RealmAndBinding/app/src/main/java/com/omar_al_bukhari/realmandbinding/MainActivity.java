package com.omar_al_bukhari.realmandbinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerviewVertical)RecyclerView recV;
    @BindView(R.id.presButton)Button button;
    @BindView(R.id.editName)EditText editName;
    @BindView(R.id.recyclerViewHorizontal)RecyclerView recH;

    private RecyclerView.Adapter adapter;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        realm=Realm.getDefaultInstance();
        recH.setHasFixedSize(true);
        LinearLayoutManager layoutmanagerHorizontal = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        layoutmanagerHorizontal.setReverseLayout(false);
        recH.setLayoutManager(layoutmanagerHorizontal);

        recV.setHasFixedSize(true);
        LinearLayoutManager layoutmanagerVertical = new LinearLayoutManager(this);
        layoutmanagerVertical.setReverseLayout(false);
        recV.setLayoutManager(layoutmanagerVertical);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                Random random = new Random(100);
                ListItem listitemobject = new ListItem(editName.getText().toString(),random.toString());
                realm.copyToRealm(listitemobject);
                realm.commitTransaction();
                updateGUI();
            }
        });

        RealmResults<ListItem> listitem = realm.where(ListItem.class).findAll();
        adapter=new MyAdapter(listitem,this);
        recH.setAdapter(adapter);
        recV.setAdapter(adapter);

    }
    public void updateGUI(){
        adapter.notifyDataSetChanged();
    }
}
