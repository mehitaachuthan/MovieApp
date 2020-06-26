package test.example.movieapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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
import java.util.ArrayList;

public class Downloader extends AsyncTask<Void, Void, Void> {
    private ArrayList<Movie> moviesList;
    private AsyncTaskListener callback;
    ProgressDialog progressDialog;

    public Downloader(AsyncTaskListener callback, Context context) {
        this.callback = callback;
        this.progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Fetching movies...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    protected Void doInBackground(Void ... voids) {
        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/top_rated?api_key=" + BuildConfig.ApiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = "";
            StringBuilder data = new StringBuilder();

            line = bufferedReader.readLine();
            while(line != null) {
                data.append(line + "\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            reader.close();
            inputStream.close();
            connection.disconnect();

            moviesList = new ArrayList<Movie>();
            JSONObject jsonObject = new JSONObject(data.toString());
            JSONArray results = jsonObject.getJSONArray("results");
            JSONObject movie;
            for(int i = 0; i < results.length(); i++) {
                movie = results.getJSONObject(i);
                double popularity = movie.getDouble("popularity");
                int voteCount = movie.getInt("vote_count");
                boolean video = movie.getBoolean("video");
                String posterPath = movie.getString("poster_path");
                int id = movie.getInt("id");
                boolean adult = movie.getBoolean("adult");
                String backdropPath = movie.getString("backdrop_path");
                String originalLanguage = movie.getString("original_language");
                String originalTitle = movie.getString("original_title");

                JSONArray genreID_jsonArray = movie.getJSONArray("genre_ids");
                int[] genreID = new int[genreID_jsonArray.length()];
                for(int j = 0; j < genreID_jsonArray.length(); j++) {
                    genreID[j] = genreID_jsonArray.getInt(j);
                }

                String title = movie.getString("title");
                double voteAverage = movie.getDouble("vote_average");
                String overview = movie.getString("overview");
                String releaseDate = movie.getString("release_date");
                moviesList.add(new Movie(popularity, voteCount, video, posterPath, id, adult, backdropPath,
                        originalLanguage, originalTitle, genreID, title, voteAverage, overview, releaseDate));
            }
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void avoid) {
        super.onPostExecute(null);
        callback.onAsyncTaskListener(moviesList);
        if(progressDialog.isShowing())
            progressDialog.dismiss();
    }
}