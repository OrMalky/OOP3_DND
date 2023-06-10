package Backend;

import Backend.PlayerCharacters.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import Backend.Enemies.*;

public class CharacterFactory {

    public enum Character{
        JON_SNOW(1),
        THE_HOUND(2),
        MELISANDRE(3),
        THOROS_OF_MYR(4),
        ARYA_STARK(5),
        BRONN(6),
        YGRITTE(7);

        private final int value;

        private Character(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private LinkedHashMap<Character, Player> playerCharacters;
    
    public CharacterFactory() {
        playerCharacters = new LinkedHashMap<Character,Player>(){{
                put(Character.JON_SNOW, new Warrior("Jon Snow", 300, 30, 4, 3));
                put(Character.THE_HOUND, new Warrior("The Hound", 400, 20, 6, 5));
                put(Character.MELISANDRE, new Mage("Melisandre", 100, 5, 1, 300, 30, 40, 5, 6));
                put(Character.THOROS_OF_MYR, new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4));
                put(Character.ARYA_STARK, new Rogue("Arya Stark", 150, 40, 2, 20));
                put(Character.BRONN, new Rogue("Bronn    ", 250, 35, 3, 50));
                put(Character.YGRITTE, new Hunter("Ygritte ", 220, 30, 2, 6));
        }};    
    }

    public Player choosePlayer(Character character) {
        return playerCharacters.get(character);
    }

    public ArrayList<Player> getCharacters() {
        return new ArrayList<Player>(playerCharacters.values());
    }

    public Enemy createEnemy(char tile) {
        switch (tile) {
            case 'B':
                return new Trap("Bonus Trap", 'B', 1, 1, 1, 250, 1, 5);
            case 'Q':
                return new Trap("Queen's Trap", 'Q', 250, 50, 10, 1000, 3, 7);
            case 'D':
                return new Trap("Death Trap", 'D', 500, 100, 20, 2000, 5, 10);
            case 's':
                return new Monster("Lannister Solider", 's', 80, 8, 3, 3, 25);
            case 'k':
                return new Monster("Lannister Knight", 'k', 200, 14, 8, 3, 50);
            case 'q':
                return new Monster("Queen's Guard", 'q', 400, 20, 15, 5, 100);
            case 'z':
                return new Monster("Wright", 'z', 600, 30, 15, 3, 100);
            case 'b':
                return new Monster("Bear-Wright", 'b', 1000, 75, 30, 4, 250);
            case 'g':
                return new Monster("Giant-Wright", 'g', 1500, 100, 40, 4, 500);
            case 'w':
                return new Monster("White Walker", 'w', 2000, 150, 50, 6, 1000);
            case 'M':
                return new Monster("The Mountain", 'M', 1000, 60, 25, 6, 500);
            case 'C':
                return new Monster("Queen Cersei", 'C', 100, 10, 10, 1, 1000);
            case 'K':
                return new Monster("Night's King", 'K', 5000, 300, 150, 8, 5000);
            case 'T': // noder
                return new Monster("Tal Malma", 'T', Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE,
                        Integer.MAX_VALUE);
            default:
                throw new IllegalArgumentException("Invalid character tile: " + tile);
        }
    }

}
