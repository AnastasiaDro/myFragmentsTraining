package com.mymur.myfragmentstraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Fragment fragment1;
    Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // получаем экземпляр FragmentTransaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragment1 = new Fragment1();
        fragmentTransaction.add(R.id.fragment1Frame, fragment1);
        //узнаем программно ориентацию и если она ландскейп, инициализируем второй фрагмент
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragment2 = new Fragment2();
            fragmentTransaction.add(R.id.fragment2Frame, fragment2);
        }
        fragmentTransaction.commit();

    }

}
