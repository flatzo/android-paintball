package com.game;

import android.R.bool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Projectile extends Node {
	static final float PROJECTILE_SPEED =0.5f;
	static int projectileId = 0;
	final static Texture textureProjectile = new Texture(Gdx.files.internal("data/projectile.png"));
	private float mTimeLeft = 3; 
	private Actor mCurrentActor;
	private Vector2 mCurrentDirection;
	
	Projectile ( Vector2 direction, Stage stage) {
		super ( new Vector3(stage.findActor("mainChar").x, stage.findActor("mainChar").y, 2)
				, new String("projectile").concat( new Integer(projectileId).toString())
				, textureProjectile
				,  new Vector2(0,0)
				, stage 
				);
		mCurrentDirection = direction.nor();
		mCurrentActor = stage.findActor(new String("projectile").concat( new Integer(projectileId).toString()));
		projectileId++;
		
	}
	
	@Override
	public boolean update (Stage stage) {
		float dTime = Gdx.graphics.getDeltaTime();
		mTimeLeft-= dTime;
		if (mTimeLeft < 0) {
			return true;
		}
		mCurrentActor.x += mCurrentDirection.x*100*dTime;
		mCurrentActor.y += mCurrentDirection.y*100*dTime;
		return false;
	}

}
