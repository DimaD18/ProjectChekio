/*package khnu.migfak.games;

import java.util.LinkedList;

public class ArmyBattles {

        static class Weapon {
            int health;
            int attack;
            int defense;
            int vampirism;
            int heal_power;

            Weapon(int health, int attack, int defense, int vampirism, int heal_power) {
                this.health = health;
                this.attack = attack;
                this.defense = defense;
                this.vampirism = vampirism;
                this.heal_power = heal_power;
            }
        }

        static class Sword extends Weapon {
            Sword() {
                super(5, 2, 0, 0, 0);
            }
        }

        static class Shield extends Weapon {
            Shield() {
                super(20, -1, 2, 0, 0);
            }
        }

        static class GreatAxe extends Weapon {
            GreatAxe() {
                super(-15, 5, -2, 10, 0);
            }
        }

        static class Katana extends Weapon {
            Katana() {
                super(-20, 6, -5, 50, 0);
            }
        }

        static class MagicWand extends Weapon {
            MagicWand() {
                super(30, 3, 0, 0, 3);
            }
        }

     class Warrior {
        int health = 50;
        int attack = 5;
        int heal_power = 0;
        Weapon weapon;
        public void equip_weapon(Weapon weapon) {
            this.weapon = weapon;
            this.health += weapon.health;
            this.attack += weapon.attack;
            if (this.health <= 0) {
                this.health = 0;
            }
        }
        public boolean isAlive() {
            return this.health > 0;
        }

        public void attack(Warrior enemy) {
            int damage = this.attack;
            if (enemy instanceof Defender) {
                damage -= ((Defender) enemy).defense;
            }
            if (damage > 0) {
                enemy.health -= damage;
                if (this instanceof Vampire) {
                    this.health += damage * ((Vampire) this).vampirism / 100;
                    if (this.health > 40) {
                        this.health = 40;
                    }
                }
            }
        }
    }

    class Knight extends Warrior {
        Knight() {
            super();
            this.attack = 7;
        }
    }

    public class Defender extends Warrior {
        int defense = 2;

        Defender() {
            super();
            this.health = 60;
            this.attack = 3;
        }
        @Override
        public void equip_weapon(Weapon weapon) {
            super.equip_weapon(weapon);
            this.defense += weapon.defense;
        }
    }

     class Vampire extends Warrior {
        int vampirism = 50;

        Vampire() {
            super();
            this.health = 40;
            this.attack = 4;
        }
        @Override
        public void equip_weapon(Weapon weapon) {
            super.equip_weapon(weapon);
            this.vampirism += weapon.vampirism;
            if (this.vampirism < 0) {
                this.vampirism = 0;
            }
        }
    }

     class Lancer extends Warrior {
        Lancer() {
            super();
            this.health = 50;
            this.attack = 6;
        }
    }

    public class Healer extends Warrior {
        Healer() {
            super();
            this.health = 60;
            this.attack = 0;
            this.heal_power=2;
        }
        @Override
        public void equip_weapon(Weapon weapon) {
            super.equip_weapon(weapon);
            this.heal_power += weapon.heal_power;
        }
        public void heal(Warrior ally) {
            if (ally.health < 50) {
                ally.health += 2;
                if (ally.health > 50) {
                    ally.health = 50;
                }
            }
        }
    }



      public static class Army {
        private final LinkedList<Warrior> warriors = new LinkedList<>();

        public void addUnits(int count, Class<? extends Warrior> type) {
            for (int i = 0; i < count; i++) {
                try {
                    warriors.add(type.getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public Warrior getNextWarrior() {
            return warriors.isEmpty() ? null : warriors.remove();
        }

        public boolean hasLivingWarriors() {
            return !warriors.isEmpty();
        }
    }
        static class Battle {
        public static boolean straight_fight(Army army1, Army army2) {
            while (true) {
                int size = Math.max(army1.warriors.size(), army2.warriors.size());
                for (int i = 0; i < size; i++) {
                    if (i < army1.warriors.size() && i < army2.warriors.size()) {
                        Warrior warrior1 = army1.warriors.get(i);
                        Warrior warrior2 = army2.warriors.get(i);
                        while (warrior1.isAlive() && warrior2.isAlive()) {
                            warrior1.attack(warrior2);
                            if (warrior2.isAlive()) {
                                warrior2.attack(warrior1);
                            }
                        }
                    } else if (i < army1.warriors.size()) {
                        return true;
                    } else {
                        return false;
                    }
                }
                army1.warriors.removeIf(warrior -> !warrior.isAlive());
                army2.warriors.removeIf(warrior -> !warrior.isAlive());
                if (army1.warriors.isEmpty()) {
                    return false;
                } else if (army2.warriors.isEmpty()) {
                    return true;
                }
            }
        }

        public static boolean fight(Army army1, Army army2) {
            while (army1.hasLivingWarriors() && army2.hasLivingWarriors()) {
                Warrior warrior1 = army1.getNextWarrior();
                Warrior warrior2 = army2.getNextWarrior();

                while (warrior1.isAlive() && warrior2.isAlive()) {
                    warrior1.attack(warrior2);
                    if (warrior2.isAlive()) {
                        warrior2.attack(warrior1);
                    }
                }
            }

            return army1.hasLivingWarriors();
        }
    }
    public static class Main {
        public static void main(String[] args) {

            ArmyBattles.Army army1 = new ArmyBattles.Army();
            army1.addUnits(3, ArmyBattles.Warrior.class);
            army1.addUnits(3, ArmyBattles.Knight.class);
            army1.addUnits(3, ArmyBattles.Defender.class);
            army1.addUnits(3, ArmyBattles.Vampire.class);
            army1.addUnits(3, ArmyBattles.Lancer.class);
            army1.addUnits(3, ArmyBattles.Healer.class);

            ArmyBattles.Army army2 = new ArmyBattles.Army();
            army2.addUnits(3, ArmyBattles.Warrior.class);
            army2.addUnits(3, ArmyBattles.Knight.class);
            army2.addUnits(3, ArmyBattles.Defender.class);
            army2.addUnits(3, ArmyBattles.Vampire.class);
            army2.addUnits(3, ArmyBattles.Lancer.class);
            army2.addUnits(3, ArmyBattles.Healer.class);
            System.out.println(ArmyBattles.Battle.fight(army1, army2)); // prints: true or false
        }
    }
}*/

