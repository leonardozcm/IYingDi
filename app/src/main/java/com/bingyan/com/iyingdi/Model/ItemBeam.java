package com.bingyan.com.iyingdi.Model;

import android.graphics.Bitmap;

/**
 * Created by 59771 on 2017/11/24.
 */

public class ItemBeam {
    private String titleText;
    private String intro;
    private String author;
    private Bitmap cover_bitmap;

    public Bitmap getCover_bitmap() {
        return cover_bitmap;
    }

    public void setCover_bitmap(Bitmap cover_bitmap) {
        this.cover_bitmap = cover_bitmap;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
