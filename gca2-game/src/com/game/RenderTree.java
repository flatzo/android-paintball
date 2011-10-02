package com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class RenderTree {
	ArrayList<Node> children = new ArrayList<Node> ();
	static Stage mStage;
	
	Texture mainCharacterTx = null;
	
	static final String nameOfMainCharacterNode= "MainChar";
	
	private final int maximumNumberOfMonster = 30;
	private int numberOfMonster = 0;
		
	public RenderTree() {
		mainCharacterTx = new Texture(Gdx.files.internal("data/badlogic.jpg"));
		mStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				false);
		numberOfMonster = 0;
		}
	
	public void addProjectile( Vector2 direction ) {		
		Projectile nouveauProjectile = new Projectile(direction, mStage); 
		children.add(nouveauProjectile);
		
	}
		public void draw() {
			
			this.instantiateMonsters();

			this.drawChildren();
			
			Actor mainChar = null;
			if(mStage.findActor("mainChar")!=null) {
				mainChar = mStage.findActor("mainChar");
				mStage.removeActor(mainChar);
			}
			
			mStage.draw();
			
			if(mainChar!=null) {
				mStage.addActor(mainChar);
			}
			
		}
		
		public Stage getStage() {
			return mStage;
		}
		
		public static void removeSplash() {
			mStage.removeActor(mStage.findActor(BallSplash.getInstance().name));
		}
		
		private void drawChildren() {
			Iterator<Node> iter = children.iterator();
			Node child = null;
			
			while (iter.hasNext()) {
				child = iter.next();
				
				if (child.update(mStage)) {
					iter.remove();
					mStage.removeActor(mStage.findActor(child.getName()));
				}
			}
		}
		
		private void instantiateMonsters() {
			if(numberOfMonster < maximumNumberOfMonster) {
				if(Math.random() < 0.1f) {
					Monster monster = new Monster(RenderTree.mStage);
					this.children.add(monster);
					RenderTree.mStage.addActor(monster.getImage());
					this.numberOfMonster++;
				}
			}
		}
}
