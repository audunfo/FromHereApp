package no.audunfo.fromhereapp.common;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;

import no.audunfo.fromhereapp.common.dialogs.DialogManager;
import no.audunfo.fromhereapp.common.events.EventBus;

/**
 * Common activity functionality.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getEventBus().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getEventBus().unregister(this);
    }

    protected void showSuccessDialog(String title){
        final MaterialDialog successDialog = DialogManager.getSuccessDialogWithTitle(this, title).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                successDialog.dismiss();
            }
        }, 1700);
    }

    protected void showErrorDialog(String title){
        final MaterialDialog errorDialog = DialogManager.getErrorDialogWithTitle(this, title).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                errorDialog.dismiss();
            }
        }, 1700);
    }
}
