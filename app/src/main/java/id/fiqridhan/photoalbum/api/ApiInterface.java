package id.fiqridhan.photoalbum.api;

import java.util.List;

import id.fiqridhan.photoalbum.model.Album;
import id.fiqridhan.photoalbum.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("photos")
    Call<List<Album>> getAlbum(@Query("albumId") int albumId);

    @GET("user")
    Call<List<User>> getUser(@Query("user") int userId);

}
