package com.testing.admin.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by admin on 10/25/2016.
 */
public class MainActivity2 extends AppCompatActivity {

//    EditText ed1,ed2,ed3,ed4;
    GridView gridView;
//    String[] web = { } ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        new SendPostRequest().execute();
//        ed1= (EditText) findViewById(R.id.editText1);
//        ed2= (EditText) findViewById(R.id.editText2);
//        ed3= (EditText) findViewById(R.id.editText3);
//        ed4= (EditText) findViewById(R.id.editText4);
//        CustomGrid adapter = new CustomGrid(MainActivity2.this, web);
//        gridView = (GridView) findViewById(R.id.gridView1);
//        gridView.setAdapter(adapter);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(
//                        getApplicationContext(),
//                        ((TextView) v.findViewById(R.id.grid_item_label))
//                                .getText(), Toast.LENGTH_SHORT).show();
//
//            }
//        });

        Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),  R.drawable.tree);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 100, bao);
        byte [] ba = bao.toByteArray();
        String ba1=Base64.encodeToString(ba, Base64.DEFAULT);
//        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
//        alertDialogBuilder.setMessage(ba1);
//
//        alertDialogBuilder.setNegativeButton("OK",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//
//        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
    }

    public class SendPostRequest extends AsyncTask<String, Void, String> {
        protected void onPreExecute(){}
        protected String doInBackground(String... arg0) {
            try {
                URL url = new URL("http://122.165.112.126:8090/api/login"); // here is your URL path
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("username", "bala5");
                postDataParams.put("password", "123");
                postDataParams.put("imei", "865980024845775");
                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();
                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

//        public void BackButton(View v) {
//            Intent intention = new Intent(v.getContext(), SignUp.class);
//            startActivity(intention);
//        }

        @Override
        protected void onPostExecute(String result) {
//            Toast.makeText(getApplicationContext(), result,
//                    Toast.LENGTH_LONG).show();
            try {
                JSONObject reader = new JSONObject(result);
//                ed1.setText(reader.getString("branchid"));
//                ed2.setText(reader.getString("locationid"));
//                ed3.setText(reader.getString("teamleaderid"));
//                ed4.setText(reader.getString("userid"));
                String[] web1 = result.split(",");
                CustomGrid adapter = new CustomGrid(MainActivity2.this, web1);
                gridView = (GridView) findViewById(R.id.gridView1);
                gridView.setAdapter(adapter);
            }
            catch (Exception e)
            {

            }
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while(itr.hasNext()){
            String key= itr.next();
            Object value = params.get(key);
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }

    public void checkclick(View view)
    {
//        CheckBox ck = (CheckBox) findViewById(R.id.grid_item_label);
////        Toast.makeText(MainActivity2.this,ck.getText(), Toast.LENGTH_LONG).show();
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setMessage(ck.getText().toString());
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
        Intent intention = new Intent(view.getContext(), SignUp.class);
        startActivity(intention);
    }
}
