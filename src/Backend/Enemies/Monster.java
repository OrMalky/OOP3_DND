package Backend.Enemies;

import Backend.Enemy;

public class Monster extends Enemy {
    protected int visionRange;

    public Monster(int _visionRange, int _abilityFrequency ,String _name, int _maxHealth, int _attack, int _defense, int _expValue){
        super(_name, _maxHealth,  _attack, _defense, _expValue);
        visionRange = _visionRange;
    }
}