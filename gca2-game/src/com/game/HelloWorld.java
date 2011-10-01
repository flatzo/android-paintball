/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.game;

import java.util.ArrayList;
import java.util.List;

import android.content.pm.ActivityInfo;

import com.game.*;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Orientation;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.graphics.g2d.tiled.TiledObject;
import com.badlogic.gdx.graphics.g2d.tiled.TiledObjectGroup;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.badlogic.gdx.scenes.scene2d.actors.Label;
import com.badlogic.gdx.math.collision.Sphere;
import com.badlogic.gdx.math.collision.*;


public class HelloWorld implements ApplicationListener, InputProcessor {
	
	Texture texture;
	BitmapFont font;
	Vector2 textPosition = new Vector2(100, 100);
	Vector2 textDirection = new Vector2(1, 1);
	RenderTree mRenderTree;
	
	List<Image> images = new ArrayList<Image>();
    
    //Deplacement du mainChar
	float mcharSpeed = 2.5f;
    int mInputX;
	int mInputYinverse;
	int mInputY;
	Vector3 directionPerso = new Vector3(0,0,0);
	boolean isOnThePlayer = false;

	// TileMap :)
	TileMapRenderer mTileMapRenderer;
	TiledMap mMap;
	TileAtlas mAtlas;

	// Audio
	Music music;
	Sound sound;

	// Camera
	OrthographicCamera mCamera;

	// Constants
	static final int WIDTH = 480;
	static final int HEIGHT = 320;

	Vector3 tmp = new Vector3();
	Vector3 spritePosition = new Vector3();

	// for pinch-to-zoom
	int mNumberOfFingers = 0;
	int mFingerOnePointer;
	int mFingerTwoPointer;
	float mLastDistance = 0;
	Vector3 mFingerOne = new Vector3();
	Vector3 mFingerTwo = new Vector3();

	// Stage - Actors

	@Override
	public void create() {

		// Stage pour scene2D

		Texture mT = new Texture(Gdx.files.internal("data/ball.png"));
		Texture mT2 = new Texture(Gdx.files.internal("data/badlogic.jpg"));
		Texture textureProjectile = new Texture(Gdx.files.internal("data/projectile.png"));
		
		
		Image mainChar = new Image("mainChar", mT);
		Image backGround = new Image("background", mT2);
		Image projectile = new Image("projectile1", textureProjectile);
		
		mainChar.x = Gdx.graphics.getWidth() / 2;
		mainChar.y = Gdx.graphics.getHeight() / 2;

		mRenderTree = new RenderTree();
		mRenderTree.getStage().addActor(mainChar);
		Gdx.input.setInputProcessor(this);
		font = new BitmapFont();
		font.setColor(Color.RED);



		// Define the audio source
		music = Gdx.audio.newMusic(Gdx.files.internal("data/music.mp3"));
		sound = Gdx.audio.newSound(Gdx.files.internal("data/sound.ogg"));
		music.setLooping(true);
		music.setVolume(0.0f);
		music.play();

		// Define the orthographic cam
		 mCamera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		this.create_tiledMap();

		this.mCamera.position.set(new Vector3(this.mTileMapRenderer
				.getMapWidthUnits() / 2, this.mTileMapRenderer
				.getMapHeightUnits() / 2, 0));
		this.mCamera.update();

	}

	@Override
	public void render() {
		
		GL10 gl = Gdx.graphics.getGL10();

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		this.mCamera.apply(gl);
		this.mTileMapRenderer.render((OrthographicCamera) mRenderTree.getStage().getCamera());
		
		if(Gdx.input.isTouched()){
			
			Vector3 position = new Vector3(mInputX,mInputY,0);
			Camera camera = mRenderTree.getStage().getCamera();
			float deplacementX = (float)(directionPerso.x * mcharSpeed);
			float deplacementY = (float)(directionPerso.y * mcharSpeed);
				
			mRenderTree.getStage().findActor("mainChar").x += deplacementX;
			camera.translate(deplacementX,0f,0f);
				
			mRenderTree.getStage().findActor("mainChar").y += deplacementY;
			camera.translate(0f,deplacementY,0f);
			
			camera.update();
			camera.apply(gl);
		}
		
		
		//stage.draw();
		mRenderTree.draw();
		
		

		
	}

	@Override
	public void resize(int width, int height) {

	//	spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		//textPosition.set(0, 0);

	}

	@Override
	public void pause() {

		// Remove number of fingers
		mNumberOfFingers = 0;

		// Close the music and sound
		music.dispose();
		sound.dispose();

	}

	@Override
	public void resume() {
		create();

		// Play music and sound
		music.play();
		sound.play();

	}

