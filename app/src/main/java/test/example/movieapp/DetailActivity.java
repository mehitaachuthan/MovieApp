package test.example.movieapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailActivity extends AppCompatActivity {
    private ImageView poster;
    private TextView title, plot, rating, release;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        getSupportActionBar().setTitle("Movie Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        poster = findViewById(R.id.detail_poster);
        title = findViewById(R.id.detail_title);
        plot = findViewById(R.id.detail_plot_synopsis);
        rating = findViewById(R.id.detail_rating);
        release = findViewById(R.id.detail_release);

        Bundle bundle = getIntent().getExtras();
        String poster_path = bundle.getString("image_url");
        String movie_title = bundle.getString("title");
        String plot_overview = bundle.getString("plot");
        double user_rating = bundle.getDouble("rating");
        String release_date = bundle.getString("release");

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading);
        Glide.with(DetailActivity.this).load(poster_path).apply(requestOptions).into(poster);

        title.setText(movie_title);
        plot.setText(plot_overview);
        rating.setText(Double.toString(user_rating));
        release.setText(release_date);
    }
}
