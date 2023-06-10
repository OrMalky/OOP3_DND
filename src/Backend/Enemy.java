package Backend;

public abstract class Enemy extends Unit{

    int expValue;
    
    public Enemy(String _name, Character _tile, int _maxHealth, int _attack, int _defense,int _expValue){
        super(_name, _tile, _maxHealth, _attack, _defense);
        expValue = _expValue;
    }

    public int getExpValue(){
        return expValue;
    }

    


}