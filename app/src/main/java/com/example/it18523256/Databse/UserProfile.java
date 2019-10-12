package com.example.it18523256.Databse;

import android.provider.BaseColumns;

public final class UserProfile{
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UserProfile() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "UserInfo";
        public static final String Col_1 = "userName";
        public static final String Col_2 = "dateOfBirth";
        public static final String Col_3 = "Gender";
        public static final String Col_4 = "Password";
    }
}