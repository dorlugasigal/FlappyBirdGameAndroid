package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyBird;
import com.mygdx.game.sprites.Character;
import com.mygdx.game.sprites.Tube;

/**
 * Created by Dor Lugasi on 11/4/2017.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    private Character bird;
    private Texture bg;
    private Texture ground;
    private Array<Tube> tubes;
    private Vector2 groundPos1, groundPos2;
    private Texture gameoverImg;
    private boolean gameover;
    private int score;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        bird = new Character(50, 300);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        tubes = new Array<Tube>();
        gameoverImg = new Texture("gameover.png");
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube((i * ((TUBE_SPACING) + Tube.TUBE_WIDTH))));
        }
        score = 0;
        gameover = false;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            if (gameover) {
                gsm.set(new MenuState(gsm));
            } else
                bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        if (!gameover) {
            updateGround();
            cam.position.x = bird.getPosition().x + 80;
            for (int i = 0; i < tubes.size; i++) {
                Tube tube = tubes.get(i);
                if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                    tube.reposition(tube.getPosTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
                }
                if (bird.getPosition().x>tube.getPosTopTube().x+5&&tube.wentThrough== false) {
                    score++;
                    tube.wentThrough=true;
                    System.out.println("score:"+score);
                }
                if (tube.collides(bird.getBounds())) {
                    gameover = true;
                    //gsm.set(new PlayState(gsm));
                }
                //System.out.println((int)bird.getPosition().x);

            }
            if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
                //gsm.set(new PlayState(gsm));
                gameover = true;
            }
            cam.update();
        }


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y, bird.getBird().getRegionWidth() / 2, bird.getBird().getRegionHeight() / 2, bird.getBird().getRegionWidth(), bird.getBird().getRegionHeight(), 1, 1, bird.getRotation());
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        if (gameover)
            sb.draw(gameoverImg, cam.position.x - gameoverImg.getWidth() / 2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("Play State Disposed");
    }

    private void updateGround() {
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
}
