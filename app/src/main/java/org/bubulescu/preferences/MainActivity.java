package org.bubulescu.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnPreferences, btnNick;
    private EditText etNick;
    private TextView tvNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPreferences = findViewById(R.id.btnPreferences);
        btnNick = findViewById(R.id.btnNick);
        etNick = findViewById(R.id.etNick);
        tvNick = findViewById(R.id.tvNick);

        btnPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PrefenceScreenActivity.class);
                startActivity(intent);
            }
        });

        btnNick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nick = etNick.getText().toString();
                if (nick.trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Unesi nadimak", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("nickname", nick);
                    editor.commit();
                    etNick.setText("");

                    getPreference();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreference();
    }

    private void getPreference() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.contains("color"))
            if (preferences.getString("color", "0").equals("1")) {
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            } else if (preferences.getString("color", "0").equals("2")) {
                getWindow().getDecorView().setBackgroundColor(Color.RED);
            } else if (preferences.getString("color", "0").equals("3")) {
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            }

        if (preferences.contains("nickname"))
            tvNick.setText(preferences.getString("nickname", ""));
    }
}
