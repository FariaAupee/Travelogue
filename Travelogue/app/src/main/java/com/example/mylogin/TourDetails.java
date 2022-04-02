package com.example.mylogin;
import android.os.AsyncTask;
import android.telecom.Call;

import com.example.mylogin.JsonActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TourDetails extends AsyncTask<Void,Void,Void>{
    String data="";
    String dataparsed="";
    String singleparsed="";
    String image;
    @Override
    protected Void doInBackground(Void...voids) {
        try {
            URL url=new URL("https://api.myjson.com/bins/z5qs0");
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            //InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

            String line="";
            while (line != null)
            {
                line=bufferedReader.readLine();
                data=data+line;
            }

            JSONArray JA = new JSONArray(data);
            for (int i=0;i<JA.length();i++)
            {
                JSONObject jo = (JSONObject) JA.get(i);
                String verify = (String) jo.get("destination");
                if(verify.equals(WhereToActivity.value)) {
                    singleparsed = jo.get("details") + "\n";
                    image=(String)jo.get("imgurl");
                    dataparsed = dataparsed + singleparsed;
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
           e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void avoid) {
        super.onPostExecute(avoid);
        JsonActivity.data.setText(this.dataparsed);
        Picasso.get().load(image).into(JsonActivity.image); //imageurl=string of image url
    }
}
