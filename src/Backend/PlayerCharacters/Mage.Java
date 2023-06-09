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

    public Mage(String _name, int _maxHealth, int _attack, int _defense, int _maxMana, int _manaCost, int _spellPower, int _spellHits, int _spellRange){
        super(_name, _maxHealth, _attack, _defense);
        maxMana = _maxMana;
        manaCost = _manaCost;
        spellPower = _spellPower;
        spellHits = _spellHits;
        currentMana = _maxMana;
        spellRange = _spellRange;
    }

    public void castAbility() {
        if(currentMana >= manaCost){
            currentMana -= manaCost;
            System.out.println("You cast a spell!");
        }
        else{
            System.out.println("You don't have enough mana!");
        }
    }

    protected void levelUp() {
        super.levelUp();
        setMaxMana(maxMana + MANA_PER_LEVEL * level);
        setCurrentMana(Math.min(maxMana, currentMana + maxMana/4));
        setSpellPower(spellPower + POWER_PER_LEVEL * level);
    }

    public void setMaxMana(int amount){
        maxMana = amount;
    }

    public void setCurrentMana(int amount){
        currentMana = amount;
    }

    public void setSpellPower(int amount){
        spellPower = amount;
    }

    public int getAbilityRange(){
        return spellRange;
    }

    @Override
    public boolean castAbility(ArrayList<Unit> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'castAbility'");
    }
}