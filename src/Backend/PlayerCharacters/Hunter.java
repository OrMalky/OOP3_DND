package Backend.PlayerCharacters;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Backend.GameManager;
import Backend.Player;
import Backend.Position2D;
import Backend.Report;
import Backend.Unit;

public class Hunter extends Player {
    protected int range;
    protected int arrowCount;
    protected int tickCount;

    protected final int ARROWS_PER_LEVEL = 10;
    protected final int ATTACK_PER_LEVEL = 2;
    protected final int DEFENSE_PER_LEVEL = 1;
    protected final int ARROW_REGEN_TICKS = 10;
    protected final int ARROW_REGEN_PER_LEVEL = 1;

    public Hunter(String _name, int _maxHealth, int _attack, int _defense, int _range, Position2D _position) {
        super(_name, _maxHealth, _attack, _defense, _position);
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
        if (tickCount == ARROW_REGEN_TICKS) {
            setArrowCount(arrowCount + ARROW_REGEN_PER_LEVEL * level);
            tickCount = 0;
        }
        tickCount++;
    }

    public void setArrowCount(int arrowCount) {
        assert arrowCount >= 0;
        this.arrowCount = arrowCount;
    }

    @Override
    public Report castAbility(List<Unit> targets) {
        if (arrowCount <= 0){
            GameManager.addMessage("Ability is on cooldown");
            return null;
        }
        
        arrowCount--;
        targets = targets.stream().filter(unit -> Position2D.getRange(unit.getPosition(), position) < range)
            .collect(Collectors.toList());
        
        if (targets.size() <= 0){
            GameManager.addMessage(name + " used Shoot, but there were no targets in range");
            return new Report();
        }
        
        Unit target = targets.stream().min(Comparator.comparingDouble(unit -> Position2D.getRange(unit.getPosition(), position))).orElse(null);
        int damage = target.takeDamage(attack);
        GameManager.addMessage(name + " used Shoot, hitting " + target.getName() + " for " + damage + " damage");
        return new Report(target);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + String.format(" | Arrow Count: %d/%d", arrowCount, 10 * level);
    }
}
