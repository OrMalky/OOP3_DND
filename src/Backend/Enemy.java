package Backend;

public abstract class Enemy extends Unit{

    int expValue;
    
    public Enemy(String _name, int _maxHealth, int _attack, int _defense,int _expValue, MassageCallBack _massageCallBack){
        super(_name, _maxHealth, _attack, _defense, _massageCallBack);
        expValue = _expValue;
    }

    public int getExpValue(){
        return expValue;
    }

    


}