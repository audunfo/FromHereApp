package no.audunfo.fromhereapp.common.dialogs;

import android.content.Context;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import no.audunfo.fromhereapp.R;

/**
 * Helper class for building dialogs.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class DialogManager {
    // Dialog for sending pickle
    public static MaterialDialog.Builder getLoadingDialog(Context context) {
        return new MaterialDialog.Builder(context)
                .title(context.getResources().getString(R.string.dialog_title_loading))
                .titleColorRes(R.color.colorPrimaryDark)
                .titleGravity(GravityEnum.CENTER)
                .progress(true, 500)
                .progressIndeterminateStyle(true)
                .theme(Theme.LIGHT)
                .contentColorRes(R.color.colorPrimary)
                .widgetColorRes(R.color.colorAccent)
                .contentGravity(GravityEnum.START);
    }

    // Dialog for sending pickle
    public static MaterialDialog.Builder getTextLoadingDialog(Context context, String title) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .titleColorRes(R.color.colorPrimaryDark)
                .titleGravity(GravityEnum.CENTER)
                .progress(true, 500)
                .progressIndeterminateStyle(true)
                .theme(Theme.LIGHT)
                .contentColorRes(R.color.colorPrimary)
                .widgetColorRes(R.color.colorAccent)
                .contentGravity(GravityEnum.START);
    }

    // Dialog with success icon and title
    public static MaterialDialog.Builder getSuccessDialogWithTitle(Context context, String title) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .titleColorRes(R.color.colorPrimaryDark)
                .titleGravity(GravityEnum.CENTER)
                .customView(R.layout.dialog_success, false)
                .theme(Theme.LIGHT)
                .autoDismiss(true)
                .contentGravity(GravityEnum.START);
    }

    // Dialog with error icon and title
    public static MaterialDialog.Builder getErrorDialogWithTitle(Context context, String title) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .titleColorRes(R.color.colorMetaDark)
                .titleGravity(GravityEnum.CENTER)
                .customView(R.layout.dialog_error, false)
                .theme(Theme.LIGHT)
                .autoDismiss(true)
                .contentGravity(GravityEnum.START);
    }
}
