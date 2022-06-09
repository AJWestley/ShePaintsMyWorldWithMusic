package com.riskrider.shepaints.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.riskrider.shepaints.ShePaints;
import com.riskrider.shepaints.entities.player.Animator;
import com.riskrider.shepaints.entities.player.Floor;
import com.riskrider.shepaints.entities.player.Player;
import com.riskrider.shepaints.tools.MessageDisplay;

public class TestScreen implements Screen {

    private final ShePaints game;

    private final Player player;

    private final Floor floor;

    public TestScreen(ShePaints game) {

        this.game = game;
        player = new Player(ShePaints.WIDTH / 2f, ShePaints.HEIGHT / 2f, Animator.DOWN_RIGHT, game);
        floor = new Floor(Gdx.files.internal("WallsAndFloors/WoodenPanelsColour-export-export.png"), 3 * ShePaints.HEIGHT / 4, 4);

    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            if (game.messageDisplay.showState() == MessageDisplay.HIDDEN) {
                game.messageDisplay.setMessage("You pressed \"E\"", Gdx.files.internal("Fonts/Wland/32pt/32pt.fnt"));
                game.messageDisplay.setShowState(MessageDisplay.TYPING);
            }
            else game.messageDisplay.skip();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            if (game.messageDisplay.showState() == MessageDisplay.HIDDEN) {
                game.messageDisplay.setMessage("The quick brown fox jumps over the lazy dog.\nThe " +
                        "lazy dog was jumped over by the quick brown fox.", Gdx.files.internal("Fonts/Wland/20pt/20pt.fnt"));
                game.messageDisplay.setShowState(MessageDisplay.TYPING);
            }
            else game.messageDisplay.skip();
        }

        player.getMovementInput(delta);

        player.update(delta);
        game.messageDisplay.updateMessage(delta);

        ScreenUtils.clear(0.5f, 0.5f, 0.5f, 1);

        game.batch.begin();

        floor.render(game.batch);

        player.render(game.batch);

        game.messageDisplay.render(game.batch);

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
