package org.example;
import java.util.Random;
import java.util.UUID;

public abstract class GameCharacter {
    private String id = UUID.randomUUID().toString();
    private String name;
    private int hp;
    private boolean isAlive;
    private int defense;

    private static final Random r =new Random();

    public GameCharacter(String name, int hp) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.hp = hp;
        this.isAlive = true;
        this.defense = r.nextInt(10) + 1;
    }


    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
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

    public int setHp(int hp) {
        this.hp = hp;
        return hp;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
    }


//       DEFENSE METHOD

    public int receiveDamage(int damage) {
        Random r = new Random();


        int randomDefense = defense + r.nextInt(5) - 2;
        if (randomDefense < 0) randomDefense = 0;

        int reduced = Math.max(0, damage - randomDefense);
        hp -= reduced; //

        if (hp <= 0) {
            hp = 0;
            isAlive = false;
        }

        System.out.println("🛡️ ACTIVATED Shield : " + randomDefense);
        return reduced;
    }
}