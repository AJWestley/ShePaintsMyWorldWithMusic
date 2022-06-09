package com.riskrider.shepaints.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.riskrider.shepaints.ShePaints;

public class MessageDisplay {

    public static int HIDDEN = 0;
    public static int TYPING = 1;
    public static int SHOWING = 2;

    // Font
    private BitmapFont textFont;

    // Black Background
    private final Texture bgTexture;
    private final Texture bgOutline;
    private final int bgWidthFinal = 2 * ShePaints.WIDTH / 3 + 40;
    private int bgHeightFinal = 10;

    // Message
    private String message;
    private GlyphLayout messageCurrent;
    private int letterOn;

    // Showing State
    private int showState;
    private float timer;

    // Co-ordinates
    private final int textBottom = ShePaints.HEIGHT / 10;
    private final int xText = ShePaints.WIDTH / 6;
    private int yText = textBottom;
    private int xBG = xText - 20;
    private int yBG = yText - 10;
    private final int xOutlineLeft = xBG - 2;
    private final int xOutlineRight = xBG + bgWidthFinal;
    private int yOutlineBottom = yBG - 2;
    private int yOutlineTop = yBG;

    public MessageDisplay() {

        bgTexture = new Texture(Gdx.files.internal("MessageAssets/bgTexture.png"));
        bgOutline = new Texture(Gdx.files.internal("MessageAssets/bgOutline.png"));
        showState = HIDDEN;

    }

    public void setMessage(String text, FileHandle path) {

        textFont = new BitmapFont(path);
        textFont.setColor(Color.WHITE);
        message = text;
        showState = TYPING;
        letterOn = 0;
        timer = 0;
    }

    public void updateMessage(float delta) {
        if (showState == TYPING) {

            timer += delta;
            if (timer > 0.05f) {
                letterOn++;
                timer = 0;
            }
            String displayMessage = message.substring(0, letterOn) + "_";
            if (message.substring(0, letterOn).equals(message)) showState = SHOWING;
            messageCurrent = new GlyphLayout(textFont, displayMessage, Color.WHITE, 2 * ShePaints.WIDTH / 3f, -1, true);
            getBGBoxParameters();
        }
        else if (showState == SHOWING) {
            String addon = " ";
            timer += delta;
            if (timer > 1f) {
                timer = 0;
            }
            else if (timer > 0.5f) addon = "_";
            String displayMessage = message + addon;
            messageCurrent = new GlyphLayout(textFont, displayMessage, Color.WHITE, 2 * ShePaints.WIDTH / 3f, -1, true);
            getBGBoxParameters();
        }

    }

    public void setShowState(int state) {
        showState = state;
    }

    public void skip() {
        showState++;
        if (showState > SHOWING) showState = HIDDEN;
    }

    public int showState() {

        return showState;
    }

    public void render(SpriteBatch batch) {

        if (showState != HIDDEN) {
            drawBox(batch);
            textFont.draw(batch, messageCurrent, xText, yText);
        }

    }

    private void getBGBoxParameters() {
        yText = (int) (textBottom + messageCurrent.height);
        bgHeightFinal = (int) (messageCurrent.height + 40);
        xBG = xText - 20;
        yBG = (int) (yText - messageCurrent.height - 20);
        yOutlineBottom = yBG - 2;
        yOutlineTop = yBG + bgHeightFinal;
    }

    private void drawBox(SpriteBatch batch) {
        batch.draw(bgTexture, xBG, yBG, bgWidthFinal, bgHeightFinal);
        int bgOutlineDimensions = 2;
        batch.draw(bgOutline, xOutlineLeft, yBG, bgOutlineDimensions, bgHeightFinal);
        batch.draw(bgOutline, xOutlineRight, yBG, bgOutlineDimensions, bgHeightFinal);
        batch.draw(bgOutline, xBG, yOutlineBottom, bgWidthFinal, bgOutlineDimensions);
        batch.draw(bgOutline, xBG, yOutlineTop, bgWidthFinal, bgOutlineDimensions);
    }

}
