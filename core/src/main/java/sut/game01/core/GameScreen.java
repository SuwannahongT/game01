package sut.game01.core;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;
import playn.core.util.Callback;
import playn.core.util.Clock;
import sut.game01.sprite.Zealot;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.Root;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;


/**
 * Created by AprilMcBkPro on 21/01/2014.
 */
public class GameScreen extends UIScreen {

    public static float M_PER_PIXEL = 1 / 26.666667f;
    //size of world
    private static int width  = 24;  // 640 pixel in physic unit (meter)
    private static int height = 18;  // 480 pixel in physic unit (meter)

    private World world;
    private DebugDrawBox2D debugDraw;
    private boolean showDebugDraw = true;

    private  ScreenStack ss;
    private Root root;
    private Image bgImage;
    private Layer bgLayer;
    private Image buImage;
    private Layer buLayer;
    private Zealot z;
    private ImageLayer backLayer;
    private Body body;
    private Image backImage;

    public GameScreen(ScreenStack ss){
        this.ss = ss;
    }



    @Override
    public void wasAdded() {
        super.wasAdded();

        //set ค่าแรงโน้มถ่วง
        Vec2 gravity = new Vec2(0.0f,10.0f);
        world = new World(gravity,true);
        world.setWarmStarting(true);
        world.setAutoClearForces(true);


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
        layer.add(bgLayer);


        backImage = assets().getImage("images/Back2.png");
        backLayer = graphics().createImageLayer(backImage);
        layer.add(backLayer);

        backLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                //super.onPointerEnd(event);
                ss.remove(GameScreen.this);
            }
        });





        if(showDebugDraw){
            CanvasImage image = graphics().createImage(
                    (int) (width / GameScreen.M_PER_PIXEL),
                    (int) (height / GameScreen.M_PER_PIXEL));
            layer.add(graphics().createImageLayer(image));
            debugDraw = new DebugDrawBox2D();
            debugDraw.setCanvas(image);
            debugDraw.setFlipY(false);
            debugDraw.setStrokeAlpha(150);
            debugDraw.setFillAlpha(75);
            debugDraw.setStrokeWidth(2.0f);
            debugDraw.setFlags(DebugDraw.e_shapeBit |
                              DebugDraw.e_jointBit  |
                              DebugDraw.e_aabbBit);
            debugDraw.setCamera(0, 0, 1f / GameScreen.M_PER_PIXEL);
            world.setDebugDraw(debugDraw);
        }
        Body ground = world.createBody(new BodyDef());
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(2f, height-2),
                               new Vec2(width-2f, height-2));
        ground.createFixture(groundShape, 0.0f);

        Body ground2 = world.createBody(new BodyDef());
        PolygonShape groundShape2 = new PolygonShape();
        groundShape.setAsEdge(new Vec2(2f, height+22),
                new Vec2(width+2f, height+2));
        ground.createFixture(groundShape, 0.0f);


        z = new Zealot(world,100f,100f);
        layer.add(z.layer());

        //createBox();

      /*  bgImage = assets().getImage("images/bg.png");
        bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);

      */
    }
    private void createBox(){
        BodyDef bf = new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position = new Vec2(0, 0);

        body = world.createBody(bf);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1f,1f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 2.0f;
        fd.friction = 0.1f;
        fd.restitution = 15f;
        body.createFixture(fd);
        body.setLinearDamping(0.5f);
        body.setTransform(new Vec2(7f, 15f),0);
        backLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                //super.onPointerEnd(event);
                body.applyForce(new Vec2(100f,0f),body.getPosition());
            }
        });
    }


    @Override
    public void update(int delta) {
        super.update(delta);
        z.update(delta);
        world.step(0.033f, 10, 10);
    }

    @Override
    public void paint(Clock clock) {
        super.paint(clock);
        z.paint(clock);
        if(showDebugDraw){
            debugDraw.getCanvas().clear();
            world.drawDebugData();
        }
    }
}
