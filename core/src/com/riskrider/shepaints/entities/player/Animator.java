package com.riskrider.shepaints.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator {

    public static int DOWN_LEFT = 0;
    public static int DOWN_RIGHT = 1;
    public static int UP_LEFT = 2;
    public static int UP_RIGHT = 3;
    public static int STAND_DOWN_LEFT = 4;
    public static int STAND_DOWN_RIGHT = 5;
    public static int STAND_UP_LEFT = 6;
    public static int STAND_UP_RIGHT = 7;

    private int state;
    private String colour;


    private final Animation[] walk;
    private final Texture idleDownLeft;
    private final Texture idleDownRight;
    private final Texture idleUpLeft;
    private final Texture idleUpRight;

    private float stateTime;

    public Animator(int state) {

        walk = new Animation[4];
        resetStateTime();
        setState(state);
        makeBW();

        TextureRegion[][] walkingSpriteSheet = TextureRegion.split(new Texture(Gdx.files.internal("Player/" + colour + "AnimationMap.png")),
                Player.PLAYER_PIC_WIDTH, Player.PLAYER_PIC_HEIGHT);
        float animationSpeed = 0.15f;
        walk[DOWN_LEFT] = new Animation<>(animationSpeed, walkingSpriteSheet[DOWN_LEFT]);
        walk[DOWN_RIGHT] = new Animation<>(animationSpeed, walkingSpriteSheet[DOWN_RIGHT]);
        walk[UP_LEFT] = new Animation<>(animationSpeed, walkingSpriteSheet[UP_LEFT]);
        walk[UP_RIGHT] = new Animation<>(animationSpeed, walkingSpriteSheet[UP_RIGHT]);

        idleDownLeft = new Texture(Gdx.files.internal("Player/" + colour + "StandDownLeft.png"));
        idleDownRight = new Texture(Gdx.files.internal("Player/" + colour + "StandDownRight.png"));
        idleUpLeft = new Texture(Gdx.files.internal("Player/" + colour + "StandUpLeft.png"));
        idleUpRight = new Texture(Gdx.files.internal("Player/" + colour + "StandUpRight.png"));

    }

    public void render(SpriteBatch batch, float x, float y) {

        if (state <= UP_RIGHT) {
            batch.draw((TextureRegion) walk[state].getKeyFrame(stateTime, true), x, y, Player.WIDTH, Player.HEIGHT);
        }
        else  if (state == STAND_DOWN_LEFT) {
            batch.draw(idleDownLeft, x, y, Player.WIDTH, Player.HEIGHT);
        }
        else  if (state == STAND_DOWN_RIGHT) {
            batch.draw(idleDownRight, x, y, Player.WIDTH, Player.HEIGHT);
        }
        else  if (state == STAND_UP_LEFT) {
            batch.draw(idleUpLeft, x, y, Player.WIDTH, Player.HEIGHT);
        }
        else  if (state == STAND_UP_RIGHT) {
            batch.draw(idleUpRight, x, y, Player.WIDTH, Player.HEIGHT);
        }

    }

    public void increaseStateTime(float delta) { stateTime += delta;}

    public void resetStateTime() {stateTime = 0;}

    public void setState(int state) {this.state = state;}

    public void makeBW() {colour = "BW";}

    public void makeColoured() {colour = "colour";}

}
