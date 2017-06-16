package com.example.muhammadghozi41.latihanlogin.schema;

import android.provider.BaseColumns;

/**
 * Created by muhammad.ghozi41 on 16/06/17.
 */

public class MyAppSchema {
    private MyAppSchema() {}

    public static class MenuItemTable implements BaseColumns {
        public static final String TABLE_NAME = "menu_item";
        public static final String COLUMN_NAME_ICON_URL = "icon_url";
        public static final String COLUMN_NAME_MENU_LABEL = "menu_label";
        public static final String COLUMN_NAME_MENU_DESC = "menu_desc";
        public static final String COLUMN_NAME_PARENT_ID = "parent_id";
    }
}
