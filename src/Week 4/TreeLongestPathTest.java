import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeLongestPathTest {

  @Test
  public void visibleTest1() {
    Tree t =
        new Tree(
            1,
            Arrays.asList(
                new Tree(2, Collections.singletonList(new Tree(4, Collections.emptyList()))),
                new Tree(4, Collections.emptyList())));
    assertEquals(2, t.longestPath());
  }

  @Test
  public void visibleTest2() {
    Tree t =
        new Tree(
            5,
            Arrays.asList(
                new Tree(6, Collections.emptyList()),
                new Tree(
                    7,
                    Arrays.asList(
                        new Tree(
                            8,
                            Collections.singletonList(
                                new Tree(
                                    9,
                                    Arrays.asList(
                                        new Tree(15, Collections.emptyList()),
                                        new Tree(10, Collections.emptyList()))))),
                        new Tree(12, Collections.emptyList())))));
    assertEquals(4, t.longestPath());
  }
}
