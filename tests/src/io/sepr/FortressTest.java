package io.sepr;

import static org.junit.Assert.*;

import com.badlogic.gdx.graphics.Texture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class FortressTest {

    private Fortress fortress;

    @Before
    public void init() {
        fortress = new Fortress(100, new Texture("../core/assets/ctower.png"), 2);
    }

    @Test
    public void testDirectionToFloatFloat() {
        assertTrue(fortress.directionTo(fortress.getX() + 394, fortress.getY() + 384) == 90);
    }
}
