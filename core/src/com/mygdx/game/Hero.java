package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private Texture texture;
    private Vector2 position;
    private float speed;
    private int fireRate;
    private int fireCounter;

    public Hero() {
        texture = new Texture("ship80x60.tga");
        position = new Vector2(100, 100);
        speed = 400.0f;
        fireRate = 5;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }


    public void update(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.U)) {
            fireCounter++;
            if(fireCounter > fireRate) {
                fireCounter = 0;
                fire(position.x + 40, position.y + 10);
                fire(position.x + 60, position.y);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += speed * dt;


            if (position.y > 720) {
                position.y = -60;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= speed * dt;
            if (position.y < -60) {
                position.y = 720;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= speed * dt;
            if (position.x < 0) {
                position.x = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += speed * dt;
            if (position.x > 1200) {
                position.x = 1200;
            }
        }
    }

    public void fire(float x, float y) {
        for (int i = 0; i < MyGdxGame.bullets.length; i++) {
            if(!MyGdxGame.bullets[i].isActive()) {
                MyGdxGame.bullets[i].setup(x, y);
                break;
            }
        }
    }
}
