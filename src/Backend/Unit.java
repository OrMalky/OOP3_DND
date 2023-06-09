package Backend;

import java.util.Random;

public abstract class Unit {
    protected String name;
    protected int maxHealth;
    protected int currentHealth;
    protected int attack;
    protected int defense;
    protected MassageCallBack messageCallback;

    private Random rand = new Random();

    public Unit(String _name, int _maxHealth, int _attack, int _defense, MassageCallBack _massageCallBack) {
        name = _name;
        maxHealth = _maxHealth;
        attack = _attack;
        defense = _defense;
        currentHealth = maxHealth;
        messageCallback = _massageCallBack;
    }

    public void takeDamage(int attackPoints, Unit attacker) {
        int attackRoll = rand.nextInt(attackPoints);
        int defenceRoll = rand.nextInt(defense);
        int damage = attackRoll - defenceRoll;
        setCurrentHealth(damage > 0 ? (currentHealth - damage) : currentHealth);
        reportDamage(attacker, this, attackRoll, defenceRoll);
        if (currentHealth <= 0) {
            currentHealth = 0;
            messageCallback.addSystemMassage("name: " + name + " is dead");
        }

    }

    public void reportDamage(Unit attacker, Unit defender, int attackRoll, int defenceRoll) {
        messageCallback.addSystemMassage(attacker.name + " attacked " + defender.name);
        messageCallback.addSystemMassage("attack roll: " + attackRoll);
        messageCallback.addSystemMassage("defence roll: " + defenceRoll);
        if (attackRoll <= defenceRoll)
            messageCallback.addSystemMassage("no damage has been done");
        else {
            messageCallback.addSystemMassage("damage done: " + (attackRoll - defenceRoll));
        }
        if (defender.currentHealth <= 0)
            messageCallback.addSystemMassage(defender.name + " is dead");
        else {
            messageCallback.addSystemMassage(defender.name + " has " + defender.currentHealth + " health left");
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

}
