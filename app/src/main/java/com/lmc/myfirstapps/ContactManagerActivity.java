package com.lmc.myfirstapps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
    ContactDBHelper contactDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_manager);
        btnAddContact = findViewById(R.id.btnAddContact);
        lvContact = findViewById(R.id.lvContact);
//        contactAdapter = new ContactAdapter(ContactManagerActivity.this,lstContact);
//        //thiết lập adapter cho listview
//        lvContact.setAdapter(contactAdapter);
        getListContact();
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactManagerActivity.this,AddNewContactActivity.class);
//                intent.putExtra("msg","Test");
//                startActivity(intent);
                startActivityForResult(intent,INSERT_CODE);
            }
        });
        registerForContextMenu(lvContact);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.item_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);

    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int i = info.position;
        String name = lstContact.get(i).getContactName();

        switch (item.getItemId()){
            case R.id.detail_option:
                Toast.makeText(this, "Chi tiet", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_option:
                Dialog updateDialog = new Dialog(ContactManagerActivity.this);
                updateDialog.setContentView(R.layout.update_dialog);
                updateDialog.show();

                TextView updateName = updateDialog.findViewById(R.id.updateName);
                EditText updatePhone = updateDialog.findViewById(R.id.updatePhone);
                Button btnSave = updateDialog.findViewById(R.id.btnSave);
                Button btnCancel = updateDialog.findViewById(R.id.btnCancel);

                updateName.setText(name);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateDialog.dismiss();
                    }
                });
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        contactDBHelper.updateContact(new Contact(name,updatePhone.getText().toString()),updateName.getText().toString());
                        getListContact();
                        updateDialog.dismiss();
                    }
                });

//                Toast.makeText(this, "update", Toast.LENGTH_SHORT).show();
                break;
            case R.id.del_option:
                contactDBHelper.delContact(name);
                getListContact();
//                Toast.makeText(this, "xoa", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void getListContact(){
        contactDBHelper = new ContactDBHelper(ContactManagerActivity.this);
        lstContact = contactDBHelper.getAllContact();
        contactAdapter = new ContactAdapter(ContactManagerActivity.this,lstContact);
        //set adapter cho listview
        lvContact.setAdapter(contactAdapter);
    }

    //khởi tạo 1 biến ActivityResult

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == INSERT_CODE){
            if(resultCode==RESULT_OK){
                String contactName = data.getStringExtra("name");
                String contactPhone = data.getStringExtra("phone");
                //Thêm đối tượn
                // g Contact vào arrayList
//                lstContact.add(new Contact(contactName,contactPhone));
                //khởi tạo đối tượng contact db helper
                contactDBHelper = new ContactDBHelper(ContactManagerActivity.this);
                //goi pthuc insert contact
                contactDBHelper.insertContact(new Contact(contactName,contactPhone));
                //cập nhật dữ liệu trên listview nếu thêm 1 item mới
//                contactAdapter.notifyDataSetChanged();
                getListContact();
//                Toast.makeText(this,contactName+contactPhone,Toast.LENGTH_LONG).show();
            }
        }else
        super.onActivityResult(requestCode, resultCode, data);
    }
}