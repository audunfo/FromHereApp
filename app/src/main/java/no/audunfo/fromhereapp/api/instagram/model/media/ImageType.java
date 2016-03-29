package no.audunfo.fromhereapp.api.instagram.model.media;

/**
 * Enum for image type.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public enum ImageType {
    LOW_RES("low_resolution"),
    STANDARD_RES("standard_resolution"),
    THUMBNAIL("thumbnail");

    private String value;

    ImageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
