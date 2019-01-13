package fr.cocorico_france.petitionhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

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
        Button btAddFirstName = (Button) rootView.findViewById(R.id.btAddFirstName);
        btAddFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button bt = (Button)v;
                MainActivity main = (MainActivity) getActivity();
                EditText editTextMailBase = (EditText)main.findViewById(R.id.editTextMailBase);
                editTextMailBase.setText(editTextMailBase.getText().append(bt.getText()));
                editTextMailBase.setSelection(editTextMailBase.getText().length());
            }});
        Button btAddLastName = (Button) rootView.findViewById(R.id.btAddLastName);
        btAddLastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button bt = (Button)v;
                MainActivity main = (MainActivity) getActivity();
                EditText editTextMailBase = (EditText)main.findViewById(R.id.editTextMailBase);
                editTextMailBase.setText(editTextMailBase.getText().append(bt.getText()));
                editTextMailBase.setSelection(editTextMailBase.getText().length());
            }});
        return rootView;
    }

}
