import java.util.Iterator;
import java.util.List;

public class FlattenedIterator implements Iterator<Integer> {
  List<Iterator<Integer>> subiterators;
  int index = 0;
  int size;
  int counter = 0;
  
  public FlattenedIterator(List<Iterator<Integer>> subiterators) {
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
      return false;
    } else if (this.subiterators.get(this.index % this.size).hasNext()) {
      this.counter = 0;
      return true;
    } else {
      this.counter++;
      this.index++;
      return this.hasNext();
    }
  }
  
  /**
   * Gets the next value in the FlattenedIterator Pres: hasNext()
   *
   * @return next value in the FlattenedIterator
   */
  @Override
  public Integer next() {
    if (this.hasNext()) {
      return this.subiterators.get(this.index++ % this.size).next();
    }
    return 0;
  }
}
