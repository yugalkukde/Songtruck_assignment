package com.example.songtruck;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.songtruck.CompletedAdapter.TEXT_PREFS;

public class DetailsActivity extends AppCompatActivity {

    private String singName;
    private int profile;
    private boolean isAvailable;
    private String singPrice;
    private double rating;

    private CircleImageView profileImage;
    private CircleImageView availability;
    private TextView singerDetailsName;
    private TextView singerDetailsPrice;
    private TextView singerRatings;

    private SQLiteDatabase mDatabase;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                , WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_details);

        sharedPreferences = getSharedPreferences(TEXT_PREFS, MODE_PRIVATE);

        profileImage = findViewById(R.id.profile_image2);
        singerDetailsName = findViewById(R.id.detail_name);
        singerDetailsPrice = findViewById(R.id.detail_price);
        availability = findViewById(R.id.singer_availability);
        singerRatings = findViewById(R.id.singer_ratings);

        profile = getIntent().getIntExtra("image", 0);
        singName = getIntent().getStringExtra("name");
        isAvailable = getIntent().getBooleanExtra("available", false);
        singPrice = getIntent().getStringExtra("price");
        rating = getIntent().getDoubleExtra("ratings", 0.0);

        double given = sharedPreferences.getFloat(singName, 0);
        if (Double.valueOf(given).equals(0.0))
            singerRatings.setText("Rating:" + String.valueOf(rating));
        else {
            double original = rating;
            int total = sharedPreferences.getInt(singName + "1", 0);
            double rating1 = (((original * (total - 1)) + given) / total);
            DecimalFormat df = new DecimalFormat("#.##");
            singerRatings.setText("Rating:" + String.valueOf(df.format(rating1)));
        }

        loadUi();
    }

    private void loadUi() {

        singerDetailsName.setText(singName);
        singerDetailsPrice.setText("Price:" + singPrice);

        profileImage.setImageResource(profile);
        if (isAvailable)
            availability.setImageResource(R.drawable.green_color);
        else
            availability.setImageResource(R.drawable.red_color);
    }

    public void bookArtist(View view) {

        if (isAvailable) {
            SingerDBHelper dbHelper = new SingerDBHelper(this);
            mDatabase = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(ItemContract.SingerEntry.COLUMN_NAME, singName);
            cv.put("icon", profile);
            cv.put("rating", 0);
            mDatabase.insert(ItemContract.SingerEntry.TABLE_NAME, null, cv);

            Toast.makeText(this, "Artist Booked", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Artist not Available", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
