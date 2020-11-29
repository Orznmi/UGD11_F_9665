package com.pbp.gd11_f_9665;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.card.MaterialCardView;
import com.pbp.gd11_f_9665.BukuAPI;
import com.pbp.gd11_f_9665.Buku;
import com.pbp.gd11_f_9665.BukuAPI;
import com.pbp.gd11_f_9665.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.DELETE;


public class AdapterBuku extends RecyclerView.Adapter<AdapterBuku.adapterBukuViewHolder> {

    private List<Buku> bukuList;
    private List<Buku> bukuListFiltered;
    private Context context;
    private View view;

    public AdapterBuku(Context context, List<Buku> bukuList) {
        this.context            = context;
        this.bukuList           = bukuList;
    }

    @NonNull
    @Override
    public AdapterBuku.adapterBukuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.list_buku, parent, false);
        return new AdapterBuku.adapterBukuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBuku.adapterBukuViewHolder holder, int position) {
        final Buku buku = bukuList.get(position);

        NumberFormat formatter = new DecimalFormat("#,###");
        holder.txtNama.setText(buku.getNamaBuku());
        holder.txtPengarang.setText(buku.getPengarang());
        holder.txtHarga.setText("Rp "+ formatter.format(buku.getHarga()));
        Glide.with(context)
                .load(BukuAPI.URL_IMAGE+buku.getGambar())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.ivGambar);
    }

    @Override
    public int getItemCount() {
        return bukuList.size();
    }

    public class adapterBukuViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtPengarang, txtHarga;
        private ImageView ivGambar;
        private CardView cardBuku;

        public adapterBukuViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama         = itemView.findViewById(R.id.txtNama);
            txtPengarang    = itemView.findViewById(R.id.txtPengarang);
            txtHarga        = itemView.findViewById(R.id.txtHarga);
            ivGambar        = itemView.findViewById(R.id.ivGambar1);
            cardBuku        = itemView.findViewById(R.id.item_card);
        }
    }
//
//    public void loadFragment(Fragment fragment) {
//        AppCompatActivity activity = (AppCompatActivity) view.getContext();
//        FragmentManager fragmentManager = activity.getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.rvBuku,fragment)
//                .commit();
//    }

}