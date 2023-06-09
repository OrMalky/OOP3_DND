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
    public boolean castAbility(ArrayList<Unit> target) {
        if (arrowCount <= 0) {
            return false;
        }
        arrowCount--;
        target.get(0).takeDamage(attack);
        return true;
    }

    @Override
    public int getAbilityRange() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAbilityRange'");
    }

    @Override
    public String toString() {
        return String.format(
                "Name: %s | Health: %d/%d | Attack: %d | Defense: %d | Level: %d | Experience: %d/%d | Range: %d | Arrow Count: %d",
                name, currentHealth, maxHealth, attack, defense, level, exp, EXP_PER_LEVEL, range, arrowCount);
    }

}
