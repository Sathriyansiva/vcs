package com.testing.admin.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 10/18/2016.
 */
public class testing1 extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,Spinner.OnItemSelectedListener {

    private RadioGroup rg1;
    private EditText edit,edit2;
    private CheckBox yes,no;
    private Spinner spinner;
    private TextView tv;
    private ImageView imageUser;
//    private static final int CAMERA_REQUEST = 1888;
final int TAKE_PICTURE = 1;
    final int ACTIVITY_SELECT_IMAGE = 2;
    private TextView tvDisplayDate;
//    private DatePicker dpResult;
//    private Button btnChangeDate;

    private int year;
    private int month;
    private int day;
    private String pictureImagePath = "";
    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing);
// Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        rg1 = (RadioGroup) findViewById(R.id.radioSex);
        rg1.setOnCheckedChangeListener(this);
        edit = (EditText) findViewById(R.id.editText1);
        edit2 = (EditText) findViewById(R.id.editText2);
        actv(true);
        tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
        imageUser = (ImageView) findViewById(R.id.imgView);
//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//            Toast.makeText(this, "GPS is Enabled in your devide", Toast.LENGTH_SHORT).show();
//        }else{
////            showGPSDisabledAlertToUser();
//            turnGPSOn();
//        }

        if(isConnectingToInternet(testing1.this))
        {
//            Toast.makeText(getApplicationContext(),"internet is available",Toast.LENGTH_LONG).show();
            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Internet is Available");

            alertDialogBuilder.setNegativeButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            android.app.AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        else {
//            Toast.makeText(getApplicationContext(),"internet is Not available",Toast.LENGTH_LONG).show();
            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Internet is Not Available");

            alertDialogBuilder.setNegativeButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            android.app.AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        setCurrentDateOnView();
//        addListenerOnButton();
        String imei = getIMEI(testing1.this);
        Toast.makeText(getApplicationContext(),imei, Toast.LENGTH_SHORT).show();
    }

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
//                Intent intent = new Intent(this, SignUp.class);
//                this.startActivity(intent);
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

        }
        return true;

    }

    public static boolean isConnectingToInternet(Context context)
    {
        ConnectivityManager connectivity =
                (ConnectivityManager) context.getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
        }
        return false;
    }

//    private void turnGPSOn(){
////        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
//        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//        intent.putExtra("enabled", true);
//        sendBroadcast(intent);
//        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
//
//        if(!provider.contains("gps")){ //if gps is disabled
//            final Intent poke = new Intent();
//            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
//            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
//            poke.setData(Uri.parse("3"));
//            sendBroadcast(poke);
//        }
//    }

//    private void showGPSDisabledAlertToUser(){
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
//                .setCancelable(false)
//                .setPositiveButton("Goto Settings Page To Enable GPS",
//                        new DialogInterface.OnClickListener(){
//                            public void onClick(DialogInterface dialog, int id){
//                                Intent callGPSSettingIntent = new Intent(
//                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                                startActivity(callGPSSettingIntent);
//                            }
//                        });
//        alertDialogBuilder.setNegativeButton("Cancel",
//                new DialogInterface.OnClickListener(){
//                    public void onClick(DialogInterface dialog, int id){
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog alert = alertDialogBuilder.create();
//        alert.show();
//    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        switch (checkedId)
        {
            case R.id.radioMale:
                actv(true);
                break;

            case R.id.radioFemale:
                actv(false);
                spinner.setSelection(0);
                edit.setText("");
                edit2.setText("");
                break;
        }
    }

    private void actv(final boolean active)
    {
        edit.setEnabled(active);
        edit2.setEnabled(active);
        spinner.setEnabled(active);
//        if (active)
//        {
//            edit.requestFocus();
//        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {

    }

//    public  void displayButton(View v)
//    {
//        RadioGroup radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
//        int selectedId = radioSexGroup.getCheckedRadioButtonId();
//        RadioButton radioSexButton = (RadioButton) findViewById(selectedId);
//        Toast.makeText(testing1.this,radioSexButton.getText(), Toast.LENGTH_SHORT).show();
//    }

    public void removeerr(View v)
    {
        EditText etUserName = (EditText) findViewById(R.id.editText1);
        etUserName.setError(null);
    }

    public void mergeclick(View v)
    {
        Intent intention = new Intent(v.getContext(),MergeImageActivity.class);
        startActivity(intention);
    }

    public void mailsend(View v)
    {
        Intent intention = new Intent(v.getContext(),MailSenderActivity.class);
        startActivity(intention);
    }

    public void DBclick(View v)
    {
        Intent intention = new Intent(v.getContext(),DataBaseActivity.class);
        startActivity(intention);
    }

    public void BackButton(View v)
    {
        Intent intention = new Intent(v.getContext(),SignUp.class);
        startActivity(intention);
//        EditText etUserName = (EditText) findViewById(R.id.editText1);
//        String strUserName = etUserName.getText().toString();
//
//        if(TextUtils.isEmpty(strUserName)) {
//            etUserName.setError("Please Enter Name");
//            return;
//        }
    }

