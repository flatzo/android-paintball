package com.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.badlogic.gdx.scenes.scene2d.actors.Label;
import com.badlogic.gdx.scenes.scene2d.actors.Image.*;
import com.badlogic.gdx.scenes.scene2d.actors.*;

public class Node {
	// sprite

	private String mName = new String();
	//private Texture texture;
	Vector3 relativePosition = new Vector3(0,0,0);
	private Vector2 mDistancePerRender = new Vector2(0,0);
	Image mImage;
	
	final int BACK = 0;
	final int FRONT = 1;
	
	public Node(Vector3 position, String nameOfNode,  Texture texture , Vector2 distancePerRender, Stage stage) {
		mImage = new Image(nameOfNode, texture);
		mDistancePerRender = distancePerRender; 
		
		mName = nameOfNode;
		//relativePosition = position;
		mImage.x= position.x;
		mImage.y = position.y;
		
		stage.addActor(mImage) ;

		/*
		if (position.x >= Gdx.graphics.getWidth()) {
			relativePosition.x = Gdx.graphics.getWidth() - 1;
		} else if (position.x < 0) {
			relativePosition.x = 0;
		} else {
			relativePosition.x = position.x;
		}

		if (position.y >= Gdx.graphics.getHeight()) {
			relativePosition.y = Gdx.graphics.getHeight() - 1;
		} else if (position.y < 0) {
			relativePosition.y = 0;
		} else {
			relativePosition.y = position.y;
		}
		*/
	}

	
	//bool true if you can delete it. 
	public boolean update(Stage stage) {
		return false;
	}

	public Vector2 getRelativePosition() {
		return new Vector2(relativePosition.x, relativePosition.y);
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
	
	public Vector2 getDistancePerRender() {
		return mDistancePerRender;
	}
	public void setDistancePerRender(Vector2 newDistancePerRender) {
		mDistancePerRender = newDistancePerRender;
	}
	public Image getImage() {
		return mImage;
	}
	
	public void setPosition( float newX, float newY, Stage stage) {
		stage.findActor(mName).x = newX;
		stage.findActor(mName).y = newY;
	}


}
