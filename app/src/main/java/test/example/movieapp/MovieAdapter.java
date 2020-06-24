package test.example.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private ArrayList<Movie> moviesList;
    private Context context;

    public MovieAdapter(ArrayList<Movie> movie, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView title, rating;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.poster);
            this.title = itemView.findViewById(R.id.title);
            this.rating = itemView.findViewById(R.id.rating);
        }
    }

    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_view, parent, false);
        MyViewHolder myView = new MyViewHolder(view);
        return myView;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(moviesList.get(position).getTitle());
        holder.rating.setText(Double.toString(moviesList.get(position).getVoteAverage()));

        //set image
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}