package com.example.songtruck;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.CompletedViewHolder> {

    public static final String TEXT_PREFS = "shared preferences new";

    private Context mContext;
    private Cursor mCursor;
    private int value;


    public CompletedAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    @NonNull
    @Override
    public CompletedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.completed_item_layout, parent, false);
        return new CompletedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedViewHolder holder, int position) {

        mCursor.moveToPosition(position);
        final String name = mCursor.getString(mCursor.getColumnIndex(ItemContract.SingerEntry.COLUMN_NAME));
        final int icon = mCursor.getInt(mCursor.getColumnIndex(ItemContract.SingerEntry.COLUMN_ICON));
        holder.singerName.setText(name);
        holder.mProfile.setImageResource(icon);
        holder.rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(mContext).inflate(R.layout.rating_dailog, null);
                final RatingBar rBar = v.findViewById(R.id.input_rating_bar);
                AlertDialog.Builder alrt = new AlertDialog.Builder(mContext)
                        .setTitle("Select The Rating")
                        .setView(v)
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                SharedPreferences sharedPreferences = mContext.getSharedPreferences(TEXT_PREFS, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                int val = sharedPreferences.getInt(name + "1", value);
                                if (val != 0) {
                                    value = val;
                                    Log.i("ON BIND VIEW", "onClick: ");
                                }
                                editor.putFloat(name, rBar.getRating());
                                editor.putInt(name + "1", ++value);
                                editor.apply();


                                Toast.makeText(mContext, "Thanks for rating", Toast.LENGTH_SHORT).show();
                            }
                        });
                alrt.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class CompletedViewHolder extends RecyclerView.ViewHolder {

        public TextView singerName;
        public Button rateButton;
        public CircleImageView mProfile;

        public CompletedViewHolder(@NonNull View itemView) {
            super(itemView);

            singerName = itemView.findViewById(R.id.textview_name_completed);
            mProfile = itemView.findViewById(R.id.profile_image_completed);
            rateButton = itemView.findViewById(R.id.rate_button);

        }
    }
}
