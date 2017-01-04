package com.testing.admin.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by admin on 10/26/2016.Spinner.OnItemSelectedListener
 */
public class SignUp extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private Spinner spinner;
    private int year;
    private int month;
    private int day;
    final int TAKE_PICTURE = 1;
    private ImageView imageUser;
    private EditText tvDisplayDate;
    static final int DATE_DIALOG_ID = 999;
    EditText imeitxt;
    private boolean hasImage;
//    static String item;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
//        spinner = (Spinner) findViewById(R.id.spinner);
        imeitxt = (EditText) findViewById(R.id.editText7);
        imageUser = (ImageView) findViewById(R.id.imgView);
        // Spinner click listener
//        spinner.setOnItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        // Spinner Drop down elements
//        List<String> categories = new ArrayList<String>();
//        categories.add("Select");
//        categories.add("B.SC");
//        categories.add("B.E");
//        categories.add("B.B.A");
//        categories.add("M.B.A");
//        categories.add("B.Com");
//        categories.add("M.Com");
//
//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        spinner.setAdapter(dataAdapter);
        setCurrentDateOnView();
        String imei = getIMEI(SignUp.this);
        imeitxt.setText(imei);
    }

    public String getIMEI(Activity activity) {
        TelephonyManager telephonyManager = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////        item = parent.getItemAtPosition(position).toString();
//    }
//
//    public void onNothingSelected(AdapterView<?> arg0) {
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.phone:
                Toast.makeText(getBaseContext(), "You selected Phone", Toast.LENGTH_SHORT).show();
                break;

            case R.id.computer:
                Toast.makeText(getBaseContext(), "You selected Computer", Toast.LENGTH_SHORT).show();
                break;

            case R.id.gamepad:
                Toast.makeText(getBaseContext(), "You selected Gamepad", Toast.LENGTH_SHORT).show();
                break;

            case R.id.camera:
                Toast.makeText(getBaseContext(), "You selected Camera", Toast.LENGTH_SHORT).show();
                break;

            case R.id.video:
                Toast.makeText(getBaseContext(), "You selected Video", Toast.LENGTH_SHORT).show();
                break;

            case R.id.email:
                Toast.makeText(getBaseContext(), "You selected EMail", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:

//                Toast.makeText(getApplicationContext(), "Home Clicked",
//                        Toast.LENGTH_LONG).show();

                // go to previous activity
                onBackPressed();
                return true;
        }
        return true;

    }

    public void VoiceRecognition(View v)
    {
        Intent intention = new Intent(v.getContext(),VoiceRecognitionActivity.class);
        startActivity(intention);
    }

    public void FullImage(View v)
    {
        Intent intention = new Intent(v.getContext(),GalleryFullImageActivity.class);
        startActivity(intention);
    }

    public void MultiImage(View v)
    {
        Intent intention = new Intent(v.getContext(),GalleryMultiImageActivity.class);
        startActivity(intention);
    }

    public void imagezooming(View v)
    {
        Intent intention = new Intent(v.getContext(),MultiTouchActivity.class);
        startActivity(intention);
    }

    public void HtmlPage1(View v)
    {
//        WebView web = new WebView();
//        WebView web = (WebView) findViewById(R.id.webView);
//        web.loadUrl("file:///android_asset/sample.html");
        Intent intention = new Intent(v.getContext(),PaymentActivity.class);
        startActivity(intention);
    }

    public void HtmlPage2(View v)
    {
//        WebView web = new WebView();
//        WebView web = (WebView) findViewById(R.id.webView);
//        web.loadUrl("file:///android_asset/sample.html");
        Intent intention = new Intent(v.getContext(),Payment2TestActivity.class);
        startActivity(intention);
    }

    public void HtmlPage3(View v)
    {
        Intent intention = new Intent(v.getContext(),Payment3TestActivity.class);
        startActivity(intention);
    }

    public void HtmlPage4(View v)
    {
        Intent intention = new Intent(v.getContext(),Payment4TestActivity.class);
        startActivity(intention);
    }

    public void ScrollMenu(View v)
    {
        Intent intention = new Intent(v.getContext(),ScrollMenuActivity.class);
        startActivity(intention);
    }

    public void SpeechText(View v)
    {
        Intent intention = new Intent(v.getContext(),SpeechTextActivity.class);
        startActivity(intention);
    }

    public void NextButton(View v)
    {
        Intent intention = new Intent(v.getContext(),MultiImages.class);
        startActivity(intention);
    }

    public void signclick(View v)
    {
        Intent intention = new Intent(v.getContext(),CaptureSignatureActivity.class);
        startActivity(intention);
    }

    public void razorpayclick(View v)
    {
        Intent intention = new Intent(v.getContext(),RazorPaymentActivity.class);
        startActivity(intention);
    }

    public void BackButton(View v)
    {
        EditText ed1 = (EditText) findViewById(R.id.editText1);
        String str1 = ed1.getText().toString();
        EditText ed2 = (EditText) findViewById(R.id.editText2);
        String str2 = ed2.getText().toString();
        EditText ed3 = (EditText) findViewById(R.id.editText3);
        String str3 = ed3.getText().toString();
        EditText ed4 = (EditText) findViewById(R.id.editText4);
        String str4 = ed4.getText().toString();
//        String str6 = item;
//        ImageView img = (ImageView) findViewById(R.id.imgView);
//        Drawable drawable = img.getDrawable();
//        boolean hasImage = (drawable != null);

        if(TextUtils.isEmpty(str1)) {
            ed1.setError(Html.fromHtml("<font color='blue'>Please Enter Name</font>"));
            return;
        }
        if(TextUtils.isEmpty(str2)) {
            ed2.setError("Please Enter Father Name");
            return;
        }
        if(TextUtils.isEmpty(str3)) {
            ed3.setError("Please Enter Mobile No");
            return;
        }
        if(TextUtils.isEmpty(str4)) {
            ed4.setError("Please Enter Address");
            return;
        }
//        if(str6 == "Select") {
//            ((TextView)spinner.getSelectedView()).setError("Please Select Qualification");
//        }
        if(hasImage != true) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Please Select Image");

            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    //Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                }
            });

