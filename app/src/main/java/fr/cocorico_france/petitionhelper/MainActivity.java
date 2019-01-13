package fr.cocorico_france.petitionhelper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0: { // Last name

                    } break;

                    case 1: { // First name

                    } break;

                    case 2: { // Code postal

                    } break;

                    case 3: { // Contact
                        AutoCompleteTextView tvLastName = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewLastName);
                        Button btAddLastName = (Button) findViewById(R.id.btAddLastName);
                        btAddLastName.setText(tvLastName.getText().toString().toLowerCase());
                        AutoCompleteTextView tvFirstName = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewFirstName);
                        Button btAddFirstName = (Button) findViewById(R.id.btAddFirstName);
                        btAddFirstName.setText(tvFirstName.getText().toString().toLowerCase());
                    } break;

                    case 4: { // Validation
                        AutoCompleteTextView acLastName = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewLastName);
                        AutoCompleteTextView acFirstName = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewFirstName);
                        AutoCompleteTextView acTownCode = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewTownCode);
                        AutoCompleteTextView acTown = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewTown);
                        EditText etMail = (EditText) findViewById(R.id.editTextMailBase);
                        AutoCompleteTextView tvMailServer = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewMailServer);
                        EditText etPhone = (EditText) findViewById(R.id.editTextPhone);

                        TextView tvFirstName = (TextView) findViewById(R.id.textViewFirstName);
                        TextView tvLastName = (TextView) findViewById(R.id.textViewLastName);
                        TextView tvTown = (TextView) findViewById(R.id.textViewTown);
                        TextView tvMail = (TextView) findViewById(R.id.textViewMail);
                        TextView tvPhone = (TextView) findViewById(R.id.textViewPhone);

                        if( tvFirstName != null && acFirstName != null) tvFirstName.setText(acFirstName.getText());
                        if( tvLastName != null && acLastName != null ) tvLastName.setText(acLastName.getText());
                        if( tvTown != null && acTown != null && acTownCode != null) tvTown.setText(String.format("%s %s",
                                acTownCode.getText(),
                                acTown.getText()));
                        if( tvMail != null && etMail != null && tvMailServer != null) tvMail.setText(String.format("%s@%s",
                                etMail.getText(),
                                tvMailServer.getText()));
                        if( tvPhone != null && etPhone != null) tvPhone.setText(etPhone.getText());
                    } break;

                }
            }

        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if( id == R.id.action_send_mail ) {
            boolean perm = false;
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    perm= true;
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    perm= false;
                }
            }
            else { //you dont need to worry about these stuff below api level 23
                perm= true;
            }


            if( perm ) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("message/rfc822");
//                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"mail_to@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Petition electronique");
                email.putExtra(Intent.EXTRA_TEXT, "Voici les données de la pétition");
                String filePath = Environment.getExternalStorageDirectory()
                        + File.separator + "PetitionHelper"
                        + File.separator + "PetitionHelper.csv";
                Uri fileUri = FileProvider.getUriForFile(this,
                        getApplicationContext().getPackageName() + ".fr.cocorico_france.petitionhelper.provider",
                        new File(filePath));
                email.putExtra(Intent.EXTRA_STREAM, fileUri);
                email.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                email.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                try {
                    // the user can choose the email client
                    startActivity(Intent.createChooser(email, "Choose an email client from..."));

                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "No email client installed.",
                            Toast.LENGTH_LONG).show();
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Tab1LastName();
                case 1:
                    return new Tab2FirstName();
                case 2:
                    return new Tab3Town();
                case 3:
                    return new Tab4Contact();
                case 4:
                    return new Tab4Validation();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

    public void setPage(int i) {
        mViewPager.setCurrentItem(i);
    }
}
