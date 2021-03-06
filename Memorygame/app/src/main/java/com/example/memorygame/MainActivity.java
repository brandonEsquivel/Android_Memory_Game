package com.example.memorygame;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /* Variables, const */
    long delay1s = 500;
    int colorCode,counter;
    int Points = 0;

    String sequence = "1";
    String Selected = "";

    boolean gameover = false;

    /* To control Events Delays*/
    final Handler handler = new Handler();

    /* Define buttons */
    Button start,btn1,btn2,btn3,btn4,btn5,btn6,txt,pts;

    /* Define de mediaPlayer instance */
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // link views buttons
        start = findViewById(R.id.start_button);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        txt = findViewById(R.id.buttonseq);
        pts = findViewById(R.id.puntaje);
        counter = sequence.length();


        // onclick methods

        // START METHOD
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                disableButtons();
                sequence();
                txt.setText("Seq: " + Selected);
                pts.setText(" PTS: "+Points);
                Toast toast = Toast.makeText(getApplicationContext(), "Iniciando Nuevo Juego!!", Toast.LENGTH_LONG);
                toast.show();
                Ingame();

            }
        });

        // Buttons ONCLICK
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animations(btn1);
                Selected = "" + Selected + '1';   // concat the sequence values
                gameover = !check_seq();
                txt.setText("Seq: " + Selected);
            }

        });
        // Buttons ONCLICK
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animations(btn2);
                Selected = "" + Selected + '2';   // concat the sequence values
                gameover = !check_seq();
                txt.setText("Seq: " + Selected);
            }

        });
        // Buttons ONCLICK
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animations(btn3);
                Selected = "" + Selected + '3';   // concat the sequence values
                gameover = !check_seq();
                txt.setText("Seq: " + Selected);
            }

        });
        // Buttons ONCLICK
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animations(btn4);
                Selected = "" + Selected + '4';   // concat the sequence values
                gameover = !check_seq();
                txt.setText("Seq: " + Selected);

            }

        });
        // Buttons ONCLICK
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animations(btn5);
                Selected = "" + Selected + '5';   // concat the sequence values
                gameover = !check_seq();
                txt.setText("Seq: " + Selected);
            }

        });
        // Buttons ONCLICK
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animations(btn6);
                Selected = "" + Selected + '6';   // concat the sequence values
                gameover = !check_seq();
                txt.setText("Seq: " + Selected);
            }

        });
    }


    /*Checks if the selected sequence is correct*/
    public boolean check_seq(){
        for (int h = 0; h<Selected.length();h++){
            if (sequence.charAt(h)!=Selected.charAt(h)){
                return false;
            }
            else{
                if (h==counter){
                    sequenceOK();
                }
            }

        }

        return true;
    }






    /* Common methods */
    public void animations(Button btn) {
        /* Reactivating the button*/
        btn.setEnabled(true);
        btn.setTextColor(Color.parseColor("#FF000000"));

        /*Getting actual Color*/
        colorCode = ((ColorDrawable)btn.getBackground()).getColor();

        /*Setting animation, changing background color and text color*/
        btn.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        btn.setTextColor(Color.parseColor("#FF9C0202"));

        /*Init and play mediaPlayer*/
        mp = MediaPlayer.create(MainActivity.this, R.raw.start);
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        /*on Completion for mediaPlayer method*/
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                try {
                    Thread.sleep(delay1s); // delay 1s
                    /*Restoring background color and text color*/
                    btn.setBackgroundColor(colorCode);
                    btn.setTextColor(Color.parseColor("#FF000000"));
                } catch (InterruptedException e) {
                }
            }
        });
    }

    public void disableButtons(){
        //disable the buttons
        btn1.setEnabled(false);
        btn1.setTextColor(Color.parseColor("#9E9E9E"));
        btn2.setEnabled(false);
        btn2.setTextColor(Color.parseColor("#9E9E9E"));
        btn3.setEnabled(false);
        btn3.setTextColor(Color.parseColor("#9E9E9E"));
        btn4.setEnabled(false);
        btn4.setTextColor(Color.parseColor("#9E9E9E"));
        btn5.setEnabled(false);
        btn5.setTextColor(Color.parseColor("#9E9E9E"));
        btn6.setEnabled(false);
        btn6.setTextColor(Color.parseColor("#9E9E9E"));
    }

    public void enableButtons(){
        //disable the buttons
        btn1.setEnabled(true);
        btn1.setTextColor(Color.parseColor("#FF000000"));
        btn2.setEnabled(true);
        btn2.setTextColor(Color.parseColor("#FF000000"));
        btn3.setEnabled(true);
        btn3.setTextColor(Color.parseColor("#FF000000"));
        btn4.setEnabled(true);
        btn4.setTextColor(Color.parseColor("#FF000000"));
        btn5.setEnabled(true);
        btn5.setTextColor(Color.parseColor("#FF000000"));
        btn6.setEnabled(true);
        btn6.setTextColor(Color.parseColor("#FF000000"));
    }

    public void PlayActualSequence(){
        for(int i = 0; i < sequence.length(); i++)
        {
            int selector = (int)sequence.charAt(i);
            switch(selector)
            {
                case 1:
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animations(btn1);
                        }
                    }, 4*delay1s);

                        break;
                case 2:
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animations(btn2);
                        }
                    }, 4*delay1s);

                    break;
                case 3:
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animations(btn3);
                        }
                    }, 4*delay1s);

                    break;
                case 4:
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animations(btn4);
                        }
                    }, 4*delay1s);

                    break;
                case 5:
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animations(btn5);
                        }
                    }, 4*delay1s);

                    break;
                default:
                            /*Case for 6*/
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animations(btn6);
                        }
                    }, 4*delay1s);

            }
        }
    }
    public void sequence(){
        Random r = new Random();
        int selector = r.nextInt(6)+1;  // Entre 0 y 5, m??s 1, osea entre 1 y 6.
        sequence = "" + sequence + (char) selector;   // concat the sequence values
        counter++;
    }

    public void sequenceOK(){  // sequence completed ok
        Selected = "";
        Points = Points + 1;
        pts.setText(Points);
        Toast toast = Toast.makeText(getApplicationContext(), "Secuencia Correcta!!", Toast.LENGTH_LONG);
        toast.show();
    }




    public void Ingame(){
      //  secuencia:
            PlayActualSequence();
            enableButtons();

                if (gameover){
                    Intent i = new Intent(this, gameover_Topscores.class);
                    startActivity(i);
                    finish();
                }
    }



} //end main activity