package com.lmc.myfirstapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SampleSharedPreActivity extends AppCompatActivity {

    EditText edtEmail, edtPass;
    CheckBox checkRememPass;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_shared_pre);
        getView();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kiểm tra login nếu check remem được chọn thì sẽ lưu thông tin vào file share preference
                String email = edtEmail.getText().toString(), pass = edtPass.getText().toString();
                if(checkRememPass.isChecked()==true){
                    //luu thong tin
                    //khởi tạo đối tượng của lớp PrefManager và gọi p thức lưu thông tin
                    new PrefManager(SampleSharedPreActivity.this).saveLogin(email,pass);
                }
                //gọi lại activity để thực hiện login thành công
                if(email!=""||email!=null){

                }
            }
        });
    }
    private void getView(){
        edtEmail=findViewById(R.id.edtEmailLogin);
        edtPass=findViewById(R.id.edtPassLogin);
        checkRememPass=findViewById(R.id.checkRememPass);
        btnLogin=findViewById(R.id.btnLogin);
    }
    private void saveLoginInfo(String email, String pass){

    }
}