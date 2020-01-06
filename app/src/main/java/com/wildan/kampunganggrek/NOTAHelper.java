package com.wildan.kampunganggrek;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class NOTAHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DBNAME = "notaanggrek";
    private static final String TABLENAME = "beli";

    private static String colID = "id";
    private static String colNama = "nama";
    private static String colHarga = "harga";
    private static String colDate = "date";

    public NOTAHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLENAME + "("
                + colID + " INTEGER PRIMARY KEY AUTOINCREMENT," + colNama + " TEXT,"
                + colHarga + " TEXT" + ","+colDate+" DATE)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLENAME);
        onCreate(db);
    }

    public void insertData(String nama, String alamat){
        String insertData = "INSERT INTO "+ TABLENAME + " ("+ colNama +","+colHarga+") VALUES ('"+nama +"', '"+alamat+"')";
        this.getWritableDatabase().execSQL(insertData);
    }

    public nota getData(int id){
        nota model = null;
        String selectData = "SELECT * FROM "+TABLENAME + " WHERE id="+String.valueOf(id);
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if(data.moveToFirst()){
            model = new nota(Integer.parseInt(data.getString(data.getColumnIndex(colID))),
                    data.getString(data.getColumnIndex(colNama)), data.getString(data.getColumnIndex(colHarga)), data.getString(data.getColumnIndex(colDate)));
        }
        return model;
    }

    public List<nota> getAll(){
        List<nota> model = new ArrayList<>();
        String selectData = "SELECT * FROM "+TABLENAME;
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if(data.moveToFirst()){
            do{
                model.add(new nota(Integer.parseInt(data.getString(data.getColumnIndex(colID))),
                        data.getString(data.getColumnIndex(colNama)), data.getString(data.getColumnIndex(colHarga)),data.getString(data.getColumnIndex(colDate))));
            }while (data.moveToNext());
        }
        return model;
    }
}