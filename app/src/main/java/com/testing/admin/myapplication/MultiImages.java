package com.testing.admin.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by admin on 11/3/2016.
 */
public class MultiImages extends AppCompatActivity {

    //private ImageView img1,img2,img3,img4;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multipleimages);
        final TextView tv = (TextView) findViewById(R.id.tv);
        final Button btn = (Button) findViewById(R.id.btn);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.pb);
//        img1=(ImageView) findViewById(R.id.imgView1);
//        img2=(ImageView) findViewById(R.id.imgView2);
//        img3=(ImageView) findViewById(R.id.imgView3);
//        img4=(ImageView) findViewById(R.id.imgView4);
//        File file1 = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
//        Uri uri1 = Uri.fromFile(file1);
//        img1.setImageURI(uri1);
//        File file2 = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
//        Uri uri2 = Uri.fromFile(file2);
//        img1.setImageURI(uri2);
//        File file3 = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
//        Uri uri3 = Uri.fromFile(file3);
//        img1.setImageURI(uri3);
//        File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
//        Uri uri = Uri.fromFile(file);
//        img1.setImageURI(uri);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the progress status zero on each button click
                progressStatus = 0;

                /*
                    A Thread is a concurrent unit of execution. It has its own call stack for
                    methods being invoked, their arguments and local variables. Each application
                    has at least one thread running when it is started, the main thread,
                    in the main ThreadGroup. The runtime keeps its own threads
                    in the system thread group.
                */
                // Start the lengthy operation in a background thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(progressStatus < 100){
                            // Update the progress status
                            progressStatus +=1;

                            // Try to sleep the thread for 20 milliseconds
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }

                            // Update the progress bar
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    pb.setProgress(progressStatus);
                                    // Show the progress on TextView
                                    tv.setText(progressStatus+"");
                                }
                            });
                        }
                    }
                }).start(); // Start the operation
            }
        });
    }
}
