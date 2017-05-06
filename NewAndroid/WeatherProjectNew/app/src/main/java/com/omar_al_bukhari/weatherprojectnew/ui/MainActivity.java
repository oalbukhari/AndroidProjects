package com.omar_al_bukhari.weatherprojectnew.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.omar_al_bukhari.weatherprojectnew.Model.City;
import com.omar_al_bukhari.weatherprojectnew.Model.Example;
import com.omar_al_bukhari.weatherprojectnew.Model.JsonReader;
import com.omar_al_bukhari.weatherprojectnew.Model.Temperature;
import com.omar_al_bukhari.weatherprojectnew.R;
import com.omar_al_bukhari.weatherprojectnew.ui.Adapter.GeneralHomeAdapter;
import com.omar_al_bukhari.weatherprojectnew.ui.Fragment.DialogEdit;

import java.text.Format;
import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DialogEdit.IDialogGeneralListener {

   @BindView(R.id.toolbar_menu_edit)Toolbar toolbarmenu;
    Realm realm;
    JsonReader jsonReader;
    @BindView(R.id.recycler_city_temperature)RecyclerView recyclerViewTemperature;
    @BindView(R.id.recycler_socialMedia)RecyclerView recycleViewSocialMedia;
    private RecyclerView.Adapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbarmenu);
        jsonReader= new JsonReader();
        realm=Realm.getDefaultInstance();


        recyclerViewTemperature.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerGeneral = new LinearLayoutManager(this);
        recyclerViewTemperature.setLayoutManager(linearLayoutManagerGeneral);

        RealmResults<City> cityRealmResults= realm.where(City.class).findAll();
        adapter= new GeneralHomeAdapter(cityRealmResults);
        recyclerViewTemperature.setAdapter(adapter);




    }

    @Override
    public void insertCity(String name) {

        jsonReader.jsonreaderApi.getnameCity(name).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.d("TAG", "response: " + response.isSuccessful());
                Example example = response.body();
                Log.d("TAG", "example check!");
                realm.beginTransaction();
                Temperature cityTemperature = realm.createObject(Temperature.class,UUID.randomUUID().toString());
                  cityTemperature.setTemperatureC(example.current_observation.temp_c);
                  cityTemperature.setTemperatureF(example.current_observation.temp_f);
                  cityTemperature.setDate(getDateandTime());
                City checkcity = realm.where(City.class).equalTo("city",example.current_observation.display_location.city.toString()).findFirst();
                if(checkcity==null) {
                    City cityRealm = realm.createObject(City.class, example.current_observation.display_location.city.toString());
                    cityRealm.temperature.add(cityTemperature);
                    realm.commitTransaction();
                    updateGUI();
                }else {
                    checkcity.temperature.add(cityTemperature);
                    realm.commitTransaction();
                    updateGUI();

                }
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("TAG", "failure: ");
            }
        });


    }
    public void updateGUI(){
        adapter.notifyDataSetChanged();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_delete:
                return false;

            case R.id.id_action_edit:
                DialogEdit dialogEdit = new DialogEdit();
                dialogEdit.show(getFragmentManager(),"");

                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }
    public String getDateandTime(){
        long mDate = new Date().getTime();
        Format vFormatter = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String toSend=vFormatter.format(mDate);
        vFormatter = new java.text.SimpleDateFormat("h:mm a");
        return toSend +"at\n"+ vFormatter.format(mDate);

    }

}
