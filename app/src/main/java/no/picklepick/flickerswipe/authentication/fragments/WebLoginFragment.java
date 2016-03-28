package no.picklepick.flickerswipe.authentication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;

/**
 * Created by audunfo on 28/03/16.
 */
public class WebLoginFragment extends WebViewFragment {

    public static WebLoginFragment newInstance(String url){
        WebLoginFragment fragment = new WebLoginFragment();
        Bundle arguments = new Bundle();
        arguments.putString("url", url);

        fragment.setArguments(arguments);

        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        String url = getArguments().getString("url");
        if(url != null){
            getWebView().clearCache(true);
            getWebView().loadUrl(url);
        }
    }
}
