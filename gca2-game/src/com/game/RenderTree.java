package com.game;

import java.util.List;
import java.util.Vector;
import java.util.List.*;
import com.game.utils.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderTree {
	Vector<Node> children = new Vector<Node> ();
	Texture mT = new Texture(Gdx.files.internal("data/badlogic.jpg"));;
	BitmapFont mFont = new BitmapFont();
	int mDebugging = 0;
	SpriteBatch mSb;
		
	public RenderTree() {
		mT = new Texture(Gdx.files.internal("data/badlogic.jpg"));
		mFont = new BitmapFont();
		mFont.setColor(Color.WHITE);
		mDebugging++;
		mSb = new SpriteBatch();
		
		mSb.begin();
		mFont.draw(mSb, "ddd", mT.getWidth()/2, mT.getHeight()/2);
		mSb.end();
		
		//Node n = new Node(0,0,mT);
		
		children.add(new Node(0, 0, mT ));
		children.add(new Node(100,0, mT ));
		children.add(new Node(0,100, mT ));
		//children.add(new Node(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), mT));
		}
		public void draw() {
			for (int i = 0; i < children.size(); i++) {
				children.get(i).draw(0,0);
			}
		}
}
