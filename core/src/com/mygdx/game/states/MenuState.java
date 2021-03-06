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
    private Texture chooseBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        chooseBtn = new Texture("choosebtn.png");
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
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(tmp);
            Rectangle playBtnBounds = new Rectangle(cam.position.x - playBtn.getWidth() / 2, cam.position.y, playBtn.getWidth(), playBtn.getHeight());
            Rectangle chooseBtnBounds = new Rectangle(cam.position.x - chooseBtn.getWidth() / 2,  cam.position.y-150, chooseBtn.getWidth(), chooseBtn.getHeight());

            if (playBtnBounds.contains(tmp.x, tmp.y)) {
                gsm.set(new PlayState(gsm));
            } else if (chooseBtnBounds.contains(tmp.x, tmp.y)) {
                gsm.set(new ChooseState(gsm));
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
        sb.draw(chooseBtn, cam.position.x - chooseBtn.getWidth() / 2, cam.position.y - 150);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        chooseBtn.dispose();
        System.out.println("Menu State Disposed");

    }
}
