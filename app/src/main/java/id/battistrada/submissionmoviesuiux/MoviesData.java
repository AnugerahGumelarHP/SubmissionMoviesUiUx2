package id.battistrada.submissionmoviesuiux;

import android.os.Parcel;
import android.os.Parcelable;

public class MoviesData implements Parcelable {
    String thumbnailPath;
    String posterPath;
    String title;
    String overview;
    String description;
    String release_date;

    public MoviesData(String thumbnailPath, String title, String overview, String release_date) {
        this.thumbnailPath = thumbnailPath;
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
    }

    public String getThumbnailPath(){
        String baseUrl ="http://image.tmdb.org/t/p/w185";
        return baseUrl + thumbnailPath;
    }

    public void setThumbnailPath(){
        this.thumbnailPath = thumbnailPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        String overview = this.overview.substring(0,this.overview.indexOf('.')+1);
        if (overview.length()>0)
            return overview;
        else
            return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPosterPath() {
        String baseUrl = "https://image.tmdb.org/t/p/original";
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getDescription(){
        return overview;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public static Creator<MoviesData> getCREATOR(){
        return CREATOR;
    }
    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.thumbnailPath);
        dest.writeString(this.posterPath);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.description);
    }

    protected MoviesData(Parcel in){
        this.thumbnailPath = in.readString();
        this.posterPath = in.readString();
        this.title = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.description = in.readString();
    }

    public static final Creator<MoviesData> CREATOR = new Creator<MoviesData>() {
        @Override
        public MoviesData createFromParcel(Parcel source) {
            return new MoviesData(source);
        }

        @Override
        public MoviesData[] newArray(int size) {
            return new MoviesData[size];
        }
    };
}
