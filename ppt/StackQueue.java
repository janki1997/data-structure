package cs570;

import java.util.*;

public class StackQueue<E> {
	
	private Stack<E> left = new Stack<E>();
	private Stack<E> right = new Stack<E>();
	private int size=0;

	/**
     * pours elements from full stack into empty stack
     */
    private void swap() {
        if(this.right.isEmpty())
            while (!this.left.isEmpty())
                this.right.push(this.left.pop());
        else
            while(!this.right.isEmpty())
                this.left.push(this.right.pop());
    }
    
	public boolean offer(E data) {
        left.push(data);
        this.size++;
        return true;
    }

    /**
     * removes first element in queue
     * @return first element in queue
     */
    public E poll() {
        if(this.size == 0)
            return null;
        this.swap();
        E temp = this.right.pop();
        this.size--;
        this.swap();
        return temp;
    }

    /**
     *  Peek implementation for queue
     * @return the <code>element</code> at the front of the line
     */
    public E peek() {
        if(this.size == 0)
            return null;
        this.swap();
        E temp = this.right.peek();
        this.swap();
        return temp;
    }

	public static void main(String[] args) {
		QueueExamples<Integer> q = new QueueExamples<Integer>();
		q.offer(5);
		q.offer(6);
		q.offer(7);
		q.offer(8);
		q.offer(9);

		System.out.println(q.poll());

		q.offer(10);

		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());

	}
}
