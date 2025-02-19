package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TqsStackTest {
    private TqsStack<Integer> tqsStack;
    
    @BeforeEach
    public void setup() {
    
        tqsStack = new TqsStack<>();
    
    }
// A stack is empty on construction
    @Test
    void emptyTest(){
        assertTrue(tqsStack.isEmpty());
    }


// A stack has size 0 on construction.

    @Test
    void sizeTest(){
        assertEquals(0,tqsStack.size());
    }


// After n pushes to an empty stack, n > 0, the stack is
//   not empty and its size is n

    @Test
        void npushes(){
            assertEquals(0,tqsStack.size());
            tqsStack.push(1);
            tqsStack.push(2);
            tqsStack.push(3);
            assertEquals(3,tqsStack.size());
            assertFalse(tqsStack.isEmpty());
        }

// If one pushes x then pops, the value popped is x.


    @Test
    void xpopped(){
        tqsStack.push(1);
        assertEquals(1, tqsStack.pop());
    }

//  If one pushes x then peeks, the value returned is x, 
//  but the size stays the same
    

    @Test
    void popped(){
        tqsStack.push(1);
        assertEquals(1, tqsStack.peek());
        assertEquals(1,tqsStack.size());
    }

//If the size is n, then after n pops, the stack is empty 
//   and has a size 0

    @Test
    void after_n_pops(){
        tqsStack.push(1);
        tqsStack.push(2);
        tqsStack.push(3);
        assertEquals(3,tqsStack.size());
        tqsStack.pop();
        tqsStack.pop();
        tqsStack.pop();
        assertEquals(0,tqsStack.size());
        assertTrue(tqsStack.isEmpty());

    }

  //  Popping from an empty stack does throw a 
  //   NoSuchElementException [You should test for the Exception occurrence]


    @Test
    void error_empty(){
        assertTrue(tqsStack.isEmpty());
        assertThrows(NoSuchElementException.class, () -> tqsStack.pop());
    }

//Peeking into an empty stack does throw a NoSuchElementException

    @Test
    void error_peek(){
        assertTrue(tqsStack.isEmpty());
        assertThrows(NoSuchElementException.class, () -> tqsStack.peek());
    }


//For bounded stacks only: pushing onto a full stack does throw an IllegalStateException

    @Test
    void full_stack(){
        tqsStack.push(1);
        tqsStack.push(2);
        tqsStack.push(3);
        tqsStack.push(4);
        tqsStack.push(5);
        assertThrows(IllegalStateException.class, () -> tqsStack.push(6));

    }

    @Test
    void testpopTopN(){
        tqsStack.push(1);
        tqsStack.push(2);
        tqsStack.push(3);
        tqsStack.push(4);
    
    int result = tqsStack.popTopN(3);
    assertEquals(3, result);
    assertEquals(4, tqsStack.pop());
    assertThrows(IllegalArgumentException.class, () -> tqsStack.popTopN(0), "Should throw exception for n = 0");
    assertThrows(IllegalArgumentException.class, () -> tqsStack.popTopN(5), "Should throw exception if n > stack size");

    }
}
