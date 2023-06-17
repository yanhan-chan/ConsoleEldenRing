package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.*;
import game.actors.enemies.LoneWolf;
import game.actors.traders.FingerReaderEnia;
import game.actors.traders.MerchantKale;
import game.grounds.*;
import game.items.GoldenRunes;
import game.items.RemembranceOfGrafted;
import game.reset.RespawnManager;
import game.statuseffects.StatusEffectType;
import game.utils.FancyMessage;
import game.utils.MenuManager;
import game.weapons.Club;
import game.weapons.SerpentboneBlade;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Graveyard(),
				new PuddleOfWater(), new GustOfWind(), new SiteOfLostGrace(), new Cliff(), new Barrack(), new Cage(),
				new SummonSign());

//		List<String> map = Arrays.asList(
//				"...........................................................................",
//				"......................#####....######......................................",
//				"......................#..___....____#......................................",
//				"..................................__#......................................",
//				"......................._____........#......................................",
//				"......................#............_#......................................",
//				"......................#...........###......................................",
//				"...........................................................................",
//				"...........................................................................",
//				"..................................###___###................................",
//				"..................................________#................................",
//				"..................................#________................................",
//				"..................................#___U___#................................",
//				"..................................###___###................................",
//				"....................................#___#..................................",
//				"...........................................................................",
//				"...........................................................................",
//				"...........................................................................",
//				"..####__##....................................................######..##...",
//				"..#.....__....................................................#....____....",
//				"..#___..........................................................__.....#...",
//				"..####__###..................................................._.....__.#...",
//				"..............................................................###..__###...",
//				"...........................................................................");
		List<String> limgrave = Arrays.asList(
				"..nnnn................#.............#.................~~~~~....+++.........",
				"......................#.............#.................~~~~~.+++++..........",
				"..nnnn................#..___....____#.................~~~~~...+++++........",
				"......................#...........__#.................~~~~~......++........",
				"......................#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"......................######...######......................................",
				"...........................................................................",
				"...........................=...............................................",
				"........++++......................###___###................................",
				"........+++++++...................________#....nnnn........................",
				"..........+++.....................#___U____................................",
				"~~~~........+++...................#_______#....nnnn........................",
				"~~~~.........+....................###___###................................",
				"~~~~........++......................#___#..................................",
				"..............+...................=........................................",
				"..............++.................................................=.........",
				"..............................................++...........................",
				"..................++++......................+++......&&.......######..##...",
				"#####___######....++...........................+++...&&.......#....____....",
				"_____________#.....+&&+..........................+..............__.....#...",
				"_____________#.....+&&..++........................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++..............................................");
		List<String> stormveilCastle = Arrays.asList(
				"...........................................................................",
				"..................<...............<........................................",
				"...........................................................................",
				"##############################################...##########################",
				"............................#................#.......B..............B......",
				".....B...............B......#................#.............................",
				"...............................<.........<.................................",
				".....B...............B......#................#.......B..............B......",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______........................B......B........................B.....B...",
				"_____..._..____....&&........<..............<..............................",
				".........____......&&......................................................",
				"...._______..................<..............<....................<.....<...",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");
		List<String> roundtableHold = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########___#######");
		List<String> bossRoom = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				"..=......................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++");

//		GameMap gameMap = new GameMap(groundFactory, map);
		GameMap limgraveMap = new EldenRingGameMap(groundFactory, limgrave, "Limgrave");
		GameMap roundtableHoldMap = new EldenRingGameMap(groundFactory, roundtableHold, "Roundtable Hold");
		GameMap stormveilCastleMap = new EldenRingGameMap(groundFactory, stormveilCastle, "Stormveil Castle");
		GameMap bossRoomMap = new EldenRingGameMap(groundFactory, bossRoom, "Godrick the Grafted");
//		world.addGameMap(gameMap);
		world.addGameMap(limgraveMap);
		world.addGameMap(roundtableHoldMap);
		world.addGameMap(stormveilCastleMap);
		world.addGameMap(bossRoomMap);

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		// Add Golden Fog Doors at limgrave
		limgraveMap.at(5, 23).setGround(new GoldenFogDoor(roundtableHoldMap, roundtableHoldMap.at(9, 10)));
		limgraveMap.at(30 ,0).setGround(new GoldenFogDoor(stormveilCastleMap, stormveilCastleMap.at(38, 23)));
		// Add Golden Runes at limgrave
		limgraveMap.at(26, 2).addItem(new GoldenRunes());
		limgraveMap.at(38, 10).addItem(new GoldenRunes());
		limgraveMap.at(73, 11).addItem(new GoldenRunes());
		limgraveMap.at(69, 19).addItem(new GoldenRunes());
		limgraveMap.at(3, 21).addItem(new GoldenRunes());
		// Add Merchant Kale to limgrave
		limgraveMap.at(40,12).addActor(new MerchantKale());

		// Add Golden Fog Door at stormveil castle
		stormveilCastleMap.at(38, 23).setGround(new GoldenFogDoor(limgraveMap, limgraveMap.at(30, 0)));
		stormveilCastleMap.at(5, 0).setGround(new GoldenFogDoor(bossRoomMap, bossRoomMap.at(0, 4)));
		// Add Golden Runes at stormveil castle
		stormveilCastleMap.at(74, 1).addItem(new GoldenRunes());
		stormveilCastleMap.at(0, 4).addItem(new GoldenRunes());
		stormveilCastleMap.at(8, 10).addItem(new GoldenRunes());
		stormveilCastleMap.at(74, 10).addItem(new GoldenRunes());
		stormveilCastleMap.at(38, 21).addItem(new GoldenRunes());

		// Add Golden Fog Door at roundtable hold
		roundtableHoldMap.at(9, 10).setGround(new GoldenFogDoor(limgraveMap, limgraveMap.at(5, 23)));
		// Add Finger Reader Enia at roundtable hold
		roundtableHoldMap.at(9, 1).addActor(new FingerReaderEnia());
		// Add SerpentBone Blade at roundtable hold (Creative Requirement)
		roundtableHoldMap.at(8, 8).addItem(new SerpentboneBlade());

		// menu console
		Player player = MenuManager.playerClassMenu();
		// set default respawn location (First Step Site of Lost Grace)
		RespawnManager.getInstance().setLocation(limgraveMap.at(38, 11));
		// added to test ExchangeAction since we did not implement the boss
		player.addItemToInventory(new RemembranceOfGrafted());

		// HINT: what does it mean to prefer composition to inheritance?
//		Player player = new Player("Tarnished", '@', 300);
//		world.addPlayer(player, gameMap.at(36, 10));
		world.addPlayer(player, limgraveMap.at(36, 10));

		world.run();
	}
}
