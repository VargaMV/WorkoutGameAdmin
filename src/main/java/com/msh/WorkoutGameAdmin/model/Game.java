package com.msh.WorkoutGameAdmin.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
public class Game {
    private String id;
    private String title;
    private int mapSize;
    private List<Player> players = new ArrayList<>();
    private boolean subscriptionOn;
    private boolean running;
    private int waitingTime;
    private double priceIncExponent;

    public Game() {}

    public Game(String id, String title, int mapSize, List<Player> players, boolean subscriptionOn, boolean running, int waitingTime, double priceIncExponent) {
        this.id = id;
        this.title = title;
        this.mapSize = mapSize;
        this.players = players;
        this.subscriptionOn = subscriptionOn;
        this.running = running;
        this.waitingTime = waitingTime;
        this.priceIncExponent = priceIncExponent;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", mapSize=" + mapSize +
                ", players=" + players +
                ", subscriptionOn=" + subscriptionOn +
                ", running=" + running +
                ", waitingTime=" + waitingTime +
                ", priceIncExponent=" + priceIncExponent +
                '}';
    }
}
