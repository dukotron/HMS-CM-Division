package com.example.homie.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceFragmentCompat;

import com.example.homie.R;
import com.example.homie.viewModels.MainViewModel;
import com.example.homie.viewModels.PreferencesViewModel;
import com.example.homie.viewModels.util.TempMemory;
import com.google.android.material.navigation.NavigationView;

import java.util.Map;

public class PreferencesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private MainViewModel viewModel;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment(ViewModelProviders.of(this).get(PreferencesViewModel.class)))
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

        private PreferencesViewModel viewModel;

        public SettingsFragment(PreferencesViewModel viewModel){
            this.viewModel = viewModel;
        }

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }

        @Override
        public void onResume() {
            super.onResume();

            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();

            getPreferenceScreen().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Map<String,?> all = sharedPreferences.getAll();
            if(all.get(key) instanceof Boolean){ // atHome preference
                viewModel.setAtHome(sharedPreferences.getBoolean(key,false));
            } else if(all.get(key) instanceof String){ // name preference
                TempMemory.saveUserName(getContext(),sharedPreferences.getString(key,""));
            }
        }
    }




    

}