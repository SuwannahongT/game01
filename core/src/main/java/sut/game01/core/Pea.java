package sut.game01.core;

import java.util.ArrayList;
import java.util.List;

import static playn.core.PlayN.*;

import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import playn.core.util.Callback;

public class Pea {
    public static String IMAGE = "images/cloudRain.png";
    private ImageLayer layer;
    private int elapsed;
    private final float angVel = (tick() % 10 - 5) / 1000f;

    public Pea(final GroupLayer peaLayer, final float x, final float y) {
        Image image = assets().getImage(IMAGE);
        layer = graphics().createImageLayer(image);

        // Add a callback for when the image loads.
        // This is necessary because we can't use the width/height (to center the
        // image) until after the image has been loaded
        image.addCallback(new Callback<Image>() {
            @Override
            public void onSuccess(Image image) {
                layer.setOrigin(image.width() / 2f, image.height() / 2f);
                layer.setTranslation(x, y);
                peaLayer.add(layer);
            }

            @Override
            public void onFailure(Throwable err) {
                log().error("Error loading image!", err);
            }
        });
    }

    public void update(int delta) {
        elapsed += delta;
    }

    public void paint(float alpha) {
        float now = elapsed + alpha * 33; //MyGame.UPDATE_RATE
        layer.setRotation(now * angVel);
    }
}
