package com.example.administrator.layout;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends BaseActivity {



    private EditText mPhoneNumberText;
    private EditText mMessageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPhoneNumberText = findViewById(R.id.mPhomeNumberText);
        mMessageText = findViewById(R.id.mMessageText);



        try {
            FileRead("a","b");
            FileWrite("a","b");
        } catch (IOException e) {
            e.printStackTrace();
        }




//        File testfile = new File(this.getFilesDir(),"woaini.txt");
//
//        try {
//            FileOutputStream fos = new FileOutputStream(testfile);
//
//            BufferedOutputStream bos =new BufferedOutputStream(fos);
//
//            byte [] a  = "woainiwowowo".getBytes();
//
//            try {
//                bos.write(a);
//                bos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//
//        }

    }



    public void ClickCall(View v){


         requestPermission(new String[]{Manifest.permission.CALL_PHONE},0x0001);

    }


    public void ClickMessage(View v){


        requestPermission(new String[]{Manifest.permission.SEND_SMS},0x0002);

    }


    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);

        switch (requestCode) {
            case 0x0001:

                call();

                break;

            case 0x0002:

                sendMessage();

            default:
                break;

        }

    }




    public void call()
    {
        Intent[] intents;
        String phonenumber  = mPhoneNumberText.getText().toString().trim();
        
        if (phonenumber.equals("")) {

            Toast.makeText(MainActivity.this,"号码不能为空",Toast.LENGTH_SHORT).show();
            return;
            
        } else {

            Intent intent1 = new Intent();
            intents = new Intent[]{intent1};
            intent1.setAction(Intent.ACTION_CALL);
            intent1.setData(Uri.parse("tel:"+phonenumber));
            startActivities(intents);


        }

    }


    public void sendMessage()
    {

        String phonenumber = mPhoneNumberText.getText().toString().trim();
        String MessageContent = mMessageText.getText().toString().trim();

        if (phonenumber.equals("") || MessageContent.equals("")) {

            Toast.makeText(MainActivity.this,"号码或者电话号码不能为空",Toast.LENGTH_SHORT).show();
            return;

        } else {

            SmsManager MessageSend =SmsManager.getDefault();
            MessageSend.sendTextMessage(phonenumber,null,MessageContent,null,null);

        }

    }


}





