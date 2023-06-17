package game.statuseffects;

import java.util.HashMap;
import java.util.Map;

public class StatusEffectFactory {
    /**
     * Return the status effect type.
     *
     * @param statusEffectType Respective status effect use to retrieve the status effect
     * @return The respective status effect type
     */
    public static StatusEffect createStatusEffect(StatusEffectType statusEffectType) {
        Map<StatusEffectType, StatusEffect> statusEffectMap = new HashMap<>();
        statusEffectMap.put(StatusEffectType.POISONED, new PoisonEffect());

        return statusEffectMap.get(statusEffectType);
    }
}
