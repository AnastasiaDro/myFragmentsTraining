package com.mymur.myfragmentstraining;

import android.content.Intent;
import android.net.Uri;
import java.util.ArrayList;

public class ContactsClass {
    ArrayList contacts;
    String sendSmsToContact;


    public ContactsClass() {
    contacts = new ArrayList();
    contacts.add("никто");
    contacts.add("89680560408");
    contacts.add("89184123685");
    contacts.add("Papa");
    sendSmsToContact = "";
    }


    public Intent sendToContact(int position, String smsText){
        //задаём строчку для отправки сообщений
        sendSmsToContact = "smsto:"+ contacts.get(position);
        Intent smsSend = new Intent(Intent.ACTION_SENDTO);
        //???делаем эту строчку ури???
        smsSend.setData(Uri.parse(sendSmsToContact));
        smsSend.putExtra("sms_body", smsText);

        return smsSend;
    }



}
