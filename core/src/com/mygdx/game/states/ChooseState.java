package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.FlappyBird;
import com.mygdx.game.sprites.Charac;

/**
 * Created by Dor Lugasi on 11/5/2017.
 */

public class ChooseState extends State {
    private Texture background,backBtn;
    private Charac bennyAnimation,sakaAnimation,blueAnimation,regularAnimation;
    private Rectangle bennyRec, sakaRec,blueRec,regularRec;



    public ChooseState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        background = new Texture("bg.png");
        backBtn = new Texture("backbtn.png");
        bennyAnimation=new Charac(50,300,"bennyanimation.png",true);
        sakaAnimation=new Charac(150,300,"sakaanimation.png",true);
        blueAnimation=new Charac(50,200,"birdanimationblue.png",true);
        regularAnimation=new Charac(150,200,"birdanimation.png",true);

    }

    @Override
    protected void handleInput() {

        if (Gdx.input.justTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(tmp);
            Rectangle backBtnBounds = new Rectangle(cam.position.x - backBtn.getWidth() / 2, cam.position.y - 150, backBtn.getWidth(), backBtn.getHeight());
            if (backBtnBounds.contains(tmp.x, tmp.y)) {
                gsm.set(new MenuState(gsm));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bennyAnimation.update(dt);
        sakaAnimation.update(dt);
        blueAnimation.update(dt);
        regularAnimation.update(dt);


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(backBtn, cam.position.x - backBtn.getWidth() / 2, cam.position.y - 150);
        sb.draw(bennyAnimation.getBird(), bennyAnimation.getPosition().x, bennyAnimation.getPosition().y, bennyAnimation.getBird().getRegionWidth() / 2, bennyAnimation.getBird().getRegionHeight() / 2, bennyAnimation.getBird().getRegionWidth(), bennyAnimation.getBird().getRegionHeight(), 1, 1, bennyAnimation.getRotation());

        sb.draw(sakaAnimation.getBird(), sakaAnimation.getPosition().x, sakaAnimation.getPosition().y, sakaAnimation.getBird().getRegionWidth() / 2, sakaAnimation.getBird().getRegionHeight() / 2, sakaAnimation.getBird().getRegionWidth(), sakaAnimation.getBird().getRegionHeight(), 1, 1, sakaAnimation.getRotation());
        sb.draw(blueAnimation.getBird(), blueAnimation.getPosition().x, blueAnimation.getPosition().y, blueAnimation.getBird().getRegionWidth() / 2, blueAnimation.getBird().getRegionHeight() / 2, blueAnimation.getBird().getRegionWidth(), blueAnimation.getBird().getRegionHeight(), 1, 1, blueAnimation.getRotation());
        sb.draw(regularAnimation.getBird(), regularAnimation.getPosition().x, regularAnimation.getPosition().y, regularAnimation.getBird().getRegionWidth() / 2, regularAnimation.getBird().getRegionHeight() / 2, regularAnimation.getBird().getRegionWidth(), regularAnimation.getBird().getRegionHeight(), 1, 1, regularAnimation.getRotation());

        //draw characters here
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bennyAnimation.dispose();
        sakaAnimation.dispose();
        blueAnimation.dispose();
        regularAnimation.dispose();
        System.out.println("Choose State Disposed");
    }


}
