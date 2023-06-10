package Backend.PlayerCharacters;

import java.util.ArrayList;

import Backend.Player;
import Backend.Unit;

public class Rogue extends Player {
    protected int cost;
    protected int currentEnergy;

    protected final int ATTACK_PER_LEVEL = 3;
    protected final int ABILITY_RANGE = 2;
    protected final int MAX_ENERGY = 100;

    public Rogue(String _name, int _maxHealth, int _attack, int _defense, int _cost) {
        super(_name, _maxHealth, _attack, _defense);
        cost = _cost;
        currentEnergy = 0;
        type = Type.ROUGE;
    }

    public void levelUp() {
        super.levelUp();
        setCurrentEnergy(MAX_ENERGY);
        setAttack(attack + ATTACK_PER_LEVEL * level);
    }

    public void tick() {
        setCurrentEnergy(Math.min(currentEnergy + 10, MAX_ENERGY));
    }

    public void setCurrentEnergy(int _currentEnergy) {
        currentEnergy = _currentEnergy;
    }

    @Override
    public int getAbilityRange() {
        return ABILITY_RANGE;
    }

    @Override
    public void castAbility(ArrayList<Unit> targets) {
        if (currentEnergy - cost < 0){
            
        }
        else{
            for (Unit target : targets) {
             target.takeDamage(attack, this);
            }
        }        
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Energy: %d/%d", currentEnergy, MAX_ENERGY);
    }

}
