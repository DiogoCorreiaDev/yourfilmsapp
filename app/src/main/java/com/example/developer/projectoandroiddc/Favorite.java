package com.example.developer.projectoandroiddc;

import com.orm.SugarRecord;

public class Favorite extends SugarRecord {

    public String title;
    public String rating;
    public String summary;
    public String website;
    public String type;
    public String status;
    public String premiere;
    public String imageMedium;
    public String imageOriginal;

    public Favorite (){
    }

    public Favorite(String title, String rating, String summary, String website, String type, String status, String premiere, String imageMedium, String imageOriginal) {
        this.title = title;
        this.rating = rating;
        this.summary = summary;
        this.website = website;
        this.type = type;
        this.status = status;
        this.premiere = premiere;
        this.imageMedium = imageMedium;
        this.imageOriginal = imageOriginal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPremiere() {
        return premiere;
    }

    public void setPremiere(String premiere) {
        this.premiere = premiere;
    }

    public String getImageMedium() {
        return imageMedium;
    }

    public void setImageMedium(String imageMedium) {
        this.imageMedium = imageMedium;
    }

    public String getImageOriginal() {
        return imageOriginal;
    }

    public void setImageOriginal(String imageOriginal) {
        this.imageOriginal = imageOriginal;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                ", summary='" + summary + '\'' +
                ", website='" + website + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", premiere='" + premiere + '\'' +
                ", imageMedium='" + imageMedium + '\'' +
                ", imageOriginal='" + imageOriginal + '\'' +
                '}';
    }
}
