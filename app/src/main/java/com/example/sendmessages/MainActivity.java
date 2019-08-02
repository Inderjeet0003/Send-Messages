package com.example.sendmessages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.Manifest.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txt_pNumber,txt_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_message=(EditText)findViewById(R.id.txt_message);
        txt_pNumber=(EditText)findViewById(R.id.txt_phone_number);
        findViewById(R.id.send).setOnClickListener(this);
    }

    public void btn_send(View view) {
        Log.e("send","true");
        int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        }
        else {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission.SEND_SMS);
            if(permissionCheck== PackageManager.PERMISSION_GRANTED){
             MyMessage();
                Log.e("permission granted","true");
            }
            else{
                ActivityCompat.requestPermissions(this,new String[]{permission.SEND_SMS},0);
            }
        }
    }

    private void MyMessage() {
        String phoneNumber = txt_pNumber.getText().toString().trim();
        String Message = txt_message.getText().toString().trim();
         int digit=txt_pNumber.length();
         if(digit>10){
             Toast.makeText(this,"Enter a proper number",Toast.LENGTH_SHORT).show();
         }
        else if(!txt_pNumber.getText().toString().equals("") && !txt_message.getText().toString().equals("")){
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber,null,Message,null,null);

             Log.e("msg send","true");
        Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show();
    }
        else{
            Toast.makeText(this,"MUST enter phone number and Message",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:
                if(grantResults.length>=0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    MyMessage();
                else
                {
                    Toast.makeText(this,"You dont have required Permission",Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    int permissionCheck = ContextCompat.checkSelfPermission(this, permission.SEND_SMS);
                    if(permissionCheck== PackageManager.PERMISSION_GRANTED){
                        MyMessage();
                        Log.e("permission granted","true");
                    }
                    else{
                        ActivityCompat.requestPermissions(this,new String[]{permission.SEND_SMS},0);
                    }
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id==R.id.send){
            Log.e("send","true");
            int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

            if (permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
            }
            else {
                int permissionCheck = ContextCompat.checkSelfPermission(this, permission.SEND_SMS);
                if(permissionCheck== PackageManager.PERMISSION_GRANTED){
                    MyMessage();
                    Log.e("permission granted","true");
                }
                else{
                    ActivityCompat.requestPermissions(this,new String[]{permission.SEND_SMS},0);
                }
            }
        }
    }
}

