package fr.cocorico_france.petitionhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Tab4Contact extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab4contact, container, false);
        String[] mailServer = {"orange.fr", "free.fr", "live.fr", "gmail.com", "outlook.fr",
        "hotmail.fr", "hotmail.com", "sfr.fr", "wanadoo.fr", "laposte.net", "libertysurf.fr",
        "yahoo.fr", "bbox.fr"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_dropdown_item_1line,
                mailServer);
        AutoCompleteTextView textView = rootView.findViewById(R.id.autoCompleteTextViewMailServer);
        textView.setAdapter(adapter);
        return rootView;
    }

}
