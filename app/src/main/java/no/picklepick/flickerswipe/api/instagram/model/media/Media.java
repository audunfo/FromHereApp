package no.picklepick.flickerswipe.api.instagram.model.media;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import no.picklepick.flickerswipe.api.instagram.model.location.Location;
import no.picklepick.flickerswipe.api.instagram.model.user.User;

/**
 * Model for Instagram media object.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class Media implements Serializable{

    @SerializedName("id")
    private Long id;

    @SerializedName("type")
    private String type;

    @SerializedName("created_time")
    private Date createdAt;

    @SerializedName("images")
    private Map<String, ImageInfo> imageInfo;

    @SerializedName("user")
    private User user;

    @SerializedName("location")
    private Location location;

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

    public Long getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }
}
