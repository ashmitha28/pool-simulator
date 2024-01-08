package sim;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * The SimplePoolSimulatorTest class contains a set of JUnit test cases for the SimplePoolSimulator
 * class. It covers various scenarios and edge cases to ensure the correct functionality of the
 * simulator.
 */

public class SimplePoolSimulatorTest {

  /**
   * Tests starting the simulation with zero velocity in the X-direction. Verifies that the
   * simulation can start with zero velocity in the X-direction and advances accordingly.
   */

  @Test
  public void checkZeroDx() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "simple");

    obj.start(2, 2, 1, 10, 0, 1);

    obj.advance();
    assertEquals(0, obj.getBallVelocityX(), 0.001);
  }

  /**
   * To test the invalid width input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalWidth() {
    SimplePoolSimulator obj = new SimplePoolSimulator(-10, 2, "simple");
    obj.start(2, 3, 4, 5, 0, 2);
    obj.advance();
  }

  /**
   * To test the invalid height input.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalHeight() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, -2, "simple");
    obj.start(2, 3, 4, 5, 0, 2);
    obj.advance();
  }

  /**
   * To test the invalid radius input.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRadius() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "simple");
    obj.start(2, 3, -4, 5, 0, 2);
    obj.advance();
  }

  /**
   * To test the invalid type input.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalType() {
    SimplePoolSimulator obj = new SimplePoolSimulator(-10, 2, "xyz");
    obj.start(2, 3, 4, 5, 0, 2);
    obj.advance();
  }

  /**
   * To test the invalid speed input.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSpeed() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "simple");
    obj.start(2, 3, 4, -95, 0, 2);
    obj.advance();
  }

  /**
   * To test the speed input as 0 which is invalid.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSpeedZero() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "simple");
    obj.start(2, 3, 4, 0, 0, 2);
    obj.advance();
  }

  /**
   * Test to throw exception when ball is not inside the table.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegaloutOfBoundsX() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "simple");
    obj.start(2, 3, 74, 5, 0, 2);
    obj.advance();
  }

  /**
   * Test to throw exception when ball is not inside the table.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testIllegaloutOfBoundsY() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 2, "simple");
    obj.start(2, 3, 4, 5, 0, 2);
    obj.advance();
  }

  /**
   * Tests starting the simulation with zero velocity in the X-direction. Verifies that the
   * simulation can start with zero velocity in the X-direction and advances accordingly.
   */

  @Test
  public void checkzeroDy() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "simple");

    obj.start(2, 2, 1, 10, 1, 0);

    obj.advance();
    assertEquals(-5.0, obj.getBallVelocityX(), 0.001);
  }

  /**
   * Test to check the case when dx <0.
   */

  @Test
  public void checkNegativeDx() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "simple");

    obj.start(2, 2, 1, 10, -2, 1);

    obj.advance();
    assertEquals(4.472, obj.getBallVelocityX(), 0.001);
  }

  /**
   * Test to check the case when dx <0.
   */

  @Test
  public void checkNegativeDy() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "simple");

    obj.start(2, 2, 1, 10, 2, -1);

    obj.advance();
    assertEquals(4.472, obj.getBallVelocityX(), 0.001);
  }

  /**
   * Test to check both x and y can be negative.
   */
  @Test
  public void checkNegativeXY() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "simple");

    obj.start(2, 2, 1, 10, -2, -1);

    obj.advance();
    assertEquals(4.472, obj.getBallVelocityX(), 0.001);
  }

  /**
   * Test to check both x and y can be positive.
   */
  @Test
  public void checkPositiveXY() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "simple");

    obj.start(2, 2, 1, 10, 2, 1);

    obj.advance();
    assertEquals(-4.472, obj.getBallVelocityX(), 0.001);
  }

  /**
   * Tests for friction. Tests starting the simulation with zero velocity in the X-direction.
   * Verifies that the simulation can start with zero velocity in the X-direction and advances
   * accordingly.
   */
  @Test
  public void checkzeroDxFr() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "friction");

    obj.start(2, 2, 1, 10, 0, 1);

    obj.advance();
    assertEquals(0, obj.getBallVelocityX(), 0.001);
  }

  /**
   * To test the invalid width input.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalWidthFr() {
    SimplePoolSimulator obj = new SimplePoolSimulator(-10, 2, "friction");
    obj.start(2, 3, 4, 5, 0, 2);
    obj.advance();
  }

  /**
   * To test the invalid height input.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalHeightFr() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, -2, "friction");
    obj.start(2, 3, 4, 5, 0, 2);
    obj.advance();
  }

  /**
   * To test the invalid radius input.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRadiusFr() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "friction");
    obj.start(2, 3, -4, 5, 0, 2);
    obj.advance();
  }

  /**
   * To test the invalid speed input.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSpeedFr() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "friction");
    obj.start(2, 3, 4, -95, 0, 2);
    obj.advance();
  }

  /**
   * To test the speed input as 0 which is invalid.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSpeedZeroFr() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "friction");
    obj.start(2, 3, 4, 0, 0, 2);
    obj.advance();
  }

  /**
   * Test to throw exception when ball is not inside the table.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testIllegaloutOfBoundsXFr() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "friction");
    obj.start(2, 3, 74, 5, 0, 2);
    obj.advance();
  }

  /**
   * Test to throw exception when ball is not inside the table.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testIllegaloutOfBoundsYFr() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 2, "friction");
    obj.start(2, 3, 4, 5, 0, 2);
    obj.advance();
  }

  /**
   * Tests starting the simulation with zero velocity in the Y-direction. Verifies that the
   * simulation can start with zero velocity in the Y-direction and advances accordingly.
   */

  @Test
  public void checkzeroDyFr() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "friction");

    obj.start(2, 2, 1, 10, 1, 0);

    obj.advance();
    assertEquals(0, obj.getBallVelocityX(), 0.001);
  }

  /**
   * Test for negative dx.
   */
  @Test
  public void checkNegativeDxFr() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "friction");

    obj.start(2, 2, 1, 10, -2, 1);

    obj.advance();
    assertEquals(0, obj.getBallVelocityX(), 0.001);
  }

  /**
   * Test for negative dx.
   */
  @Test
  public void checkNegativeDyFr() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "friction");

    obj.start(2, 2, 1, 10, 2, -1);

    obj.advance();
    assertEquals(0, obj.getBallVelocityX(), 0.001);
  }

  /**
   * Test for negative dx & dy.
   */
  @Test
  public void checkNegativeXYfr() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "friction");

    obj.start(2, 2, 1, 10, -2, -1);

    obj.advance();
    assertEquals(0, obj.getBallVelocityX(), 0.001);
  }

  /**
   * Test for positive dx&dy.
   */
  @Test
  public void checkPositiveXYfr() {
    SimplePoolSimulator obj = new SimplePoolSimulator(10, 10, "friction");

    obj.start(2, 2, 1, 10, 2, 1);

    obj.advance();
    assertEquals(0, obj.getBallVelocityX(), 0.001);
  }

  /**
   * Test for complex root case.
   */
  @Test
  public void testComplexRootCase() {
    SimplePoolSimulator obj = new SimplePoolSimulator(100, 10, "friction");

    obj.start(5, 2, 10, 5, 10, 1);

    obj.advance();
    assertEquals(0, obj.getBallVelocityX(), 0.001);
  }
}