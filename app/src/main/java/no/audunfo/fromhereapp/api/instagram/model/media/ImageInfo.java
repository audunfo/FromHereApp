package no.audunfo.fromhereapp.api.instagram.model.media;

import java.io.Serializable;

/**
 * Info for instagram image.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class ImageInfo implements Serializable{

    private String url;
    private Integer width;
    private Integer heigth;

    public String getUrl() {
        return url;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeigth() {
        return heigth;
    }
}
