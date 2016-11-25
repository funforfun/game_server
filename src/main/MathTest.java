package main;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathTest {
    private Math math;

    @Before
    public void setUp() throws Exception {
        math = new Math(5, 3);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getSum() throws Exception {
        assertEquals(5 + 3, math.getSum());

    }


    @Test
    public void getDiv() throws Exception {
        assertEquals(3 / 0, math.getDiv());

    }

}