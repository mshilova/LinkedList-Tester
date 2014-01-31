import java.util.*;

/**
 * Filename: List12Tester.java
 * This application is testing some methods of java.util.List<E> interface
 * by extending the TestCase.
 * 
 * @date 04/09/2013
 */

public class List12Tester extends junit.framework.TestCase  {
  
  // variable for the object to be tested
  private List<Integer> myList;
  
  public static void main(String [] args)  {
    junit.textui.TestRunner.main(new String [] {"List12Tester"});
  }

  // instantiate the object under test
  // invoked before every test case 
  protected void setUp()  {
    myList = new List12<Integer>();
  }
  
  // clean up after each test case 
  protected void tearDown()  {
   myList = null;
  }
  
  public void testContainsWithNull()  {
   List<String>  myList2 = new List12<String>();
    
   myList2.add(null);
   System.out.println("after adding null: " + myList2.get(0));
   System.out.println("after adding null, the size is: " + myList2.size());
   assertTrue(myList2.contains(null));
  
  }
  /**
   * This method is testing Contains(), size() and add(E o) methods of 
   * java.util.List<E> interface. Testing to make sure size() is 0 initially
   * (pre-condition), then testing contains() works on the list with multiple
   * elements. Checking if contains() can recognize when elements are both
   * present and not present in the list.
   *
   * @see java.util.List<E>
   * @see org.junit.Assert
   * @author Mariya Shilova
   */
  public void testAddContains()  {
    
    assertEquals(myList.size(), 0); // verifying that the list is empty 
    
    // adding elements to the list
    for(int index = 0; index < 1000; index++)  {
      myList.add( new Integer(index));
      // verifying mutations
      assertTrue(myList.contains(new Integer(index)));
      assertFalse(myList.contains(new Integer(2000)));
    }
  }  

  /**
   * This method is testing get(int index) and add(E o) methods of 
   * java.util.List<E> interface.
   * Testing if the elements added to an empty list are added in the right
   * order using for-loop and get() method.  
   *
   * @see java.util.List<E>
   * @see org.junit.Assert
   * @author Mariya Shilova
   */ 
  public void testAddGet()  {
    
    assertEquals(myList.size(), 0); // verifying that the list is empty 
    // add elements to the empty list 
    for(int index = 0; index < 1000; index++)  {
      myList.add(new Integer(index));
      // verify that elements are added in order
      assertEquals(myList.get(index), new Integer(index));
    }
  }

  /**
   * This method is testing for IndexOutOfBoundsException that
   * is thrown by get() method when index is negative
   *
   * @see java.util.List<E>
   * @see java.lang.Exception
   * @author Mariya Shilova
   */ 
  public void testIndexOutOfBoundsGetNeg()  {
    try  {
      myList.get(-1);
      fail("Expected IndexOutOfBoundsException");
    }
    catch(IndexOutOfBoundsException e)  {
    } // test passed
    catch(Throwable e)  {
      fail("An unexpected exception was thrown");
    }
  }

  /**
   * This method is testing for IndexOutOfBoundsException that
   * is thrown by get() method when index greater or equal to the size 
   * of the list.
   *
   * @see java.util.List<E>
   * @see java.lang.Exception
   * @author Mariya Shilova
   */ 
  public void testIndexOutOfBoundsGetPos()  {
    try  {
      myList.get( myList.size());
      fail("Expected IndexOutOfBoundsException");
    }
    catch(IndexOutOfBoundsException e)  {
    } // test passed
    catch(Throwable e)  {
      fail("An unexpected exception was thrown");
    }
  }
  
  /**
   * This method is testing the return value of add(E o)
   * method. Specifically, true should be returned if the adding
   * of a new element was sucessfull.
   *
   * @see java.util.List<E>
   * @see org.junit.Assert
   * @author Mariya Shilova
   */ 
  public void testAddBool()  {

    assertEquals(myList.size(), 0); // verifying that the list is empty 
    // adding new elements to the list
    for(int index = 0; index < 1000; index++)  {
      // verifying return value
      assertTrue(myList.add(new Integer(index)));
    }
  }
  
  /**
   * This method is testing adding an element to the list at an index.
   * S[pecifically, checking if the new element added to the correct
   * position and the rest of the elements shifted to the right. 
   *
   * @see java.util.List<E>
   * @see org.junit.Assert
   * @author Mariya Shilova
   *
   */ 
  public void testAddWithIndexSpecified()  {

    assertEquals(myList.size(), 0); // verifying that the list is empty 
    // make a list 5 4 3 2 1 
    for (int index = 5; index > 0; index--)  {
      myList.add(new Integer(index));
    }
    // add 12 to the index 3,i new the order should be 5 4 3 12 2 1 
    myList.add(3, new Integer(12));
    // verify mutation
    assertEquals(new Integer(12), myList.get(3) );
    // verify, next element 
    assertEquals( new Integer(2), myList.get(4));
  }

