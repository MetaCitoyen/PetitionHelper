package fr.cocorico_france.petitionhelper;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tab4Validation extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab4validation, container, false);

        Button btValid = (Button) rootView.findViewById(R.id.button);
        btValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean perm = false;
                if (Build.VERSION.SDK_INT >= 23) {
                    if (getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        perm= true;
                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        perm= false;
                    }
                }
                else { //you dont need to worry about these stuff below api level 23
                    perm= true;
                }
                MainActivity main = (MainActivity) getActivity();
                AutoCompleteTextView tvFirstName = (AutoCompleteTextView) main.findViewById(R.id.autoCompleteTextViewFirstName);
                AutoCompleteTextView tvLastName = (AutoCompleteTextView) main.findViewById(R.id.autoCompleteTextViewLastName);
                AutoCompleteTextView tvTownCode = (AutoCompleteTextView) main.findViewById(R.id.autoCompleteTextViewTownCode);
                AutoCompleteTextView tvTown = (AutoCompleteTextView) main.findViewById(R.id.autoCompleteTextViewTown);
                EditText etMail = (EditText) main.findViewById(R.id.editTextMailBase);
                AutoCompleteTextView tvMailServer = (AutoCompleteTextView) main.findViewById(R.id.autoCompleteTextViewMailServer);
                EditText etPhone = (EditText) main.findViewById(R.id.editTextPhone);
                EditText etRef = (EditText) main.findViewById(R.id.editTextRef);
                EditText etLine = (EditText) main.findViewById(R.id.editTextLine);

                if( perm &&
                        (tvFirstName != null) &&
                        (tvLastName != null) &&
                        (tvTown != null) ) {



                    File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File file = new File(directory, "PetitionHelper.csv");
                    Date currentTime = Calendar.getInstance().getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd;HHmmss");
                    try {
                        FileOutputStream stream = new FileOutputStream(file, true);
                        try {
                            stream.write(String.format("%s;%s;%s;%s;%s;%s@%s;%s;%s;%s\n",
                                    sdf.format(currentTime),
                                    etRef.getText().toString(),
                                    etLine.getText().toString(),
                                    tvLastName.getText().toString(),
                                    tvFirstName.getText().toString(),
                                    etMail.getText().toString(),
                                    tvMailServer.getText().toString(),
                                    tvTownCode.getText().toString(),
                                    tvTown.getText().toString(),
                                    etPhone.getText().toString()).getBytes());
                            tvFirstName.setText("");
                            tvLastName.setText("");
                            etMail.setText("");
                            tvMailServer.setText("");
                            tvTownCode.setText("");
                            tvTown.setText("");
                            etPhone.setText("");
                            etLine.setText(Integer.toString(Integer.valueOf(etLine.getText().toString())+1));

                        } catch (IOException e) {
                        } finally {
                            stream.close();
                        }
                    } catch (IOException e) {
                        Log.e("Exception", e.getMessage());
                    }
                    main.setPage(0);
                }
            }
        });
        return rootView;
    }
}
