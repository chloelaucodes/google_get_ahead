import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class FindMaxRectangleTest {

  @Test
  public void constantHeight1() {
    assertArrayEquals(
        new int[] {0, 3}, new FindMaxRectangle(new int[] {2, 2, 2, 2}).findMaxRectangle());
  }

  @Test
  public void constantHeight2() {
    assertArrayEquals(
        new int[] {2, 3}, new FindMaxRectangle(new int[] {1, 1, 5, 5, 1}).findMaxRectangle());
  }

  @Test
  public void constantHeight3() {
    assertArrayEquals(new int[] {0, 0}, new FindMaxRectangle(new int[] {5}).findMaxRectangle());
  }

  @Test
  public void emptyInput() {
    assertArrayEquals(new int[] {-1, -1}, new FindMaxRectangle(new int[] {}).findMaxRectangle());
  }

  @Test
  public void handleZeroHeight() {
    assertArrayEquals(
        new int[] {0, 0}, new FindMaxRectangle(new int[] {8, 0, 1, 1}).findMaxRectangle());
  }

  @Test
  public void zigzag1() {
    assertArrayEquals(
        new int[] {0, 2}, new FindMaxRectangle(new int[] {2, 4, 2, 1}).findMaxRectangle());
  }

  @Test
  public void zigzag2() {
    assertArrayEquals(
        new int[] {4, 6},
        new FindMaxRectangle(new int[] {2, 4, 2, 1, 10, 6, 10}).findMaxRectangle());
  }

  @Test
  public void zigzag3() {
    assertArrayEquals(
        new int[] {2, 4}, new FindMaxRectangle(new int[] {6, 2, 5, 4, 5, 1, 6}).findMaxRectangle());
  }

  @Test
  public void zigzag4() {
    assertArrayEquals(
        new int[] {0, 8},
        new FindMaxRectangle(new int[] {7, 2, 1, 4, 5, 1, 3, 3, 1}).findMaxRectangle());
  }

  @Test
  public void zigzag5() {
    assertArrayEquals(
        new int[] {2, 3}, new FindMaxRectangle(new int[] {2, 1, 4, 5, 1, 3, 3}).findMaxRectangle());
  }
}
