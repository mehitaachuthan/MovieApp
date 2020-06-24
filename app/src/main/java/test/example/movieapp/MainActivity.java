package test.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
    }
}