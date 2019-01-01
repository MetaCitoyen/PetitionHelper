package fr.cocorico_france.petitionhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Tab1LastName extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1lastname, container, false);
        String[] lastnames = getResources().getStringArray(R.array.lastnames);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_dropdown_item_1line,
                lastnames);
        AutoCompleteTextView textView = rootView.findViewById(R.id.autoCompleteTextViewLastName);
        textView.setAdapter(adapter);
        return rootView;
    }
}
