package no.audunfo.fromhereapp.api.instagram.endpoints;

import java.util.Map;

import no.audunfo.fromhereapp.api.instagram.model.ListResponse;
import no.audunfo.fromhereapp.api.instagram.model.media.Media;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Endpoint definition for Instagram /media.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public interface MediaEndpoint {

    @GET("media/search")
    Call<ListResponse<Media>> searchForNearbyMedia(@QueryMap Map<String, String> params);
}
