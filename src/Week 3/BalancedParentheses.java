// Comments given alongside the task
// In an interview you can generally omit the import statements,
// however if you decide to use some library (like Stack in this example)
// it's a good idea to mention it to the Interviewer and ask if it's allowed.

/**
 * This file is the solution for Week 3 - Balanced Parentheses
 *
 * <p>Prompt: Given a string of parentheses, find the size of the longest contiguous substring of
 * balanced parentheses. Parentheses are considered balanced when there is a valid closing
 * parenthesis for an opening one.
 */
public class BalancedParentheses {
  
  // This stores the number of left parentheses
  private static int leftCounter = 0;
  // This stores the number of right parentheses
  private static int rightCounter = 0;
  // This stores the current index
  private static int index = 0;
  // This stores the max interval of valid parentheses
  private static int maxInterval = 0;
  
  /**
   * Finds the longest number of valid balanced parentheses
   *
   * @param str the string to be parsed and checked
   * @return length of the longest valid balanced parentheses
   */
  public static int longestBalanced(String str) {
    if (index >= str.length() || maxInterval >= str.length() - index) {
      // Checks if index is bigger than the length of str or whether the remainder length of str is
      // shorter than the current maxInterval
      return maxInterval;
    } else if (str.charAt(index) == '(') {
      // Checks if the current char of str at index is '('
      leftCounter++;
    } else if (str.charAt(index) == ')') {
      // Checks if the current char of str at index is ')'
      // Checks if there are more right parentheses than left parentheses
      if (rightCounter >= leftCounter) {
        // maxInterval takes the higher value of the current maxInterval with the lower of
        // leftCounter and rightCounter
        maxInterval = Math.max(Math.min(leftCounter, rightCounter), maxInterval);
        // Resets counters
        resetCounter();
      } else {
        rightCounter++;
      }
    } else {
      // Falls back and resets counters if the current value isn't either '(' or ')'
      resetCounter();
    }
    // Increments index and recurses
    index++;
    return longestBalanced(str);
  }
  
  /**
   * resets both counters
   */
  public static void resetCounter() {
    leftCounter = 0;
    rightCounter = 0;
  }
}

