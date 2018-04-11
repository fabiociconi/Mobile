package com.ciconi.labassignment3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

/**
 *
 */
public class Exercise1 extends Activity {

    /**
     * Global Variables....
     */
    private ImageView reusableImageView;
    private TextView textView;
    private Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;
    private RadioButton redColor;
    private RadioButton yellowColor;
    private RadioButton cyanColor;
    private ImageButton buttonUp;
    private ImageButton buttonDown;
    private ImageButton buttonLeft;
    private ImageButton buttonRight;
    private Spinner SpiStrokes;


    private int startx = 0;
    private int starty = 0;
    private int endx = 0;
    private int endy = 0;


    /**
     * Method OnCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise1);

        //get list
        SpiStrokes = findViewById(R.id.SpiStrokes);

        //get resource//
        String[] valuesRest = getResources().getStringArray(R.array.menu_Stokes);

        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1,
                        android.R.id.text1, valuesRest);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Assign adapter to ListView
        SpiStrokes.setAdapter(adapter);


        //Change stroke's size
        SpiStrokes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        paint.setStrokeWidth(5);
                        break;
                    case 1:
                        paint.setStrokeWidth(10);
                        break;
                    case 2:
                        paint.setStrokeWidth(15);
                        break;
                    case 3:
                        paint.setStrokeWidth(20);
                        break;
                }

//                if (position == 0) {
//                    paint.setStrokeWidth(5);
//                } else if (position == 1) {
//                    paint.setStrokeWidth(10);
//                } else if (position == 2) {
//                    paint.setStrokeWidth(15);
//                } else if (position == 3)
//                    paint.setStrokeWidth(20);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //int width, int height, Bitmap.Config config
        bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);

        paint = new Paint();

        //default color
        paint.setColor(Color.RED);

        //take image View
        reusableImageView = findViewById(R.id.ImageViewForDrawing);

        //setting a bitmap as content view for the image
        reusableImageView.setImageBitmap(bitmap);
        reusableImageView.setVisibility(View.VISIBLE);

        //write position
        textView = findViewById(R.id.textView1);

        //down
        buttonDown = findViewById(R.id.buttonDown);
        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endy = endy + 1;
                checkPaint();
                drawLine(canvas, paint);
                reusableImageView.invalidate();
            }
        });
        //UP
        buttonUp = findViewById(R.id.buttonUp);

        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusableImageView.setVisibility(View.VISIBLE);
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endy = endy - 1;
                checkPaint();
                drawLine(canvas, checkPaint());
                reusableImageView.invalidate();
            }
        });

        //left
        buttonLeft = findViewById(R.id.buttonLeft);
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusableImageView.setVisibility(View.VISIBLE);
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endx = endx - 1;
                checkPaint();
                drawLine(canvas, paint);
                reusableImageView.invalidate();

            }
        });

        //Right
        buttonRight = findViewById(R.id.buttonRight);
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endx = endx + 1;
                checkPaint();
                drawLine(canvas, paint);
                reusableImageView.invalidate();
            }
        });
    }

    /**
     * @return
     */
    public Paint checkPaint() {

        redColor = findViewById(R.id.radio_red);
        yellowColor = findViewById(R.id.radio_yellow);
        cyanColor = findViewById(R.id.radio_cyan);

        if (redColor.isChecked()) {
            paint.setColor(Color.RED);
        }
        if (yellowColor.isChecked()) {
            paint.setColor(Color.YELLOW);
        }
        if (cyanColor.isChecked()) {
            paint.setColor(Color.CYAN);
        }

        return paint;
    }

    /**
     * @param canvas
     * @param paint
     */
    public void drawLine(Canvas canvas, Paint paint) {
        textView.setText(String.valueOf("Y: " + endy + "\nX: " + endx));
        canvas.drawLine(startx, starty, endx, endy, paint);
        if (endy < 1) {
            //Toast.makeText(this, "edge y < 0", Toast.LENGTH_SHORT).show();
            endy = 1;
        } else if (endx < 1) {
            //Toast.makeText(this, "edge", Toast.LENGTH_SHORT).show();
            endx = 1;

        } else if (endy > 200) {
            //Toast.makeText(this, "edge", Toast.LENGTH_SHORT).show();
            endy = 200;
        } else if (endx > 200) {
            //Toast.makeText(this, "edge", Toast.LENGTH_SHORT).show();
            endx = 200;
        } else {
            startx = endx;
            starty = endy;
        }
    }

    /**
     * @param view
     */
    public void ClearCanvas(View view) {
        //clear
        reusableImageView.setImageBitmap(bitmap);
        reusableImageView.setVisibility(View.VISIBLE);
        canvas.drawColor(Color.WHITE);
        startx = 0;
        starty = 0;
        endx = 0;
        endy = 0;
        textView.setText("Y:" + starty + "\nX: " + startx);
    }
}
