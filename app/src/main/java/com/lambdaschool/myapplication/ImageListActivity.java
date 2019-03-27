package com.lambdaschool.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageListActivity extends AppCompatActivity {
    public static final int NEW_ENTRY_REQUEST = 2;
    private Button addButton;
    Context context;
    static int nextId = 0;

    ArrayList<ImageEntry> entryList;
    LinearLayout listLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ActivityLifecycle", getLocalClassName() + " -onCreate");
        setContentView(R.layout.activity_image_list);

        context = this;
        listLayout = findViewById(R.id.list_view);

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                ImageEntry entry = createImageEntry();
                intent.putExtra(ImageEntry.TAG, entry);
                startActivityForResult(intent, NEW_ENTRY_REQUEST);
            }
        });


        entryList = new ArrayList<>();
       // addTestEntries();


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
        listLayout.removeAllViews();
        for(ImageEntry entry: entryList){
            listLayout.addView(createEntryView(entry));
        }
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

    private ImageEntry createImageEntry(){
        ImageEntry entry = new ImageEntry(nextId++);
        return entry;
    }

    private ImageEntry createImageEntry(String name){
        ImageEntry entry = createImageEntry();
        entry.setImageName(name);

       // entryList.add(entry);
        return entry;

    }

    private TextView createEntryView(final ImageEntry entry){

        TextView view = new TextView(context);
        view.setText(entry.getImageName());
        view.setPadding(15,15,15,15);
        view.setTextSize(22);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewEditIntent = new Intent(context,EditActivity.class);
                viewEditIntent.putExtra(ImageEntry.TAG, entry);
                startActivity(viewEditIntent);
            }
        });
        return view;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == NEW_ENTRY_REQUEST){
            if(data != null){
                ImageEntry entry = (ImageEntry) data.getSerializableExtra(ImageEntry.TAG);


                entryList.add(entry);

            }
        }
    }

    /*private void addTestEntries(){
        entryList.add(createImageEntry("BLAH BLAH BLAH"));
    }*/

    }


