package com.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;

public class MapManager {
	private static MapManager mInstance;
	
	private TiledMap mTileMap;
	private TileAtlas mTileAtlas;
	private TileMapRenderer mTileMapRenderer;
	
	private MapManager(String mapPath, String mapName) {
		mTileMap = TiledLoader.createMap(Gdx.files.internal(mapPath + mapName));
		mTileAtlas = new TileAtlas(mTileMap, Gdx.files.internal(mapPath));
		mTileMapRenderer = new TileMapRenderer(mTileMap, mTileAtlas, 10, 10);
	}
	
	public static MapManager getInstance() {
		if (mInstance == null) {
			mInstance = new MapManager("data/village/", "village.tmx");
		}
		
		return mInstance;
	}
	
	public TileMapRenderer getTileMapRenderer() {
		return mTileMapRenderer;
	}
	
	public TiledMap getTileMap() {
		return mTileMap;
	}
}