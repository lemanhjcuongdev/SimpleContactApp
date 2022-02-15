package com.lmc.myfirstapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewContactActivity extends AppCompatActivity {
//    EditText edtWeb;
//    Button btnAccess;
    EditText edtName, edtPhone;
    Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
//        edtWeb = findViewById(R.id.edtWebsite);
//        btnAccess = findViewById(R.id.btnAccess);
        edtName=findViewById(R.id.name);
        edtPhone=findViewById(R.id.phone_number);
        btnSave=findViewById(R.id.save);
        btnCancel=findViewById(R.id.cancel);
//        Intent receiveIntent = getIntent();
//        String message = receiveIntent.getStringExtra("msg");
//        Toast.makeText(AddNewContactActivity.this, message, Toast.LENGTH_LONG).show();

//        btnAccess.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String url = edtWeb.getText().toString();
//                if (!url.startsWith("http://") && !url.startsWith("https://"))
//                    url = "http://" + url;
//                Uri webpage = Uri.parse(url);
//                Intent webIntent = new Intent(Intent.ACTION_VIEW,webpage);
//                    startActivity(webIntent);
//            }
//        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentResult = new Intent();
                intentResult.putExtra("name",edtName.getText().toString());
                intentResult.putExtra("phone",edtPhone.getText().toString());
                setResult(RESULT_OK,intentResult);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}