package com.myappcompany.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9;

    int activePlayer = 0;
    int r = -1;
    int c = -1;
    int red = 1;
    int yellow = -1;
    boolean flag = true;

    Button button;
    TextView textView;

    int ans[][] = new int[3][3];

    ArrayList<Integer> list = new ArrayList<>();

    public void dropIn(View view){
        if (flag==false){
            return;
        }

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (list.contains(tappedCounter)){
            return;
        }
        list.add(tappedCounter);

        if (tappedCounter>=0 && tappedCounter<=2){
            r = 0;
            c= tappedCounter;
        }
        if (tappedCounter>=3 && tappedCounter<=5){
            r = 1;
            c = tappedCounter-3;
        }
        if (tappedCounter>=6 && tappedCounter<=8){
            r=2;
            c = tappedCounter-6;
        }

        if (activePlayer==0){
            counter.setImageResource(R.drawable.red);
            activePlayer = 1;
            ans[r][c] = red;
            if (checkWin(r,c,"RED").equals("RED WINS")){
//                Toast.makeText(this, "RED WINS", Toast.LENGTH_SHORT).show();
                button.setVisibility(view.VISIBLE);
                textView.setVisibility(view.VISIBLE);
                textView.setText("RED WINS");
                flag = false;
                return;
            }
            if (checkWin(r,c,"RED").equals("MATCH DRAW")){
//                Toast.makeText(this, "MATCH DRAW", Toast.LENGTH_SHORT).show();
                button.setVisibility(view.VISIBLE);
                textView.setVisibility(view.VISIBLE);
                textView.setText("MATCH DRAW");
                flag = false;
                return;
            }

        } else {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 0;
            ans[r][c] = yellow;
            if (checkWin(r,c,"YELLOW").equals("YELLOW WINS")){
//                Toast.makeText(this, "YELLOW WINS", Toast.LENGTH_SHORT).show();
                button.setVisibility(view.VISIBLE);
                textView.setVisibility(view.VISIBLE);
                textView.setText("YELLOW WINS");
               flag = false;
                return;
            }
            if (checkWin(r,c,"YELLOW").equals("MATCH DRAW")){
//                Toast.makeText(this, "MATCH DRAW", Toast.LENGTH_SHORT).show();
                button.setVisibility(view.VISIBLE);
                textView.setVisibility(view.VISIBLE);
                textView.setText("MATCH DRAW");

                flag = false;
                return;
            }
        }


        // winning stage

    }
     public String checkWin(int r, int c, String color){

        int count=0;
        if(color.equals("RED")) {
            for (int i = 0; i <= 2; i++) {
                if (ans[r][i]==1){
                    count++;
                }
            }
            if (count==3){
                return "RED WINS";
            }
            count=0;
            for (int i = 0; i <= 2; i++) {
                if (ans[i][c]==1){
                    count++;
                }
            }
            if (count==3){
                return "RED WINS";
            }

             if(ans[0][0] == red && ans[1][1] == red && ans[2][2] == red){
                 return "RED WINS";
             }
            if(ans[0][2] == red && ans[1][1] == red && ans[2][0] == red){
                return "RED WINS";
            }

        }

          count=0;
         if(color.equals("YELLOW")) {
             for (int i = 0; i <= 2; i++) {
                 if (ans[r][i]==-1){
                     count++;
                 }
             }
             if (count==3){
                 return "YELLOW WINS";
             }
             count=0;
             for (int i = 0; i <= 2; i++) {
                 if (ans[i][c]==-1){
                     count++;
                 }
             }
             if (count==3){
                 return "YELLOW WINS";
             }
             if(ans[0][0] == yellow && ans[1][1] == yellow && ans[2][2] == yellow){
                 return "YELLOW WINS";
             }
             if(ans[0][2] == yellow && ans[1][1] == yellow && ans[2][0] == yellow){
                 return "YELLOW WINS";
             }
         }

         int countdraw = 1;
         for (int i = 0; i < 3 ; i++) {
             for (int j = 0; j < 3; j++) {
                 if (ans[i][j] != 0){
                     countdraw++;
                 }
             }
         }
         if (countdraw==10){
             return "MATCH DRAW";
         }
        return "INVALID";
     }

     public void playAgain(View view){

        imageView1.setImageResource(0);
        imageView2.setImageResource(0);
        imageView3.setImageResource(0);
        imageView4.setImageResource(0);
        imageView5.setImageResource(0);
        imageView6.setImageResource(0);
        imageView7.setImageResource(0);
        imageView8.setImageResource(0);
        imageView9.setImageResource(0);

         for (int i = 0; i < 3 ; i++) {
             for (int j = 0; j < 3 ; j++) {
                 ans[i][j] = 0;
             }
         }

        list.clear();
         activePlayer = 0;
         r = -1;
         c = -1;
         red = 1;
         yellow = -1;
         flag = true;

         button.setVisibility(view.INVISIBLE);
         textView.setVisibility(view.INVISIBLE);

         textView.setText(" ");


     }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        getSupportActionBar().hide();
    }
}
