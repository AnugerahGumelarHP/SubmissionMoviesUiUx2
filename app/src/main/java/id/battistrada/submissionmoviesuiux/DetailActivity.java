package id.battistrada.submissionmoviesuiux;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    ImageView img_detail;
    TextView detailDescription, tvTitle, tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        tvDate = findViewById(R.id.tv_detail_date);
        tvTitle = findViewById(R.id.tv_detail_title);
        img_detail = findViewById(R.id.img_detail);
        detailDescription = findViewById(R.id.description);
        MoviesData currentMovie = getIntent().getParcelableExtra(ListMoviesAdapter.EXTRA_MOVIE);

        Glide.with(this)
                .load(currentMovie.getThumbnailPath()).into(img_detail);
        tvDate.setText(currentMovie.getRelease_date());
        tvTitle.setText(currentMovie.getTitle());
        detailDescription.setText(currentMovie.getDescription());
    }
}
