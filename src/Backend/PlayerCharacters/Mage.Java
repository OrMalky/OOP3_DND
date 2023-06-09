package Backend.PlayerCharacters;

import java.util.ArrayList;
import java.util.Random;

import Backend.MassageCallBack;
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

    private Random rand = new Random();

    public Mage(String _name, int _maxHealth, int _attack, int _defense, int _maxMana, int _manaCost, int _spellPower,
            int _spellHits, int _spellRange, MassageCallBack _massageCallBack) {
        super(_name, _maxHealth, _attack, _defense, _massageCallBack);
        maxMana = _maxMana;
        manaCost = _manaCost;
        spellPower = _spellPower;
        spellHits = _spellHits;
        currentMana = _maxMana;
        spellRange = _spellRange;
    }

    protected void levelUp() {
        super.levelUp();
        setMaxMana(maxMana + MANA_PER_LEVEL * level);
        setCurrentMana(Math.min(maxMana, currentMana + maxMana / 4));
        setSpellPower(spellPower + POWER_PER_LEVEL * level);
    }

    public void tick() {
       // setCurrentMana(Math.min(maxMana, currentMana + level));
    }

    public void setMaxMana(int amount) {
        maxMana = amount;
    }

    public void setCurrentMana(int amount) {
        currentMana = amount;
    }

    public void setSpellPower(int amount) {
        spellPower = amount;
    }

    public int getAbilityRange() {
        return spellRange;
    }

    @Override
    public void castAbility(ArrayList<Unit> target) {
        if (currentMana < manaCost) {
            messageCallback.addSystemMassage("Could not cast ability: not enough mana");
        }
        int hitsCount = 0;
        while (hitsCount < spellHits) {
            target.get(rand.nextInt(target.size())).takeDamage(spellPower, this);
            hitsCount++;
        }

    }

    @Override
    public String toString() {
        return String.format(
                "Name: %s | Health: %d/%d | Attack: %d | Defense: %d | Level: %d | Experience: %d/%d | Mana: %d/%d | Mana Cost: %d | Spell Power: %d | Spell Hits: %d | Spell Range: %d",
                name, currentHealth, maxHealth, attack, defense, level, exp, EXP_PER_LEVEL, currentMana, maxMana,
                manaCost, spellPower, spellHits, spellRange);

    }
}