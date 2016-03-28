package no.picklepick.flickerswipe.api.instagram;

import android.content.Context;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import no.picklepick.flickerswipe.api.instagram.endpoints.LocationEndpoint;
import no.picklepick.flickerswipe.api.instagram.endpoints.MediaEndpoint;
import no.picklepick.flickerswipe.api.instagram.endpoints.UsersEndpoint;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Delegate for handling API calls.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class InstagramApiDelegate {

    private static InstagramApiDelegate instance;

    private LocationEndpoint locationEndpoint;
    private UsersEndpoint usersEndpoint;
    private MediaEndpoint mediaEndpoint;


    private InstagramApiDelegate() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.instagram.com/v1/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        locationEndpoint = retrofit.create(LocationEndpoint.class);
        usersEndpoint = retrofit.create(UsersEndpoint.class);
        mediaEndpoint = retrofit.create(MediaEndpoint.class);
    }

    public static InstagramApiDelegate getInstance(Context context) {
        if (instance == null) {
            instance = new InstagramApiDelegate();
        }
        return instance;
    }

    public LocationEndpoint getLocationEndpoint() {
        return locationEndpoint;
    }

    public UsersEndpoint getUsersEndpoint() {
        return usersEndpoint;
    }

    public MediaEndpoint getMediaEndpoint() {
        return mediaEndpoint;
    }
}
