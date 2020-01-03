package com.ibnux.platscanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibnux.platscanner.data.LogPlatNomor;
import com.ibnux.platscanner.data.LogPlatNomor_;
import com.ibnux.platscanner.data.ObjectBox;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListLogPlatAdapter extends RecyclerView.Adapter<ListLogPlatAdapter.MyViewHolder>  {
    private List<LogPlatNomor> listPlat = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_nama, txt_plat;

        public MyViewHolder(View view) {
            super(view);
            txt_nama = view.findViewById(R.id.txt_nama);
            txt_plat = view.findViewById(R.id.txt_plat);
        }
    }

    public ListLogPlatAdapter() {
        super();
        getData();
    }

    public void getData(){
        listPlat.clear();
        listPlat = ObjectBox.get().boxFor(LogPlatNomor.class).query().orderDesc(LogPlatNomor_.tanggal).build().find(0,20);
        notifyDataSetChanged();
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
        LogPlatNomor plat = listPlat.get(position);
        holder.txt_plat.setText(plat.plat);
        if(plat.siapa!=null && plat.siapa.length()>1){
            holder.txt_nama.setText(plat.siapa);
        }else{
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(plat.tanggal);
            holder.txt_nama.setText(cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+" "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE));
        }
    }

    @Override
    public int getItemCount() {
        return listPlat.size();
    }
}
