package com.furkanyaylacesme.riyazsalih;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder> {
    private Context mContext;
    private ArrayList<Kategori> mKategoriList;

    public KategoriAdapter(Context context, ArrayList<Kategori> kategoriler){
        this.mContext=context;
        mKategoriList=kategoriler;
        Log.e("YYYY","KategoriAdapter: "+mKategoriList.size());
    }

    @Override
    public KategoriViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.one_kategori_row,viewGroup,false);
        KategoriViewHolder kategoriViewHolder =new KategoriViewHolder(view);
        Log.e("YYYY","onCreateViewHolder mKategorList size: "+mKategoriList.size());
        return kategoriViewHolder;
    }

    @Override
    public void onBindViewHolder(KategoriViewHolder kategoriViewHolder, int i) {
        Kategori temp=mKategoriList.get(i);
        Log.e("YYYY","Kategori Adı:"+temp.getKategoriAdi());
        Log.e("YYYY","onBindViewHolder mKategorList size: "+mKategoriList.size());
        kategoriViewHolder.textView.setText(""+temp.getKategoriAdi());

    }

    @Override
    public int getItemCount() {
        return mKategoriList.size();
    }

    class KategoriViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public KategoriViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tvKategoriAdi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Kategori temp=mKategoriList.get(getAdapterPosition());
                    Intent intent=new Intent(mContext,Metinler.class);
                    intent.putExtra("kategoriId",temp.getKategoriID());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
