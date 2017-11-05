package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.FlappyBird;

/**
 * Created by Dor Lugasi on 11/4/2017.
 */

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
//        playBtnBounds= new Rectangle(cam.position.x-playBtn.getWidth()/2,cam.position.y, playBtn.getWidth(), playBtn.getHeight());
//        Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//        cam.unproject(tmp);
//        if (playBtnBounds.contains(tmp.x, tmp.y)) {
//            System.out.println("Is touched");
//        }

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            cam.unproject(tmp);
            Rectangle textureBounds = new Rectangle(cam.position.x - playBtn.getWidth() / 2, cam.position.y, playBtn.getWidth(), playBtn.getHeight());
            if (textureBounds.contains(tmp.x, tmp.y)) {
                gsm.set(new PlayState(gsm));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");

    }
}
