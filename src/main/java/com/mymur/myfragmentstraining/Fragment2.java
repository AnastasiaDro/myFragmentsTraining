package com.mymur.myfragmentstraining;
import androidx.fragment.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;



public class Fragment2 extends Fragment implements View.OnClickListener{

    Integer position;
    TextView testText;
    ImageView imageView;
    TypedArray imgArray;
    Button sendBtn;
    ContactsClass contactsClass;
    String smsText;
   CheckBox[]checkBoxArr;


    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        imgArray = getResources().obtainTypedArray(R.array.contactsimgs);
        position = getArguments().getInt("CurrentContact");
        view = inflater.inflate(R.layout.fragment2, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        imageView = getActivity().findViewById(R.id.contactImage);

        contactsClass = new ContactsClass();
        sendBtn =  (Button)view.findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(this);
        CheckBox helloCheckBox = view.findViewById(R.id.helloCheckBox);
        CheckBox loveCheckBox = view.findViewById(R.id.loveCheckBox);
        CheckBox betterCheckBox = view.findViewById(R.id.betterCheckBox);
        CheckBox youAreCheckBox = view.findViewById(R.id.youAreCheckBox);
        checkBoxArr = new CheckBox[] {helloCheckBox, loveCheckBox, betterCheckBox, youAreCheckBox };
         smsText = "";




        try {
            imageView.setImageResource(imgArray.getResourceId(position, 0));
        } catch (NullPointerException e){
            position = 0;


        }

        String posText = position.toString();
    //    testText.setText(posText);

      //Обработчик нажатия на кнопку sendBtn


    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < checkBoxArr.length; i++) {
            System.out.println("длина массива" + checkBoxArr.length);
            if (checkBoxArr[i].isChecked()){
                System.out.println(checkBoxArr[i].getText().toString());
                smsText=smsText.concat(checkBoxArr[i].getText().toString());

            }
        }

        System.out.println(smsText);
        startActivity(contactsClass.sendToContact(position, smsText, getActivity()));

    }
}
