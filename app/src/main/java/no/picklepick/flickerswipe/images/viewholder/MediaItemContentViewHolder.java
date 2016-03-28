package no.picklepick.flickerswipe.images.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import no.picklepick.flickerswipe.R;
import no.picklepick.flickerswipe.api.instagram.model.media.ImageInfo;
import no.picklepick.flickerswipe.api.instagram.model.media.ImageType;
import no.picklepick.flickerswipe.api.instagram.model.media.Media;

/**
 * View holder for {@link no.picklepick.flickerswipe.api.instagram.model.media.Media} content.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class MediaItemContentViewHolder extends RecyclerView.ViewHolder implements MediaItemViewHolder {
    public static final int VIEW_TYPE = 2;

    private Context context;

    public MediaItemContentViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
    }


    @Override
    public void bindText(TextView textView, String text) {
        if (textView != null) {
            textView.setText(text);
        }

    }

    @Override
    public void bindImage(ImageView imageView, String imageUrl) {
        Picasso.with(context)
                .load(imageUrl)
                .into(imageView);
    }

    @Override
    public void bindMedia(Media media, int position) {
        if (media != null) {
            if (media.getImageInfo() != null && media.getImageInfo().size() > 0) {
                ImageView contentView = (ImageView) itemView.findViewById(R.id.content_image);
                ImageInfo standardImage = media.getImageInfo().get(ImageType.STANDARD_RES.getValue());
                if (contentView != null && standardImage != null) {
                    bindImage(contentView, standardImage.getUrl());
                }
            }
        }

    }
}
