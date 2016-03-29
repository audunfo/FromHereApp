package no.audunfo.fromhereapp.api.instagram.model.user;

import com.google.gson.annotations.SerializedName;

/**
 * Model for Instagram user in search.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class SearchUser extends SimpleUser {
    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
