package Backend.Enemies;

import java.util.ArrayList;

import Backend.Enemy;
import Backend.HeroicUnit;
import Backend.MassageCallBack;
import Backend.Unit;

public class Boss extends Enemy implements HeroicUnit {
    int visionRange;
    int abilityFrequency;
    int combatTicks;

    public Boss(int _visionRange, int _abilityFrequency, String _name, int _maxHealth, int _attack, int _defense,
            int _expValue,MassageCallBack _massageCallBack) {
        super(_name, _maxHealth, _attack, _defense, _expValue, _massageCallBack);
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
