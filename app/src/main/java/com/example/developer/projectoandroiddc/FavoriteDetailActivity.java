package com.example.developer.projectoandroiddc;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

public class FavoriteDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorites);

        //**********************
        //Views :
        //**********************
        TextView seriesTitle = (TextView) findViewById(R.id.seriesTitle);
        TextView seriesRating = (TextView) findViewById(R.id.seriesRating);
        final TextView seriesSummary = (TextView) findViewById(R.id.seriesSummary);
        final TextView seriesPremiered = (TextView) findViewById(R.id.seriesPremiered);
        final TextView seriesStatus = (TextView) findViewById(R.id.seriesStatus);
        final TextView seriesType = (TextView) findViewById(R.id.seriesType);
        final TextView seriesOfficialsite = (TextView) findViewById(R.id.seriesOfficialsite);
        ImageView seriesImage = (ImageView) findViewById(R.id.seriesImage);
        Button addFavorites = (Button) findViewById(R.id.addFavorites);

        //**********************
        //Caracteristicas do intent :
        //**********************

        Bundle extras = getIntent().getExtras();
        final String name = extras.getString("serieTitle");
        final String type = extras.getString("serieType");
        final String status = extras.getString("serieStatus");
        final String summary = extras.getString("serieSummary");
        final String premiered = extras.getString("seriePremiere");
        final String officialsite = extras.getString("serieWebsite");
        final String image_original = extras.getString("serieImageOriginal");
        final String ratingComplete = extras.getString("ratingComplete");

        //**********************************
        //Coloca caracteristicas nas views :
        //**********************************

        seriesTitle.setText(name);
        seriesRating.setText(ratingComplete);
        seriesSummary.setText(Html.fromHtml(summary));
        seriesPremiered.setText(Html.fromHtml(premiered));
        seriesOfficialsite.setText(Html.fromHtml(officialsite));
        seriesStatus.setText(Html.fromHtml(status));
        seriesType.setText(Html.fromHtml(type));



        //**********************
        //Se image for null:
        //**********************

        if (image_original != null) {
            // SE NAO TIVER HTTPS ELE REBENTA
            String linkImagemOriginal = image_original;
            String[] linkSemHttp = linkImagemOriginal.split(":");
            new DownloadImageTask(seriesImage, this).execute("https:" + linkSemHttp[1]);
        } else {
            seriesImage.setImageResource(R.mipmap.imagenotfound_fore);
        }
    }

}