  /**
   * This method is testing for IndexOutOfBoundsException thrown by
   * add(int index, E o) method when index is greater than the size
   * of the list.
   *
   * @see java.util.List<E>
   * @see java.lang.Exception
   * @author Mariya Shilova
   */ 
  public void testIndexOutOfBoundsAddPos()  {
     
    // add elements to the list
    for(int index = 0; index < 1000; index++)  {
      myList.add(new Integer(index));
    }
    try  {
      myList.add( myList.size() + 1, new Integer(12) );
      fail("Expected IndexOutOfBoundsException");
    }
    catch(IndexOutOfBoundsException e)  {
    } // test passed
    catch(Throwable e)  {
      fail("An unexpected exception was thrown");
    }
  }

  /**
   * This method is testing for IndexOutOfBoundsException thrown by
   * add(int index, E o) method when index is negative
   * of the list.
   *
   * @see java.util.List<E>
   * @see java.lang.Exception
   * @author Mariya Shilova
   */ 
  public void testIndexOutOfBoundsAddNeg()  {
    try  {
      myList.add( -1, new Integer(12) );
      fail("Expected IndexOutOfBoundsException");
    } 
    catch(IndexOutOfBoundsException e)  {
    } // test passed
    catch(Throwable e)  {
      fail("An unexpected exception was thrown");
    }
  }
  
  // test: public int size()
  /**
   * This method is testing size() method. Specifically
   * I am verifying that the initial size is zero and
   * it changes as the elements are added
   *
   * @see java.util.List<E>
   * @see org.junit.Assert
   * @author Mariya Shilova
   */ 
  public void testSizeContains()  {
    // verify that the size is 0
    assertEquals(myList.size(), 0);
    assertFalse(myList.contains(new Integer(1)));
   
    for(int index = 0; index < 1000; index++)  {
      myList.add(new Integer(index));
      // verify that the size increased by 1
      assertEquals( new Integer(myList.size()), new Integer( index + 1) );
      // verify that the list contains the new element
      assertTrue(myList.contains(new Integer(index))); 
    }
  }
  
  /**
   * This method is testing set(int index, E element method.
   * Namely, I am verifying that the new element is added to 
   * the correct position and the rest of the elements are 
   * shifted to the right and checking the return value.
   *
   * @see java.util.List<E>
   * @see org.junit.Assert
   * @author Mariya Shilova
   *
   */ 
  public void testSetGet()  {
    
    for(int index = 5; index  > 0; index--)  {
      myList.add(new Integer(index)); 
    } // making list 5 4 3 2 1
    
    // verifying the returned value

    assertEquals(myList.set(0, new Integer(12)), new Integer(5));

    // verifying that 0th element was set to new value
    assertEquals(myList.get(0), new Integer(12));

    // verify the rest of the elements are present in order
    int m = 4;
    for(int k = 1; k < 5; k++)  {
      assertEquals(myList.get(k), new Integer(m));
      m--;
    }
  }

  /**
   * This method is testing for IndexOutOfBoundsException thrown by
   * set(int index, E element) method when index is greater or 
   * equal to the size of the list.
   *
   * @see java.util.List<E>
   * @see java.lang.Exception
   * @author Mariya Shilova
   */ 
  public void testIndexOutOfBoundsSetPos()  {
   try  {
     for(int index = 0; index < 1000; index++)  {
       myList.add(new Integer(index));
     }
     myList.set(myList.size(), new Integer(1000));
     fail("Expected IndexOutOfBoundsException");
   }
   catch(IndexOutOfBoundsException e)  {
   } // test passed
    catch(Throwable e)  {
      fail("An unexpected exception was thrown");
    }
  }

  /**
   * This method is testing for IndexOutOfBoundsException thrown by
   * set(int index, E element) method when index negative 
   * equal to the size of the list.
   *
   * @see java.util.List<E>
   * @see java.lang.Exception
   * @author Mariya Shilova
   */ 
  public void testIndexOutOfBoundsSetNeg()  {
    try  {
      myList.set(-1, new Integer(12));
      fail("Expected IndexOutOfBoundsException");
    }
    catch(IndexOutOfBoundsException e)  {
    } // test passed
    catch(Throwable e)  {
      fail("An unexpected exception was thrown");
    }
  }
  
