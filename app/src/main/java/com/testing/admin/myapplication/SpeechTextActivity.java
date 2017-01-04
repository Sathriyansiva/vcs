package com.testing.admin.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.util.Locale;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by admin on 11/10/2016.
 */
public class SpeechTextActivity extends Activity implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private Button button;
    private EditText inputText;

    /**
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_text);
        button = (Button) findViewById(R.id.button1);
        inputText = (EditText) findViewById(R.id.inputText);
        textToSpeech = new TextToSpeech(this, this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                convertTextToSpeech();
            }

        });
        convertTextToSpeech();
    }

    /**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_speech_text, menu);
        return true;
    }

    /**
     * a callback to be invoked indicating the completion of the TextToSpeech
     * engine initialization.
     *
     * @see android.speech.tts.TextToSpeech.OnInitListener#onInit(int)
     */
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("error", "This Language is not supported");
            } else {
                convertTextToSpeech();
            }
        } else {
            Log.e("error", "Initilization Failed!");
        }
    }

    /**
     * Releases the resources used by the TextToSpeech engine. It is good
     * practice for instance to call this method in the onDestroy() method of an
     * Activity so the TextToSpeech engine can be cleanly stopped.
     *
     * @see android.app.Activity#onDestroy()
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        textToSpeech.shutdown();
    }

    /**
     * Speaks the string using the specified queuing strategy and speech
     * parameters.
     */
    private void convertTextToSpeech() {
        String text = inputText.getText().toString();
        if (null == text || "".equals(text)) {
            text = "Please give some input.";
        }
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
