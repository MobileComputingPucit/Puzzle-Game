package com.example.abc.mc_project;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static Bitmap[] splitImages;
    private int[] img_locations;
    GridView gridview;



    public MainActivity() {
        img_locations = new int[9];
        img_locations[0] = 0;
        img_locations[1] = 1;
        img_locations[2] = 2;
        img_locations[3] = 3;
        img_locations[4] = 4;
        img_locations[5] = 5;
        img_locations[6] = 6;
        img_locations[7] = 7;
        img_locations[8] = 8;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridview = (GridView) findViewById(R.id.gridview);
        ImageAdapter imAdpt = new ImageAdapter(this);
        gridview.setAdapter(imAdpt);




        //Reading image from drawables
        Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        splitImages = splitBitmap(img);

        ImageView v = (ImageView) findViewById(R.id.imgView);
        v.setImageResource(R.drawable.icon);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                        Toast.makeText(getApplicationContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public Bitmap[] splitBitmap(Bitmap picture)
    {
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(picture, 240, 240, true);
        Bitmap[] imgs = new Bitmap[9];
        imgs[0] = Bitmap.createBitmap(scaledBitmap, 0, 0, 80 , 80);
        imgs[1] = Bitmap.createBitmap(scaledBitmap, 80, 0, 80, 80);
        imgs[2] = Bitmap.createBitmap(scaledBitmap,160, 0, 80,80);
        imgs[3] = Bitmap.createBitmap(scaledBitmap, 0, 80, 80, 80);
        imgs[4] = Bitmap.createBitmap(scaledBitmap, 80, 80, 80,80);
        imgs[5] = Bitmap.createBitmap(scaledBitmap, 160, 80,80,80);
        imgs[6] = Bitmap.createBitmap(scaledBitmap, 0, 160, 80,80);
        imgs[7] = Bitmap.createBitmap(scaledBitmap, 80, 160,80,80);
        imgs[8] = Bitmap.createBitmap(scaledBitmap, 160,160,80,80);
        return imgs;
    }

}