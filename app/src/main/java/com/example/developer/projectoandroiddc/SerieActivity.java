package com.example.developer.projectoandroiddc;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import org.parceler.Parcels;
import java.util.List;

public class SerieActivity extends AppCompatActivity {

    private Button favoritesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie);

        //************************************
        //Views:
        //**************************************
        TextView seriesTitle = (TextView) findViewById(R.id.seriesTitle);
        TextView seriesRating = (TextView) findViewById(R.id.seriesRating);
        final TextView seriesSummary = (TextView) findViewById(R.id.seriesSummary);
        final TextView seriesPremiered = (TextView) findViewById(R.id.seriesPremiered);
        final TextView seriesStatus = (TextView) findViewById(R.id.seriesStatus);
        final TextView seriesType = (TextView) findViewById(R.id.seriesType);
        final TextView seriesOfficialsite = (TextView) findViewById(R.id.seriesOfficialsite);
        TextView seriesGenres = (TextView) findViewById(R.id.seriesGenres);
        ImageView seriesImage = (ImageView) findViewById(R.id.seriesImage);
        Button addFavorites = (Button) findViewById(R.id.addFavorites);

        //************************************
        //Recebe objecto:
        //**************************************
        Bundle extras = getIntent().getExtras();
        Bundle serieBundle = extras.getBundle("serieBundle");
        Parcelable serieParcel = serieBundle.getParcelable("serieParcel");
        final Serie serieRecebida = Parcels.unwrap(serieParcel);
        final String id = String.valueOf(serieRecebida.getId());
        final String url = serieRecebida.getUrl();
        final String name = serieRecebida.getName();
        final String type = serieRecebida.getType();
        final String[] genres = serieRecebida.getGenres();
        final String status = serieRecebida.getStatus();
        final String summary = serieRecebida.getSummary();
        final String premiered = serieRecebida.getPremiered();
        final String officialsite = serieRecebida.getOfficialsite();
        final Float rating = serieRecebida.getRating().getAverage();
        final Serie.Image image = serieRecebida.getImage();

        //************************************
        //                INSERE DADOS:
        //**************************************



        //************************************
        //Título:
        //**************************************
        seriesTitle.setText(name);

        //************************************
        //Género:
        //**************************************
        if (genres.length != 0) {
            String listaGenres = "<b>Genres: </b>";
            for (int ig = 0; ig < genres.length; ig++) {
                listaGenres += genres[ig] + ", ";
            }
            //retirar a ultima virgula:
            listaGenres = listaGenres.substring(0, listaGenres.length() - 2);
            seriesGenres.setText(Html.fromHtml(listaGenres + "."));
        } else {
            String listaGenres = "<b>Genres: </b> N/A.";
            seriesGenres.setText(Html.fromHtml(listaGenres));
        }

        //************************************
        //Rating:
        //**************************************
        final String ratingComplete = extras.getString("ratingComplete");
        seriesRating.setText(ratingComplete);

        //************************************
        //Sumário:
        //**************************************
        if (summary != null) {
            String summaryComplete = "<b>Summary: </b>" + summary.replaceAll("\\<.*?\\>", "");
            ;
            seriesSummary.setText(Html.fromHtml(summaryComplete));
        } else {
            String summaryComplete = "<b>Summary: </b> N/A.";
            seriesSummary.setText(Html.fromHtml(summaryComplete));
        }

        //************************************
        //Data de estreia:
        //**************************************
        if (premiered != null) {
            String premiereComplete = "<b>Premiere: </b>" + premiered;
            seriesPremiered.setText(Html.fromHtml(premiereComplete));
        } else {
            String premiereComplete = "<b>Premiere: </b> N/A.";
            seriesPremiered.setText(Html.fromHtml(premiereComplete));
        }


        //************************************
        //Website oficial:
        //**************************************
        if (officialsite != null) {
            String officialsiteComplete = "<b>Website: </b>" + officialsite;
            seriesOfficialsite.setText(Html.fromHtml(officialsiteComplete));
        } else {
            String officialsiteComplete = "<b>Website: </b> N/A.";
            seriesOfficialsite.setText(Html.fromHtml(officialsiteComplete));
        }


        //************************************
        //Imagem capa:
        //**************************************
        if (image != null) {
            final String image_medium = serieRecebida.getImage().getMedium();
            final String image_original = serieRecebida.getImage().getOriginal();
            // SE NAO TIVER HTTPS ELE REBENTA
            String linkImagemOriginal = image_original;
            String[] linkSemHttp = linkImagemOriginal.split(":");
            new DownloadImageTask(seriesImage, this).execute("https:" + linkSemHttp[1]);
        } else {
            seriesImage.setImageResource(R.mipmap.imagenotfound_fore);
        }

        //************************************
        //Status:
        //**************************************
        String statusComplete = "<b>Status: </b>" + status + " | ";
        seriesStatus.setText(Html.fromHtml(statusComplete));

        //************************************
        //Tipo:
        //**************************************
        String typeComplete = "<b>Type: </b>" + type + " | ";
        seriesType.setText(Html.fromHtml(typeComplete));




        //************************************
        //Ao adicionar favoritos:
        //**************************************
        addFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //************************************
                //Verifica permissoes:
                //**************************************
                if (ContextCompat.checkSelfPermission(SerieActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SerieActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                } else {

                    //*************************************************
                    //Verifica se serie ja foi adicionada aos favoritos
                    //*************************************************
                    List<Favorite> todosFavoritos = Favorite.listAll(Favorite.class);
                    Integer estaNosFavoritos = 0;
                    for (int i = 0; i < todosFavoritos.size(); i++) {
                        if (todosFavoritos.get(i).getTitle().equals(name)){
                          estaNosFavoritos++;
                        }
                    }
                    if (estaNosFavoritos != 0) {
                        Toast.makeText(SerieActivity.this, "You already have "+name+" favorited.", Toast.LENGTH_LONG).show();

                    } else {
                        //************************************
                        //Verifica se serie tem imagem ou nao:
                        //**************************************
                        String imagemMedium;
                        String imagemOriginal;
                        if (serieRecebida.getImage() == null) {
                            imagemMedium = "https://openclipart.org/image/2400px/svg_to_png/211479/Simple-Image-Not-Found-Icon.png";
                            imagemOriginal = "https://openclipart.org/image/2400px/svg_to_png/211479/Simple-Image-Not-Found-Icon.png";
                        } else {
                            imagemMedium = serieRecebida.getImage().getMedium();
                            imagemOriginal = serieRecebida.getImage().getOriginal();
                        }

                        //************************************
                        //Cria novo favorito
                        //**************************************
                        Favorite favoritoNovo = new Favorite(name,
                                ratingComplete,
                                seriesSummary.getText().toString(),
                                seriesOfficialsite.getText().toString(),
                                seriesType.getText().toString(),
                                seriesStatus.getText().toString(),
                                seriesPremiered.getText().toString(),
                                imagemMedium,
                                imagemOriginal
                        );

                        //************************************
                        //Guarda favorito criado:
                        //**************************************
                        favoritoNovo.save();


                        Toast.makeText(SerieActivity.this, "Favorited! You now have " + Favorite.listAll(Favorite.class).size() + " favorite series!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(SerieActivity.this, "You can now add to favorites!", Toast.LENGTH_LONG).show();
        }
    }
}
