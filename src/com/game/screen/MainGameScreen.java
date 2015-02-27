package com.game.screen;

import java.util.ArrayList;

import android.util.Log;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledObject;
import com.badlogic.gdx.graphics.g2d.tiled.TiledObjectGroup;
import com.game.actor.Animal;
import com.game.actor.Player;
import com.game.manager.MapManager;
import com.game.stage.ControlStage;
import com.game.stage.MainStage;
import com.game.util.GameUtil;
import com.game.view.Badge;

public class MainGameScreen extends BaseScreen {

	private TileMapRenderer mRenderer;
	private OrthographicCamera mCamera;
	
	private MainStage mMainStage;
	private ControlStage mControlStage;
	
	private Player mPlayer;
	private ArrayList<Animal> mCats = new ArrayList<Animal>();
	
	Badge mPlayerBadge;
	
	public MainGameScreen(Game game) {
		super(game);
		
		mRenderer = MapManager.getInstance().getTileMapRenderer();
		mCamera = new OrthographicCamera();
		mCamera.setToOrtho(false, GameUtil.SCREEN_WIDTH, GameUtil.SCREEN_HEIGHT);
		
		mMainStage = new MainStage(GameUtil.SCREEN_WIDTH, GameUtil.SCREEN_HEIGHT, false);
		initMainStageActor();
		
		mControlStage = new ControlStage();
		
		mPlayerBadge = new Badge();
		mControlStage.addActor(mPlayerBadge);
		Gdx.input.setInputProcessor(mControlStage);
	}
	
	private void initMainStageActor() {
		mPlayer = new Player(0, 0);
		initSpiritPosition();
		mMainStage.addActor(mPlayer);
		for (int i = 0; i < mCats.size(); i++) {
			mMainStage.addActor(mCats.get(i));
		}
	}
	
	private void initSpiritPosition() {
		for (TiledObjectGroup group : MapManager.getInstance().getTileMap().objectGroups) {
			for (TiledObject object : group.objects) {
				if ("player".equals(object.name)) {
					mPlayer.updatePositon(object.x, mRenderer.getMapHeightUnits() - object.y);
				} else if ("mao".equals(object.name)) {
					Animal cat = new Animal(object.x, mRenderer.getMapHeightUnits() - object.y);
					mCats.add(cat);
				}
			}
		}
	}

	@Override
	public void render(float arg0) {
		super.render(arg0);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if (mControlStage.isTouched()) {
			mPlayer.movePosition(mControlStage.getOffsetX(), mControlStage.getOffsetY());
		}
		
		if (mPlayer.getPositionX() > GameUtil.SCREEN_WIDTH / 2 && mPlayer.getPositionX() < 8000 - GameUtil.SCREEN_WIDTH / 2) {
			mMainStage.getCamera().position.x = mCamera.position.x = mPlayer.getPositionX();
		}
		
		if (mPlayer.getPositionY() > GameUtil.SCREEN_HEIGHT / 2 && mPlayer.getPositionY() < 4800 - GameUtil.SCREEN_HEIGHT / 2) {
			mMainStage.getCamera().position.y = mCamera.position.y = mPlayer.getPositionY();
		}
		
		mCamera.update();
		
		mRenderer.render(mCamera);
		mMainStage.render();
		mControlStage.render();
	}
}
