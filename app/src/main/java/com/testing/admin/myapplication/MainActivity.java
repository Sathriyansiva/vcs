package com.testing.admin.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(MainActivity.this, "can u see me", Toast.LENGTH_SHORT).show();
//        Log.i("info","Done creating the app");
    }

//    public void sampleClick(View v)
//    {
//        //call(v);
//        Intent intention = new Intent(v.getContext(),testing1.class);
//        startActivity(intention);
//    }
//
//    private void call(View v)
//    {
//        mEdit   = (EditText)findViewById(R.id.text2);
//        //Toast.makeText(MainActivity.this, mEdit.getText().toString(), Toast.LENGTH_SHORT).show();
//        //Log.i("info","button is clicked");
//        //Log.v("EditText", mEdit.getText().toString());
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setMessage(mEdit.getText().toString());
//
//        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface arg0, int arg1) {
//                //Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
//            }
//        });
//
//        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }
}
