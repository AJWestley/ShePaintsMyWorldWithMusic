package com.riskrider.shepaints.entities.player;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.riskrider.shepaints.ShePaints;

public class Floor {

    private final Texture floorTexture;

    private final int textureWidth;
    private final int textureHeight;
    private final int maxHeight;

    public Floor(FileHandle path, int maxHeight, int scale) {

        floorTexture = new Texture(path);
        this.maxHeight = maxHeight;
        textureWidth = floorTexture.getWidth() / scale;
        textureHeight = floorTexture.getHeight() / scale;
    }

    public void render(SpriteBatch batch) {

        for (int i = textureHeight; i < maxHeight; i += textureHeight) {
            for (int j = 0; j < ShePaints.WIDTH; j += textureWidth) {
                batch.draw(floorTexture, j, i, textureWidth, textureHeight);
            }
        }

    }

}
