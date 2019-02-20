package com.ncku_tainan.co2_detection;

import android.app.Application;

public class GlobalVariable extends Application {

    public boolean CheckSwitch = true;

    public boolean getCheckSwitch() {
        return CheckSwitch;
    }
    public void setCheckSwitch(boolean checkSwitch) {
        this.CheckSwitch = checkSwitch;
    }
}
