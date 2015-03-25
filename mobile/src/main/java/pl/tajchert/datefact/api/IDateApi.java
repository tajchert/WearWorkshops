package pl.tajchert.datefact.api;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface IDateApi {
    @GET("/")
    @Headers("X-Mashape-Key:ucWUYcrRE9mshD74eCRXoI52nsWyp1t8wQLjsn8ahz0FRwZKCh")
    void getFactForDate(@Query("fragment") String isFragment, @Query("json") String isJson, Callback<DateApi> callback);
}
