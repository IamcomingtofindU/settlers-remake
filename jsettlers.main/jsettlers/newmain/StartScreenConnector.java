package jsettlers.newmain;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jsettlers.graphics.startscreen.interfaces.IChangingList;
import jsettlers.graphics.startscreen.interfaces.ILoadableMapDefinition;
import jsettlers.graphics.startscreen.interfaces.IMultiplayerConnector;
import jsettlers.graphics.startscreen.interfaces.IStartScreen;
import jsettlers.graphics.startscreen.interfaces.IStartableMapDefinition;
import jsettlers.graphics.startscreen.interfaces.IStartingGame;
import jsettlers.logic.map.save.MapList;
import jsettlers.logic.map.save.MapLoader;
import jsettlers.newmain.datatypes.ChangingList;
import jsettlers.newmain.datatypes.MapDefinition;

/**
 * This class implements the {@link IStartScreen} interface and acts as connector between the start screen and the game logic.
 * 
 * @author Andreas Eberle
 * 
 */
public class StartScreenConnector implements IStartScreen {

	private final MapList mapList;

	public StartScreenConnector() {
		this.mapList = MapList.getDefaultList();
	}

	@Override
	public IChangingList<? extends IStartableMapDefinition> getSingleplayerMaps() {
		ArrayList<MapLoader> maps = mapList.getFreshMaps();
		List<MapDefinition> result = new LinkedList<MapDefinition>();

		for (MapLoader currMap : maps) {
			MapDefinition mapDef = new MapDefinition(currMap.getUniqueID(), currMap.getName(), currMap.getDescription(), currMap.getImage(),
					currMap.getMinPlayers(), currMap.getMaxPlayers());
			result.add(mapDef);
		}

		return new ChangingList<MapDefinition>(result);
	}

	@Override
	public IChangingList<? extends ILoadableMapDefinition> getStoredSingleplayerGames() {
		ArrayList<MapLoader> maps = mapList.getSavedMaps();
		List<MapDefinition> result = new LinkedList<MapDefinition>();

		for (MapLoader currMap : maps) {
			// TODO @Andreas Eberle: supply saved player information
			MapDefinition mapDef = new MapDefinition(currMap.getUniqueID(), currMap.getName(), currMap.getDescription(), currMap.getImage(), null);
			result.add(mapDef);
		}

		return new ChangingList<MapDefinition>(result);
	}

	@Override
	public IChangingList<? extends IStartableMapDefinition> getMultiplayerMaps() {
		return getSingleplayerMaps();
	}

	@Override
	public IChangingList<? extends ILoadableMapDefinition> getRestorableMultiplayerGames() {
		return getStoredSingleplayerGames();
	}

	@Override
	public IStartingGame startSingleplayerGame(IStartableMapDefinition map) {
		return startGame(map.getId());
	}

	@Override
	public IStartingGame loadSingleplayerGame(ILoadableMapDefinition map) {
		return startGame(map.getId());
	}

	private IStartingGame startGame(String mapId) {
		MapLoader mapLoader = mapList.getMapById(mapId);
		long randomSeed = 4711L;

		JSettlersGame game = new JSettlersGame(mapLoader, randomSeed, (byte) 0);
		return game.start();
	}

	@Override
	public IMultiplayerConnector getMultiplayerConnector(String serverAddr) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
