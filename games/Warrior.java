package khnu.migfak.games;

public class Warrior {
    private int health = 50;
     int attack = 5;

    public boolean isAlive() {
        return this.health > 0;
    }

    public void attack(Warrior enemy) {
        enemy.health -= this.attack;
    }
}

class Knight extends Warrior {
    public Knight() {
        super();
        this.attack = 7;
    }
}

class Battle {
    public static boolean fight(Warrior warrior1, Warrior warrior2) {
        while (true) {
            warrior1.attack(warrior2);
            if (!warrior2.isAlive()) {
                return true;
            }
            warrior2.attack(warrior1);
            if (!warrior1.isAlive()) {
                return false;
            }
        }
    }
}

/*class Main {
    public static void main(String[] args) {
        Warrior chuck = new Warrior();
        Warrior bruce = new Warrior();
        Knight carl = new Knight();
        Warrior dave = new Warrior();

        System.out.println(Battle.fight(chuck, bruce)); // prints: true
        System.out.println(Battle.fight(dave, carl)); // prints: false
        System.out.println(chuck.isAlive()); // prints: true
        System.out.println(bruce.isAlive()); // prints: false
        System.out.println(carl.isAlive()); // prints: true
        System.out.println(dave.isAlive()); // prints: false
    }
}*/
