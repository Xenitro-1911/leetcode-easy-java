package problems.p232_implement_queue_using_stacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private MyQueue queue;

    @BeforeEach
    void setUp() {
        queue = new MyQueue();
    }

    // --- Empty queue ---

    @Test
    void testNewQueue_isEmpty() {
        assertTrue(queue.empty());
    }

    @Test
    void testAfterPush_notEmpty() {
        queue.push(1);
        assertFalse(queue.empty());
    }

    @Test
    void testAfterPushAndPop_isEmpty() {
        queue.push(1);
        queue.pop();
        assertTrue(queue.empty());
    }

    // --- Single element ---

    @Test
    void testPushOne_peekReturnsIt() {
        queue.push(42);
        assertEquals(42, queue.peek());
    }

    @Test
    void testPushOne_popReturnsIt() {
        queue.push(42);
        assertEquals(42, queue.pop());
    }

    // --- FIFO order ---

    @Test
    void testPushThree_popInFIFOOrder() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertEquals(1, queue.pop());
        assertEquals(2, queue.pop());
        assertEquals(3, queue.pop());
    }

    @Test
    void testPushThree_peekIsFirst() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertEquals(1, queue.peek());
    }

    // --- Interleaved push/pop (core lazy transfer test) ---

    @Test
    void testInterleavedPushPop_FIFOOrder() {
        queue.push(1);
        queue.push(2);
        assertEquals(1, queue.pop());
        queue.push(3);           // pushed after first transfer
        assertEquals(2, queue.pop());
        assertEquals(3, queue.pop());
    }

    @Test
    void testPushAfterFullDrain_correctOrder() {
        queue.push(1);
        queue.push(2);
        queue.pop();
        queue.pop();             // s2 fully drained
        queue.push(3);
        queue.push(4);
        assertEquals(3, queue.pop());
        assertEquals(4, queue.pop());
    }

    @Test
    void testMultipleTransfers_correctOrder() {
        // Forces multiple lazy transfers
        queue.push(1);
        assertEquals(1, queue.pop());   // first transfer
        queue.push(2);
        assertEquals(2, queue.pop());   // second transfer
        queue.push(3);
        assertEquals(3, queue.pop());   // third transfer
    }

    // --- Peek does not remove ---

    @Test
    void testPeek_doesNotRemoveElement() {
        queue.push(5);
        queue.peek();
        assertFalse(queue.empty());
        assertEquals(5, queue.peek());
    }

    @Test
    void testPeekCalledMultipleTimes_sameResult() {
        queue.push(7);
        queue.push(9);
        assertEquals(7, queue.peek());
        assertEquals(7, queue.peek());
        assertEquals(7, queue.peek());
    }

    // --- Negative and boundary values ---

    @Test
    void testNegativeValues_FIFOOrder() {
        queue.push(-3);
        queue.push(-2);
        queue.push(-1);
        assertEquals(-3, queue.pop());
        assertEquals(-2, queue.pop());
        assertEquals(-1, queue.pop());
    }

    @Test
    void testIntMinAndMax_correctOrder() {
        queue.push(Integer.MIN_VALUE);
        queue.push(Integer.MAX_VALUE);
        assertEquals(Integer.MIN_VALUE, queue.pop());
        assertEquals(Integer.MAX_VALUE, queue.pop());
    }

    @Test
    void testEmptyAfterAllPopped() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.pop();
        queue.pop();
        queue.pop();
        assertTrue(queue.empty());
    }
}