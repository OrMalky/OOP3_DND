package Backend.Enemies;

import Backend.Enemy;
import Backend.MassageCallBack;

public class Trap extends Enemy {
    protected int visibilityRange;
    protected int visibilityTime;
    protected int invisibilityTime;
    protected int ticksCount;
    protected boolean isVisible;

    public Trap(String _name, int _maxHealth, int _attack, int _defense, int _expValue, int _visibilityTime,
            int _invisibilityTime, MassageCallBack _massageCallBack) {
        super(_name, _maxHealth, _attack, _defense, _expValue, _massageCallBack);
        // visibilityRange = _visibilityRange;
        visibilityTime = _visibilityTime;
        invisibilityTime = _invisibilityTime;
        ticksCount = 0;
        isVisible = true;
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

}