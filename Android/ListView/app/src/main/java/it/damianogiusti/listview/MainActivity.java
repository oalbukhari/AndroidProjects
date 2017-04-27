package it.damianogiusti.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import it.damianogiusti.listview.model.Item;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter customAdapter;
    private ArrayList<Item> database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind();
        initDatabase();
        initAdapter();
    }

    private void bind() {
        listView = (ListView) findViewById(R.id.listView);
    }

    private void initDatabase() {
        database = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Item item = new Item(i);
            database.add(item);
        }
    }

    private void initAdapter() {
        customAdapter = new CustomAdapter(getApplicationContext(), database);
        listView.setAdapter(customAdapter);
    }
}
