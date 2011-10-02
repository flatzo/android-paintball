package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Monster extends Node {
	static int monsterId = 0;
	static final int MonsterSpeed = 20;
	
	private Texture textureMonstre = new Texture(Gdx.files.internal("data/monstreBlanc16.png"));
	
	Monster ( Stage stage) {
		super(new Vector3(0,0,0), 
				new String("monster").concat( new Integer(monsterId).toString()),
				new Texture(Gdx.files.internal("data/monstreBlanc16.png")) ,
				new Vector2(MonsterSpeed, MonsterSpeed),
				stage);
		
		monsterId++;
		
		
		float distanceHorizontale = stage.findActor("mainChar").x+Gdx.graphics.getWidth()/2;
		float distanceVerticale = stage.findActor("mainChar").y + Gdx.graphics.getHeight()/2;
		float perimetre = distanceHorizontale*2 + distanceVerticale *2;
		
		float positionDeDepartX = distanceHorizontale;
		float positionDeDepartY = distanceVerticale;
		int position = 0;
		float increment = (Math.random()<(double)0.5) ? (+perimetre/25) : (-perimetre/25);
		do {
			position = (int) Math.floor((double)(Math.random()*(double)20 ));
			
				
				//make a square || ___ || ----
				//vertical downwards
				switch (position) {
				case 0:
				case 1:
				case 2:
				case 3:
				case 4: //cote droit
				case 5: positionDeDepartX = distanceHorizontale; 
						positionDeDepartY = (distanceVerticale-position*increment);
					break;
				case 6:
				case 7:
				case 8: //cote bas
				case 9: positionDeDepartX = distanceHorizontale - (position-6)*increment; 
						positionDeDepartY = distanceVerticale-Gdx.graphics.getHeight();
					break;
				case 10:
				case 11:
				case 12:
				case 13:
				case 14: //cote gauche
				case 15: positionDeDepartX = distanceHorizontale - Gdx.graphics.getWidth();
						positionDeDepartY = distanceVerticale - (position-10)*Gdx.graphics.getHeight();
					break;
				case 16:
				case 17:
				case 18: //cote haut
				case 19: positionDeDepartX = distanceHorizontale - (position-16)*Gdx.graphics.getWidth();
						positionDeDepartY = distanceVerticale;
				default: positionDeDepartX = distanceHorizontale;
						positionDeDepartY = distanceVerticale;
				}
				
			}while(false/*collision()*/);
		
		super.setPosition(positionDeDepartX,positionDeDepartY, stage);
	}
	
	@Override
	public boolean update (Stage stage) {
		//if (collision(RenderTree.list))
		return false;
	}
}
