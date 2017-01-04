package com.testing.admin.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

/**
 * Created by admin on 11/29/2016.
 */
public class MailSenderActivity extends Activity
{
    private static final String username = "littlejohnjohnlittle6@gmail.com";
    private static final String password = "littlejoh";
    private EditText emailEdit;
    private EditText subjectEdit;
    private static EditText messageEdit, otpEdit;
    private Multipart _multipart;
    private static String otpcode;

    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_send);

        emailEdit = (EditText) findViewById(R.id.emailid);
//        subjectEdit = (EditText) findViewById(R.id.subject);
//        messageEdit = (EditText) findViewById(R.id.message);
        otpEdit = (EditText) findViewById(R.id.otptext);
        Button sendButton = (Button) findViewById(R.id.otpbut);

        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = emailEdit.getText().toString();
//                String subject = subjectEdit.getText().toString();
//                String message = messageEdit.getText().toString();
                String message = generatepwd(11);
                Toast.makeText(MailSenderActivity.this,message,Toast.LENGTH_LONG).show();
                otpcode = message;
                String subject = "password";
                sendMail(email, subject, message);
            }
        });
    }

    public String generatepwd(int length)
    {
        String randomStr = UUID.randomUUID().toString();
        while(randomStr.length() < length) {
            randomStr += UUID.randomUUID().toString();
        }
        return randomStr.substring(0, length);
    }

    private void sendMail(String email, String subject, String messageBody)
    {
        Session session = createSessionObject();

        try {
            Message message = createMessage(email, subject, messageBody, session);
            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void addAttachment(String filename) throws Exception {
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);

        _multipart.addBodyPart(messageBodyPart);
    }
    private Message createMessage(String email, String subject, String messageBody, Session session) throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, email));
        message.setSubject(subject);
        message.setText(messageBody);
        return message;
    }

    private Session createSessionObject() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private class SendMailTask extends AsyncTask<Message, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MailSenderActivity.this, "Please wait", "Sending mail", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Toast.makeText(MailSenderActivity.this,"Mail Sending Successfully",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void okbut(View v)
    {
        String otpsendcode = otpEdit.getText().toString();
        if(otpsendcode.equals(otpcode))
        {
            Intent intent = new Intent(this,SignUp.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(MailSenderActivity.this,"OTP Code is Wrong",Toast.LENGTH_LONG).show();
        }
//        Toast.makeText(MailSenderActivity.this,otpsendcode,Toast.LENGTH_LONG).show();
    }

}