  /**
   * This method is testing remove() method that removes an object specified
   * by the parameter. Namely, I am testing if the method returns true if
   * removing of an object was successfull and verifying that the list no 
   * longer contains that elements.
   *
   * @see java.util.List<E>
   * @see org.junit.Assert
   * @author Mariya Shilova
   */ 
  public void testRemoveAnElement()  {
    // initialize the list
    for(int index = 0; index < 1000; index++)  {
      myList.add(new Integer(index));
    } 
    // traverse the list removing an element
    // check that the removing is successful
    // check that the list no longer contains the element removed 
    for(int index = 0; index < 1000; index++)  {
      assertTrue(myList.remove(new Integer(index)));
      assertFalse(myList.contains( new Integer(index)));
    }
  }

  /**
   * This method is tesing remove() method that removes an element 
   * at a specified by parameters index. 
   *
   * @see java.util.List<E>
   * @see org.junit.Assert
   * @author Mariya Shilova
   */  
  public void testRemoveAtIndexSpecified()  {
    for(int index = 5; index > 0; index--)  {
      myList.add(new Integer(index));
      assertTrue(myList.contains(index));
    } // making list 5 4 3 2 1
    assertEquals(myList.size(), 5); // verifying size  
    assertEquals(myList.remove(0), new Integer(5)); // verifying return
    assertEquals(myList.size(), 4); // verify size decreased by one
  }
  
  /**
   * This method is testing for IndexOutOfBoundsException thrown by
   * remove(int index, E element) method when index negative 
   * equal to the size of the list.
   *
   * @see java.util.List<E>
   * @see java.lang.Exception
   * @author Mariya Shilova
   */ 
  public void testIndexOutOfBoundsRemovePos()
  {
    for(int index = 0; index < 1000; index++)  {
      myList.add(new Integer(index));
    }
    try {
      myList.remove(myList.size());
      fail("Expected IndexOutOfBoundsException");
    }
    catch(IndexOutOfBoundsException e) {
    } // passed test
    catch(Throwable e)  {
      fail("An unexpected exception was thrown");
    }
  }  

  /**
   * This method is testing for IndexOutOfBoundsException thrown by
   * remove(int index, E element) method when index negative 
   * equal to the size of the list.
   *
   * @see java.util.List<E>
   * @see java.lang.Exception
   * @author Mariya Shilova
   */ 
  public void testIndexOutOfBoundsRemoveNeg()  {
    try  {
      myList.remove(-1);
      fail("Expected IndexOutOfBoundsException");
    }
    catch(IndexOutOfBoundsException e)  {
    } // passed test
    catch(Throwable e)  {
      fail("An unexpected exception was thrown");
    }
  }
  /**
   * This method is testing hasNext() method of
   * the Iterator class. Namley, I am verifying that it returns
   * false if the list is empty.
   *
   * @see java.util.List<E>
   * @see org.junit.Assert
   * @author Mariya Shilova
   */ 
  public void testIteratorHasNext1()  {
    // creating an iterator object
    Iterator<Integer> iter = myList.iterator();
    // verifying that the list has no next element
    assertFalse(iter.hasNext());
  }

  /**
   * This method is testing hasNext() method of
   * the Iterator class. Specifically, I am testing that it returns
   * true when the list has next elements.
   *
   * @see java.util.List<E>
   * @see org.junit.Assert
   * @author Mariya Shilova
   */ 
  public void testIteratorHasNext2()  {
    // adding element to the list
    myList.add(new Integer(12));
    // creating iterator object
    Iterator<Integer> iter = myList.iterator();
    // verifying the next object is present
    assertTrue(iter.hasNext());
  }

  /**
   * This method is testing next() and hasNext() method of
   * of the iterator class. Specifically, I am testing that
   * hasNext() is working during traversal of the list, and 
   * at the end of the list, and next() returns the next element
   * of the list
   *
   * @see java.util.List<E>
   * @see org.junit.Assert
   * @author Mariya Shilova
   */ 
  public void testIteratorNextHasNext()  {
    for(int index = 0; index < 1000; index++)  {
      myList.add(new Integer(index));
    }
    // create an iterator
    Iterator<Integer> iter = myList.iterator();
    // traverse the list  
    for(int index = 0; index < 999; index++)  {
      // verify element returned by next() is same as index
      assertEquals(new Integer(index), new Integer(iter.next()));   
      assertTrue(iter.hasNext()); // verify hasNext works during traversal.
    }
     iter.next(); // get the last element of the list
     assertFalse(iter.hasNext()); // verify return at the end of the list
  }

