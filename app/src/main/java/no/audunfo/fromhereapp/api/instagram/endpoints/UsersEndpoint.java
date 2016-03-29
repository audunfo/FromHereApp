package no.audunfo.fromhereapp.api.instagram.endpoints;

import no.audunfo.fromhereapp.api.instagram.model.ListResponse;
import no.audunfo.fromhereapp.api.instagram.model.ObjectResponse;
import no.audunfo.fromhereapp.api.instagram.model.media.Media;
import no.audunfo.fromhereapp.api.instagram.model.user.SearchUser;
import no.audunfo.fromhereapp.api.instagram.model.user.User;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Endpoint definition for Instagram /users endpoint.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public interface UsersEndpoint {

    @GET("users/self")
    Call<ObjectResponse<User>> getCurrentUser(@Query("access_token") String accessToken);

    @GET("users/{userId}")
    Call<ObjectResponse<User>> getUserById();

    @GET("users/search")
    Call<ListResponse<SearchUser>> searchUsers();

    @GET("users/{userId}/media/recent")
    Call<ListResponse<Media>> getRecentMediaForUser();

    @GET("users/self/media/recent")
    Call<ListResponse<Media>> getRecentMediaForCurrentUser(@Query("access_token") String accessToken);


}
