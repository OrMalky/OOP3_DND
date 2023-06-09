package Backend.Enemies;

import Backend.Enemy;
import Backend.MassageCallBack;

public class Monster extends Enemy {
    protected int visionRange;
    protected int expValue;
    protected int attackRange;
    protected int attackPoints;
    protected int defensePoints;

    public Monster(String _name, int _maxHealth, int _attack, int _defense, int _visionRange, int _expValue,
            MassageCallBack _massageCallBack) {
        super(_name, _maxHealth, _attack, _defense, _expValue, _massageCallBack);
        visionRange = _visionRange;
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

}