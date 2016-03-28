package no.picklepick.flickerswipe.api.instagram.endpoints;

import no.picklepick.flickerswipe.api.instagram.model.media.Media;
import no.picklepick.flickerswipe.api.instagram.model.user.User;
import no.picklepick.flickerswipe.api.instagram.model.ObjectResponse;
import no.picklepick.flickerswipe.api.instagram.model.ListResponse;
import no.picklepick.flickerswipe.api.instagram.model.user.SearchUser;
import retrofit.Call;
import retrofit.http.GET;

/**
 * Endpoint definition for Instagram /users endpoint.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public interface UsersEndpoint {

    @GET("users/self")
    Call<ObjectResponse<User>> getCurrentUser();

    @GET("users/{userId}")
    Call<ObjectResponse<User>> getUserById();

    @GET("users/search")
    Call<ListResponse<SearchUser>> searchUsers();

    @GET("users/{userId}/media/recent")
    Call<ListResponse<Media>> getRecentMediaForUser();


}
