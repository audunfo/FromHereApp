package no.audunfo.fromhereapp.api.instagram.model.user;

import com.google.gson.annotations.SerializedName;

/**
 * Model for instagram user.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class User extends SimpleUser {

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("bio")
    private String bio;

    @SerializedName("website")
    private String website;

    @SerializedName("userStatistic")
    private UserStatistic userStatistic;

    public String getFullName() {
        return fullName;
    }

    public String getBio() {
        return bio;
    }

    public String getWebsite() {
        return website;
    }

    public UserStatistic getUserStatistic() {
        return userStatistic;
    }

    /**
     * Model for statistics related to current instagram user.
     */
    private class UserStatistic {
        @SerializedName("media")
        private Integer mediaCount;

        @SerializedName("follows")
        private Integer followsCount;

        @SerializedName("followed_by")
        private Integer followersCount;

        public Integer getMediaCount() {
            return mediaCount;
        }

        public Integer getFollowsCount() {
            return followsCount;
        }

        public Integer getFollowersCount() {
            return followersCount;
        }
    }

}
