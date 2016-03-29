package no.audunfo.fromhereapp.api.instagram.model.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Common fields for instagram user.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public abstract class SimpleUser implements Serializable{

    @SerializedName("id")
    protected Long id;

    @SerializedName("profile_picture")
    protected String profilePictureUrl;

    @SerializedName("username")
    protected String userName;

    public Long getId() {
        return id;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public String getUserName() {
        return userName;
    }
}
