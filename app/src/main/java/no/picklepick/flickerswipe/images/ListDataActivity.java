package no.picklepick.flickerswipe.images;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import no.picklepick.flickerswipe.R;
import no.picklepick.flickerswipe.api.instagram.model.ListResponse;
import no.picklepick.flickerswipe.api.instagram.model.media.Media;
import no.picklepick.flickerswipe.api.instagram.model.user.User;
import no.picklepick.flickerswipe.common.BaseSlideActivity;

/**
 * Activity for listing data in a context (user, location etc)
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class ListDataActivity extends BaseSlideActivity {

    private static final String DATA_KEY = "dataKey";

    public static Intent showListData(Context context, ListResponse<? extends Serializable> response) {
        Intent intent = new Intent(context, ListDataActivity.class);
        intent.putExtra(DATA_KEY, response);

        return intent;
    }

    private RecyclerView recyclerView;

    @Override
    public void init(Bundle savedInstanceState) {
        setContent(R.layout.activity_list_media);
        setTitle("List media");
        setPrimaryColors(R.color.colorPrimary, R.color.colorPrimaryDark);
        enableFullscreen();
        recyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);

        ListResponse data = (ListResponse) getIntent().getSerializableExtra(DATA_KEY);
        handleData(data);
    }

    private void handleData(ListResponse data){
        if (data == null) {
            throw new IllegalArgumentException("Need data for showing list");
        }

        if (data.getData() != null && data.getData().size() > 0) {
            if (data.getData().get(0) instanceof Media) {
                showMediaList(data.getData());
            } else if (data.getData().get(0) instanceof User) {
                showUserList(data.getData());
            }
        }

    }

    private void showMediaList(List<Media> mediaList) {
        // TODO
    }

    private void showUserList(List<User> userList) {
        // TODO
    }


}
