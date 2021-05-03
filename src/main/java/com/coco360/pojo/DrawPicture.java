package com.coco360.pojo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DrawPicture {
    private Integer x;
    private Integer y;
    private Integer textSize;
    private String text;
    private Color color;

    @Override
    public String toString() {
        return "DrawPicture{" +
                "x=" + x +
                ", y=" + y +
                ", textSize=" + textSize +
                ", text='" + text + '\'' +
                ", color=" + color +
                '}';
    }

    public ArrayList toArray() {
        ArrayList list = new ArrayList();
        list.addAll(Arrays.asList(x, y, text, textSize, color));
        return list;
    }

    public DrawPicture() {
    }

    public DrawPicture(Integer x, Integer y,  String text, Integer textSize,Color color) {
        this.x = x;
        this.y = y;
        this.textSize = textSize;
        this.text = text;
        this.color = color;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getTextSize() {
        return textSize;
    }

    public void setTextSize(Integer textSize) {
        this.textSize = textSize;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
