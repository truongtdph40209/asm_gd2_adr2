package com.example.asm_ph40209;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_ph40209.dao.NguoiDungDAO;

public class dangki extends AppCompatActivity {
    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        EditText edt_user = findViewById(R.id.edt_userName_dk);
        EditText edt_pass = findViewById(R.id.edt_passWord_dk);
        EditText edt_fullpass = findViewById(R.id.edt_passWord_dkfull);
        EditText edt_hoten = findViewById(R.id.edt_hoten_dk);
        Button btn_signup = findViewById(R.id.btn_signUp);
        Button btn_back = findViewById(R.id.btn_back);

        nguoiDungDAO = new NguoiDungDAO(this);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edt_user.getText().toString();
                String pass = edt_pass.getText().toString();
                String fullpass = edt_fullpass.getText().toString();
                String fullname = edt_hoten.getText().toString();

                if (!pass.equals(fullpass) ){
                    Toast.makeText(dangki.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                }else {
                    boolean check = nguoiDungDAO.dangki(user, pass, fullname);
                    if (check){
                        Toast.makeText(dangki.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(dangki.this, login.class));

                    }else {
                        Toast.makeText(dangki.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });






    }
}