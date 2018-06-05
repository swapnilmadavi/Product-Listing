package com.example.swapyx.productlisting.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Model class to represent a Gaming Mouse.
 * Also represents Entity for Room db.
 */
@Entity
public class GamingMouse {

    @PrimaryKey
    private int id;

    private String name;
    private String brand;
    private String connection;
    private int price;

    @ColumnInfo(name = "image_id")
    private int imageId;

    @ColumnInfo(name = "number_of_reviews")
    private int numberOfUserReviews;

    private String pros;
    private String opinion;
    private int likes;
    private int dislikes;
    private String color;

    public GamingMouse() {
    }

    public GamingMouse(int id, String name, String brand, String connection,
                       int price, int imageId, int numberOfUserReviews,
                       String pros, String opinion, int likes, int dislikes, String color) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.connection = connection;
        this.price = price;
        this.imageId = imageId;

        this.numberOfUserReviews = numberOfUserReviews;
        this.pros = pros;
        this.opinion = opinion;
        this.likes = likes;
        this.dislikes = dislikes;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getNumberOfUserReviews() {
        return numberOfUserReviews;
    }

    public void setNumberOfUserReviews(int numberOfUserReviews) {
        this.numberOfUserReviews = numberOfUserReviews;
    }

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Gaming Mouse:- {" +
                "id : " + id +
                ", name : " + name +
                ", brand : " + brand +
                ", connection : " + connection +
                ", price : " + price +
                ", imageId : " + imageId +
                ", num of reviews : " + numberOfUserReviews +
                ", pros : " + pros +
                ", opinion : " + opinion +
                ", likes : " + likes +
                ", dislikes : " + dislikes +
                ", color : " + color +
                "}";
    }
}
