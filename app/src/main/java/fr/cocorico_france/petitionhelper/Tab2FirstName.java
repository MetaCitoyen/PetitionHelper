package fr.cocorico_france.petitionhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Tab2FirstName extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2firstname, container, false);
        String[] firstnames = getResources().getStringArray(R.array.firstnames);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                R.layout.autocomplete_custom,
                firstnames);
        AutoCompleteTextView textView = rootView.findViewById(R.id.autoCompleteTextViewFirstName);
        textView.setAdapter(adapter);
        return rootView;
    }


}
