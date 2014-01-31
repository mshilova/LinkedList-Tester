import java.util.*;
/**
 * Outer class: List12<E>
 * Implements List<E> interface using a singly-linked list.
 * Using a nested Node<T> class and an inner Iterator class.
 * This class overrides nine methods of the List<E> interface.
 *
 * @date 04/26/2013
 */ 

public class List12<E> implements java.util.List<E>  {

  private Node<E> head; // pointer to first element
  private int size;    // number of nodes int this list
  
  /**
   * private static nested class: Node<T>
   */ 
   private static class Node<T> {
      private T data;  // data field
      private Node<T> next;  // link to successor

      private Node(T data)   { // inner class constructor
         this.data = data;
      }
   }
   /**
    * private inner class: L12iter
    */ 
   private class L12iter implements java.util.Iterator<E>  {
     // fields
     private Node<E> nextToVisit; // Node that should be returned by next()
     private Node<E> lastReturned; // Node that should be deleted by remove()
     private Node<E> prevNode; // the predecessor of lastReturned;
                               // should assist with implementing remove()
     /**
      * The inner class constructor.
      */ 
     L12iter()  {
       // initializing fields
       this.prevNode = null;           
       this.lastReturned = null;
       this.nextToVisit = head;
     }

     /**
      * Overriden method of the Iterator interface. Returns the next
      * element in the iteration.
      *
      * @return next element in the iteration
      * @throw NoSuchElementException if no elements in iteration.
      * @see java.lang.Exception
      * @see java.util.Iterator
      */ 
     public E next()  {
       if(nextToVisit == null)  {
         throw new NoSuchElementException();
       }
       prevNode = lastReturned; // update predecessor pointer
       lastReturned = nextToVisit; // update last returned pointer
       // if there is next element, update next to visit pointer
       if(nextToVisit.next != null)  {
         nextToVisit = nextToVisit.next;
         } else  { nextToVisit = null; }
       return lastReturned.data;
     }
     
     /**
      * Overriden method of Iterator interface. Returns true if the iterator 
      * has more elemetns, false if no element or if next() threw exception 
      *
      * @return true or false whether there is next element or not.  
      * @see java.util.Iterator
      */  
     public boolean hasNext()  {
       return nextToVisit != null;
     }

     /**
      * Overriden method of the Iterator interface.
      * Removes from the list the last element returned by the next().
      * Only one call per next.
      *
      * @throw IllegalStateException if next() has not been called 
      * right before remove() call or if remove() has already been called 
      * after the last next() call.
      * @see java.lang.Eception
      * @see java.util.Iterator
      */ 
     public void remove()  {
        if(lastReturned == null)  {
           throw new IllegalStateException();
        }
        /* splice around lastReturned */
        //nextToVisit = lastReturned.next;
        if(prevNode != null) {
          prevNode.next = nextToVisit;
        } else {
            head = nextToVisit;
          }
        // if prevNode is null this means that we are removing
        // head, so we should set the next element to be the head
        
        lastReturned = null;
        size--;
     }
   }
   
   /**
    * Outer class constructor.
    * Default: head is null and size is 0
    */ 
   public List12 ()  {  
     head = null;  
     size = 0;
   }


   /**
    * Overriden method. Adds an element passed to the end of the list
    * and returns true indicating that adding was successful.
    * The size increases by 1. 
    * 
    * @return true indicating successful add
    * @param new element to add.
    * @see java.util.List<E>
    */ 
   public boolean add(E o)  {
     // reuse add(int index, E element) method
     // append element to the end of the list 
     this.add(this.size, o);
     return true;
   }

   /**
    * Overriden method. Adds an element passed at specified index.
    * The size of the list increases by 1 and the indeces of the 
    * subsequent elements are shifted to the right.
    *
    * @param index where to add the new element and the new element
    * @throws IndexOutOfBoundsException if index < 0 || index > size
    * @see java.lang.Exception
    * @see java.util.List<E>
    */ 
   public void add(int index, E element)  {
     // check index limits
     if(index < 0 || index > this.size)  {
       throw new IndexOutOfBoundsException();
     } else {
         // create new Node
         Node<E> newNode = new Node<E>(element);
         // if head is null, set it to the new Node
         if(this.size == 0)  {
           head = newNode; 
         } else {
             if(index == 0)  {
               // newNode should point to the current head
               newNode.next = head;
               // set head to be the newNode
               head = newNode;
             } else  {
                 // initialize current to head
                 Node<E> current = head;
                 // loop to find (index - 1)th position
                 for(int i = 0; i < index - 1; i++) {
                   current = current.next;
                 }
                   // set newNode's successor to the current's successor
                   newNode.next = current.next;
                   // set current's successor to the newNode 
                   current.next = newNode;
               }
           }
        // increment the size of the list
        size++;
     }
   }
 
   /**
    * Overriden method. Checks if the object passed is in the list.
    * Returns if the object is present and false if not.
    *
    * @return true or false whether the object is present or not
    * @see java.util.List<E>
    */ 
   public boolean contains(Object o)  {
     // if the list is empty, it does not contain o
     // thus return false
     if(this.size == 0)  { 
       return false;
     }  
     // initialize current to the head
     Node<E> current = head;
     int i = 0; 
     // loop until the end of the list
     while(i < this.size)  {
       //  if the node is null and the object is null
       //  then return false
       if(current.data == null && o ==null)   {
           return true;
       }
       // compare data with the object passed
       else if(current.data.equals(o))  {
           return true;
       }
       // move the pointer ehead.
       current = current.next; 
       i++;
     } 
      return false; 
   }
  