	@Override
	public void dispose() {

		// Close the music and sound
		music.dispose();
		sound.dispose();

	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		Vector2 touchPosition = new Vector2(x, y);

		
	
		//Increment the number of fingers
		mNumberOfFingers++;
		
		//underFinger
		mRenderTree.addProjectile(
				
				new Vector2(touchPosition.x,
						(Gdx.graphics.getHeight()/2 - touchPosition.y) - Gdx.graphics.getHeight()/2),
				
				new Vector3(
				mRenderTree.getStage().findActor("mainChar").x,
				mRenderTree.getStage().findActor("mainChar").y,
				0)
				,
				new Vector3( mRenderTree.getStage().findActor("mainChar").x, mRenderTree.getStage().findActor("mainChar").y, 1)
				//underFinger
				
				//mRenderTree.addProjectile(positionDoigtUnderFinger, cameraPositionUnderFinger)
		);
		
		//Verify the number of finger and the pointer 
		if(mNumberOfFingers == 1 && pointer == 0 && isOnThePlayer(x,y))
		{
		       mFingerOnePointer = pointer;
		       mFingerOne.set(x, y, 0);
		       
		       
		       //directionPerso.nor();
		       

		       
		}
		else if(mNumberOfFingers == 2 && pointer == 1 && isOnThePlayer)
		{
            sound.play();
            //Vector3 worldCoordonate = new Vector3(touchPosition.x, touchPosition.y, 0);
           // mCamera.unproject(worldCoordonate);
			//mRenderTree.addProjectile(new Vector2(worldCoordonate.x, worldCoordonate.y) , new Vector3(
			
		}

		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {

		if(pointer != 1 && isOnThePlayer){
	       mInputX = x;
	       mInputYinverse = y;
	       mInputY = Gdx.graphics.getHeight() - mInputYinverse;
					       
	       directionPerso = new Vector3(mInputX-(Gdx.graphics.getWidth()/2),
	    		   						mInputY-(Gdx.graphics.getHeight()/2),0);
	      directionPerso.nor();
		}

		return false;
	}

	@Override
	public boolean touchMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {

		// For pinch-to-zoom
		if (mNumberOfFingers == 1) {
			mInputX = 0;
			mInputY = 0;
			mInputYinverse = 0;
			directionPerso = new Vector3(0,0,0);
		}
		mNumberOfFingers--;
		
		if(mNumberOfFingers == 0)
			isOnThePlayer = false;
		// Remove number of fingers on the screen... clamping number of fingers
		// (ouch! :-)
		if (mNumberOfFingers < 0) {
			mNumberOfFingers = 0;
		}

		return false;
	}

	/*
	 * Private create
	 */

	private void create_tiledMap() {
		final String path = "data/tiledmap/";
		final String mapname = "foret";

		FileHandle mapHandle = Gdx.files.internal(path + mapname + ".tmx");
		FileHandle baseDir = Gdx.files.internal(path);

		this.mMap = TiledLoader.createMap(mapHandle);

		this.mAtlas = new TileAtlas(this.mMap, baseDir);

		int blockWidth = 10;
		int blockHeight = 12;

		mTileMapRenderer = new TileMapRenderer(this.mMap, this.mAtlas,
				blockWidth, blockHeight, 50, 50);

		for (TiledObjectGroup group : this.mMap.objectGroups) {
			for (TiledObject object : group.objects) {
				// TODO: Draw sprites where objects occur
				System.out.println("Object " + object.name + " x,y = "
						+ object.x + "," + object.y + " width,height = "
						+ object.width + "," + object.height);
			}
		}

		// float aspectRatio = (float)Gdx.graphics.getWidth() /
		// (float)Gdx.graphics.getHeight();
		// mCam = new OrthographicCamera(100f * aspectRatio, 100f);

		// mCam.position.set(mTileMapRenderer.getMapWidthUnits() / 2,
		// mTileMapRenderer.getMapHeightUnits() / 2, 0);

		// camController = new OrthoCamController(cam);
		// Gdx.input.setInputProcessor(camController);

		// mMaxCamPosition.set(mTileMapRenderer.getMapWidthUnits(),
		// mTileMapRenderer.getMapHeightUnits());
	}
	
	/*
	 * Verify if the the finger is on the player
	 *
	 */
	private boolean isOnThePlayer(int x, int y){
		
		float fingerX = x, fingerY = Gdx.graphics.getHeight() - y;
		float playerX = mRenderTree.getStage().findActor("mainChar").x, playerY = mRenderTree.getStage().findActor("mainChar").y;
		
		Sphere sphereFinger = new Sphere(new Vector3(fingerX,fingerY,0),10);
		Sphere spherePlayer = new Sphere(new Vector3(playerX,playerY,0),10);
		if(sphereFinger.overlaps(spherePlayer)){
			isOnThePlayer = true;
			return true;
		}
		else{
			isOnThePlayer = true;
			return true;
		}
			
			
	}

}
