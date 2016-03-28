package no.picklepick.flickerswipe.authentication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;

import no.picklepick.flickerswipe.R;

/**
 * Fragment for login.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class LoginFragment extends Fragment implements View.OnClickListener {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        rootView.findViewById(R.id.login_btn).setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, WebLoginFragment.newInstance("http://www.google.com"))
                .commit();

    }
}
