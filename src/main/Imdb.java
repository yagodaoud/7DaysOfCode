package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Imdb {
    public static void main(String[] args) throws Exception {

        String url = "https://imdb-api.com/API/Top250Movies/k_r2ocdyro";

            URL obj = new URL(url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //Prints all 250 movies
                System.out.println(response);

                JSONObject objResponse = new JSONObject(response.toString());
                JSONArray titlesArray = objResponse.getJSONArray("titles");
                JSONArray yearsArray = objResponse.getJSONArray("years");
                JSONArray imagesArray = objResponse.getJSONArray("images");

                List<String> titles = new ArrayList<>();
                List<String> years = new ArrayList<>();
                List<String> images = new ArrayList<>();

                for (int i = 0; i < titlesArray.length(); i++) {
                    titles.add(titlesArray.getString(i));
                    years.add(yearsArray.getString(i));
                    images.add(imagesArray.getString(i));

                }
                System.out.println("Titles: " + titles + "\nYears: " + years + "\nImages: " + images);
            }
        }

    }