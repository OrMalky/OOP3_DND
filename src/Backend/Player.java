package Backend;

public abstract class Player extends Unit implements HeroicUnit  {

    protected enum Type{
        MAGE,
        HUNTER,
        WARRIOR,
        ROUGE
    }

    protected int exp;
    protected int level;
    protected Type type;
    protected final int EXP_PER_LEVEL = 50;
    protected final int HEALTH_PER_LEVEL = 10;
    protected final int ATTACK_PER_LEVEL = 4;
    protected final int DEFENSE_PER_LEVEL = 1;


    public Player(String _name, int _maxHealth, int _attack, int _defense) {
        super(_name, '@', _maxHealth, _attack, _defense);
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

    public String getDescription(){
        return String.format("%s\t %s   \tHealth: %d\tAttack: %d\tDefense: %d", name, type, maxHealth, attack, defense);
    }
    
    public String toString(){
        return String.format("%s | %s | Health: %d/%d | Attack: %d | Defense: %d | Level: %d | Experience: %d/%d", name, type, currentHealth, maxHealth, attack, defense, level, exp, EXP_PER_LEVEL);
    }
}