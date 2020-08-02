import java.util.Stack;

public class FindMaxRectangle {

  private int[] histogram;
  
  /**
   * default FindMaxRectangle constructor
   * @param histogram int array of the histogram
   */
  public FindMaxRectangle(int[] histogram) {
    this.histogram = histogram;
  }
  
  /**
   * finds the area of the maximum rectangle under the curve.
   * @return int array with the left and right indices that represent the rectangle
   */
  public int[] findMaxRectangle() {
    if (histogram == null || histogram.length == 0) { // empty histogram
      return new int[] {-1, -1}; // return {-1, -1}
    }
    
    Stack<int[]> stack = new Stack<>(); // stack stores {index, height}, strictly increasing height
    int i = 0; // iterator
    int[] result = new int[3]; // results stores {start coord, end coord, area}

    while (i < histogram.length) {
      if (stack.isEmpty() || histogram[i] >= stack.peek()[1]) {
        if (stack.isEmpty() || histogram[i] > stack.peek()[1]) {
          // push index to stack when the current histogram is larger than the previous one
          stack.push(new int[] {i, histogram[i]});
        }
        i++; // increment iterator if stack empty or current height is larger or equal to stack peek
      } else {
        // calculate max value and coord when the current histogram is less than the previous one
        int[] pop = stack.pop();
        int start = stack.isEmpty() ? 0 : pop[0];
        int area = pop[1] * (i - start);
        result = (area > result[2]) ? new int[] {start, i - 1, area} : result;
        // pushes a smaller value to stack if current value is larger than peek
        if (!stack.isEmpty() && pop[1] > histogram[i] && histogram[i] > stack.peek()[1]) {
          stack.push(new int[] {pop[0], histogram[i]});
        }
      }
    }

    while (!stack.isEmpty()) {
      // loops through stack to calculate remaining areas and updates result for max area and coord
      int[] pop = stack.pop();
      int start = stack.isEmpty() ? 0 : pop[0];
      int area = pop[1] * (i - start);
      result = (area > result[2]) ? new int[] {start, i - 1, area} : result;
    }
    return new int[] {result[0], result[1]};
  }
}
