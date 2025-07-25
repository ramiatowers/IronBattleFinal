package org.example;
import java.util.UUID;

public abstract class GameCharacter {
    private String id = UUID.randomUUID().toString();
    private String name;
    private int hp;
    private boolean isAlive;

    public GameCharacter(String name, int hp) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.hp = hp;
        this.isAlive = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}