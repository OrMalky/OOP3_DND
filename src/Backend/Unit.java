package Backend;

import java.util.Random;



public abstract class Unit {
    protected String name;
    protected int maxHealth;
    protected int currentHealth;
    protected int attack;
    protected int defense;

    private Random rand = new Random();


    public Unit(String _name, int _maxHealth, int _attack, int _defense) {
        name = _name;
        maxHealth = _maxHealth;
        attack = _attack;
        defense = _defense;
        currentHealth = maxHealth;
    }

    public Hit takeDamage(int attackPoints,Unit attacker) {
        int damage = rand.nextInt(attackPoints) - rand.nextInt(defense);
        setCurrentHealth(damage > 0 ? (currentHealth - damage) : currentHealth);
        if (currentHealth <= 0) {
            currentHealth = 0;
            return new Hit(attack, damage, true, attacker, this, this.getExpValue());
        }
        return new Hit(attack, damage, false, attacker, this, this.getExpValue());
        
    }
    
    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getAttack() {
        return attack;
    }

    public abstract int getExpValue();

    public int getDefense() {
        return defense;
    }

    public void setMaxHealth(int _maxHealth) {
        maxHealth = _maxHealth;
    }

    public void setCurrentHealth(int _currentHealth) {
        currentHealth = _currentHealth;
    }

    public void setAttack(int _attack) {
        attack = _attack;
    }

    public void setDefense(int _defense) {
        defense = _defense;
    }

}
