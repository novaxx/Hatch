package com.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class Badge extends WidgetGroup {

	Image mBadgeImage;
	Label mBagetLabel;
	Image mBloodImage;
	Image mMagicImage;
	
	public Badge() {
		setSize(400, 100);
		setPosition(0, 1080 - 100);
		mBadgeImage = new Image(new Texture(Gdx.files.internal("head.png")));
		mBadgeImage.setSize(100, 100);
		addActor(mBadgeImage);
		
		LabelStyle labelStyle = new LabelStyle(new BitmapFont(), Color.ORANGE);
		labelStyle.font.setScale(2);
		mBagetLabel = new Label("NOVA", labelStyle);
		mBagetLabel.setSize(400, 100);
		mBagetLabel.setPosition(100, 30);
		addActor(mBagetLabel);
		
		Texture texture = new Texture(Gdx.files.internal("shape/rect.png"));
		TextureRegion[][] colors = TextureRegion.split(texture, 32, 32);
		mBloodImage = new Image(colors[0][1]);
		mMagicImage = new Image(colors[0][0]);
		mBloodImage.setSize(200, 10);
		mMagicImage.setSize(200, 10);
		mBloodImage.setPosition(100, 50);
		mMagicImage.setPosition(100, 40);
		addActor(mBloodImage);
		addActor(mMagicImage);
	}
	
	public void setBlood(int blood) {
		
	}
	
	public void setMagic(int magic) {
		
	}
}