  /**
   * This method is testing remove() of the iterator class.
   * Namely, I am verifying that remove() removes the last element returned 
   * by next(). Making sure that the list is empty after removing all 
   * elements of the list.
   *
   * @see java.util.List<E>
   * @see org.junit.Assert
   * @author Mariya Shilova
   */ 
  public void testIteratorRemove1()  {
    // add elements to the list
    for(int index = 0; index < 1000; index++)  {
      myList.add(new Integer(index));
    }
    // create an iterator object
    Iterator<Integer> iter = myList.iterator();
    while(iter.hasNext())  {
      iter.next(); // get the next element
      iter.remove(); // remove the last element returned by next()
    }
    assertEquals(0, myList.size()); // verify removing was successfull
  }

  /**
   * This method is testing remove of the iterator class. Namely,
   * I am verifying that after removing the indeces of the rest 
   * of the elements are shifted to the left.
   *
   * @see java.util.List<E>
   * @see org.junit.Assert
   * @author Mariya Shilova
   */ 
  public void testIteratorRemove2()  {
    // adding elements to the list
    for(int index = 5; index > 0; index--)  {
      myList.add(new Integer(index));
    } // list is: 5 4 3 2 1
    // creating an iterator
    Iterator<Integer> iter = myList.iterator();
    iter.next();
    iter.remove(); // should remove 5
    // confirm that list does not contain 5 any longer
    assertFalse(myList.contains(5));
    // 0th element is now 4
    assertEquals(new Integer(iter.next()), new Integer(myList.get(0))); 
    
  }
  
  /**
   * This method is testing for NosuchElementException thrown by next() if
   * the list is empty.
   *
   * @see java.util.List<E>
   * @see java.lang.Exception
   * @author Mariya Shilova
   *
   */ 
  public void testExceptionIterNextEmptyList()  {

    Iterator<Integer> iter = myList.iterator();
    try  {
      iter.next();
      fail("Expected NoSucElementException");
    }
    catch(NoSuchElementException e)  {
    }
    catch(Throwable e)  {
      fail("An unexpected exception was thrown");
    }
  }

  /**
   * This method is testing for NosuchElementException thrown by next() at
   * the end of the list.
   *
   * @see java.util.List<E>
   * @see java.lang.Exception
   * @author Mariya Shilova
   */ 
  public void testExceptionIterNonEmpty()  {
    // add 0 to the list
    myList.add(new Integer(0));
    // create an iterator
    Iterator<Integer> iter = myList.iterator();
    try  {
      iter.next();
      iter.next();
      fail("Expected NoSuchElementException");
    }
    catch(NoSuchElementException e)  {
    } // test passed
    catch(Throwable e)  {
      fail("An unexpected exception was thrown");
    }
  }
  
  /**
   * This method is testing for the IllegalStateException thrown by remove()
   * if it was called before calling next() on a non-empty list 
   *
   * @see java.util.List<E>
   * @see java.lang.Exception
   * @author Mariya Shilova
   */ 
  public void testExceptionIterRemove()  {
    // add 0 to the list
    myList.add(new Integer(0));
    // create an iterator
    Iterator<Integer> iter = myList.iterator();
    try  {
      iter.remove();
      fail("Expected IllegalStateException");
    }
    catch(IllegalStateException e)  { 
    } // test passed 
    catch(Throwable e)  {
      fail("An unexpected exception was thrown");
    }
  }

  /**
   * This method is testing for the IllegalStateException thrown by remove()
   * if it was called twice after calling next() on a non-empty list 
   *
   * @see java.util.List<E>
   * @see java.lang.Exception
   * @author Mariya Shilova
   */ 
  public void testExceptionIterRemove1()  {
    // add 0 to the list
    myList.add(new Integer(0));
    // create an iterator
    Iterator<Integer> iter = myList.iterator();
    try {
      iter.next();
      iter.remove();
      assertFalse(myList.contains(0));
      iter.remove();
      fail("Expected IllegalStateException");
    }
    catch(IllegalStateException e)  {
    } // test passed
    catch(Throwable e)  {
      fail("An unexpected exception was thrown");
    }
  }

  /**
   * This method is testing for the IllegalStateException thrown by remove()
   * if it was called on an empty list. 
   *
   * @see java.util.List<E>
   * @see java.lang.Exception
   * @author Mariya Shilova
   */ 
  public void testExceptionIterRemove2()  {
   // create an iterator
   Iterator<Integer> iter = myList.iterator();
   try  {
     iter.remove();
     fail("Expected IllegalStateException");
   }
   catch(IllegalStateException e)  {
   } // test passed 
   catch(Throwable e)  {
     fail();
   }
  }
}
