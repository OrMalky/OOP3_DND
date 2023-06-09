package Backend.PlayerCharacters;

import java.util.ArrayList;

import Backend.Player;
import Backend.Unit;

public class Hunter extends Player {
    protected int range;
    protected int arrowCount;
    protected int tickCount;

    public Hunter(String _name, int _maxHealth, int _attack, int _defense, int _range) {
        super(_name, _maxHealth, _attack, _defense);
        range = _range;
        arrowCount = 0;
        tickCount = 0;
    }

    public void levelUp() {
        super.levelUp();
        setAttack(attack + 2 * level);
        setDefense(defense + level);
        setArrowCount(arrowCount + 10 * level);
    }

    public void tick() {
        tickCount++;
        if (tickCount == 10) {
            setArrowCount(arrowCount + level);
            tickCount = 0;
        }
    }

    public void setArrowCount(int arrowCount) {
        this.arrowCount = arrowCount;
    }

    @Override
    public String castAbility(ArrayList<Unit> target) {
        if (arrowCount <= 0) {
           return "could not cast ability, no arrows";
        }
        arrowCount--;
        return target.get(0).takeDamage(attack,this).toString();
        
    }

    @Override
    public int getAbilityRange() {
        return range;
    }

    @Override
    public String toString() {
        return String.format(
                "Name: %s | Health: %d/%d | Attack: %d | Defense: %d | Level: %d | Experience: %d/%d | Range: %d | Arrow Count: %d",
                name, currentHealth, maxHealth, attack, defense, level, exp, EXP_PER_LEVEL, range, arrowCount);
    }

}
