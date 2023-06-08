package Backend.Enemies;
import Backend.Enemy;
import Backend.HeroicUnit;

public class Boss extends Enemy implements HeroicUnit{
    int visionRange;
    int abilityFrequency;
    int combatTicks;

    public Boss(int _visionRange, int _abilityFrequency ,String _name, int _maxHealth, int _attack, int _defense,int _expValue){
        super(_name, _maxHealth,  _attack, _defense, _expValue);
        visionRange = _visionRange;
        abilityFrequency = _abilityFrequency;
        combatTicks = 0;
    }
       
       
       
       
       public void castAbility(){
        //TODO
       }

}

