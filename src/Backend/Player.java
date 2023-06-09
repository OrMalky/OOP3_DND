package Backend;

public abstract class Player extends Unit implements HeroicUnit  {
    protected int exp;
    protected int level;
    protected final int EXP_PER_LEVEL = 50;
    protected final int HEALTH_PER_LEVEL = 10;
    protected final int ATTACK_PER_LEVEL = 4;
    protected final int DEFENSE_PER_LEVEL = 1;


    public Player(String _name, int _maxHealth, int _attack, int _defense, MassageCallBack _massageCallBack) {
        super(_name, _maxHealth, _attack, _defense, _massageCallBack);
        exp = 0;
        level = 1;
    }

    public void addExp(int amount){
        exp += amount;
        if(exp >= EXP_PER_LEVEL * level){
            levelUp();
        }
    }



    protected void levelUp(){
        exp -= EXP_PER_LEVEL * level;
        level++;
        setMaxHealth(maxHealth + HEALTH_PER_LEVEL * level);
        setCurrentHealth(maxHealth);
        setAttack(attack + ATTACK_PER_LEVEL * level);
        setDefense(defense + DEFENSE_PER_LEVEL * level);
    }
    public abstract int getAbilityRange();

    public int getExpValue(){
        return -1;
    }
    


}