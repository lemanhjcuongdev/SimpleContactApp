package com.lmc.myfirstapps;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    Context context;

    public PrefManager(Context context) {
        this.context = context;
    }
    public void saveLogin(String email, String pass){
        //tạo đối tượng SharedPreference
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginInfo",Context.MODE_PRIVATE);
        //lấy ra đối tượng Editỏr để thực hiện ghi dữ liệu
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //thực hiện put dữ liệu vào file
        editor.putString("email",email);
        editor.putString("pass",pass);
        //gọi phương thức commit để hoàn thành viẹc ghi file
        editor.commit();
    }
    //đọc dữ liệu từ file
    public String getEmail(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginInfo",Context.MODE_PRIVATE);
        return sharedPreferences.getString("email",null);
    }
}
