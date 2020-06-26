package test.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncTaskListener{
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
        movieAdapter = new MovieAdapter(moviesList, MainActivity.this);
        recyclerView.setAdapter(movieAdapter);

        Downloader d = new Downloader(MainActivity.this, MainActivity.this);
        d.execute();
    }

    @Override
    public void onAsyncTaskListener(ArrayList<Movie> moviesList) {
        this.moviesList = moviesList;
        movieAdapter = new MovieAdapter(this.moviesList, MainActivity.this);
        recyclerView.setAdapter(movieAdapter);
    }
}