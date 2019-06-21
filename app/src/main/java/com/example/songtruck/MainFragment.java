package com.example.songtruck;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private DataItemAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.home_fragment, container, false);

        final ArrayList<ItemHolder> insertList = new ArrayList<>();
        insertList.add(new ItemHolder(R.drawable.taylor_swift_1, "Taylor Swift", "20000", false, 4.0));
        insertList.add(new ItemHolder(R.drawable.justin_bieber, "Justin Beiber", "10000", true, 3.9));
        insertList.add(new ItemHolder(R.drawable.arijit_singh, "Arijit Singh ", "15000", true, 4.1));
        insertList.add(new ItemHolder(R.drawable.eminem_4, "Eminem", "30000", false, 4.8));
        insertList.add(new ItemHolder(R.drawable.bebe_rexha_1, "Bebe Rexha", "5000", true, 4.1));
        insertList.add(new ItemHolder(R.drawable.ed_sheeran_1, "ED Sheeran", "20000", true, 4.3));
        insertList.add(new ItemHolder(R.drawable.selena_gomez_1, "Salena Gomez", "12000", false, 3.8));
        insertList.add(new ItemHolder(R.drawable.adele_1, "Adele", "14000", false, 4.1));
        insertList.add(new ItemHolder(R.drawable.brie_larson, "Brie Larson", "13500", true, 3.2));
        insertList.add(new ItemHolder(R.drawable.ariana_grande_1, "Ariana Grande", "15000", false, 4.2));
        insertList.add(new ItemHolder(R.drawable.britney_spears_7, "Britney Spears", "15000", true, 4.6));
        insertList.add(new ItemHolder(R.drawable.demi_lovato_1, "Demi Lovato", "15000", false, 4.6));
        insertList.add(new ItemHolder(R.drawable.jennifer_lopez_2, "Jennifer Lopez", "15000", true, 4.0));
        insertList.add(new ItemHolder(R.drawable.john_lennon_1, "John Lenon", "15000", false, 3.2));
        insertList.add(new ItemHolder(R.drawable.kelly_clarkson_1, "Kelly Clarkson", "15000", true, 3.8));
        insertList.add(new ItemHolder(R.drawable.miley_cyrus_2, "Miley Cyrus", "15000", true, 4.5));
        insertList.add(new ItemHolder(R.drawable.shawn_mendes_1, "Shawn Mendez", "15000", false, 4.1));
        insertList.add(new ItemHolder(R.drawable.snoop_dogg_1, "Snoop Dogg", "15000", true, 0));
        insertList.add(new ItemHolder(R.drawable.sonu_nigam, "Sonu Nigam", "15000", false, 4.4));
        insertList.add(new ItemHolder(R.drawable.zayn_malik_1, "Zyan Malik", "15000", true, 4.2));
        insertList.add(new ItemHolder(R.drawable.nick_jonas_1, "Nick Jonas", "15000", true, 4.1));

        RecyclerView mRecyclerView = v.findViewById(R.id.main_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(container.getContext(), 2);
        mAdapter = new DataItemAdapter(insertList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new DataItemAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openDetails(position, insertList.get(position).getTextName()
                        , insertList.get(position).isAvailable()
                        , insertList.get(position).getTextPrice()
                        , insertList.get(position).getImageProfile()
                        , insertList.get(position).getRatings());
            }
        });

        return v;
    }

    private void openDetails(int position, String nameCeleb, Boolean available, String price, int imageProfile, double ratings) {
        mAdapter.notifyItemChanged(position);
        Intent i = new Intent(getContext(), DetailsActivity.class);
        i.putExtra("image", imageProfile);
        i.putExtra("name", nameCeleb);
        i.putExtra("available", available);
        i.putExtra("price", price);
        i.putExtra("ratings", ratings);
        startActivity(i);

    }
}
