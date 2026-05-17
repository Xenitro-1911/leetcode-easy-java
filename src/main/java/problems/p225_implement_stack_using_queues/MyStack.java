package problems.p225_implement_stack_using_queues;

import java.util.ArrayDeque;
import java.util.Queue;

    class MyStack {
        Queue<Integer> q = new ArrayDeque<>();

        public MyStack() {
        }

        public void push(int x) {
            q.add(x);
            int size = q.size();
            for(int i=0;i<size-1;i++){
                q.offer(q.poll());
            }
        }

        public int pop() {
            return q.poll();
        }

        public int top() {
            return q.peek();
        }

        public boolean empty() {
            return q.isEmpty();
        }
    }