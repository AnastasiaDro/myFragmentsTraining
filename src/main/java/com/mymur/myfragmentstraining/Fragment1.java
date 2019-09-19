package com.mymur.myfragmentstraining;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

public class Fragment1 extends ListFragment {

    int currentContact = 0; //текущий контакт
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Contacts, android.R.layout.simple_list_item_activated_1);
        setListAdapter(adapter);
        return inflater.inflate(R.layout.fragment1, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CurrentContact", currentContact);
    }

    //обработчик нажатия на элемент списка
    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
    //сохраняем в переменную текущего контактакта позицию списка на которую тыкнули
        currentContact = position;
        Bundle bundle = new Bundle();
            bundle.putInt("CurrentContact", currentContact);
            Fragment2 fragment2 = new Fragment2();
            fragment2.setArguments(bundle);
           FragmentManager fragmentManager = getFragmentManager();
           FragmentTransaction ft = fragmentManager.beginTransaction();
           if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ft.replace(R.id.fragment1Frame, fragment2);
            //добавляём транзакцию в backstack, чтобы при нажатии клавиши назад возваращаться обратно к фрагментую ытьыть
            ft.addToBackStack("back");
           } else {
               ft.replace(R.id.fragment1Frame, this);
               ft.replace(R.id.fragment2Frame, fragment2);
           }

        ft.commit();

    }



}




