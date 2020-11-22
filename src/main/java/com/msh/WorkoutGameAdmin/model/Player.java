package com.msh.WorkoutGameAdmin.model;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

@Getter
@Setter
public class Player implements Comparable<Player>, Serializable {
    private String name;
    private Color color;
    private Coordinate position;
    private int money;
    private int currentScore;
    private int totalScore;
    private int fieldsOwned;
    private Map<String, Integer> exerciseNumbers = new LinkedHashMap<>();
    private Map<String, Integer> stockNumbers = new LinkedHashMap<>();

    public Player() {}

    public Player(String name, Color color, Coordinate position, int money, int currentScore, int totalScore, int fieldsOwned, Map<String, Integer> exerciseNumbers, Map<String, Integer> stockNumbers) {
        this.name = name;
        this.color = color;
        this.position = position;
        this.money = money;
        this.currentScore = currentScore;
        this.totalScore = totalScore;
        this.fieldsOwned = fieldsOwned;
        this.exerciseNumbers = exerciseNumbers;
        this.stockNumbers = stockNumbers;
    }

    @Override
    public int compareTo(Player o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + ": [" + fieldsOwned + ",  " + totalScore + "]";
    }
}
