package no.picklepick.flickerswipe.authentication;

/**
 * API secrets for Instagram.
 *
 * TODO: Should be moved out of the code.
 */
public enum InstagramApp {
    CLIENT_ID("9edf02fad6a5430f9696f1c3eac0984b"),
    CLIENT_SECRET("9e2d9fcba1a34e0eaf90ed51a3a162ed"),
    APP_NAME("swipeify"),
    CALLBACK_URL("https://instaswipe-profsys.rhcloud.com/oauth/callback"),
    AUTHORIZE_URL("https://api.instagram.com/oauth/authorize/?client_id=9edf02fad6a5430f9696f1c3eac0984b&redirect_uri=https://instaswipe-profsys.rhcloud.com/oauth/callback&response_type=token");

    private String value;

    InstagramApp(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
