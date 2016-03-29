package no.picklepick.flickerswipe.images.viewholder;

import android.widget.ImageView;
import android.widget.TextView;

import no.picklepick.flickerswipe.api.instagram.model.media.Media;

/**
 * Define some common functions for view holders.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public interface MediaItemViewHolder {

    void bindText(TextView textView, String text);
    void bindImage(ImageView imageView, String imageUrl);
    void bindMedia(Media media, int position);
}
