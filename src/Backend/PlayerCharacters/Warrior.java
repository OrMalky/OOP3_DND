package Backend.PlayerCharacters;

import java.util.ArrayList;

import Backend.Player;
import Backend.Unit;

public class Warrior extends Player {

    protected int abilityCooldown;
    protected int currentCooldown;
    protected final int ABILITY_RANGE = 3;

    public Warrior(int _abilityCooldown, String _name, int _maxHealth, int _attack, int _defense) {
        super(_name, _maxHealth, _attack, _defense);
        abilityCooldown = _abilityCooldown;
    }

    public boolean castAbility(ArrayList<Unit> targets) {
        if (currentCooldown > 0)
            return false;
        currentCooldown = abilityCooldown;
        setCurrentHealth(Math.min(currentHealth + 10 * defense, maxHealth));
        // Randomly hits one enemy within range < 3 for an amount equals to 10% of the
        // warrior’s max health.
        // manager will send him the targets with getAbilityRange (heroic method)
        chooseRandom(targets).takeDamage(maxHealth / 10);
        return true;
    }

    public void levelUp() {
        super.levelUp();
        abilityCooldown = 0;
        setMaxHealth(maxHealth + 5 * level);
        setAttack(attack + 2 * level);
        setDefense(defense + 1 * level);
    }

    public void tick() {
        if (currentCooldown > 0)
            currentCooldown--;
    }

    public int getAbilityRange() {
        return ABILITY_RANGE;
    }

}