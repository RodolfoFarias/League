package com.example.rodolfo.leagueoflegendsquizz;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Rodolfo on 11/7/2015.
 */
public class GetData extends AsyncTask<Void, Void, ArrayList<Champions>> {
    @Override
    protected ArrayList<Champions> doInBackground(Void... params) {
        ArrayList<Champions> champions =  new ArrayList<>();

        String url = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion?locale=en_US&champData=spells&api_key=ef0e24ac-5fb5-436b-9f7e-c1939e57d9e6";
        try {
            JSONObject json = getJson(url);
            json = json.getJSONObject("data");
            Iterator<String> iterator = json.keys();
            while(iterator.hasNext()){
                JSONObject temp = json.getJSONObject(iterator.next());
                String name = temp.get("name") + "";
                String title = temp.get("title") + "";
                Champions tempChamp = new Champions(name, title);
                JSONArray spells = temp.getJSONArray("spells");
                for (int i = 0; i < spells.length(); i++){
                    tempChamp.addSkill(((JSONObject ) spells.get(i)).getString("name"));
                }
                champions.add(tempChamp);
            }
        }catch (Exception e){
            e.printStackTrace();

    }

        return champions;
    }

    public static JSONObject getJson(String url){

        InputStream is;
        String result;
        JSONObject jsonObject;

        // HTTP
        try {
            URL url2 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url2.openConnection();
            is = urlConnection.getInputStream();

        } catch(Exception e) {
            return null;
        }

        // Read response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch(Exception e) {
            return null;
        }

        // Convert string to object
        try {
            jsonObject = new JSONObject(result);
        } catch(JSONException e) {
            return null;
        }

        return jsonObject;

    }
}
