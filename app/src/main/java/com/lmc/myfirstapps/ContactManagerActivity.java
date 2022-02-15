package com.lmc.myfirstapps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactManagerActivity extends AppCompatActivity {

    FloatingActionButton btnAddContact;
    final int INSERT_CODE = 1000;
    //khai báo arrayList
    ArrayList<Contact> lstContact = new ArrayList<>();
    ListView lvContact;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_manager);
        btnAddContact = findViewById(R.id.btnAddContact);
        lvContact = findViewById(R.id.lvContact);
        contactAdapter = new ContactAdapter(ContactManagerActivity.this,lstContact);
        //thiết lập adapter cho listview
        lvContact.setAdapter(contactAdapter);
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactManagerActivity.this,AddNewContactActivity.class);
//                intent.putExtra("msg","Test");
//                startActivity(intent);
                startActivityForResult(intent,INSERT_CODE);
            }
        });
    }

    //khởi tạo 1 biến ActivityResult

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == INSERT_CODE){
            if(resultCode==RESULT_OK){
                String contactName = data.getStringExtra("name");
                String contactPhone = data.getStringExtra("phone");
                //Thêm đối tượng Contact vào arrayList
                lstContact.add(new Contact(contactName,contactPhone));
                //cập nhật dữ liệu trên listview nếu thêm 1 item mới
                contactAdapter.notifyDataSetChanged();
//                Toast.makeText(this,contactName+contactPhone,Toast.LENGTH_LONG).show();
            }
        }else
        super.onActivityResult(requestCode, resultCode, data);
    }
}