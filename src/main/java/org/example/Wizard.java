import java.util.Random;


public class Wizard extends Character implements Attack {

    private int mana;
    private int intelligence;
    Random r = new Random();



    public Wizard(String name, int hp, int mana, int intelligence) {
        super(name, topHP(hp));
        this.mana = topMana(mana);
        this.intelligence = topIntelligence(intelligence);
        System.out.println("Character created! " + this);

    }


//    GETTERS & SETTERS


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

//         CHECKERS

    public static int topHP(int hp){
        if(hp < 50 ){
            System.out.println("Health can't be lower than 50; Health set up to 50");
            return 50;
        } else if (hp > 100) {
            System.out.println("Health can't be higher than 100; Health set up to 50");
            return 50;
        }else{
            return hp;
        }
    }

    public static int topMana(int mana){
        if(mana < 10){
            System.out.println("Mana can't be lower than 10; Mana set up to 20");
            return 20;
        } else if (mana > 50) {
            System.out.println("Mana can't be higher than 50; Mana set up to 20");
            return 20;
        }else{
            return mana;
        }
    }

    public static int topIntelligence(int intelligence){
        if(intelligence < 1){
            System.out.println("Intelligence can't be lower than 1; Intelligence set up to 25");
            return 25;
        } else if (intelligence > 50) {
            System.out.println("Intelligence can't be higher than 50; Mana set up to 25");
            return 25;
        }else{
            return intelligence;
        }
    }

    //    MÉTHOD DESDE ATTACKER


    //    PELEA
    @Override
    public void attack(Character target) {
        int typeAttack = r.nextInt(2);
        int mana = getMana();

        // Comprobar si está vivo o no
        if (target.getHp() <= 0) {
            target.setAlive(false);
            System.out.println("You're so dead...");

        }
        else {
            //escoger ataque (aleatorio a ver si sale esto)
            switch (typeAttack) {
                case 0:
                    if (mana >= 5) {
                        fireBall(target);
                    } else {
                        staffHit(target);
                    }
                    break;
                case 1:
                    if (mana >= 2) {
                        staffHit(target);
                    } else {

                        recover(target);
                    }
                    break;
                default:
                    System.out.println("Aren't you like dead or something");
                    break;
            }
        }


        //escoger ataque (aleatorio a ver si sale esto)
        switch (typeAttack){
            case 0:
                if(getMana() >= 5 ){
                    fireBall(target);
                }else{
                    staffHit(target);
                }
                break;
            case 1:
                if(getMana() >= 2){
                    staffHit(target);
                }else if(getMana() <= 0){
                    recover(target);
                }
                break;
            default:
                System.out.println("Aren't you like dead or something");
                break;

        }

    }



//    public void attackTest(Character target){
//        int typeAttack = r.nextInt(2);
//
//        //Comprobar si está vivo o no
//        if(target.getHp() <= 0){
//            target.setAlive(false);
//        }
//
//        //escoger ataque (aleatorio a ver si sale esto)
//        switch (typeAttack){
//            case 0:
//                if(getMana() >= 5 ){
//                    fireBall(target);
//                }else{
//                    staffHit(target);
//                }
//                break;
//            case 1:
//                if(getMana() >= 2){
//                    staffHit(target);
//                }else if(getMana() <= 0){
//                    recover(target);
//                }
//                break;
//            default:
//                System.out.println("Aren't you like dead or something");
//                break;
//
//        }
//    }

    public void fireBall(Character opponent){
        int damage = opponent.getHp() - intelligence;
        opponent.setHp(damage);
        setMana(getMana() - 5);
        System.out.println(" >> FIREBALL! " + opponent.getName() +  " took a damage of: " + damage + ". Your Mana is at: " + getMana());
    }

    public void staffHit(Character opponent){
        int damage = opponent.getHp() - 2;
        opponent.setHp(damage);
        setMana(getMana() + 1);
        System.out.println(" >> STAFF HIT! " + opponent.getName() +  " took a damage of: " + damage + " +1 Mana. Your Mana is at: " + getMana());
    }

    public void recover(Character opponent){
        int recover = getMana() + 2;
        setMana(recover);
        System.out.println(" >> MISSED HIT! +2 Mana. Your Mana is at: " + getMana());

    }

    public void selfAttack(Warrior w){
        int hurt = w.getStrength() - 1;
        w.setStrength(hurt);

        System.out.println(w.getName() + " > WHY ARE YOU HITTING YOURSELF? ");
    }


//    VISUALIZADORES


    @Override
    public String toString() {
        return
                '\n'+ "Character type: Wizard" + '\n'+
                        "-------------------------------" + '\n'+
                        "Name: " + getName() + '\n'+
                        "Health: " + getHp() + '\n'+
                        "Mana: " + mana + '\n'+
                        "Intelligence: " + intelligence  + '\n';
    }
}