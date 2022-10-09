package collection;
// 208968560 Dan Saada

/**
 * @author Dan Saada
 * @version ass3
 * @since 2022/04/06
 */

import biuoop.DrawSurface;
import interfaces.Comparator;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds all interfaces.Sprite collection-related methods.
 */
public class SpriteCollection implements Comparator<Sprite> {
    private List<Sprite> sprites;

    //constructor
    /**
     * This constructor sets a interfaces.Sprite Collection.
     *
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * This method add sprites to the sprites list.
     *
     * @param s - a sprite.
     */
    public void addSprite(Sprite s) {
        if (sprites == null) {
            sprites = new ArrayList<>();
        }
        this.sprites.add(s);
    }

    /**
     * This method remove sprites from the sprites list.
     *
     * @param s - a sprite.
     */
    public void removeSprite(Sprite s) {
        List<Sprite> tempSprites = new ArrayList<Sprite>(this.sprites);
        for (int i = 0; i < tempSprites.size(); i++) {
            if (compare(tempSprites.get(i), s)) {
                this.sprites.remove(s);
            }
        }
    }

    /**
     * This method  call "timePassed" on all sprites.
     */
    public void notifyAllTimePassed() {
        // Make a copy of sprites before iterating over them.
        List<Sprite> tempSprites = new ArrayList<Sprite>(this.sprites);
        for (int i = 0; i < tempSprites.size(); i++) {
            tempSprites.get(i).timePassed();
        }
    }

    /**
     * This method call "drawOn" on all sprites.
     *
     * @param d - the surface to be drawn on.
     */
    public void drawAllOn(DrawSurface d) {
        // Make a copy of sprites before iterating over them.
        List<Sprite> tempSprites = new ArrayList<Sprite>(this.sprites);
        for (int i = 0; i < tempSprites.size(); i++) {
            sprites.get(i).drawOn(d);
        }
    }

    @Override
    public Boolean compare(Sprite o1, Sprite o2) {
        return (o1.equals(o2));
    }
}
