package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS=0;
    EditText ph,msg;
    String phoneNo,message;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ph=findViewById(R.id.inputPhoneNumber);
        msg=findViewById(R.id.inputMessage);
        btnSend=findViewById(R.id.btnSend);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send(v);
            }
        });
    }
    public void send(View view) {
        phoneNo=ph.getText().toString();
        message=msg.getText().toString();
        if(phoneNo.isEmpty() || phoneNo.length()>10){
            ph.setError("Invalid Number");
            ph.requestFocus();
        } else if(message.isEmpty()) {
            msg.setError("Can't Leave Blank");
            msg.requestFocus();
        } else {
            try {
                SmsManager smsManager=SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo,null,message,null,null);
                Toast.makeText(getApplicationContext(), "Message Sent Successfully", Toast.LENGTH_LONG).show();
            }
            catch(Exception ex) {
                Toast.makeText(getApplicationContext(),"Error!", Toast.LENGTH_LONG).show();
            }
        }
    }
}


