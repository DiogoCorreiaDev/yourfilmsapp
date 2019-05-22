package com.example.developer.projectoandroiddc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        List<Favorite> listaFavoritos = Favorite.listAll(Favorite.class);


        RecyclerView recyclerView = findViewById(R.id.recycler);
        RecycleFavoriteAdapter adapter = new RecycleFavoriteAdapter(listaFavoritos);

        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager listLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        RecyclerView.LayoutManager gridLayoutManager2 =  new GridLayoutManager(this, 1);

        recyclerView.setLayoutManager(listLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


    }

}
