import org.example.Attacker;
import org.example.GameCharacter;

import java.util.Random;

public class Wizard extends GameCharacter implements Attacker {
    private int mana;
    private int intelligence;
    Random r = new Random();

    public Wizard(String name, int hp, int mana, int intelligence) {
        super(name, validateHp(hp));
        this.mana = validateMana(mana);
        this.intelligence = validateIntelligence(intelligence);
        System.out.println("✅ Wizard created: " + name);
    }

    // === VALIDATORS ===
    private static int validateHp(int hp) {
        if (hp < 50) {
            return 50;
        } else if (hp > 100) {
            return 100;
        } else {
            return hp;
        }
    }

    private static int validateMana(int mana) {
        if (mana < 10) {
            return 10;
        } else if (mana > 50) {
            return 50;
        } else {
            return mana;
        }
    }

    private static int validateIntelligence(int intelligence) {
        if (intelligence < 1) {
            return 1;
        } else if (intelligence > 50) {
            return 50;
        } else {
            return intelligence;
        }
    }

    // === GETTERS & SETTERS ===
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    // === ATTACK ===
    @Override
    public void attack(GameCharacter target) {
        int typeAttack = r.nextInt(2); // 0 = fireball, 1 = staff hit


        switch (typeAttack) {
            case 0:
                if (mana >= 5) {
                    int damage = intelligence;
                    mana -= 5;
                    System.out.println("🔥 " + getName() + " casts FIREBALL on " + target.getName() + " (-" + intelligence + " HP). Mana: " + mana);
                    target.receiveDamage(damage);

                } else if (mana >= 1) {
                    int damage =  2;
                    mana += 1;
                    System.out.println("🪄 " + getName() + " uses STAFF HIT on " + target.getName() + " (-2 HP). +1 Mana: " + mana);
                    target.receiveDamage(damage);
                } else {
                    mana += 2;
                    System.out.println("😓 " + getName() + " lacks mana. No attack. +2 Mana: " + mana);
                }
                break;

            case 1:
                if (mana >= 1) {
                    int damage = 2;
                    mana += 1;
                    System.out.println("🪄 " + getName() + " uses STAFF HIT on " + target.getName() + " (-2 HP). +1 Mana: " + mana);
                    target.receiveDamage(damage);
                } else {
                    mana += 2;
                    System.out.println("😓 " + getName() + " lacks mana. No attack. +2 Mana: " + mana);
                }
                break;

            default:
                System.out.println("Invalid attack type.");
                break;
        }

        if (target.getHp() <= 0) {
            target.setAlive(false);
            target.setHp(0);
            System.out.println("☠️ " + target.getName() + " has died.");
        }
    }

    // === DISPLAY ===
    @Override
    public String toString() {
        return "\n[Wizard] " + getName() +
                " | HP: " + getHp() +
                " | Mana: " + mana +
                " | Intelligence: " + intelligence +
                " | Defense: " + getDefense();
    }
}