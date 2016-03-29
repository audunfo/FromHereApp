package no.audunfo.fromhereapp.images;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.audunfo.fromhereapp.R;
import no.audunfo.fromhereapp.api.instagram.model.media.Media;
import no.audunfo.fromhereapp.images.viewholder.MediaItemContentViewHolder;
import no.audunfo.fromhereapp.images.viewholder.MediaItemUserViewHolder;
import no.audunfo.fromhereapp.images.viewholder.MediaItemViewHolder;

/**
 * Adapter for list of {@link no.audunfo.fromhereapp.api.instagram.model.media.Media}.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class MediaItemAdapter extends RecyclerView.Adapter {

    private Media item;
    private Context context;

    public MediaItemAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case MediaItemUserViewHolder.VIEW_TYPE:
                View userRow = layoutInflater.inflate(R.layout.viewholder_media_user_row, parent, false);
                return new MediaItemUserViewHolder(userRow, context);

            case MediaItemContentViewHolder.VIEW_TYPE:
                View contentRow = layoutInflater.inflate(R.layout.viewholder_media_content_row, parent, false);
                return new MediaItemContentViewHolder(contentRow, context);
        }
        // We should never end up here
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MediaItemViewHolder) {
            ((MediaItemViewHolder) holder).bindMedia(item, position);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return MediaItemUserViewHolder.VIEW_TYPE;
        } else {
            return MediaItemContentViewHolder.VIEW_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return item != null ? 2 : 0;
    }

    /**
     * Populate this adapter with a {@link Media} object.
     *
     * @param item the item to show.
     */
    public void setMediaItem(Media item) {
        if (item != null) {
            this.item = item;
            notifyDataSetChanged();
        }
    }
}
