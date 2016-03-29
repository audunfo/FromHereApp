package no.audunfo.fromhereapp.authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import no.audunfo.fromhereapp.NavigationActivity;
import no.audunfo.fromhereapp.R;
import no.audunfo.fromhereapp.authentication.fragments.LoginFragment;
import no.audunfo.fromhereapp.common.DatabaseManager;

/**
 * Activity for authentication.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class AuthenticateActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        checkAuthentication();

    }

    private void checkAuthentication() {
        String token = DatabaseManager.getInstance().getToken(this);
        if (token != null) {
            startActivity(new Intent(this, NavigationActivity.class));
            /*finish();*/
        } else goToLoginPage();
    }

    private void goToLoginPage() {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .commit();
    }
}
