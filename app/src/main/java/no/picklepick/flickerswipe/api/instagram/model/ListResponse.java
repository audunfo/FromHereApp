package no.picklepick.flickerswipe.api.instagram.model;

import java.io.Serializable;
import java.util.List;

/**
 * Generic list response.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class ListResponse<T extends Serializable> implements Serializable {

    private List<T> data;

    public List<T> getData() {
        return data;
    }

}
