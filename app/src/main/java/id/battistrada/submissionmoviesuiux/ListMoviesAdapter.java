package id.battistrada.submissionmoviesuiux;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import com.bumptech.glide.Glide;
import static android.content.ContentValues.TAG;

public class ListMoviesAdapter extends RecyclerView.Adapter<ListMoviesAdapter.CategoryViewHolder> {
    final static String EXTRA_MOVIE = "movie";
    ArrayList<MoviesData> listMovie = new ArrayList<>();

    public ListMoviesAdapter(Context context){
        this.context=context;
    }

    private Context context;

    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movie,parent,false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position){
        holder.releaseDate.setText(listMovie.get(position).getRelease_date());
        holder.tvCategory.setText(listMovie.get(position).getOverview());
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra(EXTRA_MOVIE,listMovie.get(position));
                context.startActivity(intent);
            }
        });

        Glide.with(context)
                .load(listMovie.get(position).getThumbnailPath())
                .override(400,480)
                .into(holder.imgPoster);
        holder.tvName.setText(listMovie.get(position).getTitle());
    }

    @Override
    public int getItemCount(){
        Log.d(TAG, "getItemCount: "+listMovie.size());
        return listMovie.size();
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCategory, releaseDate;
        ImageView imgPoster;
        LinearLayout lv1;
        Button btnDetail;
        public CategoryViewHolder(View itemView) {
            super(itemView);
            btnDetail = (Button)itemView.findViewById(R.id.btn_detail);
            tvName = (TextView)itemView.findViewById(R.id.tv_item_name);
            tvCategory = (TextView)itemView.findViewById(R.id.tv_item_category);
            imgPoster = (ImageView)itemView.findViewById(R.id.img_item_poster);
            releaseDate = (TextView)itemView.findViewById(R.id.tv_date);
            lv1 = (LinearLayout)itemView.findViewById(R.id.lv1);
        }
    }

    public ArrayList<MoviesData> getListMovie() {
        return listMovie;
    }

    public void setlistMovie(ArrayList<MoviesData> listMovie) {
        this.listMovie = listMovie;
    }

}
