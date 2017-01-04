package com.testing.admin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by admin on 11/24/2016.
 */
public class Payment2TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment2_test_activity);
    }

    public void paymentclick(View view) {
        Intent intent = new Intent(view.getContext(), PaymentGateway2TestActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("amount", "50.00");
        bundle.putString("firstname", "Prabhat");
        bundle.putString("email", "littlejohnjohnlittle6@gmail.com");
        bundle.putString("phone", "9000000000");
        bundle.putString("id", "1254");
        bundle.putString("isFromOrder", "true");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
