package no.audunfo.fromhereapp.api.instagram.endpoints;

import no.audunfo.fromhereapp.api.instagram.model.ListResponse;
import no.audunfo.fromhereapp.api.instagram.model.location.InstagramLocation;
import no.audunfo.fromhereapp.api.instagram.model.media.Media;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Retrofit service definition for Instagram.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public interface LocationEndpoint {


    @GET("locations/search")
    Call<ListResponse<InstagramLocation>> searchLocations(@Query("lat") double latitude,
                                                          @Query("lng") double longitude,
                                                          @Query("access_token") String accessToken);

    @GET("locations/{locationId}/media/recent")
    Call<ListResponse<Media>> getRecentImagesForLocation(@Path("locationId") Long locationId, @Query("access_token") String accessToken);



}
