package com.testing.admin.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by admin on 11/11/2016.
 */
public class GalleryFullImageActivity extends Activity {

    private static int IMG_RESULT = 1;
    String ImageDecode;
    ImageView imageViewLoad;
    Button LoadImage;
    Intent intent;
    String[] FILE;

    private static int RESULT_LOAD = 1;
    String img_Decodable_Str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_full_image);

        imageViewLoad = (ImageView) findViewById(R.id.imageView1);
        LoadImage = (Button)findViewById(R.id.button1);

        LoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

// 1               intent = new Intent(Intent.ACTION_PICK,
// 1                       android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
// 1               startActivityForResult(intent, IMG_RESULT);

//                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                // Start new activity with the LOAD_IMAGE_RESULTS to handle back the results when image is picked from the Image Gallery.
//                startActivityForResult(i, IMG_RESULT);

                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(
                        Intent.createChooser(intent, "Select File"),
                        IMG_RESULT);

            }
        });
    }


//    public void loadImagefromGallery(View view) {
//        // Create intent to Open Image applications like Gallery, Google Photos
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        // Start the Intent
//        startActivityForResult(galleryIntent, RESULT_LOAD);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        try {
//            // When an Image is picked
//            if (requestCode == RESULT_LOAD && resultCode == RESULT_OK
//                    && null != data) {
//                // Get the Image from data
//
//                Uri selectedImage = data.getData();
//                String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//                // Get the cursor
//                Cursor cursor = getContentResolver().query(selectedImage,
//                        filePathColumn, null, null, null);
//                // Move to first row
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                img_Decodable_Str = cursor.getString(columnIndex);
//                cursor.close();
//                ImageView imgView = (ImageView) findViewById(R.id.imgView1);
//                // Set the Image in ImageView after decoding the String
//                imgView.setImageBitmap(BitmapFactory
//                        .decodeFile(img_Decodable_Str));
//
//            } else {
//                Toast.makeText(this, "Hey pick your image first",
//                        Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Toast.makeText(this, "Something went embrassing", Toast.LENGTH_LONG)
//                    .show();
//        }
//
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_RESULT) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri.toString().contains("file:"))
                selectedImageUri = getImageContentUri(getApplicationContext(), new File(selectedImageUri.getPath()));
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImageUri, projection, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();

            String selectedImagePath = cursor.getString(column_index);

            if (selectedImagePath.contains("http://") || selectedImagePath.contains("https://")) {
                AlertDialog.Builder alt_bld = new AlertDialog.Builder(GalleryFullImageActivity.this);
                alt_bld.setMessage("Don't select Web images. Invalid image.")
                        .setCancelable(true);
                AlertDialog alert = alt_bld.create();
                alert.setButton(-1, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setTitle("Error");
                alert.show();
                return;
            }


            Bitmap bm;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(selectedImagePath, options);
            final int REQUIRED_SIZE = 200;
            int scale = 1;
            while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                    && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;
            bm = BitmapFactory.decodeFile(selectedImagePath, options);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            imageViewLoad.setImageBitmap(bm);
        }
    }

    private Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Images.Media._ID },
                MediaStore.Images.Media.DATA + "=? ",
                new String[] { filePath }, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        try {
//
//            if (requestCode == IMG_RESULT && resultCode == RESULT_OK
//                    && null != data) {
//
//
//                Uri URI = data.getData();
//                String[] FILE = { MediaStore.Images.Media.DATA };
//
//
//                Cursor cursor = getContentResolver().query(URI,
//                        FILE, null, null, null);
//
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(FILE[0]);
//                ImageDecode = cursor.getString(columnIndex);
//                cursor.close();
//
//                imageViewLoad.setImageBitmap(BitmapFactory
//                        .decodeFile(ImageDecode));
//
//            }
//        } catch (Exception e) {
//            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
//                    .show();
//        }
//
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Here we need to check if the activity that was triggers was the Image Gallery.
//        // If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
//        // If the resultCode is RESULT_OK and there is some data we know that an image was picked.
//        if (requestCode == IMG_RESULT && resultCode == RESULT_OK && data != null) {
//            // Let's read picked image data - its URI
//            Uri pickedImage = data.getData();
//            // Let's read picked image path using content resolver
//            String[] filePath = { MediaStore.Images.Media.DATA };
//            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
//            cursor.moveToFirst();
//            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
//
//            // Now we need to set the GUI ImageView data with data read from the picked file.
//            imageViewLoad.setImageBitmap(BitmapFactory.decodeFile(imagePath));
//
//            // At the end remember to close the cursor or you will end with the RuntimeException!
//            cursor.close();
//        }
//    }

}
