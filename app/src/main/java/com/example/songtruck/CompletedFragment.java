package com.example.songtruck;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CompletedFragment extends Fragment {

    private CompletedAdapter mAdapter;

    private SQLiteDatabase mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        SingerDBHelper dbHelper = new SingerDBHelper(container.getContext());
        mDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = getAllItems();

        if (cursor.getCount() == 0) {
            View v = LayoutInflater.from(container.getContext()).inflate(R.layout.completed_fragment, container, false);
            TextView frag = v.findViewById(R.id.comp_frag);
            frag.setText("NO DATA");
            return v;
        } else {
            View v = LayoutInflater.from(container.getContext()).inflate(R.layout.completed_fragment2, container, false);
            RecyclerView mRecyclerView = v.findViewById(R.id.completed_recycler_view);
            mAdapter = new CompletedAdapter(container.getContext(), cursor);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
            return v;
        }


    }


    private Cursor getAllItems() {
        return mDatabase.query(ItemContract.SingerEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null, ItemContract.SingerEntry.COLUMN_TIMESTAMP
        );
    }

}
