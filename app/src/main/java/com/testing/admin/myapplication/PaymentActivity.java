package com.testing.admin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by admin on 11/21/2016.
 */
public class PaymentActivity extends Activity {

    private ArrayList<String> stringArrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_activity);
    }

    public void paymentclick(View view) {
        stringArrayList.add("key");
        stringArrayList.add("xvIiqgZq");
        stringArrayList.add("txnid");
        stringArrayList.add("5123456789012346");
        stringArrayList.add("amount");
        stringArrayList.add("5.00");
        stringArrayList.add("firstname");
        stringArrayList.add("Prabhat");
        stringArrayList.add("email");
        stringArrayList.add("littlejohnjohnlittle6@gmail.com");
        stringArrayList.add("phone");
        stringArrayList.add("9000000000");
        stringArrayList.add("productinfo");
        stringArrayList.add("Wallet");
        stringArrayList.add("surl");
        stringArrayList.add("https://www.google.co.in/");
        stringArrayList.add("furl");
        stringArrayList.add("http://www.bing.com/");
        stringArrayList.add("service_provider");
        stringArrayList.add("payu_paisa");
//        stringArrayList.add("udf1");
//        stringArrayList.add("payu_paisa");
        Intent intent = new Intent(view.getContext(), PayMentGateWay.class);
        intent.putStringArrayListExtra("post_val", stringArrayList);
        startActivity(intent);
    }
}
