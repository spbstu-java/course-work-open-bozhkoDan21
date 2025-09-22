package com.example.coursework.lab1;

import com.example.coursework.lab1.strategy.*;

/**
 * Класс для демонстрации лабораторной работы 1 в GUI
 */
public class Lab1Demo {
    private Hero hero;

    public Lab1Demo() {
        this.hero = new Hero();
    }

    public void setWalkStrategy() {
        hero.setMoveStrategy(new WalkStrategy());
    }

    public void setRideHorseStrategy() {
        hero.setMoveStrategy(new RideHorseStrategy());
    }

    public void setFlyStrategy() {
        hero.setMoveStrategy(new FlyStrategy());
    }

    public String move() {
        hero.move();
        return "Текущая позиция: " + hero.getPosition();
    }

    public String getCurrentPosition() {
        return "Текущая позиция: " + hero.getPosition();
    }

    public String getCurrentStrategy() {
        return "Текущая стратегия: " + hero.getCurrentStrategyDescription();
    }

    public void reset() {
        this.hero = new Hero();
    }
}