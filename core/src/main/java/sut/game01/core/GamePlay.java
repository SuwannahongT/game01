package sut.game01.core;

import tripleplay.game.UIScreen;


/**
 * Created by AprilMcBkPro on 22/01/2014.
 */
public class GamePlay extends UIScreen {


   /* private final ScreenStack ss;
    private Root root;
    private Image bgImage;
    private Layer bgLayer;
    private Image buImage;
    private Layer buLayer;

    public GamePlay(ScreenStack ss){
        this.ss = ss;
    }

    @Override
    public void wasAdded() {
        super.wasAdded();

        bgImage = assets().getImage("images/bg.png");
        bgImage.addCallback(new Callback<Image>() {
            @Override
            public void onSuccess(Image result) {

            }

            @Override
            public void onFailure(Throwable cause) {

            }
        });
        bgLayer = PlayN.graphics().createImageLayer(bgImage);
        //layer.add(bgLayer);

       buImage = assets().getImage("images/Back.png");
        buImage.addCallback(new Callback<Image>() {
            @Override
            public void onSuccess(Image result) {

            }

            @Override
            public void onFailure(Throwable cause) {

            }
        });
        //buLayer = PlayN.graphics().createImageLayer(buImage);
        //layer.add(buLayer);

        root = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root.addStyles(Style.BACKGROUND.is(Background.image(bgImage)));
        root.setSize(width(), height());
        //root.add(new Label("Event Driven Programming").addStyles(Style.FONT.is(StartScreen.TITLE_FONT)));
        //root.add(new Label("Disc Ball").addStyles(Style.FONT.is(StartScreen.TITLE_FONT)));
        root.add(new ImageButton(buImage).onClick(new UnitSlot() {
            @Override
            public void onEmit() {
                ss.push(new StartScreen(ss));
            }
        }));

    }
*/

}
