package no.picklepick.flickerswipe.images;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.picklepick.flickerswipe.R;
import no.picklepick.flickerswipe.api.instagram.model.media.Media;
import no.picklepick.flickerswipe.common.events.EventBus;

/**
 * Fragment for showing one {@link no.picklepick.flickerswipe.api.instagram.model.media.Media} item.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class MediaItemFragment extends Fragment {
    private static final String DATA_KEY = "dataKey";

    public static Fragment newInstance(Media item) {
        Bundle args = new Bundle();
        args.putSerializable(DATA_KEY, item);

        MediaItemFragment result = new MediaItemFragment();
        result.setArguments(args);

        return result;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getEventBus().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_media_item, container, false);

        RecyclerView rc = (RecyclerView) rootView.findViewById(R.id.media_item_recycler_view);
        Media data = (Media) getArguments().getSerializable(DATA_KEY);
        if (rc != null && data != null) {
            rc.setLayoutManager(new LinearLayoutManager(getContext()));
            MediaItemAdapter adapter = new MediaItemAdapter(getContext());
            rc.setAdapter(adapter);
            adapter.setMediaItem(data);
        }

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getEventBus().unregister(this);
    }
}
