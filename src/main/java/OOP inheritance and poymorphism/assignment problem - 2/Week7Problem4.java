public class Week7Problem4 {
    interface Attackable {
        String attack();
        String attack(String weaponName);
    }

    interface Defendable {
        String defend();
    }

    static abstract class GameCharacter {
        private static int count = 0;
        private final String characterId;

        GameCharacter() {
            characterId = "CH-" + (++count);
        }

        public abstract String getSpecialMove();

        String getCharacterId() {
            return characterId;
        }
    }

    static class Warrior extends GameCharacter
            implements Attackable, Defendable {
        String name;

        Warrior(String name) {
            this.name = name;
        }

        public String attack() {
            return name + " strikes with a blade";
        }

        public String attack(String weaponName) {
            return name + " strikes with an " + weaponName;
        }

        public String defend() {
            return name + " raises a shield";
        }

        public String getSpecialMove() {
            return name + " unleashes Whirlwind Slash";
        }
    }

    static class Trap implements Defendable {
        String trapType;

        Trap(String trapType) {
            this.trapType = trapType;
        }

        public String defend() {
            return trapType + " triggers automatically";
        }
    }

    static void resolveDefense(Defendable[] combatants) {
        for (Defendable c : combatants) {
            System.out.println(c.defend());
        }
    }

    public static void main(String[] args) {
        Warrior w = new Warrior("Kael");
        Trap t = new Trap("Spike Pit");

        System.out.println(w.attack());
        System.out.println(w.attack("Iron Sword"));
        System.out.println(w.defend());
        System.out.println(w.getSpecialMove());

        resolveDefense(new Defendable[]{w, t});
    }
}