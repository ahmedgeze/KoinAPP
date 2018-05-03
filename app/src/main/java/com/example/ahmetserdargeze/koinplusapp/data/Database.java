package com.example.ahmetserdargeze.koinplusapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmetserdargeze on 09.04.2018.
 */

public class Database {
    private static final String DATABASE_NAME = "KoinPlusApp";
    private static final String DATABASE_TABLO = "FavCoin";
    private static final int DATABASE_VERSION = 1;

    //Veritabanını kullanacak sınıfları tutan Context nesnesi
    private final Context context;
    //Oluşturduğumuz veritabanıyardımcı sınıfının nesnesi
    private DatabaseHelper dbHelper;
    //Veritabanımızın nesnesi
    private SQLiteDatabase database;

    // Oluşturulacak insanlar tablosunun sütunları
    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_KOIN_NAME = "coin_name";
    public static final String KEY_KUR_ID = "kur_id";

    public Database(Context c) {
        this.context = c;
    }



    public Database openCon() {

        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();

        return this;

    }

    public void closeCon() {

        dbHelper.close();

    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public static final String createAddFavTable= "create table "+  DATABASE_TABLO+"("+KEY_ROW_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                                                                        KEY_KOIN_NAME+" TEXT NOT NULL, "+
                                                                        KEY_KUR_ID+"TEXT NOT NULL);" ;

        public DatabaseHelper(Context contextim) {
            super(contextim, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub

            db.execSQL(createAddFavTable);
//            db.execSQL("CREATE TABLE " + DATABASE_TABLO + " (" + KEY_ROW_ID
//                    + " INTEGER PRIMARY KEY AUTOINCREMENT , " + KEY_KOIN_NAME
//                    + " TEXT NOT NULL, " + KEY_KUR_ID + " TEXT NOT NULL);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLO);
            onCreate(db);

        }

    }

    public long addRecord(String koin_name, String kur_id) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();

        cv.put(KEY_KOIN_NAME, koin_name);
        cv.put(KEY_KUR_ID, kur_id);

        return database.insert(DATABASE_TABLO, null, cv);

    }


    public List<FavCoinModel> VerileriListele() {
        // TODO Auto-generated method stub

        String[] column = new String[] { KEY_ROW_ID, KEY_KOIN_NAME, KEY_KUR_ID};
        Cursor c = database.query(DATABASE_TABLO, column, null, null,
                null, null, null);

        List<FavCoinModel> allRecords_favCoin=new ArrayList<>();


        c.moveToFirst();
        for (int i=0;i<c.getCount();i++){
            FavCoinModel object =new FavCoinModel(Integer.parseInt(c.getString(0)),
                                                    c.getString(1),
                                                    c.getString(2));
            allRecords_favCoin.add(object);
            c.moveToNext();
        }
        return allRecords_favCoin;

    }

    public List<FavCoinModel> ListByKurId(String kur_id){
        List<FavCoinModel> CoinwithKur=new ArrayList<>();
        FavCoinModel singleCoin;

        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT  "+
                Database.KEY_ROW_ID+","+
                Database.KEY_KOIN_NAME+","+
                Database.KEY_KUR_ID+
                " FROM  "+Database.DATABASE_TABLO+
                " WHERE "+
                Database.KEY_KUR_ID+"=?";


        Cursor cursor=db.rawQuery(selectQuery,new String[]{kur_id});
        cursor.moveToFirst();
        for (int i=0;i<cursor.getCount();i++){
            singleCoin=new FavCoinModel(Integer.parseInt(cursor.getString(0)),
                                        cursor.getString(1),
                                        cursor.getString(2));
            CoinwithKur.add(singleCoin);
            cursor.moveToNext();
        }

        return CoinwithKur;




    }

    public String getName(long koin_id) {
        // TODO Auto-generated method stub

        String[] columns = new String[] { KEY_ROW_ID, KEY_KOIN_NAME, KEY_KUR_ID};
        Cursor c = database.query(DATABASE_TABLO, columns, KEY_ROW_ID + "="
                + koin_id, null, null, null, null);

        if (c != null) {
            c.moveToFirst();
            String coin_name = c.getString(1);
            return coin_name;
        }

        return null;
    }

    public String getKur(long koin_id) {
        // TODO Auto-generated method stub
        String[] columns = new String[] { KEY_ROW_ID, KEY_KOIN_NAME, KEY_KUR_ID};
        Cursor c = database.query(DATABASE_TABLO, columns, KEY_ROW_ID + "="
                + koin_id, null, null, null, null);

        if (c != null) {
            c.moveToFirst();
            String kur = c.getString(2);
            return kur;
        }

        return null;
    }

    public String getId(String koinname) {
        // TODO Auto-generated method stub
        Cursor c;
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                Database.KEY_KOIN_NAME+
                " FROM "+Database.DATABASE_TABLO+
                " WHERE "+Database.KEY_KOIN_NAME+"=?";


        c=db.rawQuery(selectQuery,new String[]{koinname});

//        String[] columns = new String[] { KEY_ROW_ID, KEY_KOIN_NAME, KEY_KUR_ID};
//        Cursor c = database.query(DATABASE_TABLO, columns, KEY_KOIN_NAME + "="
//                + koinname, null, null, null, null);

        if (c != null) {
            c.moveToFirst();
            String id = c.getString(0);
            return id;
        }
        return null;
    }


    public int controlWithName(String koinname ,String kur_id){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT  "+
                Database.KEY_KOIN_NAME+","+
                Database.KEY_KUR_ID+
                " FROM  "+Database.DATABASE_TABLO+
                " WHERE "+
                Database.KEY_KOIN_NAME+"=? and "+Database.KEY_KUR_ID+" =?";

        int iCount=0;
        Cursor cursor=db.rawQuery(selectQuery,new String[]{koinname,kur_id});
        iCount=cursor.getCount();

//        String[] columns = new String[] { KEY_ROW_ID, KEY_KOIN_NAME, KEY_KUR_ID};
//        Cursor c = database.query(DATABASE_TABLO, columns, KEY_KOIN_NAME + "=?"
//                + koinname+"'", null, null, null, null);



        return iCount;

    }
    public void updateRecords(long koin_id, String newCoinName,
                              String newKurId) {
        // TODO Auto-generated method stub
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_KOIN_NAME, newCoinName);
        cvUpdate.put(KEY_KUR_ID, newKurId);

        database.update(DATABASE_TABLO, cvUpdate, KEY_ROW_ID + "="
                + koin_id, null);

    }
    public void deleteRecords(long koin_id) {
        // TODO Auto-generated method stub
        database.delete(DATABASE_TABLO, KEY_ROW_ID + "=" + koin_id, null);
    }

    public void deleteAllRecords(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(DATABASE_TABLO,null,null);
        Log.e("dbclean","veritabanaı silindi");

    }

    public void deleteSingleRecords(String coinname){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        database.execSQL("DELETE FROM " + DATABASE_TABLO + " WHERE " + KEY_KOIN_NAME + "= '" + coinname + "'");



        Log.e("dbclean","veritabanaı silindi");

    }

}
