package com.example.booklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    private static final String databaseName="Library";
    private static final String tableName = "Books";
    private static final String ID = "ID";
    private static final String bookName = "Name";
    private static final String authorName="Author";
    private static final String CREATE_TABLE= "CREATE TABLE " +
            tableName + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            bookName + " TEXT, " + authorName + " TEXT)";
    //


    public DataBase(Context context) {
        super(context, databaseName, null, 1);

        //Data data databases dosyasinda olusmasini saglar
        // super(context, String.valueOf(context.getDatabasePath(databaseName)), null, 1);
        //internal memory(dahili bellekte olusmasini istersek kullanacagimiz metot budur.)
        //super(context, new File(Environment.getExternalStorageDirectory(), databaseName).toString(), null, 1);
        //Sd card icerisinde olusmasini istiyorsak kullanacagimiz metot budur
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    public void addBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(bookName , book.getBookName());
        values.put(authorName, book.getAuthorName());
        db.insert(tableName,null,values);
        db.close();
    }
    public List<Book> bookList(){
        List<Book> books = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT*FROM " + tableName;
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            Book book = new Book(
                    Integer.valueOf(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2));
            books.add(book);
        }
        return books;
    }
    public Book BookID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT*FROM " + tableName + " WHERE " + ID + "=" + id;
        Cursor cursor = db.rawQuery(sql,null);
        Book book= new Book();
        if (cursor!=null) {
            cursor.moveToFirst();
        }
        book.setID(Integer.valueOf(cursor.getString(0)));
        book.setBookName(cursor.getString(1));
        book.setAuthorName(cursor.getString(2));
        return book;
    }
    public void delete(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, ID + " = ?" , new String[]{String.valueOf(book.getID())});
        db.close();
    }
    public int update(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(bookName,book.getBookName());
        values.put(authorName,book.getAuthorName());
       int i= db.update(tableName,values,ID + " = ?", new String[]{String.valueOf(book.getID())});
        db.close();
        return i;
    }
}
