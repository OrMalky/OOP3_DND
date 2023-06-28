package Backend.PlayerCharacters;

import Backend.GameManager;
import Backend.Player;
import Backend.Position2D;
import Backend.Report;
import Backend.Unit;
import java.util.List;
import java.util.stream.Collectors;

public class Warrior extends Player {

    protected int abilityCooldown;
    protected int currentCooldown;

    protected final int ABILITY_RANGE = 3;
    protected final float ABILITY_DAMAGE = 0.1f;
    protected final int ABILITY_HEAL = 10;
    protected final int HEALTH_PER_LEVEL = 2;
    protected final int ATTACK_PER_LEVEL = 2;
    protected final int DEFENSE_PER_LEVEL = 1;

    public Warrior(String _name, int _maxHealth, int _attack, int _defense, int _abilityCooldown, Position2D _position) {
        super(_name, _maxHealth, _attack, _defense, _position);
        abilityCooldown = _abilityCooldown;
        type = Type.WARRIOR;
    }

    public Report castAbility(List<Unit> targets) {
        if (currentCooldown > 0){
            GameManager.addMessage("Ability is on cooldown");
            return null;
        }
        
        currentCooldown = abilityCooldown;
        targets = targets.stream().filter(unit -> Position2D.getRange(unit.getPosition(), position) < ABILITY_RANGE)
            .collect(Collectors.toList());
        
        if (targets.size() <= 0){
            GameManager.addMessage(name + " used Avenger's Shield, but there were no targets in range");
            return new Report();
        }
        
        Unit target = targets.get(rand.nextInt(targets.size()));
        int heal = heal(ABILITY_HEAL * defense);
        int damage = target.takeDamage((int)(ABILITY_DAMAGE * maxHealth));
        GameManager.addMessage(name + " used Avenger's Shield, healing for " + heal + 
            " and hitting " + target.getName() + " for " + damage + " damage");
        return new Report(target);
    }

    public void levelUp() {
        super.levelUp();
        currentCooldown = 0;
        setMaxHealth(maxHealth + HEALTH_PER_LEVEL * level);
        setAttack(attack + ATTACK_PER_LEVEL * level);
        setDefense(defense + DEFENSE_PER_LEVEL * level);
    }

    public void tick() {
        if (currentCooldown > 0)
            currentCooldown--;
    }

    public int getAbilityRange() {
        return ABILITY_RANGE;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + String.format(" | Ability Cooldown: %d/%d", currentCooldown, abilityCooldown);
    }
}