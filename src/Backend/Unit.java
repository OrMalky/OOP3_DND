package Backend;



public abstract class Unit {
    protected String name;
    protected int maxHealth;
    protected int currentHealth;
    protected int attack;
    protected int defense;

    public Unit(String _name, int _maxHealth, int _attack, int _defense){
        name = _name;
        maxHealth = _maxHealth;
        currentHealth = _maxHealth;
        attack = _attack;
        defense = _defense;
        currentHealth = maxHealth;
    }
    

    public String getName(){
        return name;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public int getCurrentHealth(){
        return currentHealth;
    }

    public int getAttack(){
        return attack;
    }

    public int getDefense(){
        return defense;
    }

    public void setMaxHealth(int _maxHealth){
        maxHealth = _maxHealth;
    }

    public void setCurrentHealth(int _currentHealth){
        currentHealth = _currentHealth;
    }

    public void setAttack(int _attack){
        attack = _attack;
    }

    public void setDefense(int _defense){
        defense = _defense;
    }

    public void takeDamage(int amount){
        setCurrentHealth(defense- amount > 0 ? (currentHealth - ( defense - amount )) : currentHealth);
    }
}
