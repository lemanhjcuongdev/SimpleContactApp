package com.lmc.myfirstapps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ContactDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="contact.db";
    public static final int DB_VERSION=1;
    public static String TB_NAME="tbl_contact";
    public static String ID="id";
    public static String NAME="name";
    public static String PHONE="phone";
    public Context context;

    public ContactDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE "+TB_NAME+"("+ID+" INTEGER PRIMARY KEY"+", "+NAME+" TEXT, "+PHONE+" TEXT);";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql="DROP TABLE IF EXISTS "+TB_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
    //phuong thuc insert contact
    public void insertContact(Contact contact){
        ContentValues cv = new ContentValues();
        //put du lieu can insert trong doi tuong contentvalue
        cv.put(NAME,contact.getContactName());
        cv.put(PHONE,contact.getPhoneNum());
        //lay ra sqlite db de thuc hien ghi du lieu
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TB_NAME,null,cv);
        if(result!=-1)
            Toast.makeText(context, "Insert Successfully!", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Insert Failed!", Toast.LENGTH_SHORT).show();
    }
    //pthcu update contact
    public void updateContact(Contact contact, String name){
        ContentValues contentUpdate = new ContentValues();
        //put du lieu
        contentUpdate.put(NAME,contact.getContactName());
        contentUpdate.put(PHONE,contact.getPhoneNum());
        //lay ra sqlitedb de thuc hien ghi du lieu
        SQLiteDatabase db = getWritableDatabase();
        int result = db.update(TB_NAME,contentUpdate,NAME+" = ?",new String []{name});
        if (result>0)
            Toast.makeText(context, "Update Successfully!", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Update Failed!", Toast.LENGTH_SHORT).show();

    }
    //pthuc xoa contact
    public void  delContact(String name){
        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete(TB_NAME,NAME+" = ?",new String[] {name});
        if(result>0){
            Toast.makeText(context, "Delete Successfully!", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(context, "Delete Failed!", Toast.LENGTH_SHORT).show();
    }
    //pthuc getAllContact
    public ArrayList<Contact> getAllContact(){
        ArrayList<Contact> result = new ArrayList<>();
        //lay ra sqlitedb thuc hien doc du lieu
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TB_NAME, null);
        while(cursor.moveToNext()){
            Contact contact = new Contact();
            contact.setContactName(cursor.getString(1));
            contact.setPhoneNum(cursor.getString(2));
            result.add(contact);
        }
        return result;
    }
    //search
    public Contact getContactbyName(String name){
        Contact contact = new Contact();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TB_NAME+" WHERE "+NAME+" = ?", new String[] {name});
        if(cursor.getCount()>0){
            contact.setContactName((cursor.getString(1)));
            contact.setPhoneNum(cursor.getString(2));
        }
        return contact;
    }
}
