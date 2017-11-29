package com.linechart;

import android.view.MotionEvent;

/**
 * Created on 2017/11/22.
 *
 * @author chenchengpei@cheyipai.com
 */

public interface InterfaceTouch {
    void action_down(Object view, float startX);

    void action_move(Object view, float startX, float xInit);

    void action_up(Object view, MotionEvent motionEvent);

    void action_cancel(Object view);
}
