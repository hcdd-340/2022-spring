package edu.psu.ist.hcdd340.pennstateid;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = "ACTIVITY_REGISTER";
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = findViewById(R.id.spinner_college);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.penn_state_colleges,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        findViewById(R.id.button_register).setOnClickListener(this);
        findViewById(R.id.button_cancel).setOnClickListener(this);
        findViewById(R.id.button_add_image).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int eventSourceId = view.getId();
        if (eventSourceId == R.id.button_cancel) {
            confirmRegisterCancel();
        } else if (eventSourceId == R.id.button_register) {
            String selectedItem = (String) spinner.getSelectedItem();
            Log.d(TAG, "Register button clicked with: " + selectedItem);
        } else if (eventSourceId == R.id.button_add_image) {
            handleAddImage();
        }
    }

    private void confirmRegisterCancel() {
        AlertDialog.Builder confirmCancelDialog = new AlertDialog.Builder(this);
        confirmCancelDialog.setTitle("Discard Information?");
        confirmCancelDialog.setMessage(R.string.register_cancel_confirm);


        confirmCancelDialog.show();
    }

    ActivityResultLauncher<Intent> mCaptureImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int resultCode = result.getResultCode();
                    if (resultCode == RESULT_OK) {
                        assert result.getData() != null;
                        Bitmap imageBitmap = (Bitmap) result.getData().getExtras().get("data");
                        ImageView imageFromCamera = findViewById(R.id.imageFromCamera);
                        imageFromCamera.setImageBitmap(imageBitmap);

                    } else if (resultCode == RESULT_CANCELED) {
                        Log.d(TAG, "Canceled without taking an image");
                    } else {
                        Log.d(TAG, String.format("Unknown return code from the Camera App: %s", resultCode));
                    }
                }
            }
    );

    private void handleAddImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mCaptureImage.launch(takePictureIntent);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String currentItem = (String) adapterView.getItemAtPosition(i);
        Log.d(TAG, "Currently selected item: " + currentItem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d(TAG, "No item selected");
    }
}