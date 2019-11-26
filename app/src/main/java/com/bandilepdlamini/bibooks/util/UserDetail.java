package com.bandilepdlamini.bibooks.util;

public class UserDetail {
    private String username;
    private String bestTime;
    private String bestScore;
    private String active;

    public UserDetail(String pUsername, String pBestScore, String pBestTime, String pActive){
        username = pUsername;
        bestScore = pBestScore;
        bestTime = pBestTime;
        active = pActive;
    }

    public UserDetail(){
        username = "";
        bestScore = "";
        bestTime = "";
        active = "";
    }

    public String getBestScore() {
        return bestScore;
    }

    public String getBestTime() {
        return bestTime;
    }

    public String getUsername() {
        return username;
    }

    public String getActive() {
        return active;
    }

    public void setBestScore(String bestScore) {
        this.bestScore = bestScore;
    }

    public void setBestTime(String bestTime) {
        this.bestTime = bestTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
