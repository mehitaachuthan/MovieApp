package test.example.movieapp;

import java.util.ArrayList;

public interface AsyncTaskListener {
    public abstract void onAsyncTask(ArrayList<Movie> moviesList);
}
