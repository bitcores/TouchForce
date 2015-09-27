package net.bitcores.touchforce;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;


public class MainActivity extends Activity {

    private static LinearLayout linearLayout;
    private static HashMap<Integer, TextView> pointerList = new HashMap<Integer, TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout)this.findViewById(R.id.linearLayout);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        int pointerIndex = MotionEventCompat.getActionIndex(event);

        if (!pointerList.containsKey(pointerIndex)) {
            TextView tv = new TextView(this);
            linearLayout.addView(tv);
            pointerList.put(pointerIndex, tv);
        }

        float pointerPressure = event.getPressure(pointerIndex);

        pointerList.get(pointerIndex).setText(String.valueOf(pointerPressure));

        if (action == MotionEvent.ACTION_POINTER_UP) {
            linearLayout.removeView(pointerList.get(pointerIndex));
            pointerList.remove(pointerIndex);
        } else if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            linearLayout.removeAllViews();
            pointerList.clear();
        }

        Log.i("TouchForce", "Event ID " + event.getAction());

        return true;
    }
}
