package Backend;

public class Hit {
    public int damage;
    public int attackRoll;
    public int defenseRoll;
    public boolean isDead;
    public Unit attacker;
    public Unit defender;
    public int expValue;

    public Hit(int _attackRoll, int _defenseRoll, boolean _isDead, Unit _attacker, Unit _defender, Integer expValue) {
        attackRoll = _attackRoll;
        defenseRoll = _defenseRoll;
        damage = _attackRoll - _defenseRoll;
        isDead = _isDead;
        attacker = _attacker;
        defender = _defender;
        expValue = expValue == null ? 0 : expValue;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(attacker.getName());
        s.append(" attacked ");
        s.append(defender.getName() + "\n");
        s.append("attack roll: ");
        s.append(attackRoll + "\n");
        s.append(" defense roll: ");
        s.append(defenseRoll + "\n");
        if (damage > 0) {
            s.append(" damage: ");
            s.append(damage);
        } else {
            s.append(" no damage");
        }
        if (isDead) {
            s.append(attacker.getName());
            s.append(" killed");
            s.append(defender.getName());
            s.append(" and gained ");
            s.append(expValue);
            if (expValue > 0) {
                s.append(expValue);
                s.append(" experience");
            } else {
                s.append("no experience");
            }
        }

        return s.toString();
    }

}
