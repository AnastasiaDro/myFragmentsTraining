package com.mymur.myfragmentstraining;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.util.ArrayList;


public class ContactsClass {
    ArrayList contacts;
    String sendSmsToContact;



    public ContactsClass() {
    contacts = new ArrayList();
    contacts.add("89680560408");
    contacts.add("89680560408");
    contacts.add("89184123685");
    contacts.add("89854226566");
    sendSmsToContact = "";
    }


    public Intent sendToContact(int position, String smsText, Activity activity){

        //задаём строчку для отправки сообщений
        if (position == 0){
            Toast.makeText((activity), "Давай всё-таки отправим Муре", Toast.LENGTH_LONG).show();
        }
        sendSmsToContact = "smsto:"+ contacts.get(position);
        Intent smsSend = new Intent(Intent.ACTION_SENDTO);
        //???делаем эту строчку ури???
        smsSend.setData(Uri.parse(sendSmsToContact));
        smsSend.putExtra("sms_body", smsText);
        return smsSend;
    }



}
