package com.example.developer.projectoandroiddc;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout containerLayout;
    private ImageView serieCapa;
    private TextView serieNome;
    private TextView serieAno;
    private TextView serieStatus;
    private TextView serieRating;
    private Button deleteFavorite;


    public MyViewHolder(View itemView) {
        super(itemView);
        serieNome = itemView.findViewById(R.id.serieNome);
        serieAno = itemView.findViewById(R.id.serieAno);
        serieCapa = itemView.findViewById(R.id.serieCapa);
        serieStatus = itemView.findViewById(R.id.serieStatus);
        serieRating = itemView.findViewById(R.id.serieRating);
        containerLayout = itemView.findViewById(R.id.rlSerieLine);
        //**************************************
        //Apenas para usar na Favoritos Activity
        //**************************************
        deleteFavorite = itemView.findViewById(R.id.deleteFavorite);
    }

    public Button getDeleteFavorite() {
        return deleteFavorite;
    }

    public void setDeleteFavorite(Button deleteFavorite) {
        this.deleteFavorite = deleteFavorite;
    }

    public TextView getSerieNome() {
        return serieNome;
    }

    public void setSerieNome(TextView serieNome) {
        this.serieNome = serieNome;
    }

    public TextView getSerieAno() {
        return serieAno;
    }

    public void setSerieAno(TextView serieAno) {
        this.serieAno = serieAno;
    }

    public LinearLayout getContainerLayout() {
        return containerLayout;
    }

    public void setContainerLayout(LinearLayout containerLayout) {
        this.containerLayout = containerLayout;
    }

    public ImageView getSerieCapa() {
        return serieCapa;
    }

    public void setSerieCapa(ImageView serieCapa) {
        this.serieCapa = serieCapa;
    }

    public TextView getSerieStatus() {
        return serieStatus;
    }

    public void setSerieStatus(TextView serieStatus) {
        this.serieStatus = serieStatus;
    }

    public TextView getSerieRating() {
        return serieRating;
    }

    public void setSerieRating(TextView serieRating) {
        this.serieRating = serieRating;
    }
}
