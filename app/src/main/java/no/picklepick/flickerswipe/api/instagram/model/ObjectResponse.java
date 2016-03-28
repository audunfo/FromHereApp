package no.picklepick.flickerswipe.api.instagram.model;

import java.io.Serializable;

/**
 * Generic instagram response for single data item.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class ObjectResponse<T extends Serializable> implements Serializable {

    private T data;

    public T getData() {
        return data;
    }
}
