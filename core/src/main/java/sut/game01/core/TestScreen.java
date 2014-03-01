package sut.game01.core;

import tripleplay.game.Screen;


/**
 * Created by AprilMcBkPro on 21/01/2014.
 */
public class TestScreen extends Screen {

 /*   public static float M_PER_PIXEL = 1 / 26.666667f;
    //size of world
    private static int width  = 24;
    private static int hieght = 18;

    private World world;


    private final ScreenStack ss;
    private Root root;
    private Image bgImage;
    private Layer bgLayer;
    private Image buImage;
    private Layer buLayer;
    private Zealot z = new Zealot(560f,400f);


    public TestScreen(ScreenStack ss){
        this.ss = ss;
    }



    @Override
    public void wasAdded() {
        super.wasAdded();

        bgImage = assets().getImage("images/bg2.png");
        bgImage.addCallback(new Callback<Image>() {
            @Override
            public void onSuccess(Image result) {

            }

            @Override
            public void onFailure(Throwable cause) {

            }
        });
        bgLayer = PlayN.graphics().createImageLayer(bgImage);
        layer.add(bgLayer);


        layer.add(z.layer());




      /*  bgImage = assets().getImage("images/bg.png");
        bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);


    }
    @Override
    public void update(int delta) {
        z.update(delta);
    }

    */
}
