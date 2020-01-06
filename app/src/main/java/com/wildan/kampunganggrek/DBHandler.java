package com.wildan.kampunganggrek;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "produk_anggrek";
    private static final String TABLE_NAME = "anggrek";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_HARGA = "harga";
    private static final String COLUMN_GB = "gambar";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // FUNGSI UNTUK MEMBUAT DATABASENYA
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAMA + " TEXT,"
                + COLUMN_HARGA + " DOUBLE" + ")";
        db.execSQL(CREATE_USER_TABLE);

        db.execSQL("insert into " + TABLE_NAME + " values(1,'Anggrek Bulan',120000), (2,'Anggrek Vanda',350000),(3,'Anggrek Putih',200000),(4,'Anggrek Ungu',210000), (5,'Anggrek Dendrobium Secund',350000),(6,'Anggrek Candy Strip',150000)");
    }

    // FUNGSI UNTUK MENGECEK DATABASE ADA ATAU TIDAK.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // FUNGSI UNTUK TAMBAH DATA Anggrek
    public void tambahAnggrek(String nama, String harga){
        String insertData = "INSERT INTO "+ TABLE_NAME + " ("+ COLUMN_NAMA +","+COLUMN_HARGA+") VALUES ('"+nama +"', "+harga+")";
        this.getWritableDatabase().execSQL(insertData);
    }

    // FUNGSI UNTUK AMBIL 1 DATA Anggrek
    public Anggrek getAnggrek(int id){
        Anggrek anggrek = null;
        String selectData = "SELECT * FROM "+TABLE_NAME + " WHERE id="+id;
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if(data.moveToFirst()){
            anggrek = new Anggrek(Integer.parseInt(data.getString(data.getColumnIndex(COLUMN_ID))),
                    data.getString(data.getColumnIndex(COLUMN_NAMA)), data.getString(data.getColumnIndex(COLUMN_HARGA)));
        }
        return anggrek;
    }

    // FUNGSI UNTUK AMBIL SEMUA DATA Anggrek
    public List<Anggrek> getSemuaAnggrek(){
        List<Anggrek> AnggrekList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                AnggrekList.add(new Anggrek(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAMA)), cursor.getString(cursor.getColumnIndex(COLUMN_HARGA))));
            }while (cursor.moveToNext());
        }
        return AnggrekList;
    }

    // FUNGSI UPDATE DATA Anggrek
    public void updateDataAnggrek(int id, String nama, Double harga) {
        String updateData = "UPDATE " + TABLE_NAME + " SET "+ COLUMN_NAMA + "= '"+nama +"', "+COLUMN_HARGA + "= "+harga + " WHERE "+COLUMN_ID +" ="+id;
        this.getWritableDatabase().execSQL(updateData);
    }

    // FUNGSI HAPUS DATA 1 Anggrek
    public void hapusDataAnggrek(int id) {
        String deleteData = "DELETE FROM "+TABLE_NAME +" WHERE id="+id;
        this.getWritableDatabase().execSQL(deleteData);
    }
}