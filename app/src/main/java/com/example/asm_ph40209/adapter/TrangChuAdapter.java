package com.example.asm_ph40209.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_ph40209.R;
import com.example.asm_ph40209.dao.SanPhamDAO;
import com.example.asm_ph40209.model.trangchu;

import java.util.ArrayList;

public class TrangChuAdapter extends RecyclerView.Adapter<TrangChuAdapter.ViewHolder>{
    private Context context;
    private ArrayList<trangchu> list;
    private SanPhamDAO sanPhamDAO;

    public TrangChuAdapter(Context context, ArrayList<trangchu> list, SanPhamDAO sanPhamDAO) {
        this.context = context;
        this.list = list;
        this.sanPhamDAO = sanPhamDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_trangchu, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_tensp.setText(list.get(position).getTensp());
        holder.txt_dongia.setText(list.get(position).getGiaban());
        holder.txt_soluong.setText(list.get(position).getSoluong());


        trangchu home = list.get(holder.getAdapterPosition());
        //update
        holder.txt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSua(home);
            }
        });

        //xóa
        holder.txt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context); //tạo dối tượng
                builder.setIcon(R.drawable.canhbao); //set icon
                builder.setMessage("Bạn chắc chắn muốn xóa");
//                tạo nut button YES
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean row = sanPhamDAO.delete(home.getMasp());
                        if (row){
                            loadData();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create(); //tạo hộp thoại
                dialog.show();

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_tensp, txt_dongia, txt_soluong, txt_update, txt_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_tensp = itemView.findViewById(R.id.txt_tensp_trangchu);
            txt_dongia = itemView.findViewById(R.id.txt_dongia_trangchu);
            txt_soluong = itemView.findViewById(R.id.txt_soluong_trangchu);
            txt_update = itemView.findViewById(R.id.txt_update_trangchu);
            txt_delete = itemView.findViewById(R.id.txt_delete_trangchu);


        }
    }
    //opendialog
    public void openSua(trangchu home) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update, null);
        builder.setView(view); // gắn view vào hộp thoại
        Dialog dialog = builder.create(); //tạo hộp thoại
        dialog.show();// hiện thị hộp thoại lên màn hình
        //ánh xạ
        EditText edt_tensp = view.findViewById(R.id.edt_tensp_update);
        EditText edt_giaban = view.findViewById(R.id.edt_giaban_update);
        EditText edt_soluong = view.findViewById(R.id.edt_soluong_update);
        Button btn_update = view.findViewById(R.id.btn_update_update);

        edt_tensp.setText(home.getTensp());
        edt_giaban.setText(home.getGiaban());
        edt_soluong.setText(home.getSoluong());

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masp = home.getMasp();
                String tensp = edt_tensp.getText().toString();
                String giaban = edt_giaban.getText().toString();
                String soluong = edt_soluong.getText().toString();

                if (tensp.length() == 0 || giaban.length() == 0 || soluong.length() == 0){
                    Toast.makeText(context, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else{
                    trangchu home = new trangchu(masp, tensp, giaban, soluong);
                    boolean row = sanPhamDAO.update(home);
                    if (row){
                        Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(context, "Update không thành công", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });


    }
    private void loadData(){
        list.clear();
        list = sanPhamDAO.selectALL();
        notifyDataSetChanged();
    }
}
