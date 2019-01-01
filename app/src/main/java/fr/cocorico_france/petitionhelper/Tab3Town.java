package fr.cocorico_france.petitionhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Tab3Town extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3town, container, false);
        String[] towns = getResources().getStringArray(R.array.towns);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_dropdown_item_1line,
                towns);
        AutoCompleteTextView textView = rootView.findViewById(R.id.autoCompleteTextViewTown);
        textView.setAdapter(adapter);
        return rootView;
    }

}
