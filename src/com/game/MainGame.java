package com.game;

import com.badlogic.gdx.Game;
import com.game.screen.MainGameScreen;

public class MainGame extends Game {

	@Override
	public void create() {
		setScreen(new MainGameScreen(this));
	}

}
