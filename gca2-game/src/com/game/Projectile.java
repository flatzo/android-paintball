package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.*;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Projectile extends Node {
	static final float PROJECTILE_SPEED = 0.1f;
	static int projectileId = 0;
	//static public enum DirectionProjectile { EST, NORDEST, NORD, NORDOUEST, OUEST, SUDOUEST, SUD, SUDEST };
	private Vector2 mDistancePerRender = new Vector2(0,0);
	final static Texture textureProjectile = new Texture(Gdx.files.internal("data/projectile.png"));
	
	Projectile ( Vector2 positionUnderFinger, Vector2 distanceAParcourirNormaliseeUnderFinger , Vector3 positionJoueur) {
		
		//under Finger
		super ( positionJoueur
				/*new Vector3(positionUnderFinger.x + distanceAParcourirNormaliseeUnderFinger.x, positionUnderFinger.y+distanceAParcourirNormaliseeUnderFinger.y, 1)*/
				, new String("projectile").concat( new Integer(projectileId).toString()) , textureProjectile,  new Vector2(0,0) );
		projectileId++;
		super.setDistancePerRender( new Vector2(distanceAParcourirNormaliseeUnderFinger.x*PROJECTILE_SPEED, distanceAParcourirNormaliseeUnderFinger.y*PROJECTILE_SPEED));
		//under finger
		
		
	}
	
	@Override
	public void update (Stage stage) {
		stage.findActor(super.getName() ).x += super.getDistancePerRender().x;
		stage.findActor(super.getName() ).y += super.getDistancePerRender().y;
	}

}
