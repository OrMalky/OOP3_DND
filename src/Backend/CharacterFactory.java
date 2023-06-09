package Backend;

import Backend.PlayerCharacters.*;
import java.util.HashMap;
import Backend.Enemies.*;

public class CharacterFactory {
    public HashMap<String, Character> tileDictionary;

    public CharacterFactory() {
        createTileDictionary();
    }

    public Player createPlayer(String chr) {
        switch (chr) {
            case "1":
                return new Warrior("Jon Snow", 300, 30, 4, 3);
            case "2":
                return new Warrior("The Hound", 400, 20, 6, 5);
            case "3":
                return new Mage("Melisandre", 100, 5, 1, 300, 30, 40, 5, 6);
            case "4":
                return new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4);
            case "5":
                return new Rogue("Arya Stark", 150, 40, 2, 20);
            case "6":
                return new Rogue("Bronn", 250, 35, 3, 50);
            case "7":
                return new Hunter("Ygritte", 220, 30, 2, 6);
            default:
                throw new IllegalArgumentException("Invalid character name: " + chr);
        }
    }

    public char getTileChar(String tileName) {

        return tileDictionary.containsKey(tileName) ? tileDictionary.get(tileName) : '@';
    }

    private HashMap<String, Character> createTileDictionary() {
        tileDictionary = new HashMap<String, Character>();
        tileDictionary.put("Bonus Trap", 'B');
        tileDictionary.put("Queen's Trap", 'Q');
        tileDictionary.put("Death Trap", 'D');
        tileDictionary.put("Lannister Solider", 's');
        tileDictionary.put("Lannister Knight", 'k');
        tileDictionary.put("Queen's Guard", 'q');
        tileDictionary.put("Wright", 'z');
        tileDictionary.put("Bear-Wright", 'b');
        tileDictionary.put("Giant-Wright", 'g');
        tileDictionary.put("White Walker", 'w');
        tileDictionary.put("The Mountain", 'M');
        tileDictionary.put("Queen Cersei", 'C');
        tileDictionary.put("Night's King", 'K');
        tileDictionary.put("Tal Malma", 'T');
        return tileDictionary;
    }

    public Enemy createEnemy(char tile) {
        switch (tile) {
            case 'B':
                return new Trap("Bonus Trap", 1, 1, 1, 250, 1, 5);
            case 'Q':
                return new Trap("Queen's Trap", 250, 50, 10, 1000, 3, 7);
            case 'D':
                return new Trap("Death Trap", 500, 100, 20, 2000, 5, 10);
            case 's':
                return new Monster("Lannister Solider", 80, 8, 3, 3, 25);
            case 'k':
                return new Monster("Lannister Knight", 200, 14, 8, 3, 50);
            case 'q':
                return new Monster("Queen's Guard", 400, 20, 15, 5, 100);
            case 'z':
                return new Monster("Wright", 600, 30, 15, 3, 100);
            case 'b':
                return new Monster("Bear-Wright", 1000, 75, 30, 4, 250);
            case 'g':
                return new Monster("Giant-Wright", 1500, 100, 40, 4, 500);
            case 'w':
                return new Monster("White Walker", 2000, 150, 50, 6, 1000);
            case 'M':
                return new Monster("The Mountain", 1000, 60, 25, 6, 500);
            case 'C':
                return new Monster("Queen Cersei", 100, 10, 10, 1, 1000);
            case 'K':
                return new Monster("Night's King", 5000, 300, 150, 8, 5000);
            case '#': // noder
                return new Monster("Tal Malma", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE,
                        Integer.MAX_VALUE);
            default:
                throw new IllegalArgumentException("Invalid character tile: " + tile);
        }
    }

}
