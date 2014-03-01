package sut.game01.core;

import playn.core.ImageLayer;

import playn.core.*;

import playn.core.Font;
import playn.core.PlayN;

import playn.core.Font;
import playn.core.PlayN;

import react.UnitSlot;
import sut.game01.sprite.Sprite;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 * Created by AprilMcBkPro on 21/01/2014.
 */
public class HomeScreen extends UIScreen{

    public static final Font TITLE_FONT = PlayN.graphics().createFont(
            "Helvetica",
            Font.Style.BOLD,
            24
    );

    private final ScreenStack ss;
    private Root root;
    private Sprite sprite;

    private Image bgImage;
    private ImageLayer bgLayer;
    private Image bkImage;
    private ImageLayer bkLayer;
    private ImageLayer stLayer;
    private Image stImage;

    public HomeScreen(ScreenStack ss){
        this.ss = ss;
    }

    @Override
    public void wasShown() {
        super.wasShown();

        bgImage = assets().getImage("images/bg5.png");
        bgLayer = graphics().createImageLayer(bgImage);
        layer.add(bgLayer);

        stImage = assets().getImage("images/start3.png");
        stLayer = graphics().createImageLayer(stImage);
        layer.add(stLayer);

        root = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root.addStyles(Style.BACKGROUND.is(Background.image(bgImage)));//.bordered(0xBAC, 0xFF99CCFF, 5).inset(5, 10)));
        root.setSize(width(), height());
        root.add(new Label("Event Driven Programming").addStyles(Style.FONT.is(HomeScreen.TITLE_FONT)));
        root.add(new Label("Disc Ball").addStyles(Style.FONT.is(HomeScreen.TITLE_FONT)));
        root.add(new Button("Start").onClick(new UnitSlot() {
            @Override
            public void onEmit() {
                ss.push(new StartScreen(ss));
            }
        }));

        /*stLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                //super.onPointerEnd(event);
                ss.push(new StartScreen(ss));

            }
        });*/


    }
}
