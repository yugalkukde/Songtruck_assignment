package com.example.songtruck;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
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

            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    removeItem((int) viewHolder.itemView.getTag());
                    mAdapter.notifyDataSetChanged();
                }
            }).attachToRecyclerView(mRecyclerView);
            return v;
        }


    }

    private void removeItem(int tag) {
        mDatabase.delete(ItemContract.SingerEntry.TABLE_NAME, ItemContract.SingerEntry.COLUMN_ICON + "=" + tag, null);
        mAdapter.swapCursor(getAllItems());
        mDatabase.close();
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
