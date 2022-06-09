package com.riskrider.shepaints.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.riskrider.shepaints.ShePaints;
import java.math.*;

public class Player {

    public static final int PLAYER_PIC_HEIGHT = 32;
    public static final int PLAYER_PIC_WIDTH = 16;
    public static final int HEIGHT = 4 * PLAYER_PIC_HEIGHT;
    public static final int WIDTH = 4 * PLAYER_PIC_WIDTH;

    private final float moveSpeed = 150;

    private float x;
    private float y;
    private float xMiddle;
    private int direction;
    private boolean walking;

    private final ShePaints game;

    public Animator Animations;

    public Player(float x, float y, int direction, ShePaints game) {

        this.game = game;

        Animations = new Animator(getState());
        this.x = x;
        this.y = y;
        this.xMiddle = x + WIDTH / 2f;
        this.direction = direction;
        walking = false;

    }

    public void getMovementInput(float delta) {

        if (ShePaints.IS_MOBILE) {
            if ((Gdx.input.isTouched()) && ((game.camera.getInput().x < xMiddle - 2 || game.camera.getInput().x > xMiddle + 2)
                    || (game.camera.getInput().y < y - 2 || game.camera.getInput().y > y + 2))) {
                walking = true;
                float yDisplacement = game.camera.getInput().y - y;
                float xDisplacement = game.camera.getInput().x - xMiddle;
                double angle =  Math.atan2(yDisplacement, xDisplacement);
                double yMoveSpeed = moveSpeed * Math.abs(Math.sin(angle));
                double xMoveSpeed = moveSpeed * Math.abs(Math.cos(angle));
                if (game.camera.getInput().y > y + 2) {
                    y += yMoveSpeed * delta;
                    if (direction == Animator.DOWN_RIGHT) direction = Animator.UP_RIGHT;
                    else if (direction == Animator.DOWN_LEFT) direction = Animator.UP_LEFT;
                }
                if (game.camera.getInput().y < y - 2) {
                    y -= yMoveSpeed * delta;
                    if (direction == Animator.UP_RIGHT) direction = Animator.DOWN_RIGHT;
                    else if (direction == Animator.UP_LEFT) direction = Animator.DOWN_LEFT;
                }
                if (game.camera.getInput().x < xMiddle - 2) {
                    x -= xMoveSpeed * delta;
                    if (direction == Animator.DOWN_RIGHT) direction = Animator.DOWN_LEFT;
                    else if (direction == Animator.UP_RIGHT) direction = Animator.UP_LEFT;
                }
                if (game.camera.getInput().x > xMiddle + 2) {
                    x += xMoveSpeed * delta;
                    if (direction == Animator.DOWN_LEFT) direction = Animator.DOWN_RIGHT;
                    else if (direction == Animator.UP_LEFT) direction = Animator.UP_RIGHT;
                }
            }
            else {
                walking = false;
            }
        }
        else {
            if ((Gdx.input.isKeyPressed(Input.Keys.W) ^ Gdx.input.isKeyPressed(Input.Keys.S)) ||
                    (Gdx.input.isKeyPressed(Input.Keys.A) ^ Gdx.input.isKeyPressed(Input.Keys.D))) {
                walking = true;
                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    y += moveSpeed * delta;
                    if (direction == Animator.DOWN_RIGHT) direction = Animator.UP_RIGHT;
                    else if (direction == Animator.DOWN_LEFT) direction = Animator.UP_LEFT;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                    y -= moveSpeed * delta;
                    if (direction == Animator.UP_RIGHT) direction = Animator.DOWN_RIGHT;
                    else if (direction == Animator.UP_LEFT) direction = Animator.DOWN_LEFT;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    x -= moveSpeed * delta;
                    if (direction == Animator.DOWN_RIGHT) direction = Animator.DOWN_LEFT;
                    else if (direction == Animator.UP_RIGHT) direction = Animator.UP_LEFT;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    x += moveSpeed * delta;
                    if (direction == Animator.DOWN_LEFT) direction = Animator.DOWN_RIGHT;
                    else if (direction == Animator.UP_LEFT) direction = Animator.UP_RIGHT;
                }
            }
            else {
                walking = false;
            }
        }
    }

    public void update(float delta) {
        xMiddle = x + WIDTH / 2f;
        Animations.setState(getState());
        if (walking) {
            Animations.increaseStateTime(delta);
        }
        else {
            Animations.resetStateTime();
        }
    }

    public void render(SpriteBatch batch) {
        Animations.render(batch, x, y);
    }

    public int getState() {
        int state = 0;
        if (walking) {
            if (direction == Animator.DOWN_LEFT) state = Animator.DOWN_LEFT;
            else if (direction == Animator.DOWN_RIGHT) state = Animator.DOWN_RIGHT;
            else if (direction == Animator.UP_LEFT) state = Animator.UP_LEFT;
            else if (direction == Animator.UP_RIGHT) state = Animator.UP_RIGHT;
        }
        else {
            if (direction == Animator.DOWN_LEFT) state = Animator.STAND_DOWN_LEFT;
            else if (direction == Animator.DOWN_RIGHT) state = Animator.STAND_DOWN_RIGHT;
            else if (direction == Animator.UP_LEFT) state = Animator.STAND_UP_LEFT;
            else if (direction == Animator.UP_RIGHT) state = Animator.STAND_UP_RIGHT;
        }
        return state;
    }

}
