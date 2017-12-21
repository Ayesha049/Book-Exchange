package com.ayeshaapp.android.bookexchange1.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by l on 11/17/17.
 */

public class BookContract {

    private BookContract() {}


    public static final String CONTENT_AUTHORITY = "com.example.android.bookexchange1";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PERSON = "person";

    public static final String PATH_ADVERTISEMENT = "advertisements";

    public static final String PATH_ORDERLIST = "orders";

    public static final class PersonEntry implements BaseColumns {


        public static final Uri PERSON_URI = Uri.withAppendedPath(
                BASE_CONTENT_URI, PATH_PERSON);
        public final static String PERSON_TABLE_NAME ="person";
        public final static String PERSON_ID = BaseColumns._ID;
        public final static String COLUMN_PERSON_FIRSTNAME ="firstname";
        public final static String COLUMN_PERSON_LASTNAME ="lastname";
        public final static String COLUMN_PERSON_EMAIL ="Email";
        public final static String COLUMN_PERSON_PHONE ="phone";
        public final static String COLUMN_PERSON_PASSWORD ="password";

    }


    public static final class AdvertisementEntry implements BaseColumns {


        public static final Uri AD_URI = Uri.withAppendedPath(
                BASE_CONTENT_URI, PATH_ADVERTISEMENT);
        public final static String AD_TABLE_NAME = "advertisements";
        public final static String AD_ID = BaseColumns._ID;
        public final static String COLUMN_AD_BOOK_NAME ="bookname";
        public final static String COLUMN_AD_AUTHOR_NAME ="authorname";
        public final static String COLUMN_AD_IMAGE ="image";
        public final static String COLUMN_AD_PRICE ="price";
        public final static String COLUMN_AD_BOOKTAG ="tag";
        public final static String COLUMN_AD_PERSON_ID ="Personid";

    }


    public static final class OrderlistEntry implements BaseColumns {


        public static final Uri ORDER_URI = Uri.withAppendedPath(
                BASE_CONTENT_URI, PATH_ORDERLIST);
        public final static String ORDER_TABLE_NAME = "orders";
        public final static String ORDER_ID = BaseColumns._ID;
        public final static String ORDER_PERSON_ID = "personid";
        public final static String ORDER_AD_ID = "id";


    }





}
