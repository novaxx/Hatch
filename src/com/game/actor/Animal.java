package com.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.manager.Detection;

public class Animal extends Actor {
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
	
	private int mCurrentTime = 0;
	
	private int[] mMoveRect = new int[4];
	
	public Animal(float x, float y) {
		mPostionX = x;
		mPostionY = y;
		mDirection = DIRECTION.Down;
		mState = STATE.Stand;
		initPlayerMoveRect();
		initPlayerImage();
	}
	
	private void initPlayerImage() {
		Texture texture = new Texture(Gdx.files.internal("data/spirit/mao.png"));
		TextureRegion[][] players = TextureRegion.split(texture, 32, 32);
		for (TextureRegion[] regions : players) {
			for (TextureRegion region : regions) {
				region.flip(true, false);
			}
		}
		// TextureRegion[] color = new TextureRegion[1];
		mTextureRegion = players[0][0];
		
		mDownAnim = new Animation(0.3f, players[0]);
		mLeftAnim = new Animation(0.3f, players[1]);
		mRightAnim = new Animation(0.3f, players[2]);
		mUpAnim = new Animation(0.3f, players[3]);
	}
	
	private void initPlayerMoveRect() {
		if (mPostionX < 80) {
			mMoveRect[0] = 0;
			mMoveRect[2] = 160;
		} else {
			mMoveRect[0] = (int)(mPostionX - 80);
			mMoveRect[2] = (int)(mPostionX + 80);
		}
		
		if (mPostionY < 80) {
			mMoveRect[1] = 0;
			mMoveRect[3] = 160;
		} else {
			mMoveRect[1] = (int)(mPostionY - 80);
			mMoveRect[3] = (int)(mPostionY + 80);
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		mStateTime += Gdx.graphics.getDeltaTime();
		
		// 随机乱跳
		mCurrentTime++;
		if (mCurrentTime >= 100) {
			mCurrentTime = 0;
			
			int num[] = {5, 0, 5, 0, -5, -5, 0, 5, 0, 0};
			int rax = (int)(Math.random() * 10);
			int ray = (int)(Math.random() * 10);
			movePosition(num[rax < 10 ? rax : 1], num[ray < 10 ? ray : 1]);
		}
		// 按照特定区域跑
		
		batch.draw(getCurrentFrame(), mPostionX, mPostionY);
	}
	
	private TextureRegion getCurrentFrame() {
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
	
	public void updatePositon(float x, float y) {
		mPostionX = x;
		mPostionY = y;
	}
	
	public void movePosition(float offsetx, float offsety) {
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