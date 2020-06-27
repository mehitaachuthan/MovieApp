package test.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncTaskListener, OnMovieClickListener{
    private RecyclerView recyclerView;
    private ArrayList<Movie> moviesList;
    private MovieAdapter movieAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView() {
        moviesList = new ArrayList<Movie>();
        recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(manager);
        movieAdapter = new MovieAdapter(moviesList, MainActivity.this, MainActivity.this);
        recyclerView.setAdapter(movieAdapter);

        Downloader d = new Downloader(MainActivity.this, MainActivity.this);
        d.execute();
    }

    @Override
    public void onAsyncTask(ArrayList<Movie> moviesList) {
        this.moviesList = moviesList;
        movieAdapter = new MovieAdapter(this.moviesList, MainActivity.this, MainActivity.this);
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void onMovieClick(int position) {
        Movie movie = moviesList.get(position);
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("image_url", movie.getPosterPath());
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("plot", movie.getOverview());
        intent.putExtra("rating", movie.getVoteAverage());
        intent.putExtra("release", movie.getReleaseDate());
        startActivity(intent);
    }
}