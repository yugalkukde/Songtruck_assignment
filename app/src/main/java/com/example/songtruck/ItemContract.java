package com.example.songtruck;

import android.provider.BaseColumns;

public class ItemContract {

    public ItemContract() {
    }

    public static class SingerEntry implements BaseColumns {
        public static final String TABLE_NAME = "singerList";
        public static final String COLUMN_ICON = "icon";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
