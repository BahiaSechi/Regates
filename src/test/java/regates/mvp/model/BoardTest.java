package regates.mvp.model;

import org.junit.Test;

public class BoardTest {

    @Test
    public void testGetInstance() {
        Board b = Board.getInstance();
        Board c = Board.getInstance();
    }
}
