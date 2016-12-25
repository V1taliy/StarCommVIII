package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    private static Texture texture;
    private Vector2 position;
    private float speed;
    private Rectangle rect;
    private float angle;
    private int hp;

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Asteroid() {
        position = new Vector2(1280 + (float) Math.random() * 1280, (float) Math.random() * 720);
        speed = 400.0f + (float) Math.random() * 8.0f;
        rect = new Rectangle(position.x, position.y, 60, 60);
        angle = (float) Math.random() * 360;
        hp = 10;
        if (texture == null) {
            texture = new Texture("asteroid60.tga");
        }
    }

    public void recreate() {
        position.x = 1280 + (float) Math.random() * 1280;
        position.y = (float) Math.random() * 720;
        speed = 400.0f + (float) Math.random() * 8.0f;
        angle = (float) Math.random() * 360;
        hp = 30;
    }

    public void getDamage(int dmg) {
        hp -= dmg;
        if (hp <= 0) {
            recreate();
        }
    }

    public void render(SpriteBatch batch) {
        //batch.draw(texture, position.x, position.y);
        batch.draw(texture, position.x, position.y, 30, 30, 60, 60, 1.0f, 1.0f, angle, 0, 0, 60, 60, false, false);
    }

    public void update(float dt) {
        position.x -= speed * dt;
        angle += speed * dt / 2;
        if (position.x < -60) {
            recreate();
        }
        rect.x = position.x;
        rect.y = position.y;
    }
}
