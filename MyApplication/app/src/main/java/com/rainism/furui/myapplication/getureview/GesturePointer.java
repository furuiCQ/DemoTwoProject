package com.rainism.furui.myapplication.getureview;

/**
 * Created by furui on 16/7/20.
 */

public class GesturePointer {
    int tag;
    int x;
    int y;
    boolean checked = false;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public GesturePointer() {

    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public GesturePointer(double x, double y) {
        setX((int) x);
        setY((int) y);
    }


    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return obj.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        String str = this.getX() + "1" + this.getY();
        return str.hashCode();
    }
}
