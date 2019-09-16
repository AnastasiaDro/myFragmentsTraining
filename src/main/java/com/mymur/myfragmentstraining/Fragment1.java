package com.mymur.myfragmentstraining;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

public class Fragment1 extends ListFragment {

    int currentContact = 0; //текущий контакт
    boolean isExistContactsFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment1, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //чтобы показать список, надо задействовать адаптер
        //Такая конструкция работает для списков, например ListActivity
        //Здесь создаём из ресурсов список городов (из массива)
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Contacts, android.R.layout.simple_list_item_activated_1);
        setListAdapter(adapter);

        //Посмотрим, существует ли у нас рядом фрагмент 2, если ландскейп ориентация, то да
        //То есть истина ли, что рамка fragment2Frame не пуста
        isExistContactsFragment = getActivity().findViewById(R.id.fragment2Frame) != null;


        //если это повторное создание, то восстановим текущую позицию
        if (savedInstanceState != null) {
            currentContact = savedInstanceState.getInt("CurrentContact", 0);
        }
        //если ориентация ландскейп, т.е. если есть второй фрагмент на экране, то нарисуем его
        if (isExistContactsFragment) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showMessages();
        }

    }

    //Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CurrentContact", currentContact);
    }

    private void showMessages() {
        if (isExistContactsFragment) {
            getListView().setItemChecked(currentContact, true);
        //Проверим, что фрагмент с гербом существует в activity
        Fragment2 detail = (Fragment2)getFragmentManager().findFragmentById(R.id.fragment_2);
        //если существует и текущая позиция списка не совпадает с позицией картинки, выводим новый контакт
        if (detail == null || detail.getIndex() != currentContact){
            detail = Fragment2.create(currentContact);

        //Выполняем транзакцию по замене фрагмента
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_2, detail); //замена фрагмента
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();


        } else {
            //если рядом вывести нельзя, откроем ещё 1 активити
            Intent intent = new Intent();
            intent.setClass(getActivity(), MessageActivity.class);
            intent.putExtra("index", currentContact);
            startActivity(intent);
        }

        }
    }
}