package khnu.migfak.games;

import java.util.LinkedList;

public class ArmyBattles {

    static class Weapon {
        int health;
        int attack;
        int defense;
        int vampirism;
        int heal_power;

        Weapon(int health, int attack, int defense, int vampirism, int heal_power) {
            this.health = health;
            this.attack = attack;
            this.defense = defense;
            this.vampirism = vampirism;
            this.heal_power = heal_power;
        }
    }

    static class Warrior {
        int health = 50;
        int attack = 5;
        int heal_power = 0;
        Weapon weapon;
        private int vampirism;
        int defense;

        public void equip_weapon(Weapon weapon) {
            this.weapon = weapon;
            this.health += weapon.health;
            this.attack += weapon.attack;
            this.defense += weapon.defense;
            this.vampirism += weapon.vampirism;
            if (this.vampirism < 0) {
                this.vampirism = 0;
            }
        }

        public boolean isAlive() {
            return this.health > 0;
        }

        public void attack(Warrior enemy) {
            int damage = this.attack - enemy.defense;
            if (damage > 0) {
                enemy.health -= damage;
                if (this instanceof Vampire) {
                    this.health += damage * ((Vampire) this).vampirism / 100;
                    if (this.health > 40) {
                        this.health = 40;
                    }
                }
            } else {
                this.health = 0;
            }
        }
    }

