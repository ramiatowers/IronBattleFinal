import java.util.Random;

public class Warrior extends Character implements Attack {
    private int stamina;
    private int strength;
    Random r = new Random();

    public Warrior(String name, int hp, int stamina, int strength) {
        super(name, Auxiliar.topHP(hp));
        this.stamina = Auxiliar.topStamina(stamina);
        this.strength = Auxiliar.topStrength(strength);
        System.out.println("Character created! " +name);

    }

//    GETTERS & SETTERS

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

    public void attackTest(Character target){
        int typeAttack = r.nextInt(2);

        //Comprobar si está vivo o no
        if(target.getHp() <= 0){
            target.setAlive(false);
        }

        //escoger ataque (aleatorio a ver si sale esto)
        switch (typeAttack){
            case 0:
                if(getStamina() >= 5 ){
                    stab(target);
                }else{
                    zweihander(target);
                }
                break;
            case 1:
                if(getStamina() >= 2){
                    stab(target);
                }else if(getStamina() <= 0){
                    zweihander(target);
                }
                break;
            default:
                System.out.println("Aren't you like dead or something");
                break;
        }


    }

    public void stab(Character opponent){
        int damage = opponent.getHp() - 3;
        opponent.setHp(damage);
        setStamina(getStamina() + 3);
        System.out.println(" >> STAB! " + opponent.getName() +  " took a damage of: " + damage + ". +3 Stamina. Your Stamina is at: " + getStamina());
    }

    public void zweihander(Character opponent){
        int damage = opponent.getHp() - strength;
        opponent.setHp(damage);
        setStamina(getStamina() - 5);
        System.out.println(" >> ZWEIHANDER! " + opponent.getName() +  " took a damage of: " + damage + " -5 Stamina. Your Stamina is at: " + getStamina());
    }

    public void recover(Character opponent){
        int recover = getStamina() + 2;
        setStamina(recover);
        System.out.println(" >> MISSED HIT! +2 Stamina. Your Stamina is at: " + getStamina());

    }



//    VISUALIZADORES


    @Override
    public String toString() {
        return
                '\n'+ "Character type: Warrior" + '\n'+
                        "-------------------------------" + '\n'+
                        "Name: " + getName() + '\n'+
                        "Health: " + super.getHp() + '\n'+
                        "Mana: " + stamina + '\n'+
                        "strength: " + strength + '\n';
    }

    @Override
    public void attack(Character target) {
        System.out.println(getName() + " (Warrior) ha atacado a " + target.getName() + " critical hit " + strength);
        target.setHp(target.getHp() - strength);
        if (target.getHp() <= 0) {
            target.setAlive(false);
            target.setHp(0);
            System.out.println(target.getName() + " ha muerto.");
        }
    }
}