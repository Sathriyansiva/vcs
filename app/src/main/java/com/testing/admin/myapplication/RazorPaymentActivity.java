package com.testing.admin.myapplication;

import android.app.Activity;
import android.view.View;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.razorpay.Checkout;
import org.json.JSONObject;

/**
 * Created by admin on 12/1/2016.
 */
public class RazorPaymentActivity extends Activity
{
    final String public_key = "rzp_test_1DP5mmOlF5G5ag";

    public RazorPaymentActivity(){}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razorpay_main);

        // payment button created by you in xml layout
        View button = (View) findViewById(R.id.pay_btn);

        // you need to pass current activity in order to let razorpay create CheckoutActivity
        final Activity activity = this;
        final RazorpayCheckoutFragment co = new RazorpayCheckoutFragment();
        co.setPublicKey(public_key);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try{
                    JSONObject options = new JSONObject("{" +
                            "description: 'Service Charges'," +
                            "image: 'http://nflshop.frgimages.com/FFImage/thumb.aspx?i=/productImages/_2544000/ff_2544701_full.jpg&w=340'," +
                            "currency: 'INR'}"
                    );

                    options.put("amount", "500");
                    options.put("name", "Palani");
                    options.put("prefill", new JSONObject("{email: 'palanichamy9204@gmail.com', contact: '9876543210'}"));

                    co.open(activity, options);

                } catch(Exception e){
                    Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            };
        });
    }
}