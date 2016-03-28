package no.picklepick.flickerswipe.api.instagram.endpoints;

import no.picklepick.flickerswipe.api.instagram.model.ListResponse;
import no.picklepick.flickerswipe.api.instagram.model.location.InstagramLocation;
import no.picklepick.flickerswipe.api.instagram.model.media.Media;
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
