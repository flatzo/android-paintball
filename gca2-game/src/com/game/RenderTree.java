package com.game;

import java.util.List;
import java.util.Vector;
import java.util.List.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class RenderTree {
	Vector<Node> children = new Vector<Node> ();
	Stage mStage;
	
	Texture mainCharacterTx = new Texture(Gdx.files.internal("data/badlogic.jpg"));
	
	static final String nameOfMainCharacterNode= "MainCharacter";
		
	public RenderTree() {
		mainCharacterTx = new Texture(Gdx.files.internal("data/badlogic.jpg"));
		mStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				false);
		//Node mainCharacter = new Node(new Vector3( Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,1) , nameOfMainCharacterNode, mainCharacterTx, new Vector2(10,10)); 
		//children.add(mainCharacter);
		
		//Node n = new Node(0,0,mT);
		
		//main character
		
		//children.add(new Node(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 , nameOfMainCharacterNode, new Vector2(10,10) )) ;
		
		//children.add(new Node(300,10, mT2 , "other", new Vector2(0,0) ));
		//children.add(new Node(0,900, mT ));
		//children.add(new Node(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), mT));
		}
	
	public void addProjectile( Vector2 positionDoigtUnderFinger, Vector3 cameraPositionUnderFinger, Vector3 positionJoueur) {
		
		Vector2 beginningPosition = new Vector2(cameraPositionUnderFinger.x, cameraPositionUnderFinger.y);// mStage.findActor("mainChar").x, mStage.findActor("mainChar").y);
		Vector2 distanceAParcourir = new Vector2 (
				(  positionDoigtUnderFinger.x - beginningPosition.x ),
				( Gdx.graphics.getWidth()/2 + positionDoigtUnderFinger.y - beginningPosition.y)
				);
		
		//distanceAParcourir.nor();
		
		Projectile nouveauProjectile = new Projectile( beginningPosition, distanceAParcourir, positionJoueur); 
		children.add(nouveauProjectile);
		
		
		mStage.addActor(nouveauProjectile.getImage());
		
	}
		public void draw() {
			
			for (int i = 0; i < children.size(); i++) {
				children.get(i).update(mStage);
			}
			mStage.draw();
			//children.get(0).draw(0,0);
			//children.get(1).draw(0,0);
		}
		
//		public Node getMainCharacter() {
//			return mStage.findActor("mainChar");
//		}
		public Stage getStage() {
			return mStage;
		}
}
