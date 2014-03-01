package sut.game01.core;

import playn.core.Font;
import playn.core.PlayN;
import react.UnitSlot;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;

/**
 * Created by AprilMcBkPro on 22/01/2014.
 */
public class StartScreen extends UIScreen {




   public static final Font TITLE_FONT = PlayN.graphics().createFont(
            "Helvetica",
            Font.Style.BOLD,
            24
    );

    private final ScreenStack ss;
    private Root root;

    public StartScreen(ScreenStack ss){
        this.ss = ss;
    }

    @Override
    public void wasShown() {
        super.wasShown();
        root = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root.addStyles(Style.BACKGROUND.is(Background.bordered(0xFFCCCCCC, 0xFF99CCFF, 5).inset(5, 10)));
        root.setSize(width(), height());

        root.add(new Label("Hocky Disc").addStyles(Style.FONT.is(StartScreen.TITLE_FONT)));
        root.add(new Button("Start").onClick(new UnitSlot() {
            @Override
            public void onEmit() {
                ss.push(new GameScreen(ss));
            }

        }));
        root.add(new Button("Back").onClick(new UnitSlot() {
            @Override
            public void onEmit() {

                ss.remove(StartScreen.this);

            }
        }));

    }


}
