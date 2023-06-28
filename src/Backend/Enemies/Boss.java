package Backend.Enemies;

import java.util.List;

import Backend.GameManager;
import Backend.HeroicUnit;
import Backend.Position2D;
import Backend.Report;
import Backend.Unit;

public class Boss extends Monster implements HeroicUnit {
    int visionRange;
    int abilityFrequency;
    int combatTicks;

    public Boss(String _name, char _tile, int _maxHealth, int _attack, int _defense, int _expValue, int _visionRange,
        int _abilityFrequency, Position2D _position) {
        super(_name, _tile, _maxHealth, _attack, _defense, _visionRange, _expValue, _position);
        visionRange = _visionRange;
        abilityFrequency = _abilityFrequency;
        combatTicks = 0;
    }
    

    @Override
    public Report castAbility(List<Unit> targets) {
        assert targets.size() == 1;
        Unit player = targets.get(0);
        int damage = player.takeDamage(attack);
        GameManager.addMessage(name + " used Shoot, hitting " + player.getName() + " for " + damage + " damage");
        return new Report(player);
    }

    @Override
    public Position2D turn(Unit player) {
        if (Position2D.getRange(position, player.getPosition()) < visionRange) {
            if (combatTicks == abilityFrequency){
                combatTicks = 0;
                castAbility(List.of(player));
                return null;
            }
            else {
                combatTicks++;
                return super.turn(player);
            }
        }
        return null;
    }
}
