package Backend.PlayerCharacters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Backend.GameManager;
import Backend.Player;
import Backend.Position2D;
import Backend.Report;
import Backend.Unit;

public class Rogue extends Player {
    protected int energyCost;
    protected int currentEnergy;

    protected final int ATTACK_PER_LEVEL = 3;
    protected final int ABILITY_RANGE = 2;
    protected final int MAX_ENERGY = 100;

    public Rogue(String _name, int _maxHealth, int _attack, int _defense, int _cost, Position2D _position) {
        super(_name, _maxHealth, _attack, _defense, _position);
        energyCost = _cost;
        currentEnergy = MAX_ENERGY;
        type = Type.ROUGE;
    }

    public void levelUp() {
        super.levelUp();
        setCurrentEnergy(MAX_ENERGY);
        setAttack(attack + ATTACK_PER_LEVEL * level);
    }

    public void tick() {
        setCurrentEnergy(Math.min(currentEnergy + 10, MAX_ENERGY));
    }

    public void setCurrentEnergy(int _currentEnergy) {
        currentEnergy = _currentEnergy;
    }

    @Override
    public Report castAbility(List<Unit> targets) {
        if (currentEnergy < energyCost) {
            GameManager.addMessage("Not enough energy to use ability");
            return null;
        }

        currentEnergy -= energyCost;
        targets = targets.stream().filter(unit -> Position2D.getRange(unit.getPosition(), position) < ABILITY_RANGE)
            .collect(Collectors.toList());
        
        if (targets.size() <= 0){
            GameManager.addMessage(name + " used Fan of Knives, but there were no targets in range");
            return new Report();
        }
        
        List<Unit> hitResult = new ArrayList<>();
        StringBuilder message = new StringBuilder(name + " used Fan of Knives:");
        for (Unit target : targets) {
            int damage = target.takeDamage(attack);
            message.append("\n\tFan of Knives hit " + target.getName() + " for " + damage + " damage");
            hitResult.add(target);
        }
        GameManager.addMessage(message.toString());
        return new Report(hitResult);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + String.format(" | Energy: %d/%d", currentEnergy, MAX_ENERGY);
    }
}
