package no.audunfo.fromhereapp.api.instagram.model.media;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import no.audunfo.fromhereapp.api.instagram.model.location.InstagramLocation;
import no.audunfo.fromhereapp.api.instagram.model.user.User;

/**
 * Model for Instagram media object.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class Media implements Serializable{

    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String type;

    @SerializedName("created_time")
    private Date createdAt;

    @SerializedName("images")
    private Map<String, ImageInfo> imageInfo;

    @SerializedName("user")
    private User user;

    @SerializedName("instagramLocation")
    private InstagramLocation instagramLocation;

    public String getType() {
        return type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Map<String, ImageInfo> getImageInfo() {
        return imageInfo;
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return id;
    }

    public InstagramLocation getInstagramLocation() {
        return instagramLocation;
    }
}
