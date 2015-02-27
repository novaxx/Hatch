package com.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.manager.Detection;

public class Player extends Actor {
	private enum DIRECTION {
		Down, Left, Right, Up
	}
	
	private enum STATE {
		Stand, Move
	}
	
	private float mPostionX;
	private float mPostionY;
	private DIRECTION mDirection;
	private STATE mState;
	private TextureRegion mTextureRegion;
	
	private float mStateTime;
	
	private Animation mDownAnim;
	private Animation mLeftAnim;
	private Animation mRightAnim;
	private Animation mUpAnim;
	
	public Player(float x, float y) {
		mPostionX = x;
		mPostionY = y;
		mDirection = DIRECTION.Down;
		mState = STATE.Stand;
		initPlayerImage();
	}
	
	private void initPlayerImage() {
		Texture texture = new Texture(Gdx.files.internal("data/spirit/xiaoyan.png"));
		TextureRegion[][] players = TextureRegion.split(texture, 32, 32);
		for (TextureRegion[] regions : players) {
			for (TextureRegion region : regions) {
				region.flip(true, false);
			}
		}
		// TextureRegion[] color = new TextureRegion[1];
		mTextureRegion = players[0][0];
		
		mDownAnim = new Animation(0.2f, players[0]);
		mLeftAnim = new Animation(0.2f, players[1]);
		mRightAnim = new Animation(0.2f, players[2]);
		mUpAnim = new Animation(0.2f, players[3]);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		mStateTime += Gdx.graphics.getDeltaTime();
		batch.draw(getCurrentFrame(), mPostionX, mPostionY);
	}
	
	private TextureRegion getCurrentFrame() {
		if (mState == STATE.Stand) {
			switch (mDirection) {
			case Down:
				return mDownAnim.getKeyFrame(0.2f, true);
			case Left:
				return mLeftAnim.getKeyFrame(0.2f, true);
			case Right:
				return mRightAnim.getKeyFrame(0.2f, true);
			case Up:
				return mUpAnim.getKeyFrame(0.2f, true);
			default:
				break;
			}
			return mDownAnim.getKeyFrame(0.2f, true);
		} else {
			switch (mDirection) {
			case Down:
				return mDownAnim.getKeyFrame(mStateTime, true);
			case Left:
				return mLeftAnim.getKeyFrame(mStateTime, true);
			case Right:
				return mRightAnim.getKeyFrame(mStateTime, true);
			case Up:
				return mUpAnim.getKeyFrame(mStateTime, true);
			default:
				break;
			}
			return mDownAnim.getKeyFrame(mStateTime, true);
		}
	}
	
	public void updatePositon(float x, float y) {
		mPostionX = x;
		mPostionY = y;
	}
	
	public void movePosition(float offsetx, float offsety) {
		if (offsetx == 0 && offsety == 0) {
			mState = STATE.Stand;
			return;
		}
		
		mState = STATE.Move;
		
		if (Math.abs(offsetx) > Math.abs(offsety)) {
			mDirection = offsetx > 0 ? DIRECTION.Left : DIRECTION.Right;
		} else {
			mDirection = offsety > 0 ? DIRECTION.Up : DIRECTION.Down;
		}
		
		if (offsetx > 0 && Detection.RightEnable(mPostionX, mPostionY, mTextureRegion.getRegionWidth(), mTextureRegion.getRegionHeight())) {
			mPostionX += offsetx;
		} else if (offsetx < 0 && Detection.LeftEnable(mPostionX, mPostionY, mTextureRegion.getRegionWidth(), mTextureRegion.getRegionHeight())) {
			mPostionX += offsetx;
		}
		
		if (offsety < 0  && Detection.DownEnable(mPostionX, mPostionY, mTextureRegion.getRegionWidth(), mTextureRegion.getRegionHeight())) {
			mPostionY += offsety;
		} else if (offsety > 0  && Detection.UpEnable(mPostionX, mPostionY, mTextureRegion.getRegionWidth(), mTextureRegion.getRegionHeight())) {
			mPostionY += offsety;
		}
		
		if (mPostionX < 0) mPostionX = 0;
		if (mPostionY < 0) mPostionY = 0;
		if (mPostionX > (250 * 32 - mTextureRegion.getRegionWidth())) mPostionX = 250 * 32 - mTextureRegion.getRegionWidth();
		if (mPostionY > (150 * 32 - mTextureRegion.getRegionHeight())) mPostionY = 150 * 32 - mTextureRegion.getRegionHeight();
	}
	
	public float getPositionX() {
		return mPostionX;
	}
	
	public float getPositionY() {
		return mPostionY;
	}
}