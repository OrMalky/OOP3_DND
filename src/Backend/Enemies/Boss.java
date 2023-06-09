package Backend.Enemies;

import java.util.ArrayList;

import Backend.Enemy;
import Backend.HeroicUnit;
import Backend.Unit;

public class Boss extends Enemy implements HeroicUnit {
    protected int visionRange;
    protected int abilityFrequecy;
    protected int combatTicks;

    public Boss(int _visionRange, int _abilityFrequency, String _name, int _maxHealth, int _attack, int _defense,
            int _expValue) {
        super(_name, _maxHealth, _attack, _defense, _expValue);
        visionRange = _visionRange;
        abilityFrequecy = _abilityFrequency;
        combatTicks = 0;
    }

    @Override
    public boolean castAbility(ArrayList<Unit> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'castAbility'");
    }

    @Override
    public int getAbilityRange() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAbilityRange'");
    }

}
