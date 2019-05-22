package com.example.developer.projectoandroiddc;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import java.util.List;

public class RecycleFavoriteAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private Button deleteFavorite;

    private List<Favorite> favorites;

    public RecycleFavoriteAdapter(List<Favorite> favorites){
        this.favorites = favorites;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_favorite, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Favorite favorite = favorites.get(position);
        holder.getSerieNome().setText(favorite.getTitle());

        //************************************
        //Para as capas da lista dos favoritos:
        //**************************************
        Context capacontext = holder.getSerieCapa().getContext();
        if (favorite.getImageMedium() != null){
            String linkImagemOriginal = favorite.getImageMedium();
            String[] linkSemHttp = linkImagemOriginal.split(":");
            new DownloadImageTask(holder.getSerieCapa(), capacontext).execute("https:"+linkSemHttp[1]);
        } else {
            holder.getSerieCapa().setImageResource(R.mipmap.imagenotfound_fore);
        }


        //************************************
        //Para a cor do status dos favoritos:
        //**************************************
        holder.getSerieStatus().setText(favorite.getStatus());
        if (favorite.getStatus().equals("Status: Ended | ")) {
            holder.getSerieStatus().setTextColor(Color.RED);
        } else {
            holder.getSerieStatus().setTextColor(Color.GREEN);
        }
        //************************************
        //Para colocar rating dos favoritos:
        //**************************************
        holder.getSerieRating().setTextColor(Color.YELLOW);
        holder.getSerieRating().setText(favorite.getRating());


        //*****************************************
        //Para devolver apenas o ano dos favoritos:
        //******************************************
        if (favorite.getPremiere() != null){
            String dataDaSerie = favorite.getPremiere();
            String[] dataCompletaDaSerie = dataDaSerie.split(" ");
            String[] anoDaSerie = dataCompletaDaSerie[1].split("-");
            holder.getSerieAno().setText("("+anoDaSerie[0]+")");
        } else {
            holder.getSerieAno().setText("(Date N/A)");
        }

        holder.getDeleteFavorite().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //************************************
                //Apaga favorito clickado:
                //**************************************
                favorite.delete();

                //************************************
                //Simula refresh da página:
                //**************************************
                Intent i = new Intent(view.getContext(), FavoritosActivity.class);

                //************************************************
                //Ao clicar no Back button ele nao desfaz o delete:
                //************************************************
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(i);
                Toast.makeText(view.getContext(), "Delete Successful!", Toast.LENGTH_SHORT).show();

            }
        });
        holder.getContainerLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //********************************************************************
                //Passar as caracteristicas necessarias para o FavoriteDetailActivity:
                //*********************************************************************

                Context Cliquecontext = view.getContext();
                Intent i = new Intent(Cliquecontext, FavoriteDetailActivity.class);

                i.putExtra("serieTitle", favorite.getTitle());
                i.putExtra("serieSummary", favorite.getSummary());
                i.putExtra("serieWebsite", favorite.getWebsite());
                i.putExtra("serieType", favorite.getType());
                i.putExtra("serieStatus", favorite.getStatus());
                i.putExtra("seriePremiere", favorite.getPremiere());
                i.putExtra("serieImageOriginal", favorite.getImageOriginal());
                i.putExtra("ratingComplete",holder.getSerieRating().getText().toString());
                view.getContext().startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

}