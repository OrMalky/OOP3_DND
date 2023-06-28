package Backend.PlayerCharacters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Backend.GameManager;
import Backend.Player;
import Backend.Position2D;
import Backend.Report;
import Backend.Unit;

public class Mage extends Player {
    protected int maxMana;
    protected int currentMana;
    protected int manaCost;
    protected int spellPower;
    protected int spellHits;
    protected int spellRange;

    protected final int MANA_PER_LEVEL = 25;
    protected final int POWER_PER_LEVEL = 4;
    protected final int MANA_PER_TICK = 1;
    protected final float MANA_LEVELUP_REGEN = 0.25f;

    public Mage(String _name, int _maxHealth, int _attack, int _defense, int _maxMana, int _manaCost, int _spellPower,
            int _spellHits, int _spellRange, Position2D _position) {
        super(_name, _maxHealth, _attack, _defense, _position);
        maxMana = _maxMana;
        manaCost = _manaCost;
        spellPower = _spellPower;
        spellHits = _spellHits;
        currentMana = _maxMana;
        spellRange = _spellRange;
        type = Type.MAGE;
    }

    protected void levelUp() {
        super.levelUp();
        setMaxMana(maxMana + MANA_PER_LEVEL * level);
        setCurrentMana(Math.min(maxMana, (int)(currentMana + maxMana * MANA_LEVELUP_REGEN)));
        setSpellPower(spellPower + POWER_PER_LEVEL * level);
    }

    public void tick() {
        setCurrentMana(Math.min(maxMana, currentMana + MANA_PER_TICK * level));
    }

    public void setMaxMana(int amount) {
        maxMana = amount;
    }

    public void setCurrentMana(int amount) {
        currentMana = amount;
    }

    public void setSpellPower(int amount) {
        spellPower = amount;
    }

    @Override
    public Report castAbility(List<Unit> targets) {
        if (currentMana < manaCost) {
            GameManager.addMessage("Not enough mana to cast ability");
            return null;
        }

        currentMana -= manaCost;
        targets = targets.stream().filter(unit -> Position2D.getRange(unit.getPosition(), position) < spellRange)
            .collect(Collectors.toList());
        
        if (targets.size() <= 0){
            GameManager.addMessage(name + " cast Blizzard, but there were no targets in range");
            return new Report();
        }
        
        int hits = 0;
        List<Unit> hitResult = new ArrayList<>();
        StringBuilder message = new StringBuilder(name + " cast Blizzard:");
        while (hits < spellHits){
            Unit target = targets.get(rand.nextInt(targets.size()));
            int damage = target.takeDamage(spellPower);
            if(!hitResult.contains(target))
                hitResult.add(target);
            message.append("\n\tBlizzard hit " + target.getName() + " for " + damage + " damage");
            hits++;
        }
        GameManager.addMessage(message.toString());
        return new Report(hitResult);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + String.format(" | Mana: %d/%d | Spell Power: %d | Spell Hits: %d | Spell Range: %d",
                currentMana, maxMana, spellPower, spellHits, spellRange);

    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format("\tMax Mana: %d\tMana Cost: %d\tSpell Power: %d\tSpell Hits: %d\t Range: %d",
                maxMana, manaCost, spellPower, spellHits, spellRange);
    }
}