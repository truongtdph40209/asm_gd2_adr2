package com.example.asm_ph40209;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_ph40209.adapter.TrangChuAdapter;
import com.example.asm_ph40209.dao.SanPhamDAO;
import com.example.asm_ph40209.model.trangchu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frg_trangchu extends Fragment {
    RecyclerView recycler_trangchu;
    FloatingActionButton float_add;
    private SanPhamDAO sanPhamDAO;

    public frg_trangchu() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frg_trangchu, container, false);

        recycler_trangchu = view.findViewById(R.id.recyclerView_trangchu);
        float_add = view.findViewById(R.id.float_add);
//
        sanPhamDAO = new SanPhamDAO(getContext());
        ArrayList<trangchu> list = sanPhamDAO.selectALL();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_trangchu.setLayoutManager(linearLayoutManager);
        TrangChuAdapter adapter = new TrangChuAdapter(getContext(), list, sanPhamDAO);
        recycler_trangchu.setAdapter(adapter);

        //add
        float_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThem();
                loadData();

            }
        });

        return view;


    }
    private void loadData(){
        ArrayList<trangchu> list = sanPhamDAO.selectALL();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_trangchu.setLayoutManager(linearLayoutManager);
        TrangChuAdapter adapter = new TrangChuAdapter(getContext(), list, sanPhamDAO);
        recycler_trangchu.setAdapter(adapter);
    }
    public void openThem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // tạo view và gán layout
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add, null);
        builder.setView(view); // gắn view vào hộp thoại


        Dialog dialog = builder.create(); //tạo hộp thoại
        dialog.show();// hiện thị hộp thoại lên màn hình

        EditText edt_tensp = view.findViewById(R.id.edt_tensp_add);
        EditText edt_giaban = view.findViewById(R.id.edt_giaban_add);
        EditText edt_soluong = view.findViewById(R.id.edt_soluong_add);
        Button btn_add = view.findViewById(R.id.btn_add_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensp = edt_tensp.getText().toString();
                String giaban = edt_giaban.getText().toString();
                String soluong = edt_soluong.getText().toString();

                if (tensp.length() == 0 || giaban.length() == 0 || soluong.length() == 0){
                    Toast.makeText(getContext(), "Nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    trangchu home = new trangchu(tensp, giaban, soluong);
                    boolean row = sanPhamDAO.add(home);
                    if (row){
                        Toast.makeText(getContext(), "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }

}