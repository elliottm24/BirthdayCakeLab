package cs301.birthdaycake;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener{

    private CakeView cakeV;
    private CakeModel cakeM;

    public CakeController(CakeView cakeV) {
        this.cakeV = cakeV;
        cakeM = cakeV.getCakeM();
    }

    @Override
    public void onClick(View view) {
        Log.d("button", "button pressed");

        cakeM.candleLit = false;
        cakeV.invalidate();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Log.d("switch", "switch flipped");

        cakeM.candleExists = false;
        cakeM.candleLit = false;
        cakeV.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        //Log.d();

        cakeM.candleNum = i;
        cakeV.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //Log.d();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //Log.d();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        cakeM.x = (int)motionEvent.getX();
        cakeM.y = (int)motionEvent.getY();

        Log.d("touch","position: " + cakeM.x + ", " + cakeM.y);
        cakeV.invalidate();
        return false;
    }
}
