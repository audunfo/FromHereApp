package no.audunfo.fromhereapp.images.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import no.audunfo.fromhereapp.R;
import no.audunfo.fromhereapp.api.instagram.model.media.Media;
import no.audunfo.fromhereapp.common.events.AppEvent;
import no.audunfo.fromhereapp.common.events.EventBus;

/**
 * View holder that contains info about {@link Media}'s author.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class MediaItemUserViewHolder extends RecyclerView.ViewHolder implements MediaItemViewHolder, View.OnClickListener {
    public static final int VIEW_TYPE = 1;
    private Context context;

    public MediaItemUserViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
    }

    @Override
    public void bindText(TextView textView, String text) {
        textView.setText(text);
    }

    @Override
    public void bindImage(ImageView imageView, String imageUrl) {
        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.icon_profile)
                .into(imageView);

    }

    @Override
    public void bindMedia(Media media, int position) {
        if (media != null) {

            if (media.getUser() != null) {
                // Bind user image
                CircleImageView avatarView = (CircleImageView) itemView.findViewById(R.id.user_image);
                if (avatarView != null) {
                    bindImage(avatarView, media.getUser().getProfilePictureUrl());
                }
                // Bind username
                TextView usernameView = (TextView) itemView.findViewById(R.id.username);
                if (usernameView != null) {
                    usernameView.setText(media.getUser().getUserName());
                }

                // Bind onClick
                View exitView = itemView.findViewById(R.id.exit);
                if (exitView != null) {
                    exitView.setOnClickListener(this);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit:
                Logger.d("Exit media view");
                EventBus.getEventBus().post(AppEvent.CLOSE_ITEM_VIEW);
                break;
        }
    }
}
