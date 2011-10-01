package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.*;

public class Node {
	// sprite

	private String mName = new String();
	private Texture texture;
	Vector2 relativePosition = new Vector2();
	Vector2 mDistancePerRender = new Vector2(0,0);
	
	SpriteBatch mSb = new SpriteBatch();
	

	
	public Node(int x, int y, Texture textureInput, String nameOfNode, Vector2 distancePerRender) {

		mDistancePerRender = distancePerRender; 
		
		mName = nameOfNode;
		texture = textureInput;
		relativePosition = new Vector2();
		mSb = new SpriteBatch();

		if (x >= Gdx.graphics.getWidth()) {
			relativePosition.x = Gdx.graphics.getWidth() - 1;
		} else if (x < 0) {
			relativePosition.x = 0;
		} else {
			relativePosition.x = x;
		}

		if (y >= Gdx.graphics.getHeight()) {
			relativePosition.y = Gdx.graphics.getHeight() - 1;
		} else if (y < 0) {
			relativePosition.y = 0;
		} else {
			relativePosition.y = y;
		}
	}

	public void draw(int shiftX, int shiftY) {
		mSb.begin();
		mSb.draw(texture, relativePosition.x + shiftX,
				relativePosition.y + shiftY);
		mSb.end();
	}

	public Vector2 getRelativePosition() {
		return relativePosition;
	}

	public String getName() {
		return mName;
	}
	
	Vector2 moveToward(Vector2 destination) {
		Vector2 distanceToTravel = new Vector2(destination.x - relativePosition.x, destination.y - relativePosition.y);

		
		Math.abs(3);
		if (Math.abs(distanceToTravel.x) < mDistancePerRender.x ) {
			relativePosition.x = destination.x;
		}
		else {
			relativePosition.x += distanceToTravel.x;
		}
		
		if (Math.abs(distanceToTravel.y) < mDistancePerRender.y ) {
			relativePosition.y = destination.y;
		}
		else {
			relativePosition.y += distanceToTravel.y;
		}
		
		//TODO considers adding obstacle detection
		
		return (new Vector2(distanceToTravel.x, distanceToTravel.y));
	}

}
