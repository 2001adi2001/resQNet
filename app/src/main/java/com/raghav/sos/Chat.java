package com.raghav.sos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Chat extends AppCompatActivity {

    EditText txt_message,txt_pNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        txt_message= findViewById(R.id.txt_message);
        txt_pNumber= findViewById(R.id.txt_phone_number);

    }

    public void btn_send(View view) {
        int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if(permissionCheck== PackageManager.PERMISSION_GRANTED){

            MyMessage();
        }

        else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.SEND_SMS},0);
        }
    }

    private void MyMessage() {

        String phoneNumber = txt_pNumber.getText().toString().trim();
        String Message = txt_message.getText().toString().trim();
        if (!txt_pNumber.getText().toString().equals("") || !txt_message.getText().toString().equals("")) {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, Message, null, null);

            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Plese enter number Or message", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                MyMessage();
            } else {
                Toast.makeText(this, "you don't have required permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}