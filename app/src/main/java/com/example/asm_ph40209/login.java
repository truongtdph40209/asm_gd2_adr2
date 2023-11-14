package com.example.asm_ph40209;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_ph40209.dao.NguoiDungDAO;

public class login extends AppCompatActivity {
    private NguoiDungDAO nguoiDungDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edt_user = findViewById(R.id.edt_userName);
        EditText edt_pass = findViewById(R.id.edt_passWord);
        Button btn_login = findViewById(R.id.btn_login);
        TextView txt_signup = findViewById(R.id.txt_signUp);

        nguoiDungDAO = new NguoiDungDAO(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edt_user.getText().toString();
                String pass = edt_pass.getText().toString();

                boolean check = nguoiDungDAO.checkLogin(user, pass);
                if (check){
                    Toast.makeText(login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login.this, MainActivity.class));
                }else {
                    Toast.makeText(login.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, dangki.class));
            }
        });
    }
}