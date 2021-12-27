package com.bjtu.recitewords.discover.bean;

public class ArticleBean {
    public String imgUrl;
    public String tPrimary;
    public String tSecondary;
    public String tTag;
    public String tPlayAmount;

    public ArticleBean(){

    }

    public ArticleBean(String imgUrl, String tPrimary,String tSecondary) {
        this.imgUrl = imgUrl;
        this.tPrimary = tPrimary;
        this.tSecondary = tSecondary;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String gettPrimary() {
        return tPrimary;
    }

    public void settPrimary(String tPrimary) {
        this.tPrimary = tPrimary;
    }

    public String gettSecondary() {
        return tSecondary;
    }

    public void settSecondary(String tSecondary) {
        this.tSecondary = tSecondary;
    }

    public String gettTag() {
        return tTag;
    }

    public void settTag(String tTag) {
        this.tTag = tTag;
    }

    public String gettPlayAmount() {
        return tPlayAmount;
    }

    public void settPlayAmount(String tPlayAmount) {
        this.tPlayAmount = tPlayAmount;
    }
}
