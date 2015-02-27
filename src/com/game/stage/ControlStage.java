package com.game.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ControlStage extends Stage {
	
	private Touchpad mTouchpad;
	private TouchpadStyle mTouchpadStyle;
	
	private static final int SPEED = 2;
	
	public ControlStage() {
		super();
		TextureRegionDrawable touchpadBackground = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("shape/touchBackground.png"))));
		
		TextureRegionDrawable touchpadKnob = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("shape/touchKnob.png"))));
		
		mTouchpadStyle = new TouchpadStyle(touchpadBackground, touchpadKnob);
		mTouchpad = new Touchpad(15, mTouchpadStyle);
		addActor(mTouchpad);
	}
	
	public void render() {
		act();
		draw();
	}
	
	public boolean isTouched() {
		return mTouchpad.isTouched();
	}
	
	public float getOffsetX() {
		return mTouchpad.getKnobPercentX() * SPEED;
	}
	
	public float getOffsetY() {
		return mTouchpad.getKnobPercentY() * SPEED;
	}
}