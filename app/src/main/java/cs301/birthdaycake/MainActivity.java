package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private CakeView cakeV;
    private CakeController cakeC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        cakeV = (CakeView) findViewById(R.id.cakeview);
        cakeC = new CakeController(cakeV);

        Button blownOut = (Button) findViewById(R.id.button);
        blownOut.setOnClickListener(cakeC);

        Switch toggleCandles = (Switch) findViewById(R.id.switch3);
        toggleCandles.setOnCheckedChangeListener(cakeC);

        SeekBar candleNum = (SeekBar) findViewById(R.id.seekBar);
        candleNum.setOnSeekBarChangeListener(cakeC);

        cakeV.setOnTouchListener(cakeC);
    }

    public void goodbye(View button) {
        Log.i("button", "Goodbye");
    }

}
