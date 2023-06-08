package Backend.PlayerCharacters;

import Backend.Player;
import Backend.Unit;
import java.util.ArrayList;

import java.util.Random;


public class Warrior extends Player {

    protected int abilityCooldown;
    protected int currentCooldown;
    protected final int ABILITY_RANGE = 3;

    private Random rand = new Random();

    public Warrior(String _name, int _maxHealth, int _attack, int _defense, int _abilityCooldown) {
        super(_name, _maxHealth, _attack, _defense);
        abilityCooldown = _abilityCooldown;
    }

    //Not sure we want this to work this way, we need a way to tell the GameManager that the player has used their ability
    //Maybe EventListener?
    public void castAbility(ArrayList<Unit> targets) {
        if (currentCooldown <= 0)
        {
            currentCooldown = abilityCooldown;
            setCurrentHealth(Math.min(currentHealth + 10 * defense, maxHealth));
            targets.get(rand.nextInt(targets.size())).takeDamage(maxHealth / 10);
        }
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