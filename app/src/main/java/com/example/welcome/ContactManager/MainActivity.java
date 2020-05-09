package com.example.welcome.familyapp;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    // Wiget GUI
    EditText  txtMessage;
    AutoCompleteTextView txtNumber;
    Button btnSend;
    String mphonenumber;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        // Init GUI
        mphonenumber = String.valueOf( findViewById(R.id.phone));

        txtMessage = (EditText) findViewById(R.id.editText2);
        btnSend = (Button) findViewById(R.id.btnSendSMS);
        txtNumber = (AutoCompleteTextView)findViewById(R.id.editText);



        // Attached Click Listener
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        if (v == btnSend) {


            // Initialize SmsManager Object
            SmsManager smsManager = SmsManager.getDefault();

            // Send Message using method of SmsManager object
            smsManager.sendTextMessage(txtNumber.getText().toString(), null,
                    txtMessage.getText().toString(), null, null);

            Toast.makeText(this, "Message sent successfully", Toast.LENGTH_LONG)
                    .show();

        }

    }
}