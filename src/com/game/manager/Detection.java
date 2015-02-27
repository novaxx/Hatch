package com.game.manager;

import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;

public class Detection {

	public static int mTile[][];
	public static TiledMap mTiledMap;
	
	static {
		mTiledMap = MapManager.getInstance().getTileMap();
		mTile = mTiledMap.layers.get(1).tiles;
	}
	
	private static final int LOCAL_TRIM = 1;
	private static boolean mIsDetection = false;
	
	public static boolean LeftEnable(float spiritX, float spiritY, float spiritWidth, float spiritHeight) {
		int x = MathUtils.ceilPositive(spiritX / mTiledMap.tileWidth);
		int y_up = mTile.length
		           - MathUtils.ceilPositive((spiritY + spiritHeight / 2) / mTiledMap.tileHeight);
		int y_down = mTile.length - MathUtils.ceilPositive(spiritY / mTiledMap.tileHeight);
		int y_center = mTile.length
		           - MathUtils.ceilPositive((spiritY + spiritHeight) / mTiledMap.tileHeight);
		
		if (mTile[y_up][x - 1] != 0 || mTile[y_down][x - 1] != 0
	            || mTile[y_center][x - 1] != 0) {
			if (mIsDetection) {
				return false;
			} else {
				return DetectionLeftEnable(spiritX, spiritY, spiritWidth, spiritHeight);
			}
	    } else {
		    return true;
	    }
	}
	
	private static boolean DetectionLeftEnable(float spiritX, float spiritY, float spiritWidth, float spiritHeight) {
		mIsDetection = true;
		boolean enable = LeftEnable(spiritX, spiritY + LOCAL_TRIM, spiritWidth, spiritHeight);
		
		if (!enable) {
			enable = LeftEnable(spiritX, spiritY - LOCAL_TRIM, spiritWidth, spiritHeight);
		}
		
		mIsDetection = false;
		
		return enable;
	}
	
	public static boolean RightEnable(float spiritX, float spiritY, float spiritWidth, float spiritHeight) {
		int x = MathUtils.ceilPositive((spiritX + spiritWidth) / mTiledMap.tileWidth);
		int y_up = mTile.length
		           - MathUtils.ceilPositive((spiritY + spiritHeight / 2) / mTiledMap.tileHeight);
		int y_down = mTile.length - MathUtils.ceilPositive(spiritY / mTiledMap.tileHeight);
		int y_center = mTile.length
		           - MathUtils.ceilPositive((spiritY + spiritHeight) / mTiledMap.tileHeight);
		
		if (mTile[y_up][x - 1] != 0 || mTile[y_down][x - 1] != 0
	            || mTile[y_center][x - 1] != 0) {
			if (mIsDetection) {
				return false;
			} else {
				return DetectionRightEnable(spiritX, spiritY, spiritWidth, spiritHeight);
			}
	    } else {
		    return true;
	    }
	}
	
	private static boolean DetectionRightEnable(float spiritX, float spiritY, float spiritWidth, float spiritHeight) {
		mIsDetection = true;
		boolean enable = RightEnable(spiritX, spiritY + LOCAL_TRIM, spiritWidth, spiritHeight);
		
		if (!enable) {
			enable = RightEnable(spiritX, spiritY - LOCAL_TRIM, spiritWidth, spiritHeight);
		}
		
		mIsDetection = false;
		
		return enable;
	}
	
	public static boolean UpEnable(float spiritX, float spiritY, float spiritWidth, float spiritHeight) {
	    int y = mTile.length - MathUtils.ceilPositive((spiritY + spiritHeight) / mTiledMap.tileHeight);
	    int x_left = MathUtils.ceilPositive(spiritX / mTiledMap.tileWidth);
	    int x_right = MathUtils.ceilPositive((spiritX + spiritWidth) / mTiledMap.tileWidth);
	    int x_center = MathUtils.ceilPositive((spiritX + spiritWidth / 2) / mTiledMap.tileWidth);
	
	    if (mTile[y][x_left - 1] != 0 || mTile[y][x_right - 1] != 0
	            || mTile[y][x_center - 1] != 0) {
	    	if (mIsDetection) {
	    		return false;
	    	} else {
	    		return DetectionUpEnable(spiritX, spiritY, spiritWidth, spiritHeight);
	    	}
	    } else {
	        return true;
	    }
	}
	
	private static boolean DetectionUpEnable(float spiritX, float spiritY, float spiritWidth, float spiritHeight) {
		mIsDetection = true;
		boolean enable = UpEnable(spiritX + LOCAL_TRIM, spiritY, spiritWidth, spiritHeight);
		
		if (!enable) {
			enable = UpEnable(spiritX - LOCAL_TRIM, spiritY, spiritWidth, spiritHeight);
		}
		
		mIsDetection = false;
		
		return enable;
	
	}
	
	public static boolean DownEnable(float spiritX, float spiritY, float spiritWidth, float spiritHeight) {
	    int y = mTile.length - MathUtils.ceilPositive(spiritY / mTiledMap.tileHeight);
	    int x_left = MathUtils.ceilPositive(spiritX / mTiledMap.tileWidth);
	    int x_right = MathUtils.ceilPositive((spiritX + spiritWidth) / mTiledMap.tileWidth);
	    int x_center = MathUtils.ceilPositive((spiritX + spiritWidth / 2) / mTiledMap.tileWidth);
	
	    if (mTile[y][x_left - 1] != 0 || mTile[y][x_right - 1] != 0
	            || mTile[y][x_center - 1] != 0) {
	    	if (mIsDetection) {
	    		return false;
	    	} else {
	    		return DetectionDownEnable(spiritX, spiritY, spiritWidth, spiritHeight);
	    	}
	    } else {
	        return true;
	    }
	}
	
	private static boolean DetectionDownEnable(float spiritX, float spiritY, float spiritWidth, float spiritHeight) {
		mIsDetection = true;
		boolean enable = DownEnable(spiritX + LOCAL_TRIM, spiritY, spiritWidth, spiritHeight);
		
		if (!enable) {
			enable = DownEnable(spiritX - LOCAL_TRIM, spiritY, spiritWidth, spiritHeight);
		}
		
		mIsDetection = false;
		
		return enable;
	
	}
}
