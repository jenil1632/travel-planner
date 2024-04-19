package com.planmytrip.travelplanner.model;

public class Part {

    private String text;
    private Blob inlineData;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Blob getInlineData() {
        return inlineData;
    }

    public void setInlineData(Blob inlineData) {
        this.inlineData = inlineData;
    }

    @Override
    public String toString() {
        return "Part{" +
                "text='" + text + '\'' +
                ", inlineData=" + inlineData +
                '}';
    }
}
