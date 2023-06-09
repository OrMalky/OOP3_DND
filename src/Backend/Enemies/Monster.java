package Backend.Enemies;

import Backend.Enemy;

public class Monster extends Enemy {
    protected int visionRange;
    protected int expValue;
    protected int attackRange;
    protected int attackPoints;
    protected int defensePoints;


    public Monster(String _name, int _maxHealth, int _attack, int _defense, int _visionRange, int _expValue) {
        super(_name, _maxHealth, _attack, _defense, _expValue);
        visionRange = _visionRange;
    }



}