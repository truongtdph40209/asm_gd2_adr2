package com.example.asm_ph40209;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import kotlinx.coroutines.channels.ProducerScope;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView nav;
    Toolbar toolbar;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Trang Chủ");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        //set mặc định khi chạy lên
        getSupportFragmentManager().beginTransaction().replace(R.id.frmNav, new frg_trangchu()).commit();



        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.dangxuat){
                    Toast.makeText(MainActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                    finish();
                }else if (item.getItemId()==R.id.trangchu) {
                    frg_trangchu trangchu = new frg_trangchu();
                    replaceFragment(trangchu);
                    Toast.makeText(MainActivity.this, "Trang chủ", Toast.LENGTH_SHORT).show();
                }else if (item.getItemId()==R.id.dautrang) {
                    frg_dautrang dautrang = new frg_dautrang();
                    replaceFragment(dautrang);
                    Toast.makeText(MainActivity.this, "Dấu trang", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId()==R.id.caidat) {
                    frg_caidat caidat = new frg_caidat();
                    replaceFragment(caidat);
                    Toast.makeText(MainActivity.this, "Cài đặt", Toast.LENGTH_SHORT).show();
                }

                //set title
                getSupportActionBar().setTitle(item.getTitle());
                return true;
            }
        });
    }
    public void replaceFragment(Fragment frg){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmNav,frg).commit();
    }
}