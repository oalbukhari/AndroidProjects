package it.damianogiusti.listviewwithwidgets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> dataset;
    private ItemAdapter itemAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind();
        generateDataset(1000);
        bindAdapter();
    }

    private void bind() {
        listView = (ListView) findViewById(R.id.listView);
    }

    private void generateDataset(int count) {
        dataset = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            dataset.add(new Item("Item #" + (i + 1)));
        }
    }

    private void bindAdapter() {
        itemAdapter = new ItemAdapter(getApplicationContext(), dataset);
        listView.setAdapter(itemAdapter);
    }
}
