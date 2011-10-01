package com.game;
import com.game.RenderTree;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.OrthographicCamera.*;
import com.badlogic.gdx.math.Vector3;

public class CharacterCamera extends OrthographicCamera {
	
	public CharacterCamera(Node character) {
		super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		focusOn(character);
	}
	
	public void focusOn(Node character) {
		this.position.set( new Vector3(character.getRelativePosition().x, character.getRelativePosition().y, 10) );
		this.update();
	}

}
