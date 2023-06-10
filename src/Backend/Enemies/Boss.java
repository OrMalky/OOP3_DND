package Backend.Enemies;

import java.util.ArrayList;

import Backend.Enemy;
import Backend.HeroicUnit;
import Backend.Unit;

public class Boss extends Enemy implements HeroicUnit {
    int visionRange;
    int abilityFrequency;
    int combatTicks;

    public Boss(String _name, Character _tile, int _maxHealth, int _attack, int _defense, int _visionRange, int _abilityFrequency, 
            int _expValue) {
        super(_name, _tile, _maxHealth, _attack, _defense, _expValue);
        visionRange = _visionRange;
        abilityFrequency = _abilityFrequency;
        combatTicks = 0;
    }
    

    @Override
    public void castAbility(ArrayList<Unit> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'castAbility'");
    }

    @Override
    public int getAbilityRange() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAbilityRange'");
    }


    @Override
    public void tick() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }



    

}
