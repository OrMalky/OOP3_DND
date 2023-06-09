package Backend.PlayerCharacters;

import java.util.ArrayList;

import Backend.Player;
import Backend.Unit;

public class Mage extends Player {
    protected int maxMana;
    protected int currentMana;
    protected int manaCost;
    protected int spellPower;
    protected int spellHits;
    protected int spellRange;

    protected final int MANA_PER_LEVEL = 25;
    protected final int POWER_PER_LEVEL = 4;

    public Mage(String _name, int _maxHealth, int _attack, int _defense, int _maxMana, int _manaCost, int _spellPower,
            int _spellHits, int _spellRange) {
        super(_name, _maxHealth, _attack, _defense);
        maxMana = _maxMana;
        currentMana = _maxMana;
        manaCost = _manaCost;
        spellPower = _spellPower;
        spellHits = _spellHits;
        currentMana = _maxMana;
        spellRange = _spellRange;
    }

    // i deleted your prints because : 5.1 : Do not use System.out.println directly
    // from any non-UI class.
    // A player gets a list of units (dependes on he's ability range) and then apply
    // on them the neccecery method.
    // see the warrior class for example.
    public boolean castAbility(ArrayList<Unit> target) {
        if (currentMana >= manaCost) {
            currentMana -= manaCost;

            return true;
        }

        else {
            return false;
        }

    }

    protected void levelUp() {
        super.levelUp();
        setMaxMana(maxMana + MANA_PER_LEVEL * level);
        setCurrentMana(Math.min(maxMana, currentMana + maxMana / 4));
        setSpellPower(spellPower + POWER_PER_LEVEL * level);

    }

    public void setMaxMana(int _maxMana) {
        maxMana = _maxMana;
    }

    public void setCurrentMana(int _currentMana) {
        currentMana = _currentMana;
    }

    public void setSpellPower(int _spellPower) {
        spellPower = _spellPower;
    }

    public int getAbilityRange() {
        return spellRange;
    }

}