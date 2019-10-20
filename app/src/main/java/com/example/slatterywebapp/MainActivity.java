package com.example.slatterywebapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {



    class Weather extends AsyncTask<String, Void, String> { //First String means URL is in String, Void mean nothing, Third String means Return type will be String

        // String ... means multiple address can be send. It acts as array
        @Override
        protected String doInBackground(String... address) {

            try {
                URL url = new URL(address[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // Establish connection with address
                connection.connect();

                //retrieve data from url
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                //Retrieve data and return it as String
                int data = isr.read();
                String content = "";
                char ch;
                while (data != -1) {
                    ch = (char) data;
                    content = content + ch;
                    data = isr.read();
                }
                return content;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String content;
        Weather weather = new Weather();
        try {
            content = weather.execute("https://api.darksky.net/forecast/57c8ab4731ae54d48badbe0a62e924dd/41.0793,85.1394").get();
            //First we will check data is retrieve successfully or not
            Log.i("contentData", content);
            TextView mTextView = findViewById(R.id.results);
           mTextView.setText (content);



            //JSON
            String weatherData;
            JSONObject jsonObject = new JSONObject(content);
            weatherData = jsonObject.getString("weather");
            Log.i("weatherData", weatherData);
            //weather data is in Array
            JSONArray array = new JSONArray(weatherData);


            String main = "";
            String description = "";

            for (int i = 0; i < array.length(); i++) {
                JSONObject weatherPart = array.getJSONObject(i);
                main = weatherPart.getString("main");
                description = weatherPart.getString("description");

            }
            Log.i("main",main);
            Log.i("description",description);

         /*   String resultText = "Main : "+main+"\nDescription : "+description;

            results.setText(resultText);
*/
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

    }
}



