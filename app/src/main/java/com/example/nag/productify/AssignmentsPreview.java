/**
 * Shows the user the interims due dates created by the algorithm and allows them to either confirm, edit, or cancel it.
 * code adapted from https://developers.google.com/calendar/create-events
 */
package com.example.nag.productify;

import android.Manifest;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class AssignmentsPreview extends Activity implements EasyPermissions.PermissionCallbacks {

    Button confirmBut, editBut, cancelBut;
    GoogleAccountCredential mCredential;
    private static final String[] SCOPES = { CalendarScopes.CALENDAR_READONLY };

    ProgressDialog mProgress;

    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;

    private static final String PREF_ACCOUNT_NAME = "accountName";

    private String name;
    private ArrayList <DateTime> interims;


    /**
     * Instantiates the AssignmentPreview activity
     * @param savedInstanceState previously saved app state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments_preview);

        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());

        confirmBut = findViewById(R.id.confirmBut);
        editBut =  findViewById(R.id.editBut);
        cancelBut = findViewById(R.id.cancelBut);

        TextView assignmentName = findViewById(R.id.assignmentName);
        TextView dueDateText = findViewById(R.id.dueDateText);
        TextView startDate = findViewById(R.id.startDate);
        TextView lengthText = findViewById(R.id.lengthText);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("nm");
        int sYear = bundle.getInt("sy");
        int sMonth = bundle.getInt("sm");
        int sDay = bundle.getInt("sd");
        int sHour = bundle.getInt("sh");
        int sMinute = bundle.getInt("smin");
        int dYear = bundle.getInt("dy");
        int dMonth = bundle.getInt("dm");
        int dDay = bundle.getInt("dd");
        int dHour = bundle.getInt("dh");
        int dMinute = bundle.getInt("dmin");
        double predictedLength = bundle.getDouble("pred");
        Boolean mon = bundle.getBoolean("mo");
        Boolean tues = bundle.getBoolean("tu");
        Boolean wed = bundle.getBoolean("we");
        Boolean thurs = bundle.getBoolean("th");
        Boolean fri = bundle.getBoolean("fr");
        Boolean sat = bundle.getBoolean("sa");
        Boolean sun = bundle.getBoolean("su");

        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Calling Google Calendar API ...");

        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());

        EventTask event1 = new EventTask(sYear, sMonth, sDay, sHour, sMinute, dYear, dMonth, dDay, dHour, dMinute, name, predictedLength, mon, tues, wed, thurs, fri, sat, sun);

        interims = event1.createEventDates();

        Log.d("interims", interims.toString());

        assignmentName.setText(name);

        String dM;
        String sM;

        if (dMinute < 10)
            {
                dM = ("0" +dMinute);
            }
        else
            {
                dM = (dMinute + "");
            }

        if (sMinute < 10)
            {
                sM = ("0" +sMinute);
            }
        else
            {
                sM = (sMinute + "");
            }

        dueDateText.setText("Due: " + dMonth+ "/" + dDay + "/" + dYear + " at " + dHour + ":" + dM);
        startDate.setText("Start: " + sMonth+ "/" + sDay + "/" + sYear + " at " + sHour + ":" + sM);
        lengthText.setText("Length: " + predictedLength + " hours");

      populateListView ();

      confirmBut.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              confirmBut.setEnabled(false);
              getResultsFromApi();
              confirmBut.setEnabled(true);

              Intent confirm =  new Intent (AssignmentsPreview.this, CalendarScreen.class);
              startActivity(confirm);
          }
      });

    }

    /**
     * A method which populates the listview to display all the interim due dates generated by the createEventDates method
     */
    private void populateListView ()
    {
        //create list of items

        ArrayList<String> interimDates = new ArrayList<>();

        if (interims!=null)
        {
            for (int n = 0; n < interims.size(); n += 2) {
                DateTime start = interims.get(n); //first item is start, second is end then repeats
                DateTime end = interims.get(n + 1);
//            String sDate = sdf.format(start.toString());
                //           String eDate = sdf.format(end.toString());
                //           String date = (sDate + " to " + eDate); //fix this later so it is in the proper form
                String s = start.toString();
                String e = end.toString();
                String date  = (s.substring(5,7) + "/" + s.substring(8,10) + "/" +  s.substring(0,4) + " at " +
                        s.substring(11,13) + ":" + s.substring(14, 16) + " to " + e.substring(5,7) + "/" + e.substring(8,10) + "/" +  e.substring(0,4) + " at " +
                        e.substring(11,13) + ":" + e.substring(14, 16));

                interimDates.add(date);
            }
        }
        else
            {
                interimDates.add("There were no possible times to work added.");
                interimDates.add("Please adjust your assignment inputs.");
            }
        //now somehow add all the datetime objects in the right format to be displayed (see above) does it say how much percent?
        //just do the math to find the percent probably
        // from the algorithm into this arraylist, probably with a for loop

        //make adapter

        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, //context
                R.layout.listofcreateddates, //layout to use (create)
                interimDates);                //items to be displayed
        //configure listview

        ListView list = findViewById(R.id.listviewView);
        list.setAdapter(adapter);
    }

    /**
     * Creates a toast to show to the user.
     * @param text the String shown to the user
     */
    private void showToast (String text)
    {
        Toast.makeText(AssignmentsPreview.this,text,Toast.LENGTH_LONG).show();
    }

    /**
     * brings the user back to the assignment class on button click
     * @param view view
     */
    public void goEdit (View view)
    {
        Intent confirm =  new Intent (AssignmentsPreview.this, Assignment.class);
        startActivity(confirm);
    }

    /**
     * Brings the user to the MainActivity activity and cancels the EventTask
     * @param view the view the method is being used for
     */

    public void goCancel (View view)
    {
        Intent confirm =  new Intent (AssignmentsPreview.this,MainActivity.class);
        startActivity(confirm);
    }

    /**
     * Attempt to call the API, after verifying that all the preconditions are
     * satisfied. The preconditions are: Google Play Services installed, an
     * account was selected and the device currently has online access. If any
     * of the preconditions are not satisfied, the app will prompt the user as
     * appropriate.
     */
    private void getResultsFromApi() {
        if (! isGooglePlayServicesAvailable()) {
            acquireGooglePlayServices();
        } else if (mCredential.getSelectedAccountName() == null) {
            chooseAccount();
        } else if (! isDeviceOnline()) {
            showToast("No network connection available.");
        } else {
            new AssignmentsPreview.MakeRequestTask3(mCredential).execute();
        }
    }

    /**
     * Attempts to set the account used with the API credentials. If an account
     * name was previously saved it will use that one; otherwise an account
     * picker dialog will be shown to the user. Note that the setting the
     * account to use with the credentials object requires the app to have the
     * GET_ACCOUNTS permission, which is requested here if it is not already
     * present. The AfterPermissionGranted annotation indicates that this
     * function will be rerun automatically whenever the GET_ACCOUNTS permission
     * is granted.
     */
    @AfterPermissionGranted(REQUEST_PERMISSION_GET_ACCOUNTS)
    private void chooseAccount() {
        if (EasyPermissions.hasPermissions(
                this, Manifest.permission.GET_ACCOUNTS)) {
            String accountName = getPreferences(Context.MODE_PRIVATE)
                    .getString(PREF_ACCOUNT_NAME, null);
            if (accountName != null) {
                mCredential.setSelectedAccountName(accountName);
                getResultsFromApi();
            } else {
                // Start a dialog from which the user can choose an account
                startActivityForResult(
                        mCredential.newChooseAccountIntent(),
                        REQUEST_ACCOUNT_PICKER);
            }
        } else {
            // Request the GET_ACCOUNTS permission via a user dialog
            EasyPermissions.requestPermissions(
                    this,
                    "This app needs to access your Google account (via Contacts).",
                    REQUEST_PERMISSION_GET_ACCOUNTS,
                    Manifest.permission.GET_ACCOUNTS);
        }
    }

    /**
     * Called when an activity launched here (specifically, AccountPicker
     * and authorization) exits, giving you the requestCode you started it with,
     * the resultCode it returned, and any additional data from it.
     * @param requestCode code indicating which activity result is incoming.
     * @param resultCode code indicating the result of the incoming
     *     activity result.
     * @param data Intent (containing result data) returned by incoming
     *     activity result.
     */
    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != RESULT_OK) {
                    showToast(
                            "This app requires Google Play Services. Please install " +
                                    "Google Play Services on your device and relaunch this app.");
                } else {
                    getResultsFromApi();
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    String accountName =
                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        SharedPreferences settings =
                                getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.apply();
                        mCredential.setSelectedAccountName(accountName);
                        getResultsFromApi();
                    }
                }
                break;
            case REQUEST_AUTHORIZATION:
                if (resultCode == RESULT_OK) {
                    getResultsFromApi();
                }
                break;
        }
    }

    /**
     * Respond to requests for permissions at runtime for API 23 and above.
     * @param requestCode The request code passed in
     *     requestPermissions(android.app.Activity, String, int, String[])
     * @param permissions The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *     which is either PERMISSION_GRANTED or PERMISSION_DENIED. Never null.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(
                requestCode, permissions, grantResults, this);
    }

    /**
     * Callback for when a permission is granted using the EasyPermissions
     * library.
     * @param requestCode The request code associated with the requested
     *         permission
     * @param list The requested permission list. Never null.
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Do nothing.
    }

    /**
     * Callback for when a permission is denied using the EasyPermissions
     * library.
     * @param requestCode The request code associated with the requested
     *         permission
     * @param list The requested permission list. Never null.
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Do nothing.
    }

    /**
     * Checks whether the device currently has a network connection.
     * @return true if the device has a network connection, false otherwise.
     */
    private boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Check that Google Play services APK is installed and up to date.
     * @return true if Google Play Services is available and up to
     *     date on this device; false otherwise.
     */
    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }

    /**
     * Attempt to resolve a missing, out-of-date, invalid or disabled Google
     * Play Services installation via a user dialog, if possible.
     */
    private void acquireGooglePlayServices() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
        }
    }


    /**
     * Display an error dialog showing that Google Play Services is missing
     * or out of date.
     * @param connectionStatusCode code describing the presence (or lack of)
     *     Google Play Services on this device.
     */
    void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        Dialog dialog = apiAvailability.getErrorDialog(
                AssignmentsPreview.this,
                connectionStatusCode,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }

    /**
     * An asynchronous task that handles the Google Calendar API call.
     * Placing the API calls in their own task ensures the UI stays responsive.
     */
    private class MakeRequestTask3 extends AsyncTask<Void, Void, List<String>> {
        private com.google.api.services.calendar.Calendar mService;
        private Exception mLastError = null;

        MakeRequestTask3(GoogleAccountCredential credential) {
            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            mService = new com.google.api.services.calendar.Calendar.Builder(
                    transport, jsonFactory, credential)
                    .setApplicationName("Google Calendar API Android Quickstart")
                    .build();
        }

        /**
         * Background task to call Google Calendar API.
         * @param params no parameters needed for this task.
         */
        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            try {
                return addEvent();

            } catch (Exception e) {
                mLastError = e;
                cancel(true);
                return null;
            }
        }

        /**
         * Adds and returns events added to google calendar
         * @return List of Strings describing returned events.
         * @throws IOException
         */
        private ArrayList<String> addEvent() throws IOException {

            Event thingy = new Event()
                    .setSummary(name)
                    .setDescription(name);

            EventReminder[] reminderOverrides = new EventReminder[] {
                    new EventReminder().setMethod("email").setMinutes(24 * 60),
                    new EventReminder().setMethod("popup").setMinutes(10),
            };

            ArrayList<String> items = new ArrayList<>();

            for (int i = 0; i <interims.size(); i+=2)

            {
                DateTime startDateTime = interims.get(i);
                EventDateTime s = new EventDateTime()
                        .setDateTime(startDateTime)
                        .setTimeZone("America/New_York");
                thingy.setStart(s);
                DateTime endDateTime = interims.get(i+1);
                EventDateTime end = new EventDateTime()
                        .setDateTime(endDateTime)
                        .setTimeZone("America/New_York");
                thingy.setEnd(end);

                Event.Reminders reminders = new Event.Reminders()
                        .setUseDefault(false)
                        .setOverrides(Arrays.asList(reminderOverrides));
                thingy.setReminders(reminders);

                Event events = mService.events().insert("primary", thingy)
                        .execute();

                String eventObj = events.getSummary().toString();

                items.add(eventObj);
            }
            return items;
        }

        /**
         * before executing show progress
         */
        @Override
        protected void onPreExecute() {
            mProgress.show();
        }

        /**
         * After executing display success to user
         * @param output output ArrayList to display success to user
         */
        @Override
        protected void onPostExecute(List<String> output) {
            mProgress.hide();
            if (output == null || output.size() == 0) {
                showToast("No results returned.");
            } else {
                //output.add(0, "Data retrieved using the Google Calendar API:");
                showToast("Your task was added to your Google Calendar:");
            }
        }

        /**
         * If the process is interrupted and cancelled by an error, tell the user in a pop-up
         */
        @Override
        protected void onCancelled() {
            mProgress.hide();
            if (mLastError != null) {
                if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
                    showGooglePlayServicesAvailabilityErrorDialog(
                            ((GooglePlayServicesAvailabilityIOException) mLastError)
                                    .getConnectionStatusCode());
                } else if (mLastError instanceof UserRecoverableAuthIOException) {
                    startActivityForResult(
                            ((UserRecoverableAuthIOException) mLastError).getIntent(),
                            MainActivity.REQUEST_AUTHORIZATION);
                } else {
                    showToast("The following error occurred:\n"
                            + mLastError.getMessage());
                }
            } else {
                showToast("Request cancelled.");
            }
        }
    }

}
