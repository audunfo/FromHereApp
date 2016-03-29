package no.picklepick.flickerswipe.images;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import no.picklepick.flickerswipe.R;
import no.picklepick.flickerswipe.api.instagram.model.media.Media;
import no.picklepick.flickerswipe.common.BaseActivity;
import no.picklepick.flickerswipe.common.events.AppEvent;

/**
 * Activity for listing data in a context (user, location etc)
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class ListMediaActivity extends BaseActivity {

    private static final String DATA_KEY = "dataKey";
    private ArrayList<Media> dataList;

    public static Intent showListData(Context context, ArrayList<Media> mediaList) {
        Intent intent = new Intent(context, ListMediaActivity.class);
        intent.putExtra(DATA_KEY, mediaList);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_media);
        setTitle("List media");

        dataList = (ArrayList<Media>) getIntent().getSerializableExtra(DATA_KEY);
        if (dataList == null) {
            throw new IllegalArgumentException("Cant list media without data");
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null && viewPager != null) {
            viewPager.setAdapter(new ItemPagerAdapter(fragmentManager));
            viewPager.setCurrentItem(0);
        }
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void evenRecieved(AppEvent event) {
        if (event.equals(AppEvent.CLOSE_ITEM_VIEW)) {
            finish();
        }
    }

    /**
     * Pager adapter for showing {@link Media} items.
     */
    private class ItemPagerAdapter extends FragmentStatePagerAdapter {

        public ItemPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Media item = dataList.get(position);
            return MediaItemFragment.newInstance(item);
        }

        @Override
        public int getCount() {
            return dataList.size();
        }
    }
}