//            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    public void removeerr1(View v)
    {
        EditText ed1 = (EditText) findViewById(R.id.editText1);
        ed1.setError(null);
    }

    public void removeerr2(View v)
    {
        EditText ed1 = (EditText) findViewById(R.id.editText2);
        ed1.setError(null);
    }

    public void removeerr3(View v)
    {
        EditText ed1 = (EditText) findViewById(R.id.editText3);
        ed1.setError(null);
    }

    public void removeerr4(View v)
    {
        EditText ed1 = (EditText) findViewById(R.id.editText4);
        ed1.setError(null);
    }

    public void loadImagefromGallery(View view) {
        selectImage();
    }

    public void selectImage()
    {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(options,new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if(options[which].equals("Take Photo"))
                {
                    imagecapture(1);
                }
                else if(options[which].equals("Choose from Gallery"))
                {
//                    Intent intent=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(intent, ACTIVITY_SELECT_IMAGE);
                    imagecapture(2);
                }
                else if(options[which].equals("Cancel"))
                {
                    dialog.dismiss();
                }

            }
        });
        builder.show();
    }

    public void imagecapture(int value)
    {
        if(value == 1)
        {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, TAKE_PICTURE);
        }
        else
        {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            intent.setType("image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("scale", true);
            intent.putExtra("outputX", 256);
            intent.putExtra("outputY", 256);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, 1);
        }
        hasImage = true;
    }

    public void onActivityResult(int requestcode,int resultcode,Intent intent)
    {
        super.onActivityResult(requestcode, resultcode, intent);
        if(resultcode==RESULT_OK)
        {
            if(requestcode==TAKE_PICTURE)
            {
                Bitmap photo = (Bitmap)intent.getExtras().get("data");
                imageUser.setImageBitmap(photo);
            }
            else if(requestcode==1)
            {
                final Bundle extras = intent.getExtras();
                if (extras != null) {
                    Bitmap ProfilePic = extras.getParcelable("data");
                    imageUser.setImageBitmap(ProfilePic);
                }
            }
        }
    }

    public void setCurrentDateOnView() {

        tvDisplayDate = (EditText) findViewById(R.id.editText6);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(day).append("-").append(month + 1).append("-")
                .append(year).append(" "));
    }

    public void datebtn(View view)
    {
        showDialog(DATE_DIALOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            tvDisplayDate.setText(new StringBuilder().append(day)
                    .append("-").append(month + 1).append("-").append(year)
                    .append(" "));
        }
    };
}
