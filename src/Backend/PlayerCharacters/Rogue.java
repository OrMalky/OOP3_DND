package Backend.PlayerCharacters;

import java.util.ArrayList;

import Backend.Hit;
import Backend.Player;
import Backend.Unit;

public class Rogue extends Player {
    protected int cost;
    protected int currentEnergy;
    protected final int ABILITY_RANGE = 2;
    protected final int MAX_ENERGY = 100;

    public Rogue(String _name, int _maxHealth, int _attack, int _defense, int _cost) {
        super(_name, _maxHealth, _attack, _defense);
        cost = _cost;
        currentEnergy = 0;
    }

    public void levelUp() {
        super.levelUp();
        setCurrentEnergy(MAX_ENERGY);
        setAttack(attack + 3 * level);
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
    public String castAbility(ArrayList<Unit> targets) {
        ArrayList<Hit> hits = new ArrayList<Hit>();
        if (currentEnergy - cost < 0)
            return "Could not cast ability, not enough energy";
        for (Unit target : targets) {
            hits.add(target.takeDamage(attack, this));
        }
        return (hits.toString());
        

    }

    @Override
    public String toString() {
        return String.format(
                "Name: %s | Health: %d/%d | Attack: %d | Defense: %d | Level: %d | Experience: %d/%d |Energy: %d/%d | Cost: %d",
                name, currentHealth, maxHealth, attack, defense, level, exp, EXP_PER_LEVEL, MAX_ENERGY, currentEnergy,
                cost);
    }

}
