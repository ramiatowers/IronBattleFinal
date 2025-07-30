import org.example.Attacker;
import org.example.GameCharacter;

import java.util.Random;

public class Warrior extends GameCharacter implements Attacker {
    // === PROPERTIES ===
    private int stamina;
    private int strength;
    Random r = new Random();

    // === CONSTRUCTOR ===
    public Warrior(String name, int hp, int stamina, int strength) {
        super(name, validateHp(hp));
        this.stamina = validateStamina(stamina);
        this.strength = validateStrength(strength);
        System.out.println("✅ Warrior created: " + name);
    }

    // === VALIDATORS ===
    private static int validateHp(int hp) {
        if (hp < 100) {
            return 100;
        } else if (hp > 200) {
            return 200;
        } else {
            return hp;
        }
    }

    private static int validateStamina(int stamina) {
        if (stamina < 10) {
            return 10;
        } else if (stamina > 50) {
            return 50;
        } else {
            return stamina;
        }
    }

    private static int validateStrength(int strength) {
        if (strength < 1) {
            return 1;
        } else if (strength > 10) {
            return 10;
        } else {
            return strength;
        }
    }

    // === GETTERS & SETTERS ===
    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    // === ATTACK ===
    @Override
    public void attack(GameCharacter target) {
        int typeAttack = r.nextInt(2); // 0 = Heavy attack, 1 = Weak attack


        switch (typeAttack) {
            case 0:
                if (stamina >= 5) {
                    int damage = strength;
                    stamina -= 5;
                    System.out.println("💥 " + getName() + " uses HEAVY ATTACK on " + target.getName() + " (-" + strength + " HP). Stamina: " + stamina);
                    target.receiveDamage(damage);
                } else if (stamina >= 1) {
                    int damage = strength / 2;
                    stamina += 1;
                    System.out.println("⚔️ " + getName() + " uses fallback WEAK ATTACK on " + target.getName() + " (-" + damage + " HP). +1 Stamina: " + stamina);
                    target.receiveDamage(damage);
                } else {
                    stamina += 2;
                    System.out.println("😴 " + getName() + " is too tired to attack. +2 Stamina: " + stamina);
                }
                break;

            case 1:
                if (stamina >= 1) {
                    int damage = strength / 2;
                    stamina += 1;
                    System.out.println("⚔️ " + getName() + " uses WEAK ATTACK on " + target.getName() + " (-" + damage + " HP). +1 Stamina: " + stamina);
                    target.receiveDamage(damage);
                } else {
                    stamina += 2;
                    System.out.println("😴 " + getName() + " is too tired to attack. +2 Stamina: " + stamina);
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
        return "\n[Warrior] " + getName() +
                " | HP: " + getHp() +
                " | Stamina: " + stamina +
                " | Strength: " + strength +
                " | Defense: " + getDefense();
    }
}