   /**
    * Overriden method. Gets an element at index specified and
    * returns it. 
    * 
    * @return the element obtained
    * @param index of the element to be obtained
    * 
    * @throws IndexOutOfBoundsException if index < 0 || >= size
    * @see java.lang.Exception
    * @see java.util.List<E>
    */ 
   public E get(int index)  {
     // check index limits
     if(index < 0 || index >= this.size)  {
       throw new IndexOutOfBoundsException();
     } else {
         if(index == 0)  {
           return head.data;
         } else  {
             // initialize current to the head
             Node<E> current = head; 
             // loop to get to the given element
             for(int i = 0; i < index - 1; i++)  {
               current = current.next;
             }
             return current.next.data;
           }
       }
   }

   /**
    * Overriden method. Creates and returns an Iterator object.
    *
    * @return iterator object for the list
    * @see java.util.List<E>
    */ 
   public Iterator<E> iterator()  {
     return new L12iter();
   }

   /**
    * Overriden method. Removes the first instance of the object item 
    * from the calling list; if present returns true, false if not.
    * If object found and removed, the size decreases and the indeces
    * of the subsequent elements are shifted to the left.
    *
    * @return true or false depending if the object is present 
    * @param object which should be removed if present
    * @see java.util.List<E>
    */ 
   public boolean remove(Object o)  {
     // if list is empty, return false
     if(this.size == 0)  {
       return false;
     } else  {
         // initialize current to head
         Node<E> current = head;
         // default item is not present
         boolean itemPresent = false; 
         if(current.data.equals(o))  {
           // if head matches the object and its the only element, set it to null
           if(this.size == 1)  {
             head = null;
             size--;
             itemPresent = true; 
           } else  {
               Node<E> target = head; // target to be removed
               head = target.next; // set head to be its successor
               size--;
               itemPresent = true;
             }
         }
         while(itemPresent != true && current.next != null)  {
           Node<E> target; // node to be removed
           if(current.data.equals(o))  {
             target = current;
             current = target.next;
             size--;
             itemPresent = true;
           }
         }
         return itemPresent;
       }
   }

   /**
    * Overriden method. Removes an element at index specified and
    * returns the element that has been removed. The size is decreased
    * and the indeces of subsequent elements are shifted to the left.
    * 
    * @return the element removed
    * @param index of the element to be removed
    * @throws IndexOutOfBoundsException if index < 0 || >= size
    * @see java.util.List<E>
    * @see java.lang.Exception
    */ 
   public E remove(int index)  {
     // reference to data to return
     E element;
     // check index limits
     if(index < 0 || index >= this.size)  {
       throw new IndexOutOfBoundsException();
     } else {
         // check if the index is 0
         // then need to remove and reset the head of the list
         if(index == 0)  {
           // if head is the only node, then set it to null
           if(this.size == 1)  {
             element = head.data;
             head = null;
             size--;
            } else {
                // if head is not the only node, then set it
                // to its successor and save the data for return
                element = head.data;
                head = head.next;
                size--;
              }
         } else  {
             Node<E> current = head;
             for(int i = 0; i < index; i++)  {
             current = current.next;
           }  
           // set target for removigng to the current node
           Node<E> target = current;
           // save target's data for returning
           element = target.data;
           current = target.next;
           size--;
          }
       }
     return element;
   }

   /**
    * Overriden method. Replaces the element at the specified 
    * position with the element passed, returning the old element.
    *
    * @return the element that has been replaces
    * @param index of the element that should be replaced and 
    * the new element.
    * @see java.util.List<E>
    * @see java.lang.Exception
    */ 
   public E set(int index, E element)  {
     E oldElement;
     // 
     Node<E> newNode = new Node<E>(element);
     // check index limits
     if(index < 0 || index >= this.size)  {
       throw new IndexOutOfBoundsException();
     } else {
        if(index == 0) {
          // replace the head with the new element
          // save data for old element
          oldElement = head.data;
          if(this.size > 1)
            newNode.next = head.next;
          head = newNode;
        } else {
            Node<E> current = head;
            for(int i = 0; i < index; i++) {
              current = current.next;
            }
            oldElement = current.data;
            newNode.next = current.next;
            current = newNode;
          }
        return oldElement;
       }
   }

   /**
    * Overriden method, returns the size if the list.
    *
    * @return size of the list
    * @see java.util.List<E>
    */ 
   public int size()  {
     return this.size;
   }
   
   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public boolean addAll(Collection<? extends E> c)  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public boolean addAll(int index, Collection<? extends E> c)  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public void clear()  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public boolean containsAll(Collection<?> c)  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public boolean equals(Object o)  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public int hashCode()  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public int indexOf(Object o)  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public boolean isEmpty()  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public int lastIndexOf(Object o)  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public ListIterator<E> listIterator()  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public ListIterator<E> listIterator(int index)  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public boolean removeAll(Collection<?> c)  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public boolean retainAll(Collection<?> c)  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public List<E> subList(int fromIndex, int toIndex)  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public Object[] toArray()  {
     throw new UnsupportedOperationException();
   }

   /**
    * This method is not supported.
    * @throws UnsupportedOperationException.
    * @see java.lang.Exception
    */ 
   public <T> T[] toArray(T[] a) {
     throw new UnsupportedOperationException();
   }
}
