package fr.cmm.tags;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by pomme on 17/12/2015.
 */
public class FunctionsTest {

    @Test
    public void newLineTest() {

        assertEquals("a", Functions.text("a"));
        assertEquals("a<br>", Functions.text("a\n"));
        assertEquals("&amp;a", Functions.text("&a"));
        assertEquals("", Functions.text(null));
    }


}