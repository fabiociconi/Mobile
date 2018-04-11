package com.ciconi.fabiociconi_comp304_003test1;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class StockPriceActivity extends AppCompatActivity {

    private TextView stringTextView;
    private ArrayList<String> checkedStates = new ArrayList<String>();
    private ImageView imageView;//background to drawing
    private Paint paint;//sets color, thickness, style etc
    private Bitmap bitmap;//picture format
    private Canvas canvas;//contains drawing methods


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sotck_price);
        setTitle("Stock Price");

        imageView = findViewById(R.id.imageView);
        int width = (int) getResources().getDimension(R.dimen.img_width);
        int height = (int) getResources().getDimension(R.dimen.img_height);


        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);

        canvas.drawColor(Color.BLACK);
        imageView.setImageBitmap(bitmap);
        imageView.draw(canvas);
        DrawLineAXY();
        canvas.translate(0, canvas.getHeight());
        canvas.scale(1, -1);

        checkedStates = getIntent().getExtras().getStringArrayList("checkedStates");
        for (int i = 0; i < checkedStates.size(); i++) {

            int resourceID = getResources().getIdentifier
                    (checkedStates.get(i).toLowerCase() + "StockValues", "array", this.getPackageName());

            String[] line = getResources().getStringArray(resourceID);
            int[] colors = {Color.YELLOW, Color.RED, Color.CYAN, Color.WHITE, Color.BLUE, Color.GREEN};

            // set up the paint
            paint = new Paint();
            paint.setColor(colors[i]);
            paint.setStrokeWidth(10);

            DrawLines(line, paint);

            stringTextView = findViewById(R.id.stocksSelected);
            stringTextView.setText(("Charts: ") + checkedStates);
        }
    }

    private void DrawLineAXY() {

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);
        paint.setTextSize(50);

        canvas.drawText("0",0,600,paint);
        canvas.drawText("8",700,580,paint);
        canvas.drawText("600",0,60,paint);

        for(int i =0 ; i< 8; i++) {
            canvas.drawLine(i*100, 600,i*100+10 , 590, paint);
        }
        for(int i =0 ; i< 8; i++) {
            canvas.drawLine(0, i*100,10 , i*100+10, paint);
        }


    }
    private void DrawLines(String[] ibm, Paint paint) {

        int startx = 0;
        int starty = 0;
        int stopx = 0;
        int stopy = 0;

        for (int i = 0; i < ibm.length; i++) {
            int pos = Integer.parseInt(ibm[i]);
            if (i == 0) {
                startx = 0;
                starty = pos;
                stopx = 100;
                stopy = pos;
                canvas.drawLine(startx, starty, stopx, stopy, paint);
            } else {
                startx = stopx;
                starty = stopy;
                stopx = i * 100;
                stopy = pos;
                canvas.drawLine(startx, starty, stopx, stopy, paint);
            }
        }
    }
}
