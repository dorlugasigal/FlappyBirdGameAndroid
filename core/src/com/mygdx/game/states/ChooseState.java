package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyBird;

import javax.lang.model.type.ArrayType;

/**
 * Created by Dor Lugasi on 11/5/2017.
 */

public class ChooseState extends State {
    private Texture background;
    private Array<Character> characters;

    public ChooseState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        background = new Texture("bg.png");
    }
    @Override
    protected void handleInput() {
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
        //draw characters here
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        // dispose characters here
        System.out.println("Choose State Disposed");
    }


}
