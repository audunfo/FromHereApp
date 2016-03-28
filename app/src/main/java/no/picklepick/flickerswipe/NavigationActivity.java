package no.picklepick.flickerswipe;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import no.picklepick.flickerswipe.api.instagram.InstagramApiDelegate;
import no.picklepick.flickerswipe.api.instagram.model.ListResponse;
import no.picklepick.flickerswipe.api.instagram.model.location.InstagramLocation;
import no.picklepick.flickerswipe.api.instagram.model.media.Media;
import no.picklepick.flickerswipe.authentication.AuthenticateActivity;
import no.picklepick.flickerswipe.common.BaseActivity;
import no.picklepick.flickerswipe.common.DatabaseManager;
import no.picklepick.flickerswipe.common.dialogs.DialogManager;
import no.picklepick.flickerswipe.images.ListMediaActivity;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Main activity for navigation in the app.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class NavigationActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private GoogleApiClient googleApiClient;
    private Location lastLocation;
    private MaterialDialog loadingDialog;
    private boolean locationConnected = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.init(NavigationActivity.class.getSimpleName());

        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Button findImagesBtn = (Button) findViewById(R.id.findImagesBtn);
        if (findImagesBtn != null) {
            findImagesBtn.setOnClickListener(this);
        }

        setSupportActionBar(toolbar);
        /*initNavigationViews(toolbar);*/
        initLocationServices();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    private void initLocationServices() {
        // Create an instance of GoogleAPIClient.
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    private void initNavigationViews(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null && toggle != null) {
            drawer.setDrawerListener(toggle);
            toggle.syncState();
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    /**
     * Navigation callback.
     *
     * @param item navigation item
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    /**
     * InstagramLocation callbacks.
     *
     * @param bundle data
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Logger.d("onConnected");
        locationConnected = true;
    }

    private void requestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (lastLocation != null) {

                /**
                 * Important: Due to Instagram API permissions and review time
                 * I needed to drop getting images for location.
                 *
                 * The logic is here and can be enabled when we have a accessToken with the appropriate
                 * permissions.
                 */
                //getLocationsFromInstagram(lastLocation);

                // Instead we get images for the current user, until we have public_content scope permissions.
                getMediaForCurrentUser();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                DatabaseManager.getInstance().deleteToken(this);
                startActivity(new Intent(this, AuthenticateActivity.class));
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == RESULT_CANCELED) {
            Logger.d("Success getting location permissions");
            requestLocation();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Logger.d("onConnectionSuspended");
        locationConnected = false;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Logger.e("onConnectionFailed");
        locationConnected = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.findImagesBtn:
                if (locationConnected) {
                    requestLocation();
                }
                break;
        }
    }

    /**
     * API: Get locations from Instagram close to current location.
     *
     * @param location current location.
     */
    private void getLocationsFromInstagram(Location location) {
        // Show dialog
        loadingDialog = DialogManager.getLoadingDialog(this).show();

        // Execute data call
        Call<ListResponse<InstagramLocation>> call = InstagramApiDelegate.getInstance(this).getLocationEndpoint()
                .searchLocations(location.getLatitude(), location.getLongitude(), DatabaseManager.getInstance().getToken(this));

        call.enqueue(new Callback<ListResponse<InstagramLocation>>() {
            @Override
            public void onResponse(Response<ListResponse<InstagramLocation>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (response.body().getData().size() > 0) {
                        // To reduce complexity, we select the top location
                        getMediaForLocation(response.body().getData().get(0));
                    }
                } else {
                    handleError(getResources().getString(R.string.dialog_error_get_locations));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                handleError(getResources().getString(R.string.dialog_error_get_locations));
            }
        });
    }

    /**
     * API Call to get {@link Media} for a specific {@link InstagramLocation}.
     *
     * @param location instagram location (example Oslo, Bergen etc is a instagram location)
     */
    private void getMediaForLocation(InstagramLocation location) {
        Call<ListResponse<Media>> call = InstagramApiDelegate.getInstance(this).getLocationEndpoint()
                .getRecentImagesForLocation(location.getId(), DatabaseManager.getInstance().getToken(this));

        call.enqueue(new Callback<ListResponse<Media>>() {
            @Override
            public void onResponse(Response<ListResponse<Media>> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body().getData().size() > 0) {
                    safeDismissLoadingDialog();
                    // Go to image viewing
                    startActivity(ListMediaActivity.showListData(NavigationActivity.this,
                            new ArrayList<>(response.body().getData())));
                } else {
                    handleError(getResources().getString(R.string.dialog_error_get_images_location));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                handleError(getString(R.string.dialog_error_get_images_location));
            }
        });
    }

    /**
     * API: Load {@link Media} for current user.
     */
    private void getMediaForCurrentUser() {
        loadingDialog = DialogManager.getLoadingDialog(this).show();

        Call<ListResponse<Media>> call = InstagramApiDelegate.getInstance(this).getUsersEndpoint()
                .getRecentMediaForCurrentUser(DatabaseManager.getInstance().getToken(this));

        call.enqueue(new Callback<ListResponse<Media>>() {
            @Override
            public void onResponse(Response<ListResponse<Media>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    safeDismissLoadingDialog();
                    startActivity(ListMediaActivity.showListData(NavigationActivity.this,
                            new ArrayList<>(response.body().getData())));
                } else {
                    handleError(getString(R.string.dialog_error_get_images));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                handleError(getString(R.string.dialog_error_http_error));
            }
        });
    }

    private void handleError(String errorReason) {
        safeDismissLoadingDialog();
        showErrorDialog(errorReason);
    }

    private void safeDismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

}
