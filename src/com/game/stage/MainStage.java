package com.game.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainStage extends Stage {
	
	public MainStage(float width, float height, boolean keepAspectRatio) {
		super(width, height, keepAspectRatio);
		
	}
	
	public void render() {
        act();
        draw();
	}
}