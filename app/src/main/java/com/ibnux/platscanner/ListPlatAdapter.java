package com.ibnux.platscanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibnux.platscanner.data.ObjectBox;
import com.ibnux.platscanner.data.PlatNomor;
import com.ibnux.platscanner.data.PlatNomor_;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class ListPlatAdapter extends RecyclerView.Adapter<ListPlatAdapter.MyViewHolder>  {
    private List<PlatNomor> listPlat = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_nama, txt_plat;

        public MyViewHolder(View view) {
            super(view);
            txt_nama = view.findViewById(R.id.txt_nama);
            txt_plat = view.findViewById(R.id.txt_plat);
        }
    }

    public ListPlatAdapter() {
        super();
        listPlat = ObjectBox.get().boxFor(PlatNomor.class).query().orderDesc(PlatNomor_.tanggal).build().find(0,10);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_platnomor, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listPlat.size();
    }
}
