package com.example.muhammadghozi41.latihanlogin.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.muhammadghozi41.latihanlogin.model.ListMenuItem;
import com.example.muhammadghozi41.latihanlogin.schema.MyAppSchema.MenuItemTable;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhammad.ghozi41 on 16/06/17.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE "+ MenuItemTable.TABLE_NAME+ "(" +
                    MenuItemTable._ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                    MenuItemTable.COLUMN_NAME_ICON_URL+ " TEXT, "+
                    MenuItemTable.COLUMN_NAME_MENU_LABEL+ " TEXT, "+
                    MenuItemTable.COLUMN_NAME_MENU_DESC+ " TEXT, "+
                    MenuItemTable.COLUMN_NAME_PARENT_ID+ " INTEGER"
                    +")";
    private static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS "+MenuItemTable.TABLE_NAME;

    private Context context;
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "myapp.db";
    private SQLiteDatabase db;
    private String databasePath;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        databasePath = context.getExternalFilesDir(null) +"/"+ DATABASE_NAME;
        openDb();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        if(!checkTable(MenuItemTable.TABLE_NAME))
//            sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL(SQL_DROP_TABLE);
//        onCreate(sqLiteDatabase);
    }

    private boolean checkDb() {
        File db = new File(databasePath);
        if(db.exists()) {
            Log.d("DEBUG", "Database exist");
            return true;
        }
        else return false;
    }

    private void openDb() {

        Log.d("DEBUG", "OPENING DATABASE CONNECTION");
        if(!checkDb())
            db = SQLiteDatabase.openOrCreateDatabase(databasePath, null);
        else
            db = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);

        Log.d("DEBUG", "Database path = "+ db.getPath());
        Log.d("DEBUG", "DB is read only? "+ db.isReadOnly());

        if(!checkTable(MenuItemTable.TABLE_NAME)) {
            Log.d("DEBUG", "Table is not exist, create new one");
            db.execSQL(SQL_CREATE_TABLE);
        }
        else
            Log.d("DEBUG", "Table exists");
    }


    public void insertMenu(List<ListMenuItem> listMenuItems) {
        String sqlDelete = "DELETE FROM "+MenuItemTable.TABLE_NAME;
        String sqlInsert = "INSERT INTO "+MenuItemTable.TABLE_NAME+"("+
                MenuItemTable.COLUMN_NAME_ICON_URL+","+
                MenuItemTable.COLUMN_NAME_MENU_LABEL+","+
                MenuItemTable.COLUMN_NAME_MENU_DESC+","+
                MenuItemTable.COLUMN_NAME_PARENT_ID+
                ") VALUES (?, ?, ?, ?)";
        if(db.isOpen()) {
            Log.d("DEBUG", "SQLite database open");
            db.execSQL(sqlDelete);
            Log.d("DEBUG","Delete all record on menu_item table");

            SQLiteStatement sqLiteStatement = db.compileStatement(sqlInsert);
            for (ListMenuItem item : listMenuItems) {
                sqLiteStatement.bindString(1, item.getIconUrl());
                sqLiteStatement.bindString(2, item.getMenuLabel());
                sqLiteStatement.bindString(3, item.getMenuDesc());
                sqLiteStatement.bindLong(4, item.getParentId());
                sqLiteStatement.execute();
                sqLiteStatement.clearBindings();
                Log.d("DEBUG", "Finish insert record : "+ new Gson().toJson(item));
            }
            sqLiteStatement.close();
        }
    }

    public ArrayList<ListMenuItem> getAllParentMenu(int i) {
        ArrayList<ListMenuItem> itemList = new ArrayList<ListMenuItem>();
        String sql = "SELECT _id, icon_url, menu_label, menu_desc, parent_id FROM menu_item WHERE parent_id = "+i;
        Cursor cur = db.rawQuery(sql, null);
        Log.d("DEBUG", "Total Menu = "+cur.getCount());
        if(cur.getCount() > 0) {
            if(cur.moveToFirst()) {
                while(!cur.isAfterLast()) {
                    ListMenuItem item = new ListMenuItem();
                    item.setId(cur.getInt(cur.getColumnIndex("_id")));
                    item.setIconUrl(cur.getString(cur.getColumnIndex(MenuItemTable.COLUMN_NAME_ICON_URL)));
                    item.setMenuLabel(cur.getString(cur.getColumnIndex(MenuItemTable.COLUMN_NAME_MENU_LABEL)));
                    item.setMenuDesc(cur.getString(cur.getColumnIndex(MenuItemTable.COLUMN_NAME_MENU_DESC)));
                    item.setParentId(cur.getInt(cur.getColumnIndex(MenuItemTable.COLUMN_NAME_PARENT_ID)));

                    itemList.add(item);
                    cur.moveToNext();
                }

            }
        }
        cur.close();
        return itemList;
    }

    private boolean checkTable(String tableName) {
        String sql = "SELECT _id FROM "+tableName;
        if(db.isOpen()) {
            try {
                Cursor cur = db.rawQuery(sql, null);
                cur.close();
                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }
        else return false;
    }
}
