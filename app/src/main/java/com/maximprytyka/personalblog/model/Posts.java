package com.maximprytyka.personalblog.model;


public class Posts {

    private int id;
    private String title;
    private String image;
    private String text;
    private String categorie_id;
    private String pubdate;
    private String views;

    public Posts(){
    }

    public Posts(int id, String title, String image, String text, String categorie_id, String pubdate, String views) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.text = text;
        this.categorie_id = categorie_id;

        this.pubdate = pubdate;
        this.views = views;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String  getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(String categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }


}
