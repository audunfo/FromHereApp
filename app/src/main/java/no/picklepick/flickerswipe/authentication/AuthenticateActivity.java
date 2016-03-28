package no.picklepick.flickerswipe.authentication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import no.picklepick.flickerswipe.MainActivity;
import no.picklepick.flickerswipe.R;
import no.picklepick.flickerswipe.authentication.fragments.LoginFragment;
import no.picklepick.flickerswipe.common.DatabaseManager;

/**
 * Activity for authentication.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class AuthenticateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        checkAuthentication();
    }

    private void checkAuthentication(){
        String token = DatabaseManager.getInstance().getToken(this);
        if(token != null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        else goToLogin();
    }

    private void goToLogin(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .commit();
    }
}
