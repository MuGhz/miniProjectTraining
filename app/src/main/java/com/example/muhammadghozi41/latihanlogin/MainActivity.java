package com.example.muhammadghozi41.latihanlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String user = getIntent().getStringExtra("user");
        TextView txt = (TextView) findViewById(R.id.text);
        txt.setText("Login Success ! Hi "+user+" !");
        Toast.makeText(MainActivity.this,R.string.success,Toast.LENGTH_LONG).show();
    }
}
