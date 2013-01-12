package com.example.smsdemo;

  
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

  
public class SMSDemo extends Activity {
	
private EditText phoneNumber, messageText;


  
  
      @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_smsdemo);
          
          phoneNumber = (EditText)findViewById(R.id.phoneNumber);
          messageText = (EditText)findViewById(R.id.messageText);
  
          
    
          Button sendAndSaveButton = (Button) findViewById(R.id.sendAndSaveButton);
                  
          
           
           
           sendAndSaveButton.setOnClickListener(new View.OnClickListener() {
        	   
             public void onClick(View view) {                        
                 if(phoneNumber.getText().toString().trim().length() == 0) { 
                     Toast.makeText(getApplicationContext(), "Please enter a Phone Number.", Toast.LENGTH_LONG).show();
                     return;
                 }
                 
                 if(messageText.getText().toString().trim().length() == 0) { 
                     Toast.makeText(getApplicationContext(), "Please enter your message.", Toast.LENGTH_LONG).show();
                     return;
                 }
                 
                 if(messageText.getText().toString().trim().length() > 160) {
                     sendLongSMS();    
                     //Save in SENT folder
                     saveInSent();
                     
                 }
                 else {
                     sendSMS();
                     //Save in SENT folder
                     saveInSent();
                 }
             }
           });
           
      }
      
      
      public void sendSMS() {
          String phoneNo = "9847061066";
          String message = "Hardware Test";
  
          SmsManager smsManager = SmsManager.getDefault();
          smsManager.sendTextMessage(phoneNo, null, message, null, null);
          
          Toast.makeText(getApplicationContext(), "Message Sent!", Toast.LENGTH_LONG).show();
      }
  
      @Override
      
      public boolean onKeyLongPress(int keycode, KeyEvent e) {
          switch(keycode) {
              case KeyEvent.KEYCODE_VOLUME_UP:
              { String phoneNo = "9947066666";
                  String message = "Hardware Test";
          
                  SmsManager smsManager = SmsManager.getDefault();
                  smsManager.sendTextMessage(phoneNo, null, message, null, null);
                  
                  Toast.makeText(getApplicationContext(), "Message Sent!", Toast.LENGTH_LONG).show();
                  return true;}
          }

          return super.onKeyDown(keycode, e);
      }
      
     
      
      public void sendLongSMS() {      
          //String phoneNo = "0123456789";
          //String message = "Hello World! Now we are going to demonstrate how to send a message with more than 160 characters from your Android application.";
  
          SmsManager smsManager = SmsManager.getDefault();
          ArrayList<String> parts = smsManager.divideMessage(messageText.getText().toString()); 
          smsManager.sendMultipartTextMessage(phoneNumber.getText().toString(), null, parts, null, null);
          
          Toast.makeText(getApplicationContext(), "Message Sent!", Toast.LENGTH_LONG).show();
      }      
      
      
  
      
      public void saveInSent() {
          ContentValues values = new ContentValues(); 
          
          values.put("address", phoneNumber.getText().toString()); 
                    
          values.put("body", messageText.getText().toString()); 
                    
          getContentResolver().insert(Uri.parse("content://sms/sent"), values);
      }        
  }
