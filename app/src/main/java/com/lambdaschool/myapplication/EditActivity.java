package com.lambdaschool.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

public class EditActivity extends AppCompatActivity {

    private static final int IMAGE_REQUEST_CODE = 1;
    private static final int SWITCH_VALUE = 1;

    private ImageView imageView;
    private ImageEntry entry;
    private EditText imageNameEntry;
    private Switch watchedSwitch;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ActivityLifecycle", getLocalClassName() + "-onCreate");
        setContentView(R.layout.activity_edit);

        //createImageEntry();
        Intent intent = getIntent();
        entry = (ImageEntry) intent.getSerializableExtra(ImageEntry.TAG);
        if(entry == null){
            entry = new ImageEntry(ImageEntry.INVALID_ID);
        }

        imageView = findViewById(R.id.image_view);
        final Uri imageUri = entry.getImageUri();
        if(imageUri != null) {
            imageView.setImageURI(imageUri);
        }

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageSearchIntent = new Intent (Intent.ACTION_GET_CONTENT);
                imageSearchIntent.setType("image/*");
                startActivityForResult(imageSearchIntent, IMAGE_REQUEST_CODE);
            }
        });

        watchedSwitch = findViewById(R.id.watched_switch);
        watchedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //figure out how to do the setSwitchedValue(?)
                    entry.setSwitchValue(SWITCH_VALUE);


            }
        });

        imageNameEntry = findViewById(R.id.image_name_entry);
        imageNameEntry.setText(entry.getImageName());
        imageNameEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String nameEntryString = s.toString();
                entry.setImageName(nameEntryString);

            }
        });



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == IMAGE_REQUEST_CODE){
            if(data != null){
                Uri dataUri = data.getData();
                entry.setImageUri(dataUri);

                imageView.setImageURI(dataUri);

                // Intent sendUriIntent = new Intent();


            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent ();
        resultIntent.putExtra(ImageEntry.TAG, entry);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ActivityLifecycle", getLocalClassName() + "-onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ActivityLifecycle", getLocalClassName() + "-onResume");
    }
    //user interacting with app

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ActivityLifecycle", getLocalClassName() + "-onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ActivityLifecycle", getLocalClassName() + "-onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ActivityLifecycle", getLocalClassName() + "-onDestroy");
    }



}
