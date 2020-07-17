import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlattenedIteratorTest {
  @Test
  public void hiddenTest1() {
    var A = java.util.List.<Integer>of().iterator();
    var B = java.util.List.<Integer>of().iterator();
    var it = new FlattenedIterator(java.util.List.of(A, B));
    assertFalse(it.hasNext());
  }

  @Test
  public void hiddenTest2() {
    var A = java.util.List.<Integer>of(11, 0).iterator();
    var it = new FlattenedIterator(java.util.List.of(A));
    assertTrue(it.hasNext() && it.next() == 11 && it.hasNext() && it.next() == 0 && !it.hasNext());
  }

  @Test
  public void hiddenTest3() {
    var A = java.util.List.<Integer>of(8).iterator();
    var B = java.util.List.<Integer>of(3).iterator();
    var it = new FlattenedIterator(java.util.List.of(A, B));
    assertTrue(it.hasNext() && it.next() == 8 && it.hasNext() && it.next() == 3 && !it.hasNext());
  }

  @Test
  public void hiddenTest4() {
    var A = java.util.List.<Integer>of(11, 44).iterator();
    var B = java.util.List.<Integer>of(22).iterator();
    var C = java.util.List.<Integer>of().iterator();
    var D = java.util.List.<Integer>of(33, 55, 66).iterator();
    var it = new FlattenedIterator(java.util.List.of(A, B, C, D));
    assertTrue(
        it.hasNext()
            && it.next() == 11
            && it.hasNext()
            && it.next() == 22
            && it.hasNext()
            && it.next() == 33
            && it.hasNext()
            && it.next() == 44
            && it.hasNext()
            && it.next() == 55
            && it.hasNext()
            && it.next() == 66
            && !it.hasNext());
  }

  @Test
  public void hiddenTest5() {
    var A = java.util.List.<Integer>of(-8, 2, 5).iterator();
    var B = java.util.List.<Integer>of(7, 4).iterator();
    var C = java.util.List.<Integer>of(1, -9, -2).iterator();
    var it = new FlattenedIterator(java.util.List.of(A, B, C));
    assertTrue(
        it.hasNext()
            && it.next() == -8
            && it.hasNext()
            && it.next() == 7
            && it.hasNext()
            && it.next() == 1
            && it.hasNext()
            && it.next() == 2
            && it.hasNext()
            && it.next() == 4
            && it.hasNext()
            && it.next() == -9
            && it.hasNext()
            && it.next() == 5
            && it.hasNext()
            && it.next() == -2
            && !it.hasNext());
  }

  @Test
  public void hiddenTest6() {
    var A = java.util.List.<Integer>of(1).iterator();
    var B = java.util.List.<Integer>of(2, 2).iterator();
    var C = java.util.List.<Integer>of(3, 3, 3).iterator();
    var D = java.util.List.<Integer>of(4, 4, 4, 4).iterator();
    var it = new FlattenedIterator(java.util.List.of(A, B, C, D));
    assertTrue(
        it.hasNext()
            && it.next() == 1
            && it.hasNext()
            && it.next() == 2
            && it.hasNext()
            && it.next() == 3
            && it.hasNext()
            && it.next() == 4
            && it.hasNext()
            && it.next() == 2
            && it.hasNext()
            && it.next() == 3
            && it.hasNext()
            && it.next() == 4
            && it.hasNext()
            && it.next() == 3
            && it.hasNext()
            && it.next() == 4
            && it.hasNext()
            && it.next() == 4
            && !it.hasNext());
  }

  @Test
  public void hiddenTest7() {
    var rand = new java.util.Random();
    var subits1 = new java.util.ArrayList<java.util.Iterator<Integer>>();
    var subits2 = new java.util.ArrayList<java.util.Iterator<Integer>>();
    int num_subits = 10 + rand.nextInt(10);
    // Generate two copies of random iterators (same seed in corresponding indices).
    for (int i = 0; i < num_subits; ++i) {
      long seed = rand.nextLong();
      int num_elems = rand.nextInt(100);
      subits1.add(
          new java.util.Iterator<Integer>() {
            private java.util.Random mRand = new java.util.Random(seed);
            private int mRemaining = num_elems;

            @Override
            public boolean hasNext() {
              return mRemaining > 0;
            }

            @Override
            public Integer next() {
              if (hasNext()) {
                mRemaining--;
                return mRand.nextInt();
              } else {
                throw new java.util.NoSuchElementException();
              }
            }
          });
      subits2.add(
          new java.util.Iterator<Integer>() {
            private java.util.Random mRand = new java.util.Random(seed);
            private int mRemaining = num_elems;

            @Override
            public boolean hasNext() {
              return mRemaining > 0;
            }

            @Override
            public Integer next() {
              if (hasNext()) {
                mRemaining--;
                return mRand.nextInt();
              } else {
                throw new java.util.NoSuchElementException();
              }
            }
          });
    }
    // Create FlattenedIterator from our solution.
    var it1_queue = new java.util.LinkedList<java.util.Iterator<Integer>>();
    for (java.util.Iterator<Integer> it : subits1) {
      if (it.hasNext()) {
        it1_queue.add(it);
      }
    }
    var it1 =
        new java.util.Iterator<Integer>() {
          private java.util.Queue<java.util.Iterator<Integer>> mIteratorQueue = it1_queue;

          @Override
          public boolean hasNext() {
            return !mIteratorQueue.isEmpty();
          }

          @Override
          public Integer next() {
            if (hasNext()) {
              var iterator = mIteratorQueue.remove();
              Integer value = iterator.next();
              if (iterator.hasNext()) {
                mIteratorQueue.add(iterator);
              }
              return value;
            } else {
              throw new java.util.NoSuchElementException();
            }
          }
        };
    // Instantiate candidate's solution
    var it2 = new FlattenedIterator(subits2);
    boolean has1 = it1.hasNext();
    boolean has2 = it2.hasNext();
    assertEquals(has1, has2);
    if (has1) {
      int val1 = it1.next();
      int val2 = it2.next();
      assertEquals(val1, val2);
    } else {
      assert true;
    }
  }

  @Test
  public void hiddenTest8() {
    var A = java.util.List.<Integer>of().iterator();
    var B = java.util.List.<Integer>of(3, 2).iterator();
    var it = new FlattenedIterator(java.util.List.of(A, B));
    assertTrue(it.hasNext() && it.next() == 3 && it.hasNext() && it.next() == 2 && !it.hasNext());
  }

  @Test
  public void hiddenTest9() {
    var A = java.util.List.<Integer>of(1, 5).iterator();
    var B = java.util.List.<Integer>of().iterator();
    var it = new FlattenedIterator(java.util.List.of(A, B));
    assertTrue(it.hasNext() && it.next() == 1 && it.hasNext() && it.next() == 5 && !it.hasNext());
  }

  @Test
  public void visibleTest1() {
    var A = java.util.List.<Integer>of(1, 2, 3).iterator();
    var B = java.util.List.<Integer>of(4, 5).iterator();
    var C = java.util.List.<Integer>of(6, 7, 8).iterator();
    var it = new FlattenedIterator(java.util.List.of(A, B, C));
    assertTrue(
        it.hasNext()
            && it.next() == 1
            && it.hasNext()
            && it.next() == 4
            && it.hasNext()
            && it.next() == 6
            && it.hasNext()
            && it.next() == 2
            && it.hasNext()
            && it.next() == 5
            && it.hasNext()
            && it.next() == 7
            && it.hasNext()
            && it.next() == 3
            && it.hasNext()
            && it.next() == 8
            && !it.hasNext());
  }

  @Test
  public void visibleTest2() {
    var it = new FlattenedIterator(java.util.List.of());
    assertFalse(it.hasNext());
  }
}
