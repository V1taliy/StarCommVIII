package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	private Background background;
	private Hero hero;
	private final int ASTEROIDS_COUNT = 30;
	private Asteroid[] asteroids;
	private final int BULLETS_COUNT = 100;
	public static Bullet[] bullets;
	private Texture textureBullet;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		hero = new Hero();
		asteroids = new Asteroid[ASTEROIDS_COUNT];
		for (int i = 0; i < ASTEROIDS_COUNT; i++) {
			asteroids[i] = new Asteroid();
		}
		bullets = new Bullet[BULLETS_COUNT];
		for (int i = 0; i < BULLETS_COUNT; i++) {
			bullets[i] = new Bullet();
		}
		textureBullet = new Texture("bullet20.png");
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(1, 1, 1, 1); // RGBA
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		hero.render(batch);
		for (int i = 0; i < ASTEROIDS_COUNT; i++) {
			asteroids[i].render(batch);
		}
		for (int i = 0; i < BULLETS_COUNT; i++) {
			if(bullets[i].isActive())
				batch.draw(textureBullet, bullets[i].getPosition().x, bullets[i].getPosition().y);
		}
		batch.end();
	}

	public void update(float dt) {
		background.update();
		hero.update(dt);
		for (int i = 0; i < ASTEROIDS_COUNT; i++) {
			asteroids[i].update(dt);
		}
		for (int i = 0; i < BULLETS_COUNT; i++) {
			if(bullets[i].isActive()) {
				bullets[i].update();
				for (int j = 0; j < ASTEROIDS_COUNT; j++) {
					if(asteroids[j].getRect().contains(bullets[i].getPosition())) {
						asteroids[j].getDamage(1);
						bullets[i].destroy();
						break;
					}
				}
			}
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
