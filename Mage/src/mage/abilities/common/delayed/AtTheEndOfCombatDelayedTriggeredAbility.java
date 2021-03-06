package mage.abilities.common.delayed;

import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.effects.Effect;
import mage.game.Game;
import mage.game.events.GameEvent;

public class AtTheEndOfCombatDelayedTriggeredAbility extends DelayedTriggeredAbility {
    public AtTheEndOfCombatDelayedTriggeredAbility(Effect effect) {
        super(effect);
    }

    public AtTheEndOfCombatDelayedTriggeredAbility(AtTheEndOfCombatDelayedTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public AtTheEndOfCombatDelayedTriggeredAbility copy() {
        return new AtTheEndOfCombatDelayedTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.COMBAT_PHASE_POST;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        return true;
    }

    @Override
    public String getRule() {
        String ruleText = modes.getText();
        if (ruleText.contains("at end of combat")) {
            return ruleText;
        }
        return "At end of combat, " + ruleText;
    }
}
