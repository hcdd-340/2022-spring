package edu.psu.ist.hcdd340.pennstateid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowProfileDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ACTIVITY_9_DETAILS";
    private static final String CURRENT_COUNT_KEY = "CURRENT_COUNT_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile_details);

        findViewById(R.id.button_details_ok).setOnClickListener(this);
        findViewById(R.id.button_details_cancel).setOnClickListener(this);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_SHOW_MESSAGE_KEY);
        boolean inOffice = intent.getBooleanExtra(MainActivity.EXTRA_IN_OFFICE_STATE_KEY, false);

        TextView textView = findViewById(R.id.details_profile_name);
        textView.setText(message);

        CheckBox checkBox = findViewById(R.id.details_in_office_checkbox);
        checkBox.setChecked(inOffice);

        findViewById(R.id.button_details_decrease).setOnClickListener(this);
        findViewById(R.id.button_details_increase).setOnClickListener(this);

        if (savedInstanceState != null) {
            int currentCount = savedInstanceState.getInt(CURRENT_COUNT_KEY);
            setCurrentCount(currentCount);
        }

        Log.d(TAG, "On Create");
    }

    @Override
    public void onClick(View view) {
        int eventSourceId = view.getId();

        if (eventSourceId == R.id.button_details_ok) {
            CheckBox checkBox = findViewById(R.id.details_in_office_checkbox);
            boolean inOfficeStatus = checkBox.isChecked();
            Intent returnIntent = new Intent();
            returnIntent.putExtra(MainActivity.EXTRA_RETURN_IN_OFFICE, inOfficeStatus);
            setResult(RESULT_OK, returnIntent);
            finish();
        } else if (eventSourceId == R.id.button_details_cancel) {
            setResult(RESULT_CANCELED);
            finish();
        }
        else if (eventSourceId == R.id.button_details_increase) {
            increaseCount();
        } else if (eventSourceId == R.id.button_details_decrease) {
            decreaseCount();
        } else
            Log.d(TAG, String.format("Unknown click event source: %s", eventSourceId));
    }

    private int getCurrentCount() {
        TextView textView = findViewById(R.id.textViewCount);
        return Integer.parseInt(textView.getText().toString());
    }

    private void setCurrentCount(int count) {
        TextView textView = findViewById(R.id.textViewCount);
        textView.setText(String.format("%s", count));
    }

    private void increaseCount() {
        int nextCount = getCurrentCount() + 1;
        setCurrentCount(nextCount);
    }

    private void decreaseCount() {
        int nextCount = getCurrentCount() - 1;
        setCurrentCount(nextCount);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "On Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "On Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "On Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "On Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "On Destroy");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(CURRENT_COUNT_KEY, getCurrentCount());

        Log.d(TAG, "On Save Instance");
    }
}