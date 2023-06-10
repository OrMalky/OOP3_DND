package Backend;

import java.util.Random;

public abstract class Unit {

    protected Character tile;
    protected String name;
    protected int maxHealth;
    protected int currentHealth;
    protected int attack;
    protected int defense;

    private Random rand = new Random();

    public Unit(String _name, Character _tile, int _maxHealth, int _attack, int _defense) {
        name = _name;
        maxHealth = _maxHealth;
        attack = _attack;
        defense = _defense;
        currentHealth = maxHealth;
        tile = _tile;
    }

    public void takeDamage(int attackPoints, Unit attacker) {
        int attackRoll = rand.nextInt(attackPoints);
        int defenceRoll = rand.nextInt(defense);
        int damage = attackRoll - defenceRoll;
        setCurrentHealth(damage > 0 ? (currentHealth - damage) : currentHealth);
        if (currentHealth <= 0) {
            currentHealth = 0;
        }

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

    public abstract void tick();


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

    public Character getTile(){
        return tile;
    };

}
