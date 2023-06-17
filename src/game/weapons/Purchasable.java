package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;

import java.util.HashMap;
import java.util.Map;

/**
 * Interface that indicate the weapon is purchasable.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public interface Purchasable {

    /**
     * Return the purchase price of weapons.
     *
     * @return The purchase price of weapons
     */
    int getPurchasePrice();

}
