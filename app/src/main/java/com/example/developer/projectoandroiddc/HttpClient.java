package com.example.developer.projectoandroiddc;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import org.greenrobot.eventbus.EventBus;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpClient extends AsyncTask<String, Void, String> {
    private ProgressDialog load;
    private Context context;
    private String url;
    private EVENTTYPE eventtype;


    public HttpClient(Context context, String url, EVENTTYPE eventtype)
    {
        this.context = context;
        this.url = url;
        this.eventtype = eventtype;

    }

    @Override
    protected void onPreExecute(){
    }

    @Override
    protected String doInBackground(String... params) {
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            //*********************************************************
            //Envia objecto resultadoHttp com Eventtype e String JSON :
            //********************************************************

            Response response = client.newCall(request).execute();
            ResultadoHttp resultadoHttp = new ResultadoHttp();
            resultadoHttp.setEventtype(eventtype);
            resultadoHttp.setSeriesString(response.body().string());
            EventBus.getDefault().post(resultadoHttp);


        }catch (IOException e){
            Log.d("httpclient erro",  e.toString());
        }


        return null;
    }

}