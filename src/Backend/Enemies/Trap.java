package Backend.Enemies;

import Backend.Enemy;

public class Trap extends Enemy{
    protected int visibilityRange;
    protected int visibilityTime;
    protected int invisibilityTime;
    protected int ticksCount;
    protected boolean isVisible;

    public Trap(String _name, int _maxHealth, int _attack, int _defense, int _expValue, int _visibilityTime, int _invisibilityTime){
        super(_name, _maxHealth,  _attack, _defense, _expValue);
       // visibilityRange = _visibilityRange;
        visibilityTime = _visibilityTime;
        invisibilityTime = _invisibilityTime;
        ticksCount = 0;
        isVisible = true;
    }
}