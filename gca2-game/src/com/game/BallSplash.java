package com.game;

import java.util.ArrayList;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.game.BallSplash.Side;

public class BallSplash extends Image {

	private static BallSplash mInstance = null;

	public enum Side {
		LEFT, RIGHT, UNDEFINED
	};

	Animation mExplosion;
	Animation mSwipeFromLeft;
	Animation mSwipeFromRight;
	private float mElapsedTime;
	private Side mSide;
	private TextureRegion mTransparent;
	private boolean mDestroy;
	private Vector2 mPlayerPosition = new Vector2(0.0f,0.0f);

	
	public static BallSplash getInstance() {
		if (mInstance == null) {
			mInstance = new BallSplash();
		}
		return mInstance;
	}

	private BallSplash() {
		super("ballsplash");

		FileHandle imageFileHandle = Gdx.files
				.internal("data/ballsplash/transparent.png");

		this.mTransparent = new TextureRegion(new Texture(imageFileHandle));

		this.height = HelloWorld.HEIGHT;
		this.width = HelloWorld.WIDTH;

		ArrayList<TextureRegion> swipeleft = new ArrayList<TextureRegion>();
		ArrayList<TextureRegion> swiperight = new ArrayList<TextureRegion>();
		ArrayList<TextureRegion> splash = new ArrayList<TextureRegion>();

		for (int i = 1; i < 6; i++) {

			imageFileHandle = Gdx.files.internal("data/ballsplash/swipeleft"
					+ i + ".png");
			swipeleft.add(new TextureRegion(new Texture(imageFileHandle)));

			imageFileHandle = Gdx.files.internal("data/ballsplash/swiperight"
					+ i + ".png");
			swiperight.add(new TextureRegion(new Texture(imageFileHandle)));
		}
		for (int i = 1; i < 4; i++) {
			imageFileHandle = Gdx.files.internal("data/ballsplash/splash" + i
					+ ".png");
			splash.add(new TextureRegion(new Texture(imageFileHandle)));
		}

		this.mExplosion = new Animation(0.5f, splash);
		this.mSwipeFromLeft = new Animation(0.2f, swipeleft);
		this.mSwipeFromRight = new Animation(0.2f, swiperight);
	}

	public void splashIt(Vector2 playerPosition) {
		this.mPlayerPosition = playerPosition;
		this.mElapsedTime = 0;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		if (this.mElapsedTime <= 1.5f) {
			this.region = this.mExplosion.getKeyFrame(this.mElapsedTime, false);
		} else if (this.mSide == Side.RIGHT) {
			this.region = this.mSwipeFromRight.getKeyFrame(
					this.mElapsedTime - 1.5f, false);
		} else if (this.mSide == Side.LEFT) {
			this.region = this.mSwipeFromLeft.getKeyFrame(
					this.mElapsedTime - 1.5f, false);
		}

		if (this.mElapsedTime <= 2.5f && this.region != null) {
			batch.draw(this.region, this.mPlayerPosition.x,
					this.mPlayerPosition.y);
		} else {
			RenderTree.removeSplash();
		}

		mElapsedTime += Gdx.graphics.getDeltaTime();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchDragged(float x, float y, int pointer) {
		if (x <= HelloWorld.WIDTH / 2) {
			this.mSide = Side.LEFT;
		} else {
			this.mSide = Side.RIGHT;
		}
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}

}
