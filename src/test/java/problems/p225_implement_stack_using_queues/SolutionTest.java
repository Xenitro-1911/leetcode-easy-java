package problems.p225_implement_stack_using_queues;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private MyStack stack;  // not Solution.MyStack;

    @BeforeEach
    void setUp() {
        stack = new MyStack();
    }

    // --- Empty stack ---

    @Test
    void testNewStack_isEmpty() {
        assertTrue(stack.empty());
    }

    @Test
    void testAfterPush_notEmpty() {
        stack.push(1);
        assertFalse(stack.empty());
    }

    @Test
    void testAfterPushAndPop_isEmpty() {
        stack.push(1);
        stack.pop();
        assertTrue(stack.empty());
    }

    // --- Single element ---

    @Test
    void testPushOne_topReturnsIt() {
        stack.push(42);
        assertEquals(42, stack.top());
    }

    @Test
    void testPushOne_popReturnsIt() {
        stack.push(42);
        assertEquals(42, stack.pop());
    }

    // --- LIFO order ---

    @Test
    void testPushThree_topIsLast() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.top());
    }

    @Test
    void testPushThree_popInLIFOOrder() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void testInterleavedPushPop_LIFOOrder() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());
    }

    // --- Top does not remove ---

    @Test
    void testTop_doesNotRemoveElement() {
        stack.push(5);
        stack.top();
        assertFalse(stack.empty());
        assertEquals(5, stack.top());
    }

    @Test
    void testTopCalledMultipleTimes_sameResult() {
        stack.push(7);
        stack.push(9);
        assertEquals(9, stack.top());
        assertEquals(9, stack.top());
        assertEquals(9, stack.top());
    }

    // --- Rotation correctness ---

    @Test
    void testPushFive_correctOrder() {
        for (int i = 1; i <= 5; i++) stack.push(i);
        for (int i = 5; i >= 1; i--) {
            assertEquals(i, stack.pop());
        }
    }

    @Test
    void testPushAfterPop_correctTop() {
        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.push(3);
        assertEquals(3, stack.top());
    }

    // --- Negative and boundary values ---

    @Test
    void testNegativeValues_LIFOOrder() {
        stack.push(-1);
        stack.push(-2);
        stack.push(-3);
        assertEquals(-3, stack.pop());
        assertEquals(-2, stack.pop());
        assertEquals(-1, stack.pop());
    }

    @Test
    void testIntMinAndMax_correctOrder() {
        stack.push(Integer.MIN_VALUE);
        stack.push(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, stack.pop());
        assertEquals(Integer.MIN_VALUE, stack.pop());
    }

    @Test
    void testEmptyAfterAllPopped() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue(stack.empty());
    }
}