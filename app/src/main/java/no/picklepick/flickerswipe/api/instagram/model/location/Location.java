package no.picklepick.flickerswipe.api.instagram.model.location;

import com.google.gson.annotations.SerializedName;

/**
 * Instagram model for location.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class Location {

    @SerializedName("street_address")
    private String streetAddress;

    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;

    public String getStreetAddress() {
        return streetAddress;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
