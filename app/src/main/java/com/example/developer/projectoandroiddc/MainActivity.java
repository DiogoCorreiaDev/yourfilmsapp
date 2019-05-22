package com.example.developer.projectoandroiddc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //**********************
        //PESQUISA :
        //**********************

        final SearchView pesquisa = (SearchView) findViewById(R.id.searchView);

        pesquisa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String pesquisaFinal = "https://api.tvmaze.com/search/shows?q="+query;

                new HttpClient(MainActivity.this,pesquisaFinal,EVENTTYPE.SEARCH_SERIE).execute();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String pesquisaFinal2 = "https://api.tvmaze.com/search/shows?q="+newText;

                new HttpClient(pesquisa.getContext(),pesquisaFinal2,EVENTTYPE.SEARCH_SERIE).execute();
                return false;
            }
        });



        //***************************
        //HTTP CLIENT - LISTA GERAL:
        //***************************

            new HttpClient(this, "https://api.tvmaze.com/shows?page=0", EVENTTYPE.GET_ALL_SERIES).execute();

        //***************************
        //IR PARA ACTIVITY FAVORITOS:
        //***************************

        final LinearLayout layoutFavoritos = (LinearLayout) findViewById(R.id.layout_favoritos);

        layoutFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), FavoritosActivity.class);
                view.getContext().startActivity(i);
            }
        });
    }


    //******************************************************
    //Recebe objecto responseHTTP e actualiza recycle view :
    //******************************************************
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void ActualizaLista(ResultadoHttp resultadoHttp) {

        String eventTypeName;
        eventTypeName = resultadoHttp.getEventtype().name();
        String seriesString = resultadoHttp.getSeriesString();

        switch (eventTypeName) {

            //**********************
            //Recebe pesquisa:
            //**********************
            case "SEARCH_SERIE":

                String pesquisaOriginal = seriesString;
                ArrayList<Serie> listaSeriesDeserializada = new ArrayList<Serie>(){};


                JsonArray jsonArray = new Gson().fromJson(pesquisaOriginal, JsonArray.class);

                Gson gson = new Gson();
                for (JsonElement element : jsonArray){
                    Serie tmpSerie = gson.fromJson(element.getAsJsonObject().get("show").toString(),Serie.class);
                    listaSeriesDeserializada.add(tmpSerie);
                }

                RecyclerView recyclerView2 = findViewById(R.id.recycler);
                RecycleAdapter adapter2 = new RecycleAdapter(listaSeriesDeserializada);

                recyclerView2.setAdapter(adapter2);

                RecyclerView.LayoutManager listLayoutManager2 = new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false);

                RecyclerView.LayoutManager gridLayoutManager2 =  new GridLayoutManager(this, 1);

                recyclerView2.setLayoutManager(listLayoutManager2);

                break;

            //**********************
            //Recebe lista geral:
            //**********************
            case "GET_ALL_SERIES":

                Gson gson2 = new Gson();
                Type listType = new TypeToken<ArrayList<Serie>>(){}.getType();
                ArrayList<Serie> listaSeries = gson2.fromJson(seriesString, listType);
                RecyclerView recyclerView = findViewById(R.id.recycler);
                RecycleAdapter adapter = new RecycleAdapter(listaSeries);

                recyclerView.setAdapter(adapter);

                RecyclerView.LayoutManager listLayoutManager = new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false);

                RecyclerView.LayoutManager gridLayoutManager =  new GridLayoutManager(this, 1);

                recyclerView.setLayoutManager(listLayoutManager);

                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        LinearLayoutManager.VERTICAL);
                recyclerView.addItemDecoration(dividerItemDecoration);
                Log.d("subscribe", "FIM SUBSCRIBE");
                break;

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
