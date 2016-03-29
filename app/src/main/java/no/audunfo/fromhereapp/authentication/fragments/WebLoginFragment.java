package no.audunfo.fromhereapp.authentication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.logging.Logger;

import no.audunfo.fromhereapp.NavigationActivity;
import no.audunfo.fromhereapp.common.DatabaseManager;
import no.audunfo.fromhereapp.common.dialogs.DialogManager;

/**
 * WebView for doing implicit oauth authentication with Instagram.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class WebLoginFragment extends WebViewFragment {
    private static Logger logger = Logger.getLogger(WebLoginFragment.class.getSimpleName());
    private MaterialDialog loadingDialog;

    public static WebLoginFragment newInstance(String url) {
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

        getWebView().setWebViewClient(new WebViewClient());
        String url = getArguments().getString("url");
        if (url != null) {
            getWebView().clearCache(true);
            getWebView().loadUrl(url);
            loadingDialog = DialogManager.getLoadingDialog(getActivity()).show();
        }
    }

    private void finishAuthentication(String accessToken) {
        DatabaseManager.getInstance().saveToken(accessToken, getActivity());
        startActivity(new Intent(getActivity(), NavigationActivity.class));
        getActivity().finish();
    }

    private class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (url.contains("access_token=")) {
                String accessToken = url.substring(url.indexOf("=") + 1, url.length());
                logger.info("Found accessToken in url: " + accessToken);
                finishAuthentication(accessToken);
                return true;
            }

            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if(loadingDialog != null && loadingDialog.isShowing()){
                loadingDialog.dismiss();
            }
            super.onPageFinished(view, url);
        }
    }
}
