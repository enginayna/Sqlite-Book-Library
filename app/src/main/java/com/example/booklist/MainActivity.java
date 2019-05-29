package com.example.booklist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private EditText authorName;
    private EditText bookName;
    private Button buttonAdd;
    Context context = this;
    Book book;
    List<Book> bookList;
    ArrayList<String> list = new ArrayList<>();
    DataBase db= new DataBase(context);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        bookName=findViewById(R.id.bookName);
        authorName=findViewById(R.id.authorName);
        buttonAdd = findViewById(R.id.buttonAdd);

        bookList=db.bookList();
        if (bookList !=null){
            for (int i=0; i<bookList.size();i++){
                list.add(bookList.get(i).getBookName());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,android.R.id.text1,list);
        listView.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = bookName.getText().toString();
                String b = authorName.getText().toString();
                if( a.equals(null) ||  b.equals(null) || a.equals(null) &&  b.equals(null) ){
                    Toast.makeText(context,"Book name or Author name are empty", Toast.LENGTH_LONG).show();

                }else{
                    book = new Book(bookName.getText().toString(),authorName.getText().toString());
                    db.addBook(book);
                    bookList = db.bookList();
                    list.clear();
                        for (int i=0; i<bookList.size();i++){
                            list.add(bookList.get(i).getBookName());
                        }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,android.R.id.text1,list);
                    listView.setAdapter(adapter);
                }
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("id" , bookList.get(position).getID());
                Log.e("ID" , String.valueOf(bookList.get(position).getID()));
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // db.onUpgrade(db.getWritableDatabase(),1,2);
        bookList=db.bookList();
        list.clear();
        if (bookList !=null){
            for (int i=0; i<bookList.size();i++){
                list.add(bookList.get(i).getBookName());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,android.R.id.text1,list);
        listView.setAdapter(adapter);
    }


}
