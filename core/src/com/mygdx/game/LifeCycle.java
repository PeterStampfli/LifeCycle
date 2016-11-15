package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class LifeCycle extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	OrthographicCamera camera;

	float fontSizeFractionOfHeight;
	float basicFontSize;
	float lineHeight;
	List<String> logs;
	
	@Override
	public void create () {
		logs=new ArrayList<String>();
		logs.add("create");
		batch = new SpriteBatch();
		font=new BitmapFont();
		fontSizeFractionOfHeight=20;
		basicFontSize=18;
		font.getData().setScale(2.0f);
		camera=new OrthographicCamera();
	}

	@Override
	public void resize(int width,int height){
		logs.add("resize");
		camera.setToOrtho(false,width,height);
		lineHeight=height/fontSizeFractionOfHeight;
		font.getData().setScale(lineHeight/basicFontSize);
	}

	
	@Override
	public void render () {
		String message="render (multiple)";
		if (!(logs.get(logs.size()-1).equals(message))) logs.add(message);
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		float top=logs.size()*lineHeight;
		for (int i=0,n=logs.size();i<n;i++){
			font.draw(batch,logs.get(i),50,lineHeight*(n-i));
		}
		batch.end();
	}

	@Override
	public void pause(){
		logs.add("pause");
	}

	@Override
	public void resume(){
		logs.add("resume");
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
