package com.testing.admin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by admin on 11/24/2016.
 */
public class Payment3TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment3_test_activity);
    }

    public void paymentclick(View view) {
        Intent intent = new Intent(view.getContext(), PaymentGateway3TestActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("amount", "50.00");
        bundle.putString("firstname", "Prabhat");
        bundle.putString("email", "littlejohnjohnlittle6@gmail.com");
        bundle.putString("phone", "9000000000");
        bundle.putString("id", "1254");
        bundle.putString("isFromOrder", "true");
        bundle.putString("productInfo", "Food Items");
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
