package jsettlers.main.android.mainmenu.factories;

import jsettlers.common.menu.IJoinPhaseMultiplayerGameConnector;
import jsettlers.common.menu.IMapDefinition;
import jsettlers.main.android.core.GameStarter;
import jsettlers.main.android.mainmenu.navigation.MainMenuNavigator;
import jsettlers.main.android.mainmenu.presenters.NewMultiPlayerSetupPresenter;
import jsettlers.main.android.mainmenu.presenters.NewMultiPlayerSetupPresenterImpl;
import jsettlers.main.android.mainmenu.presenters.NewMultiPlayerSetupPresenterPop;
import jsettlers.main.android.mainmenu.views.NewMultiPlayerSetupView;

import android.app.Activity;

/**
 * Created by tompr on 03/02/2017.
 */

public class PresenterFactory {
    public static NewMultiPlayerSetupPresenter createNewMultiPlayerSetupPresenter(Activity activity, NewMultiPlayerSetupView view) {
        MainMenuNavigator mainMenuNavigator = (MainMenuNavigator) activity;
        GameStarter gameStarter = (GameStarter) activity.getApplication();
        IJoinPhaseMultiplayerGameConnector joinPhaseMultiplayerGameConnector = gameStarter.getJoinPhaseMultiplayerConnector();
        IMapDefinition mapDefinition = gameStarter.getMapDefinition();

        if (joinPhaseMultiplayerGameConnector == null || mapDefinition == null) {
            return new NewMultiPlayerSetupPresenterPop(mainMenuNavigator);
        } else {
            return new NewMultiPlayerSetupPresenterImpl(view, mainMenuNavigator, gameStarter, joinPhaseMultiplayerGameConnector, mapDefinition);
        }
    }
}