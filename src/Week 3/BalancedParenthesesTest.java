import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BalancedParenthesesTest {

  @Test
  public void test1() {
    assertEquals(2, (int) BalancedParentheses.longestBalanced("()("));
  }

  @Test
  public void test2() {
    assertEquals(6, (int) BalancedParentheses.longestBalanced("(()())"));
  }

  @Test
  public void test3() {
    assertEquals(2, (int) BalancedParentheses.longestBalanced("())"));
  }

  @Test
  public void test4() {
    assertEquals(4, (int) BalancedParentheses.longestBalanced("())(())"));
  }

  @Test
  public void test5() {
    assertEquals(4, (int) BalancedParentheses.longestBalanced("(())"));
  }

  @Test
  public void test6() {
    assertEquals(4, (int) BalancedParentheses.longestBalanced(")(()))))(((()"));
  }

  @Test
  public void test7() {
    assertEquals(4, (int) BalancedParentheses.longestBalanced("())(())"));
  }

  @Test
  public void test8() {
    assertEquals(0, (int) BalancedParentheses.longestBalanced("(("));
  }

  @Test
  public void test9() {
    assertEquals(4, (int) BalancedParentheses.longestBalanced("()((())(())"));
  }

  @Test
  public void test10() {
    assertEquals(0, (int) BalancedParentheses.longestBalanced(""));
  }

  @Test
  public void test11() {
    assertEquals(0, (int) BalancedParentheses.longestBalanced("("));
  }

  @Test
  public void test12() {
    assertEquals(0, (int) BalancedParentheses.longestBalanced(")"));
  }
}
