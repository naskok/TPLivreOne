package com.example.tplivre;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class LivreAdapter extends RecyclerView.Adapter<LivreAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<Livre> listeLivres;
    private static int currentSelectedIndex = -1;
    private SparseBooleanArray selectedItems;
    public void toggleSelection(int pos) {
        currentSelectedIndex = pos;
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);

        } else {
            selectedItems.put(pos, true);

        }
        notifyItemChanged(pos);
    }
    public LivreAdapter(Context mContext, ArrayList<Livre> listeLivres) {
        this.mContext = mContext;
        this.listeLivres = listeLivres;
        selectedItems = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View mItemView = (View) LayoutInflater.from(mContext).inflate(
                R.layout.macarteview,null);
        return new MyViewHolder(mItemView);


    }

    @Override
    public void onBindViewHolder( MyViewHolder viewHolder, int i) {
        viewHolder.titre.setText(listeLivres.get(i).getTitre());
        viewHolder.auteur.setText(listeLivres.get(i).getAuteur());
        viewHolder.rating.setRating(listeLivres.get(i).getRating());
        viewHolder.rating.setEnabled(false);


        if ((selectedItems.get(i, false)))
        {
            viewHolder.monLa.setBackgroundResource(R.drawable.rounded_corners);
        }
        else
            viewHolder.monLa.setBackgroundResource(R.drawable.main_background);
        final int pos=i;

        viewHolder.monLa.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toggleSelection(pos);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listeLivres.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titre, auteur;
        public RatingBar rating;
      public  LinearLayout monLa;
        public MyViewHolder(View view) {
            super(view);
             monLa=view.findViewById(R.id.layoutCard);

            titre = (TextView) view.findViewById(R.id.txtTitre);
            auteur = (TextView) view.findViewById(R.id.txtAuteur);
            rating = view.findViewById(R.id.ratingBar);



        }
    }


    public void clearSelections() {

        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public ArrayList<Livre> getSelectedItems() {
        ArrayList<Livre> items =
                new ArrayList<>();
        for (int j = 0; j < selectedItems.size(); j++)
            items.add(listeLivres.get(selectedItems.keyAt(j)));
        return items;
    }
}

