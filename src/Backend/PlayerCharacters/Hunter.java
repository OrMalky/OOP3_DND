package Backend.PlayerCharacters;

import java.util.ArrayList;

import Backend.Player;
import Backend.Unit;

public class Hunter extends Player {
    protected int range;
    protected int arrowCount;
    protected int tickCount;

    protected final int ARROWS_PER_LEVEL = 10;
    protected final int ATTACK_PER_LEVEL = 2;
    protected final int DEFENSE_PER_LEVEL = 1;

    public Hunter(String _name, int _maxHealth, int _attack, int _defense, int _range) {
        super(_name, _maxHealth, _attack, _defense);
        range = _range;
        arrowCount = 0;
        tickCount = 0;
        type = Type.HUNTER;
    }

    public void levelUp() {
        super.levelUp();
        setAttack(attack + ATTACK_PER_LEVEL * level);
        setDefense(defense + DEFENSE_PER_LEVEL * level);
        setArrowCount(arrowCount + ARROWS_PER_LEVEL * level);
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
    public void castAbility(ArrayList<Unit> target) {
        if (arrowCount <= 0) {
           
        }
        arrowCount--;
        target.get(0).takeDamage(attack,this);
        
    }

    @Override
    public int getAbilityRange() {
        return range;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Arrow Count: %d/%d", arrowCount, 10 * level);
    }

}
