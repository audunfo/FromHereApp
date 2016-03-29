package no.audunfo.fromhereapp.api.instagram;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
import java.util.Date;

import no.audunfo.fromhereapp.api.instagram.endpoints.LocationEndpoint;
import no.audunfo.fromhereapp.api.instagram.endpoints.MediaEndpoint;
import no.audunfo.fromhereapp.api.instagram.endpoints.UsersEndpoint;
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

        // Add Type adapter for Date
        GsonBuilder gsonBuilder = new GsonBuilder();

        // De-serialize date
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });

        // Serialize Date
        gsonBuilder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.getTime());
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.instagram.com/v1/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
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
