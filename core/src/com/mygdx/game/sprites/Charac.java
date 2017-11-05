package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Vector;

import javax.xml.soap.Text;

/**
 * Created by Dor Lugasi on 11/4/2017.
 */

public class Charac {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 110;
    private Vector3 position, velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;
    private boolean statue;


    public Charac(int x, int y, String path, boolean statue) {
        this.statue = statue;
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture(path);
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    public void update(float dt) {
        birdAnimation.update(dt);
        if (!statue) {
            if (position.y > 0)
                velocity.add(0, GRAVITY, 0);
            velocity.scl(dt);
            position.add(MOVEMENT * dt, velocity.y, 0);
            if (position.y < 0)
                position.y = 0;
            if (dt > 0)
                velocity.scl(1 / dt);
            bounds.setPosition(position.x, position.y);
        }

    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void jump() {
        velocity.y = 250;
        flap.play(0.5f);
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }

    public float getRotation() {
        if (velocity.y >= 45)
            return 20F;
        if (velocity.y < -25)
            return -10F;
        return velocity.y;
    }

}
