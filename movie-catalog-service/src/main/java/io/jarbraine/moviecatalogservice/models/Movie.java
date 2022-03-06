package io.jarbraine.moviecatalogservice.models;

public class Movie {
    private  String Name;
    private  String movieId;

public Movie(){

}
    public Movie(String movieId, String name) {
        Name = name;
        this.movieId = movieId;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTest() {
        return movieId;
    }

    public void setTest(String test) {
        movieId = test;
    }

}
