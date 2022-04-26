package edu.psu.ist.dynamicview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner spinnerDigit;
    Spinner spinnerLetter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerDigit = findViewById(R.id.spinnerDigit);
        ArrayAdapter<CharSequence> adapterDigit = ArrayAdapter.createFromResource(this,
                R.array.digits,
                android.R.layout.simple_spinner_item);
        adapterDigit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDigit.setAdapter(adapterDigit);

        spinnerLetter = findViewById(R.id.spinnerLetter);
        ArrayAdapter<CharSequence> adapterLetter = ArrayAdapter.createFromResource(this,
                R.array.letters,
                android.R.layout.simple_spinner_item);
        adapterLetter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLetter.setAdapter(adapterLetter);

        findViewById(R.id.buttonLetters).setOnClickListener(this);
        findViewById(R.id.buttonDigit).setOnClickListener(this);
        findViewById(R.id.buttonRemove).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int eventSourceId = view.getId();
        if (eventSourceId == R.id.buttonLetters) {
            removeAllSpinners();
            spinnerLetter.setVisibility(View.VISIBLE);
            setLabel(getString(R.string.label_letters));
        } else if (eventSourceId == R.id.buttonDigit) {
            removeAllSpinners();
            spinnerDigit.setVisibility(View.VISIBLE);
            setLabel(getString(R.string.label_digit));
        } else if (eventSourceId == R.id.buttonRemove) {
            removeAllSpinners();
        }
    }

    void setLabel(String label) {
        TextView view = findViewById(R.id.textViewSpinnerLabel);
        view.setText(label);
    }

    void removeAllSpinners() {
        spinnerLetter.setVisibility(View.INVISIBLE);
        spinnerDigit.setVisibility(View.INVISIBLE);
        setLabel("");
    }


}