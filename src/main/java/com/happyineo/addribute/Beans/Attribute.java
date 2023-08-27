package com.happyineo.addribute.Beans;

import java.util.HashMap;
import java.util.Map;

public class Attribute {

    public static class weakness{
        private String name;    // 名前
        private double mag; // 倍率

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getMag() {
            return mag;
        }

        public void setMag(double mag) {
            this.mag = mag;
        }
    }

    private String name;    // 名前

    // RGB
    private int colorR = 0; // Red
    private int colorG = 0; // Green
    private int colorB = 0; // Blue

    private Map<String,weakness> mags = new HashMap<>();    // 弱点

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColorR() {
        return colorR;
    }

    public void setColorR(int colorR) {
        this.colorR = colorR;
    }

    public int getColorG() {
        return colorG;
    }

    public void setColorG(int colorG) {
        this.colorG = colorG;
    }

    public int getColorB() {
        return colorB;
    }

    public void setColorB(int colorB) {
        this.colorB = colorB;
    }

    public Map<String, weakness> getMags() {
        return mags;
    }

    public void setMags(Map<String, weakness> mags) {
        this.mags = mags;
    }
}
