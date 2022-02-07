package edu.psu.ist.hcdd340.pennstateid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "ACTIVITY_6";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_activity_6), MODE_PRIVATE);

        Button button = findViewById(R.id.button_next);
        button.setOnClickListener(this);

        button = findViewById(R.id.button_details);
        button.setOnClickListener(this);

        SwitchCompat switchInOffice = findViewById(R.id.switch_in_office);
        switchInOffice.setOnCheckedChangeListener(this);

        showInOfficeStatus(getCurrentProfileId());
    }

    @Override
    public void onClick(View view) {
        int eventSourceId = view.getId();
        Log.d(TAG, String.format("event source id: %s", eventSourceId));

        if (eventSourceId == R.id.button_next) {
            handleNextButtonClick();
        } else if (eventSourceId == R.id.button_details) {
            handleDetailsButtonClick();
        } else {
            Log.d(TAG, String.format("Unknown click event source: %s", eventSourceId));
        }

    }

    /**
     * Handle the click event for the "Next" Button
     */
    private void handleNextButtonClick() {
        // what's the current profile now?
        String currentProfileId = getCurrentProfileId();
        Log.d(TAG, String.format("Current profile id: %s", currentProfileId));

        // let's compare with the ID assigned to the president
        // we will have to convert the ID (an int) to the assigned String value
        String presidentId = this.getString(R.string.president_id_number);

        if (presidentId.equals(currentProfileId)) {
            // currently the president's profile is being shown
            // we will replace it with the dean's profile
            showProfile(R.drawable.ist_dean,
                    this.getString(R.string.ist_dean_first_name),
                    this.getString(R.string.ist_dean_last_name),
                    this.getString(R.string.ist_dean_machine_id),
                    this.getString(R.string.ist_dean_id_number),
                    this.getString(R.string.ist_dean_position_description));
        } else {
            // currently the dean's profile is being shown
            // we will replace it with the president's profile
            showProfile(R.drawable.psu_president,
                    this.getString(R.string.president_first_name),
                    this.getString(R.string.president_last_name),
                    this.getString(R.string.president_machine_id),
                    this.getString(R.string.president_id_number),
                    this.getString(R.string.president_position_description));
        }
    }

    /**
     * Handle the click event for the "Details" Button
     */
    private void handleDetailsButtonClick() {

    }

    private void showProfile(int profileImageId, String firstName, String lastName, String machineId, String idNumber, String positionDescription) {
        // update profile image
        ShapeableImageView idHolderImage = findViewById(R.id.id_holder_image);
        idHolderImage.setImageResource(profileImageId);

        // first name
        TextView label = findViewById(R.id.first_name);
        label.setText(firstName);

        label = findViewById(R.id.last_name);
        label.setText(lastName);

        label = findViewById(R.id.machine_id);
        label.setText(machineId);

        label = findViewById(R.id.id_number);
        label.setText(idNumber);

        label = findViewById(R.id.position_description);
        label.setText(positionDescription);

        // we will have to update the in office status
        showInOfficeStatus(idNumber);
    }

    /**
     * Get current profile Id shown on the app
     */
    private String getCurrentProfileId() {
        TextView idNumberTextView = findViewById(R.id.id_number);
        // what's the id number that is currently being displayed?
        return idNumberTextView.getText().toString();
    }

    /**
     * Store office status for a given profile
     */
    private void storeInOfficeStatus(String profileId, boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(profileId, status);
        editor.apply();
    }

    /**
     * Retrieves in-office status for a given profile. Default is false.
     */
    private boolean getInOfficeStatus(String profileId) {
        final boolean defaultState = false;
        return sharedPreferences.getBoolean(profileId, defaultState);
    }

    /**
     * Show the in-office status in app
     */
    private void showInOfficeStatus(String profileId) {
        // retrieve the status from shared preferences
        boolean status = getInOfficeStatus(profileId);
        SwitchCompat inOfficeSwitch = findViewById(R.id.switch_in_office);
        inOfficeSwitch.setChecked(status);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        // we will store the updated status for the current profile
        String currentProfileId = getCurrentProfileId();

        Log.d(TAG, String.format("Current profile id: %s. Status: %s", currentProfileId, b));

        storeInOfficeStatus(currentProfileId, b);
    }
}