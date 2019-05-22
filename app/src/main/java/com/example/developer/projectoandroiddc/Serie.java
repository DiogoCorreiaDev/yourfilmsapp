package com.example.developer.projectoandroiddc;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class Serie {

    public Integer id;
    public String url;
    public String name;
    public String summary;
    public String type;
    public String[] genres;
    public String status;
    public String premiered;
    public String officialSite;
    public Rating rating;
    public Image image;


    @ParcelConstructor
    public Serie(Integer id, String url, String name, String summary, String type, String[] genres, String status, String premiered, String officialSite, Rating rating, Image image) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.summary = summary;
        this.type = type;
        this.genres = genres;
        this.status = status;
        this.premiered = premiered;
        this.officialSite = officialSite;
        this.rating = rating;
        this.image = image;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getPremiered() {
        return premiered;
    }

    public void setPremiered(String premiere) {
        this.premiered = premiere;
    }
    
    public String getOfficialsite() {
        return officialSite;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Parcel(Parcel.Serialization.BEAN)
    public static class Image {
        public String medium;
        public String original;

        @ParcelConstructor
        public Image(String medium, String original) {
            this.medium = medium;
            this.original = original;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getOfficialSite() {
        return this.officialSite;
    }
    
    @Parcel(Parcel.Serialization.BEAN)
    public static class Rating {
        public Float average;

        @ParcelConstructor
        public Rating(Float average) {
            this.average = average;
        }

        public Float getAverage() {
            return average;
        }

        public void setAverage(Float average) {
            this.average = average;
        }
    }

}
