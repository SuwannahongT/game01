package sut.game01.sprite;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;
import playn.core.util.Callback;
import playn.core.util.Clock;
import tripleplay.game.Screen;
import sut.game01.core.*;
import tripleplay.game.UIScreen;


/**
 * Created by AprilMcBkPro on 28/01/2014.
 */
public class Zealot{

    private Sprite sprite;
    private int spriteIndex = 0;
    private boolean hasLoaded = false;
    private Body body;
    private World world;



    public enum State {
        IDLER,RUNR,ATTKR,IDLEL,RUNL,ATTKL
    }

    private State state = State.IDLER;

    private  int e = 0;
    private  int offset = 0;
    private float xx = 25.0f;


    public Zealot(final World world, final float x_px, final float y_px){
        sprite = SpriteLoader.getSprite("images/zealot.json");
        sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width() /2f ,sprite.height() /2f);
                sprite.layer().setTranslation(x_px, y_px);
                //sprite.layer().setTranslation(0 , 380);

                body = initPhysicsBody(world,
                        GameScreen.M_PER_PIXEL * x_px,
                        GameScreen.M_PER_PIXEL * y_px);
                hasLoaded = true;
            }

            @Override
            public void onFailure(Throwable cause) {
                PlayN.log().error("Error loading image!", cause);
            }
        });
        /*sprite.layer().addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                state = State.ATTKR;
                spriteIndex = -1;
                e = 0;
            }
        });*/



        PlayN.keyboard().setListener(new Keyboard.Listener() {
            @Override
            public void onKeyDown(Keyboard.Event event) {
                if (event.key() == Key.RIGHT) {
                    state = State.RUNR;
                    spriteIndex = 1;
                    e = 0;
                }
                if (event.key() == Key.LEFT) {
                    state = State.RUNL;
                    spriteIndex = 1;
                    e = 0;
                }
                if (event.key() == Key.SPACE) {

                    if (state == State.IDLER) {
                        state = State.ATTKR;
                        spriteIndex = -1;
                        e = 0;
                    }
                    if (state == State.IDLEL) {
                        state = State.ATTKL;
                        spriteIndex = -1;
                        e = 0;
                    }
                }
                if( event.key() == Key.UP) {
                    if (state == State.IDLER){
                        state = State.IDLER;
                        body.applyForce(new Vec2(-10f, -500f), body.getPosition()); 
                        spriteIndex = 0;
                        e = 0;
                    }
                    else{
                        state = State.IDLEL;
                        body.applyForce(new Vec2(-10f,-500f), body.getPosition());
                        spriteIndex = 0;
                        e = 0;
                    }

                }

            }


            @Override
            public void onKeyTyped(Keyboard.TypedEvent event) {

            }

            @Override
            public void onKeyUp(Keyboard.Event event) {
                if (event.key() == Key.RIGHT) {
                    state = State.IDLER;
                    spriteIndex = 0;
                    e = 0;
                }
                if (event.key() == Key.LEFT) {
                    state = State.IDLEL;
                    spriteIndex = 0;
                    e = 0;
                }
            }
        });


    }



    private Body initPhysicsBody(World world,float x, float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position = new Vec2(0,0);
        Body body = world.createBody(bodyDef);

        //EdgeShape shape = new EdgeShape();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(56 * GameScreen.M_PER_PIXEL /2, sprite.layer().height()* GameScreen.M_PER_PIXEL /2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.4f;
        fixtureDef.friction = 0.1f;
       // fixtureDef.restitution = 0.35f;
        body.createFixture(fixtureDef);

        body.setLinearDamping(0.2f);
        body.setTransform(new Vec2(x,y),0f);
        return body;
    }


    public Layer layer(){
        return sprite.layer();
    }


    public void update(int delta) {
        //update(delta);
        if (!hasLoaded) return;
        e += delta;

        if (e > 180){
            switch (state) {
                case IDLER:offset = 0;
                    break;
                case RUNR:offset = 4;
                    break;
                case ATTKR:offset = 8;
                    if (spriteIndex == 8){
                        state = State.IDLER;
                    }
                    break;
                case IDLEL:offset = 10;
                    break;
                case RUNL:offset = 14;
                    break;
                case ATTKL:offset = 18;
                    if (spriteIndex == 18){
                        spriteIndex -= 2;
                        state = State.IDLEL;
                    }
                    break;
            }

            spriteIndex = offset + ((spriteIndex + 1) % 4);
            sprite.setSprite(spriteIndex);
            e = 0;
            /*if(state == State.IDLER || state == State.RUNR || state == State.ATTKR){
                spriteIndex = offset + ((spriteIndex +1) % 4);
                sprite.setSprite(spriteIndex);
                e = 0;
             }
            if(state == State.IDLEL || state == State.RUNL || state == State.ATTKL){
                spriteIndex = offset - ((spriteIndex +1) % 4);
                sprite.setSprite(spriteIndex);
                e = 0;
            }*/

            if (state == State.RUNR){
                xx += 20f ;
                sprite.layer().setTranslation(xx,380);
                body.applyLinearImpulse(new Vec2(7f,0f),body.getPosition());

            }
            if (state == State.RUNL){
                xx -= 20f ;
                sprite.layer().setTranslation(xx,380);
                body.applyLinearImpulse(new Vec2(-7f,0f),body.getPosition());

            }
        }
    }
    public void paint(Clock clock){
        if(!hasLoaded) return;
        sprite.layer().setTranslation(
                (body.getPosition().x / GameScreen.M_PER_PIXEL) -10,
                 body.getPosition().y / GameScreen.M_PER_PIXEL );
        sprite.layer().setRotation(body.getAngle());


    }

}
