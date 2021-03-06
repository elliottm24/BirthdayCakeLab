package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CakeView extends SurfaceView {

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint textPaint = new Paint();
    Paint balloonTopPaint = new Paint();
    Paint balloonString = new Paint();
    Paint square1 = new Paint();
    Paint square2 = new Paint();

    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 40.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;
    private CakeModel cakeM;

    public CakeModel getCakeM() {
        return cakeM;
    }

    public void setCakeM(CakeModel cakeM) {
        this.cakeM = cakeM;
    }

    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        cakeM = new CakeModel();

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(0xFFC755B5);  //violet-red
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(50);
        balloonTopPaint.setColor(Color.BLUE);
        balloonTopPaint.setStyle(Paint.Style.FILL);
        balloonString.setColor(Color.GRAY);
        balloonString.setStyle(Paint.Style.FILL);
        square1.setColor(Color.GREEN);
        square1.setStyle(Paint.Style.FILL);
        square2.setColor(Color.RED);
        square2.setStyle(Paint.Style.FILL);

        setBackgroundColor(Color.WHITE);  //better than black default

    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
        if (cakeM.candleExists == true){
            canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);

            if (cakeM.candleLit == true) {
                //draw the outer flame
                float flameCenterX = left + candleWidth / 2;
                float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                //draw the inner flame
                flameCenterY += outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
            }

            //draw the wick
            float wickLeft = left + candleWidth/2 - wickWidth/2;
            //float wickRight = left + candleWidth/2 - wickWidth/2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
        }

    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        canvas.drawText("Location: " + cakeM.x + "," + cakeM.y, 1350, 530, textPaint);

        //canvas.drawOval(cakeM.x + 20, cakeM.y + 10, cakeM.x - 20, cakeM.y + 10, balloonTopPaint);

        //Now a candle in the center
        if (cakeM.candleNum != 0){
            for (int i = 0; i < cakeM.candleNum; i++){
                //drawCandle(canvas, cakeLeft + cakeWidth/3 - candleWidth/2, cakeTop);
                drawCandle(canvas, cakeLeft + ((i+1)*(cakeWidth/(cakeM.candleNum+1))) - candleWidth/2, cakeTop);
            }
        }

        canvas.drawRect(cakeM.x,cakeM.y,cakeM.x + 80,cakeM.y + 80,square1);
        canvas.drawRect(cakeM.x - 80,cakeM.y - 80,cakeM.x,cakeM.y,square1);
        canvas.drawRect(cakeM.x,cakeM.y - 80,cakeM.x + 80,cakeM.y,square2);
        canvas.drawRect(cakeM.x - 80,cakeM.y,cakeM.x,cakeM.y + 80,square2);

        canvas.drawRect(cakeM.x-5, cakeM.y, cakeM.x+5, cakeM.y+100, balloonString);
        canvas.drawOval(cakeM.x-30, cakeM.y-60, cakeM.x+30, cakeM.y+60, balloonTopPaint);

    }//onDraw

}//class CakeView

