package com.example.muhammadghozi41.latihanlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muhammadghozi41.latihanlogin.adapter.ListMenuAdapter;
import com.example.muhammadghozi41.latihanlogin.model.ListMenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Menu");
        String user = getIntent().getStringExtra("user");
        TextView txt = (TextView) findViewById(R.id.text);
        txt.setText("Login Success ! Hi "+user+" !");
        Toast.makeText(MainActivity.this,R.string.success,Toast.LENGTH_LONG).show();

        //loading dynamic menu with adapter
        ListView listView = (ListView) findViewById(R.id.list_menu);
        ArrayList<ListMenuItem> listMenuItems = createSampleMenu();
        final ListMenuAdapter listMenuAdapter = new ListMenuAdapter(MainActivity.this, R.layout.list_menu_layout, listMenuItems);
        listView.setAdapter(listMenuAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String label = listMenuAdapter.getItem(i).getLabel().toString();
                Toast.makeText(MainActivity.this, label, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private ArrayList<ListMenuItem> createSampleMenu() {
        ListMenuItem item1 = new ListMenuItem(1, "http://icons.iconarchive.com/icons/designcontest/ecommerce-business/256/wallet-icon.png", "Check Balance", "check your balance");
        ListMenuItem item2 = new ListMenuItem(2, "http://icons.iconarchive.com/icons/awicons/vista-artistic/256/coin-add-icon.png", "Top Up", "top up your balance");
        ListMenuItem item3 = new ListMenuItem(3, "http://icons.iconarchive.com/icons/graphicloads/colorful-long-shadow/256/Cart-icon.png", "Shopping cart", "go to shopping cart");
        ListMenuItem item4 = new ListMenuItem(4, "http://icons.iconarchive.com/icons/ampeross/qetto-2/256/profile-icon.png", "Profile", "check your profile");
        ListMenuItem item5 = new ListMenuItem(5, "http://icons.iconarchive.com/icons/ampeross/qetto-2/256/settings-icon.png", "Setting", "go to setting");
        ArrayList<ListMenuItem> list = new ArrayList<ListMenuItem>();
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);

        return list;
    }
}
