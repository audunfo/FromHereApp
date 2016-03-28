package no.picklepick.flickerswipe.authentication;

/**
 * API secrets for Instagram.
 *
 * TODO: Should be moved out of the code.
 */
public enum  AppData {
    CLIENT_ID(""),
    CLIENT_SECRET(""),
    APP_NAME("");

    private String value;

    AppData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