    static class Knight extends Warrior {
        Knight() {
            super();
            this.attack = 7;
        }
    }

    public static class Defender extends Warrior {
        int defense = 2;

        Defender() {
            super();
            this.health = 60;
            this.attack = 3;
        }
    }

    static class Vampire extends Warrior {
        int vampirism = 50;

        Vampire() {
            super();
            this.health = 40;
            this.attack = 4;
        }
    }

    static class Lancer extends Warrior {
        Lancer() {
            super();
            this.health = 50;
            this.attack = 6;
        }
    }

    public static class Healer extends Warrior {
        Healer() {
            super();
            this.health = 60;
            this.attack = 0;
            this.heal_power = 2;
        }

        public void heal(Warrior ally) {
            if (ally.health < 50) {
                ally.health += this.heal_power;
                if (ally.health > 50) {
                    ally.health = 50;
                }
            }
        }
    }

    static class Warlord extends Warrior {
        Warlord() {
            super();
            this.health = 100;
            this.attack = 4;
            this.defense = 2;
        }
    }
    public static class Army {
        private final LinkedList<Warrior> warriors = new LinkedList<>();
        public void addUnits(int count, Class<? extends Warrior> type) {
            for (int i = 0; i < count; i++) {
                try {
                    warriors.add(type.getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        public Warrior getNextWarrior() {
            return warriors.isEmpty() ? null : warriors.remove();
        }
        public boolean hasLivingWarriors() {
            return !warriors.isEmpty();
        }
        public void move_units() {
            LinkedList<Warrior> lancers = new LinkedList<>();
            LinkedList<Warrior> healers = new LinkedList<>();
            LinkedList<Warrior> others = new LinkedList<>();
            for (Warrior warrior : warriors) {
                if (warrior instanceof Lancer) {
                    lancers.add(warrior);
                } else if (warrior instanceof Healer) {
                    healers.add(warrior);
                } else {
                    others.add(warrior);
                }
            }
            warriors.clear();
            warriors.addAll(lancers);
            warriors.addAll(healers);
            warriors.addAll(others);
        }
    }

    static class Battle {
        public static boolean fight(Army army1, Army army2) {
            while (army1.hasLivingWarriors() && army2.hasLivingWarriors()) {
                Warrior warrior1 = army1.getNextWarrior();
                Warrior warrior2 = army2.getNextWarrior();

                while (warrior1.isAlive() && warrior2.isAlive()) {
                    warrior1.attack(warrior2);
                    if (warrior1.isAlive() && warrior2.isAlive()) {
                        warrior2.attack(warrior1);
                    }
                }
                army1.move_units();
                army2.move_units();
            }

            return army1.hasLivingWarriors();
        }
    }

    public static void main(String[] args) {

        ArmyBattles.Army army1 = new ArmyBattles.Army();
        army1.addUnits(1, ArmyBattles.Warrior.class);
        army1.addUnits(3, ArmyBattles.Knight.class);
        army1.addUnits(5, ArmyBattles.Defender.class);
        army1.addUnits(3, ArmyBattles.Vampire.class);
        army1.addUnits(8, ArmyBattles.Lancer.class);
        army1.addUnits(3, ArmyBattles.Healer.class);
        army1.addUnits(1, ArmyBattles.Warlord.class);

        ArmyBattles.Army army2 = new ArmyBattles.Army();
        army2.addUnits(3, ArmyBattles.Warrior.class);
        army2.addUnits(6, ArmyBattles.Knight.class);
        army2.addUnits(3, ArmyBattles.Defender.class);
        army2.addUnits(3, ArmyBattles.Vampire.class);
        army2.addUnits(9, ArmyBattles.Lancer.class);
        army2.addUnits(1, ArmyBattles.Healer.class);
        army1.addUnits(1, ArmyBattles.Warlord.class);
        System.out.println(ArmyBattles.Battle.fight(army1, army2));
    }
}