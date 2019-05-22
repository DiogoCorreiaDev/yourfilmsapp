package com.example.developer.projectoandroiddc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<Serie> series;

        public RecycleAdapter(ArrayList<Serie> series){
            this.series = series;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_serie, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            final Serie serie = series.get(position);
            holder.getSerieNome().setText(serie.getName());

            //**********************************
            //Para as capas da lista principal :
            //**********************************
            Context capacontext = holder.getSerieCapa().getContext();
            if (serie.getImage() != null){
                String linkImagemOriginal = serie.getImage().getMedium();
                String[] linkSemHttp = linkImagemOriginal.split(":");
                new DownloadImageTask(holder.getSerieCapa(), capacontext).execute("https:"+linkSemHttp[1]);
            } else {
                holder.getSerieCapa().setImageResource(R.mipmap.imagenotfound_fore);
            }

            //*************************************************
            //Para alterar a cor do Status da lista principal
            //***********************************************
            holder.getSerieStatus().setText(serie.getStatus());
            if (serie.getStatus().equals("Ended")) {
                holder.getSerieStatus().setTextColor(Color.RED);
            } else {
                holder.getSerieStatus().setTextColor(Color.GREEN);
            }

            //*********************************
            //Para criar o rating com estrelas:
            //**********************************
            holder.getSerieRating().setTextColor(Color.YELLOW);
            if(serie.getRating().getAverage() != null) {
                Float rating = serie.getRating().getAverage();
                StringBuilder estrelas = new StringBuilder();
                for (int i =0;i<Math.round(rating);i++){
                    estrelas.append("\u2605");
                }
                String estrelasTotais = estrelas.toString();
                holder.getSerieRating().setText(estrelasTotais+" ("+Math.round(rating)+"/10)");
            } else {
                holder.getSerieRating().setText("Rating N/A");
            }

            //***************************************
            //Para devolver apenas o ano de estreia:
            //****************************************
            if (serie.getPremiered() != null){
                String dataDaSerie = serie.getPremiered();
                String[] anoDaSerie = dataDaSerie.split("-");
                holder.getSerieAno().setText("("+anoDaSerie[0]+")");
            } else {
                holder.getSerieAno().setText("(Date N/A)");
            }

            holder.getContainerLayout().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //*******************************************
                    //Enviar objecto Serie para SerieActivity :
                    //*******************************************

                    Bundle bundle = new Bundle();
                    Parcelable wrapped = Parcels.wrap(serie);
                    bundle.putParcelable("serieParcel", wrapped);

                    Context Cliquecontext = view.getContext();
                    Intent i = new Intent(Cliquecontext, SerieActivity.class);

                    i.putExtra("serieBundle", bundle);
                    i.putExtra("ratingComplete",holder.getSerieRating().getText().toString());
                    view.getContext().startActivity(i);

                }
            });
        }

        @Override
        public int getItemCount() {
            return series.size();
        }

    }

