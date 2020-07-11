import java.util.Iterator;
import java.util.List;

/**
 * This file is the solution for Week 2 - Flattened Iterator
 *
 * Prompt: Given a list of iterators, implement a FlattenedIterator class which incrementally
 * iterates over the integers from all the iterators in an interleaved fashion.
 */
public class FlattenedIterator implements Iterator<Integer> {

  /** Initial declarations */
  // This stores the subiterator globally
  private List<Iterator<Integer>> subiterators;
  // This hold the overall index
  private Integer index = 0;
  // This stores the size of the list of the subiterator
  private Integer size;
  // This counter counts how many times hasNext() is called
  private Integer counter = 0;

  /**
   * Class constructor
   *
   * @param subiterators : a List of Integer Iterators
   */
  public FlattenedIterator(List<Iterator<Integer>> subiterators) {
    // Initialises the global variables
    this.subiterators = subiterators;
    this.size = subiterators.size();
  }

  /**
   * Checks if there is a next value in the FlattenedIterator
   *
   * @return boolean value of whether it has a next value
   */
  @Override
  public boolean hasNext() {
    if (this.counter >= size) {
      // Return false as hasNext() has been recursing for at least as many times as size as all
      // Iterators in the List has reached their end
      return false;
    } else if (this.subiterators.get(this.index % this.size).hasNext()) {
      // Returns true if the respective Iterator returns true for hasNext(), and resets counter
      this.counter = 0;
      return true;
    } else {
      // Recurses after incrementing counter and index, testing the Iterator next in List
      this.counter++;
      this.index++;
      return this.hasNext();
    }
  }

  /**
   * Gets the next value in the FlattenedIterator Pres: this.hasNext()
   *
   * @return next value in the FlattenedIterator
   */
  @Override
  public Integer next() {
    if (this.hasNext()) {
      // hasNext() being called to ensure the index to be at the right position, returns the next()
      // value in the correct Iterator and increments the index
      return this.subiterators.get(this.index++ % this.size).next();
    }
    // According to the precondition this will never be reached unless being used illegally
    return null;
  }
}