//    public void loadImagefromGallery(View view) {
//        Intent intent = new Intent(Intent.ACTION_PICK,
//                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//        intent.setType("image/*");
//        intent.putExtra("crop", "true");
//        intent.putExtra("scale", true);
//        intent.putExtra("outputX", 256);
//        intent.putExtra("outputY", 256);
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, 1);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != RESULT_OK) {
//            return;
//        }
//        if (requestCode == 1) {
//            final Bundle extras = data.getExtras();
//            if (extras != null) {
//                //Get image
//                Bitmap ProfilePic = extras.getParcelable("data");
//                imageUser.setImageBitmap(ProfilePic);
//            }
//        }
//    }

    public String getIMEI(Activity activity) {
        TelephonyManager telephonyManager = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
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
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    Uri imageFileUri= Uri.parse("file:///sdcard/picture44.jpg");
//                    cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String imageFileName =  "copy-"+ timeStamp + ".jpg";
//                    File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/palani4208");
                    if (!storageDir.exists()) {
                        File wallpaperDirectory = new File("/sdcard/palani4208/");
                        wallpaperDirectory.mkdirs();
                    }
                    pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
//                    Toast.makeText(getApplicationContext(),pictureImagePath,Toast.LENGTH_LONG).show();
                    File file = new File(pictureImagePath);
                    Uri outputFileUri = Uri.fromFile(file);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                    startActivityForResult(cameraIntent, TAKE_PICTURE);
                }
                else if(options[which].equals("Choose from Gallery"))
                {
//                    Intent intent=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(intent, ACTIVITY_SELECT_IMAGE);
                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
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
                else if(options[which].equals("Cancel"))
                {
                    dialog.dismiss();
                }

            }
        });
        builder.show();
    }

    public void onActivityResult(int requestcode,int resultcode,Intent intent)
    {
        super.onActivityResult(requestcode, resultcode, intent);
        if(resultcode==RESULT_OK)
        {
            if(requestcode==TAKE_PICTURE)
            {
                // for safety reasons you can use thumbnail if not retrieved full sized image.
                // Below bitmap only get small size image.
                // Bitmap photo = (Bitmap) intent.getExtras().get("data");
                try {
                    File imgFile = new File(pictureImagePath);
                    if (imgFile.exists()) {
                        Bitmap photo = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                    Bitmap photo = (Bitmap) intent.getExtras().get("data");
                        imageUser.setImageBitmap(photo);
                        //                method 1 ......
                        int width = photo.getWidth();
                        int height = photo.getHeight();
                        Bitmap resizedbitmap = null;
                        if ((width > 780) && (height > 1052)) {
                            resizedbitmap = Bitmap.createScaledBitmap(photo, 780, 1052, true);
                        } else if ((width > 780) && (height < 1052)) {
                            resizedbitmap = Bitmap.createScaledBitmap(photo, 780, height, true);
                        } else if ((width < 780) && (height > 1052)) {
                            resizedbitmap = Bitmap.createScaledBitmap(photo, width, 1052, true);
                        } else {
                            resizedbitmap = Bitmap.createScaledBitmap(photo, width, height, true);
                        }
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        String imageFileName = timeStamp + ".jpg";
                        createDirectoryAndSaveFile(resizedbitmap, imageFileName);
                        File file = new File(pictureImagePath);
                        file.delete();
//                Toast.makeText(getApplicationContext(),sd.getParent().toString(),Toast.LENGTH_LONG).show();
//                method 2 ......
//                String state = Environment.getExternalStorageState();
//                if (Environment.MEDIA_MOUNTED.equals(state)) {
//                    File sd = Environment.getExternalStorageDirectory();
//                    File dest = new File(sd.getAbsolutePath(), "palani.jpg");
//                    try {
//                        FileOutputStream out = new FileOutputStream(dest);
//                        photo.compress(Bitmap.CompressFormat.PNG, 90, out);
//                        out.flush();
//                        out.close();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        Toast.makeText(getApplicationContext(),"error....",Toast.LENGTH_LONG).show();
//                    }
//                }
//                        Toast.makeText(getApplicationContext(), "saved successfully", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"error...",Toast.LENGTH_LONG).show();
                }
            }
            else if(requestcode==1)
            {
//                Uri selectedImage = intent.getData();
//                String[] filePath = { MediaStore.Images.Media.DATA };
//                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
//                c.moveToFirst();
//                int columnIndex = c.getColumnIndex(filePath[0]);
//                String picturePath = c.getString(columnIndex);
//                c.close();
//                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
//                imageUser.setImageBitmap(thumbnail);
                final Bundle extras = intent.getExtras();
                if (extras != null) {
                    //Get image
                    Bitmap ProfilePic = extras.getParcelable("data");
                    imageUser.setImageBitmap(ProfilePic);
                }
            }
        }
    }

    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        File direct = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/palani4208");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/palani4208/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/palani4208/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        //overridePendingTransition(R.anim.slide_down,0);
    }


//    public void camerabutton(View view) {
//        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(cameraIntent, CAMERA_REQUEST);
//    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            imageView.setImageBitmap(photo);
//        }
//    }

//    public void checkbutton1(View v)
//    {
//        yes = (CheckBox) findViewById(R.id.checkbox1);
//        no = (CheckBox) findViewById(R.id.checkbox2);
//        if(yes.isChecked() == true)
//        {
//            no.setChecked(false);
//            yes.setChecked(true);
//        }
//    }
//
//    public void checkbutton2(View v)
//    {
//        yes = (CheckBox) findViewById(R.id.checkbox1);
//        no = (CheckBox) findViewById(R.id.checkbox2);
//        if(no.isChecked() == true)
//        {
//            yes.setChecked(false);
//            no.setChecked(true);
//        }
//    }

    // display current date
    public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
//        dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(day).append("-").append(month + 1).append("-")
                .append(year).append(" "));

        // set current date into datepicker
//        dpResult.init(year, month, day, null);
    }

//    public void addListenerOnButton() {
//
//        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
//
//        btnChangeDate.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                showDialog(DATE_DIALOG_ID);
//
//            }
//
//        });
//
//    }

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

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(day)
                    .append("-").append(month + 1).append("-").append(year)
                    .append(" "));

            // set selected date into datepicker also
//            dpResult.init(year, month, day, null);
        }
    };
}
