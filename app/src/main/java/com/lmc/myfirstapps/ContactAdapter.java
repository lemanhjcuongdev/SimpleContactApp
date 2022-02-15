package com.lmc.myfirstapps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {
    public ContactAdapter(Context context, ArrayList<Contact> lstContact){
        super(context,0,lstContact);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentView = convertView;
        if(currentView == null){
            //ghép view từ layout item_contact
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact,parent,false);
        }
        //lấy ra position của item contact
        Contact contact = getItem(position);
        //lấy ra các đối tượng điều khiển
        TextView tvName= currentView.findViewById(R.id.tvName);
        TextView tvPhone = currentView.findViewById(R.id.tvPhone);
        ImageButton btnCall = currentView.findViewById(R.id.btnCall);
        ImageButton btnSend = currentView.findViewById(R.id.btnSend);
        //điền dữ liệu vào đối tượng view và xử lí sự kiện
        tvName.setText(contact.getContactName());
        tvPhone.setText(contact.getPhoneNum());
        //Thiết lập xử lí sự kiện cho các nút
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "tel:"+tvPhone.getText().toString();
                Uri uri = Uri.parse(data);
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(uri);
                getContext().startActivity(callIntent);
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "smsto:"+tvPhone.getText().toString();
                Uri uri = Uri.parse(data);
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                getContext().startActivity(sendIntent);
            }
        });

        return currentView;
    }
}
