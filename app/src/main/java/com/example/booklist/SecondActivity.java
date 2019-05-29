package com.example.booklist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private ImageView img;
    private TextView tvBook;
    private TextView tvBookName;
    private TextView tvAuthor;
    private TextView tvAuthorName;
    private Button delete;
    private TextView etBook;
    private TextView etBookName;
    private TextView etAuthor;
    private TextView etAuthorName;
    private Button update;
    Context context = this;
    DataBase dataBase = new DataBase(context);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        img = findViewById(R.id.img);
        tvBook = findViewById(R.id.tvBook);
        tvBookName = findViewById(R.id.tvBookName);
        tvAuthor = findViewById(R.id.tvAuthor);
        tvAuthorName = findViewById(R.id.tvAuthorName);
        delete = findViewById(R.id.delete);
        etBook = findViewById(R.id.etBook);
        etBookName = findViewById(R.id.etBookName);
        etAuthor = findViewById(R.id.etAuthor);
        etAuthorName = findViewById(R.id.etAuthorName);
        update = findViewById(R.id.update);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",-1);

        final Book book = dataBase.BookID((id));
        tvBookName.setText(book.getBookName());
        tvAuthorName.setText(book.getAuthorName());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase.delete(book);
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book.setBookName(etBookName.getText().toString());
                book.setAuthorName(etAuthorName.getText().toString());
                int i = dataBase.update(book);
                Intent intent = new Intent(context,MainActivity.class);
                intent.putExtra("up" , i);
                startActivity(intent);
                finish();
            }
        });

    }
}
