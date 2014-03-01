package sut.game01.core;

import playn.core.Game;
import playn.core.Mouse;
import playn.core.PlayN;
import playn.core.util.Clock;
import tripleplay.game.ScreenStack;



public class MyGame extends Game.Default {
    //GroupLayer peaLayer;
    //List<Pea> peas = new ArrayList<Pea>(0);

    //public static final int UPDATE_RATE = 25;
    private ScreenStack ss = new ScreenStack();
    private Clock.Source clock = new Clock.Source(33);

    public MyGame() {
        super(33);
    }

    @Override
    public void init() {
        //final Screen home = new HomeScreen(ss);
        ss.push(new HomeScreen(ss));

        PlayN.mouse().setListener(new Mouse.Listener() {
            @Override
            public void onMouseDown(Mouse.ButtonEvent event) {

            }

            @Override
            public void onMouseUp(Mouse.ButtonEvent event) {

            }

            @Override
            public void onMouseMove(Mouse.MotionEvent event) {

            }

            @Override
            public void onMouseWheelScroll(Mouse.WheelEvent event) {

            }
        });

        /*
        // create and add background image layer
        Image bgImage = assets().getImage("images/bg.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);

        // create a group layer to hold the peas
        peaLayer = graphics().createGroupLayer();
        graphics().rootLayer().add(peaLayer);

        // preload the pea image into the asset manager cache
        assets().getImage(Pea.IMAGE);

        // add a listener for pointer (mouse, touch) input
        pointer().setListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                Pea pea = new Pea(peaLayer, event.x(), event.y());
                peas.add(pea);
            }
        });*/
    }

    @Override
    public void update(int delta) {
        /*for (Pea pea : peas) {
            pea.update(delta);
        }*/
        ss.update(delta);

    }

    @Override
    public void paint(float alpha) {
        /*for (Pea pea : peas) {
            pea.paint(alpha);
        }*/
        clock.paint(alpha);
        ss.paint(clock);

    }
  /*public MyGame() {
    super(33); // call update every 33ms (30 times per second)
  }

  private Image bgImage;
  private ImageLayer bgLayer;

  private Image cloudImage;
  private ImageLayer cloudLayer;

  private Image cloudImage2;
  private ImageLayer cloudLayer2;

  private float x = 24.0f;
  private float y = 3.0f;

  private float x2 = 24.0f;
  private float y2 = 25.0f;

  private int i = 0,i2 = 0, vectorX = 0, vectorXX = 0;


        @Override
  public void init() {
    // create and add background image layer
    bgImage = assets().getImage("images/bg.png");
    bgLayer = graphics().createImageLayer(bgImage);
    graphics().rootLayer().add(bgLayer);

    cloudImage = assets().getImage("images/cloud 3.png");
    cloudLayer = graphics().createImageLayer(cloudImage);
    graphics().rootLayer().add(cloudLayer);

    cloudImage2 = assets().getImage("images/cloudRain.png");
    cloudLayer2 = graphics().createImageLayer(cloudImage2);
    graphics().rootLayer().add(cloudLayer2);

    cloudLayer.setTranslation(x,y);
    cloudLayer2.setTranslation(x2,y2);
  }

  @Override
  public void update(int delta) {


      if(i+cloudLayer.width()>640){
          vectorXX=(-1);
      }
      if(i<1){
          vectorXX=1;
      }
      i=i+vectorXX*5;
      cloudLayer.setTx(i);


      if(i2+cloudLayer2.width()>640){
          vectorX=(-1);
      }
      if(i2<1){
          vectorX=1;
      }
      i2=i2+vectorX*10;
      cloudLayer2.setTx(i2);
  }

  @Override
  public void paint(float alpha) {
    // the background automatically paints itself, so no need to do anything here!



  }*/
}
