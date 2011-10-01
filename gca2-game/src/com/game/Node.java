package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Node {
	//sprite
	
	private Texture texture;
	Vector2 relativePosition = new Vector2();
	SpriteBatch mSb = new SpriteBatch();
	
	public Node(int x, int y, Texture textureInput) {
		
		texture = textureInput;
		relativePosition = new Vector2();
		mSb = new SpriteBatch();
		
		if (x >= Gdx.graphics.getWidth()) {
			relativePosition.x = Gdx.graphics.getWidth()-1;
		}
		else if (x < 0) {
			relativePosition.x = 0;
		}
		else {
			relativePosition.x = x;
		}
		
		if (y >= Gdx.graphics.getHeight()) {
			relativePosition.y = Gdx.graphics.getHeight()-1;
		}
		else if (y < 0) {
			relativePosition.y = 0;
		}
		else {
			relativePosition.y = y;
		}
	}
	
	public void draw(int shiftX, int shiftY) {
		mSb.begin();
		mSb.draw(texture, relativePosition.x+shiftX, relativePosition.y+shiftY);
		mSb.end();
	}

}

