package no.picklepick.flickerswipe.api.instagram.endpoints;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Retrofit service definition for Instagram.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public interface LocationEndpoint {


    @GET("locations/search")
    Call<?> searchLocation();

    @GET("locations/{locationId}/media/recent")
    Call<?> getRecentImagesForLocation();



}
