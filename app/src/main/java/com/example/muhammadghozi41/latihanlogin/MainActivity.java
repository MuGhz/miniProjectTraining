package com.example.muhammadghozi41.latihanlogin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muhammadghozi41.latihanlogin.adapter.ListMenuAdapter;
import com.example.muhammadghozi41.latihanlogin.model.ListMenuItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.muhammadghozi41.latihanlogin.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    private long balance;
    public void initBalance(){
        balance = 100000;
    }
    public void setBalance(long balance) {
        this.balance = this.balance+balance;
    }
    public long getBalance() {
        return balance;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBalance();
        setTitle("Main Menu");
        String user = getIntent().getStringExtra("user");
        TextView txt = (TextView) findViewById(R.id.text);
        txt.setText("Login Success ! Hi "+user+" !");
        Toast.makeText(MainActivity.this,R.string.success,Toast.LENGTH_LONG).show();

        //loading dynamic menu with adapter
        ListView listView = (ListView) findViewById(R.id.list_menu);
        String jsonMenu = getIntent().getStringExtra("myMenu");
        //ArrayList<ListMenuItem> listMenuItems = createSampleMenu();
        ArrayList<ListMenuItem> listMenuItems = new Gson().fromJson(jsonMenu, new TypeToken<List<ListMenuItem>>(){}.getType());
        final ListMenuAdapter listMenuAdapter = new ListMenuAdapter(MainActivity.this, R.layout.list_menu_layout, listMenuItems);
        listView.setAdapter(listMenuAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String label = listMenuAdapter.getItem(i).getLabel().toString();
                Toast.makeText(MainActivity.this, label, Toast.LENGTH_SHORT).show();
                
                if(label.equals("Check Balance")){
                    AlertDialog.Builder builder =  new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Your Balance")
                            .setMessage(getBalance()+"")
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .create().show();
                }

                if(label.equals("Top Up")){
                    LayoutInflater li = LayoutInflater.from(context);
                    View topUpDialog = li.inflate(R.layout.dialog_top_up,null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(topUpDialog);
                    builder.setTitle("Top Up your balance");
                    final EditText userInput = (EditText) topUpDialog
                            .findViewById(R.id.editTextDialogTopUp);
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    setBalance(Long.parseLong(userInput.getText().toString()));
                                    Toast.makeText(context,"Top Up Success, your balance is "+getBalance(),Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog topUpAlert = builder.create();
                    topUpAlert.show();
                }

                if(label.equals("Log Out")){

                }

                }
        });
    }
    private ArrayList<ListMenuItem> createSampleMenu() {
        ListMenuItem item1 = new ListMenuItem(1, "http://icons.iconarchive.com/icons/designcontest/ecommerce-business/256/wallet-icon.png", "Check Balance", "check your balance");
        ListMenuItem item2 = new ListMenuItem(2, "http://icons.iconarchive.com/icons/awicons/vista-artistic/256/coin-add-icon.png", "Top Up", "top up your balance");
        ListMenuItem item3 = new ListMenuItem(3, "http://icons.iconarchive.com/icons/graphicloads/colorful-long-shadow/256/Cart-icon.png", "Shopping cart", "go to shopping cart");
        ListMenuItem item4 = new ListMenuItem(4, "http://icons.iconarchive.com/icons/ampeross/qetto-2/256/profile-icon.png", "Profile", "check your profile");
        ListMenuItem item5 = new ListMenuItem(5, "http://icons.iconarchive.com/icons/ampeross/qetto-2/256/settings-icon.png", "Setting", "go to setting");
        ListMenuItem item6 = new ListMenuItem(6, "http://icons.iconarchive.com/icons/graphicloads/100-flat-2/256/inside-logout-icon.png", "Log Out", "see u!");
        ArrayList<ListMenuItem> list = new ArrayList<ListMenuItem>();
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        list.add(item6);
        return list;
    }
}
