package problems.p232_implement_queue_using_stacks;

import java.util.Stack;

class MyQueue {
    Stack<Integer> s = new Stack<>();
    Stack<Integer> s2 = new Stack<>();
    public MyQueue() {

    }

    public void push(int x) {
        s.push(x);
    }

    public int pop() {
        if(s2.isEmpty()) {
            while (!s.isEmpty()) {
                s2.push(s.pop());
            }
        }
        return s2.pop();
    }

    public int peek() {
        if(s2.isEmpty()) {
            while (!s.isEmpty()) {
                s2.push(s.pop());
            }
        }
        return s2.peek();
    }

    public boolean empty() {
        return s.isEmpty() && s2.isEmpty();
    }
}
