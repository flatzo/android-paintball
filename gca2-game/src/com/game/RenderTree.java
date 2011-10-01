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

public class RenderTree {
	Vector<Node> children = new Vector<Node> ();
	Texture mT = new Texture(Gdx.files.internal("data/badlogic.jpg"));
	  
	Texture mT2 = new Texture(Gdx.files.internal("data/badlogic2.jpg"));
	
	BitmapFont mFont = new BitmapFont();
	int mDebugging = 0;
	SpriteBatch mSb;
	
	static final String nameOfMainCharacterNode= "MainCharacter";
		
	public RenderTree() {
		mT = new Texture(Gdx.files.internal("data/badlogic.jpg"));
		mT2 = new Texture(Gdx.files.internal("data/badlogic2.jpg"));
		mFont = new BitmapFont();
		mFont.setColor(Color.WHITE);
		mDebugging++;
		mSb = new SpriteBatch();
		
		mSb.begin();
		mFont.draw(mSb, "ddd", mT.getWidth()/2, mT.getHeight()/2);
		mSb.end();
		
		//Node n = new Node(0,0,mT);
		
		//main character
		
		children.add(new Node(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, mT , nameOfMainCharacterNode, new Vector2(10,10) )) ;
		
		children.add(new Node(100,0, mT , "other", new Vector2(0,0) ));
		//children.add(new Node(0,900, mT ));
		//children.add(new Node(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), mT));
		}
		public void draw() {
			mSb.begin();
			mSb.setColor(Color.WHITE);
			mSb.end();
			//for (int i = 0; i < children.size(); i++) {
			//	children.get(i).draw(0,0);
			//}
			children.get(0).draw(0,0);
			children.get(1).draw(0,0);
		}
		
		public Node getMainCharacter() {
			for ( int i = 0; i < children.size(); i++) {
				if (children.get(i).getName() == nameOfMainCharacterNode) {
					return children.get(i);
				}
			}
			//should never happen, because mainCharacter should remain in the renderTree
			Node mainCharacter = new Node(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, mT , nameOfMainCharacterNode, new Vector2(10,10)); 
			children.add(mainCharacter);
			return mainCharacter;
		}
}
