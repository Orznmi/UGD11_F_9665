package com.pbp.gd11_f_9665.ui.pdf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pbp.gd11_f_9665.R;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    private Context context;
    private View view;
    private Mahasiswa[] mhs;

    public MahasiswaAdapter(Mahasiswa[] mhs){
        this.mhs=mhs;
    }

    @NonNull
    @Override
    public MahasiswaAdapter.MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_mahasiswa, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.MahasiswaViewHolder holder, int position) {
            final Mahasiswa listMahasiswa = mhs[position];
            if(position==0){
                holder.txtNo.setText("No.");
            }else{
                holder.txtNo.setText(String.valueOf(mhs[position].getNomor()));
            }
            holder.txtNama.setText(mhs[position].getNama());
            holder.txtNIM.setText(mhs[position].getNim());
    }

    @Override
    public int getItemCount() {
        return mhs.length;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNo, txtNama, txtNIM;
        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNo=itemView.findViewById(R.id.tvNomorMahasiswa);
            txtNama=itemView.findViewById(R.id.tvNamaMahasiswa);
            txtNIM=itemView.findViewById(R.id.tvNIMMAhasiswa);
        }
    }



}
