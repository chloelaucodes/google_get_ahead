// In an interview you can generally omit the import statements,
// however if you decide to use some library (like Stack in this example)
// it's a good idea to mention it to the Interviewer and ask if it's allowed.

/**
 * This file is the solution for Week 3 - Balanced Parentheses
 *
 * Prompt: Given a string of parentheses, find the size of the longest contiguous substring of
 * balanced parentheses. Parentheses are considered balanced when there is a valid closing
 * parenthesis for an opening one.
 */
public class BalancedParentheses {

  /** Initial declarations */
  // this stores the number of left parentheses
  private static Integer leftCounter = 0;
  // this stores the number of right parentheses
  private static Integer rightCounter = 0;
  // this stores the max interval of valid parentheses
  private static Integer maxInterval = 0;
  // this stores the starting point of the interval
  private static Integer startPoint = 0;
  // this stores the string to be evaluated
  private static String string;

  /**
   * finds the longest number of valid balanced parentheses
   *
   * @param str the string to be parsed and checked
   * @return length of the longest valid balanced parentheses
   */
  public static Integer longestBalanced(String str) {
    // assign string
    string = str;
    // resets counters and startPoint every time longestBalanced is called
    resetCounter(0);
    // calls helper function findLongestBalanced()
    return findLongestBalanced();
  }

  /**
   * helper function of longestBalanced(), recursive
   *
   * @return length of the longest valid balanced parentheses
   */
  private static Integer findLongestBalanced() {
    // resets counters every time longestBalanced is called
    resetCounter();
    // stores length as a variable
    int length = string.length();
    if (length == 0) return 0;
    // loops through from startingPoint to end of string
    for (int i = startPoint; i < length; i++) {
      // checks what the current character is
      switch (string.charAt(i)) {
          // if the current char is '('
        case '(':
          leftCounter++;
          // if this is end of list
          if (i == length - 1) {
            return printAndResetMax();
          }
          break;
          // if the current char is ')'
        case ')':
          rightCounter++;
          // if parentheses are balanced
          if (leftCounter.equals(rightCounter)) {
            // returns larger of maxInterval and current largest set of balanced parentheses
            maxInterval = Math.max(maxInterval, leftCounter * 2);
            // resets counters and set startPoint to index after this
            resetCounter(i + 1);
            // if there are more ')' than '(' (with the fall thru it should have 0 '(')
          } else if (rightCounter > leftCounter) {
            // resets counters and set startPoint to index after this
            resetCounter(i + 1);
            // if this is end of list
          } else if (i == length - 1) {
            // if startPoint is before end of list, then increments startPoint
            if (startPoint++ < length - 1) {
              // returns larger of current maxInterval and the maxInterval of from startPoint
              return Math.max(printAndResetMax(), findLongestBalanced());
            }
            // returns maxInterval
            return printAndResetMax();
          }
          break;
          // if input not '(' or ')', skip
        default:
          break;
      }
    }
    // returns maxInterval
    return printAndResetMax();
  }

  /**
   * [Overloaded] resets leftCounter and rightCounter, and sets startPoint to index
   *
   * @param index startPoint to be set
   */
  private static void resetCounter(int index) {
    startPoint = index;
    resetCounter();
  }

  /**
   * [Overloaded] resets leftCounter and rightCounter
   */
  private static void resetCounter() {
    leftCounter = 0;
    rightCounter = 0;
  }

  /**
   * resets maxInterval and returns the value prior to reset
   *
   * @return maxInterval before reset
   */
  private static Integer printAndResetMax() {
    Integer max = maxInterval;
    maxInterval = 0;
    return max;
  }
}
