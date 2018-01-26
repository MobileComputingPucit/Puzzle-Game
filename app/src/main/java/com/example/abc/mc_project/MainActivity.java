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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static Bitmap[] splitImages;
    private int[] img_locations;
    private int previouslyClicked;
    public Button btn ;
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
        previouslyClicked = -1;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridview = (GridView) findViewById(R.id.gridview);
        ImageAdapter imAdpt = new ImageAdapter(this);
        gridview.setAdapter(imAdpt);
        btn = (Button)findViewById(R.id.resetBtn);
        btn.setOnClickListener(this);



        //Reading image from drawables
        Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        splitImages = splitBitmap(img);
        Random_img();
        ImageView v = (ImageView) findViewById(R.id.imgView);
        v.setImageResource(R.drawable.icon);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                if(previouslyClicked==-1) {
                    previouslyClicked = position;
                }
                else
                {
                    if(is_neighbour(position,previouslyClicked))
                    {
                        swap_resourses( position,gridview);

                        if(check_Win_condition()==true) {

                            Intent i = new Intent(getApplicationContext(),finishingActivity.class);
                            startActivity(i);

                        }
                        previouslyClicked = -1;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Only neighbouring tiles can move", Toast.LENGTH_SHORT).show();
                        previouslyClicked =-1;
                    }
                }
            }
        });

    }

    public boolean is_neighbour(int curPosition,int prePosition) {

        switch(prePosition) {
            case 0:
                if(curPosition==1 || curPosition==3)
                    return true;
                else
                    return  false;
            case 1:
                if(curPosition==0 || curPosition==2 || curPosition ==4 )
                    return true;
                else
                    return false;
            case 2:
                if(curPosition==1 || curPosition==5 )
                    return true;
                else
                    return false;
            case 3:
                if(curPosition==0 || curPosition==6 || curPosition ==4 )
                    return true;
                else
                    return false;
            case 4:
                if(curPosition==1 || curPosition==3 || curPosition ==5 || curPosition==7 )
                    return true;
                else
                    return false;
            case 5:
                if(curPosition==8 || curPosition==2 || curPosition ==4 )
                    return true;
                else
                    return false;
            case 6:
                if(curPosition==3 || curPosition==7  )
                    return true;
                else
                    return false;
            case 7:
                if(curPosition==6 || curPosition==4 || curPosition ==8 )
                    return true;
                else
                    return false;
            case 8:
                if(curPosition==5 || curPosition==7 )
                    return true;
                else
                    return false;
        }

        return false;
    }
    public void swap_resourses(int position,GridView gridview)
    {
        Bitmap btmap = splitImages[previouslyClicked];
        splitImages[previouslyClicked]= splitImages[position];
        splitImages[position]=btmap;
        int tmp = img_locations[previouslyClicked];
        img_locations[previouslyClicked]=img_locations[position];
        img_locations[position]=tmp;

        ImageAdapter imAdpt = new ImageAdapter(getApplicationContext());
        gridview.setAdapter(imAdpt);
    }
    public void Random_img() {

        Random r = new Random();
        for (int i = 0; i < 1; i++) {
            int random1 = r.nextInt(8);
            int random2 = r.nextInt(8);
            Bitmap bt = splitImages[random1];
            splitImages[random1] = splitImages[random2];
            splitImages[random2] = bt;

            int temp = img_locations[random1];
            img_locations[random1] = img_locations[random2];
            img_locations[random2] = temp;
        }
    }

    public boolean check_Win_condition() {
        if (img_locations[0] == 0 && img_locations[1] == 1 && img_locations[2] == 2 && img_locations[3] == 3 && img_locations[4] == 4 && img_locations[5] == 5 &&
                img_locations[6] == 6 && img_locations[7] == 7 && img_locations[8] == 8) {
            return true;
        }
        return  false;
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

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.resetBtn)) {
            Random_img();
            ImageAdapter imAdpt = new ImageAdapter(getApplicationContext());
            gridview.setAdapter(imAdpt);
        }

    }
}