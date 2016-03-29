package no.audunfo.fromhereapp.authentication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import no.audunfo.fromhereapp.R;
import no.audunfo.fromhereapp.authentication.InstagramApp;

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

        Button loginBtn = (Button) rootView.findViewById(R.id.login_btn);
        if(loginBtn != null){
            loginBtn.setOnClickListener(this);
        }

        return rootView;
    }

    @Override
    public void onClick(View v) {
        getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, WebLoginFragment.newInstance(InstagramApp.AUTHORIZE_URL.getValue()))
                .commit();

    }
}
