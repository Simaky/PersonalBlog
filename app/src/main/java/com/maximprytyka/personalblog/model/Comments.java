package com.maximprytyka.personalblog.model;


public class Comments {

    private int id;
    private String author;
    private String nickname;
    private String email;
    private String text;
    private String pubdate;
    private String articles_id;

    public Comments(){
    }

    public Comments(int id, String author, String nickname, String email, String text, String pubdate, String articles_id) {
        this.id = id;
        this.author = author;
        this.nickname = nickname;
        this.email = email;
        this.text = text;
        this.pubdate = pubdate;
        this.articles_id = articles_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getArticles_id() {
        return articles_id;
    }

    public void setArticles_id(String articles_id) {
        this.articles_id = articles_id;
    }
}
