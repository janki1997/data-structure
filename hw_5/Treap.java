package Tree;

import java.util.Random;

import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	/**
	 * Node Class
	 * @param <E>
	 */

	private class Node<E> {
		public E data; 
		public int priority; 
		public Node<E> left;
		public Node<E> right;

		public Node(E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException();
			} else {
				this.data = data;
				this.priority = priority;
				this.left = null;
				this.right = null;
			}
		}

		/**
		 *  Right rotation method
		 * @return
		 */

		Node<E> rotateRight() {
			Node<E> l1 = this.left;
			Node<E> l2 = l1.right;
			l1.right = this;
			this.left = l2;
			return l1;
		}

		/**
		 *  Left rotation method
		 * @return
		 */

		Node<E> rotateLeft() {
			Node<E> r1 = this.right;
			Node<E> r2 = r1.left;
			r1.left = this;
			this.right = r2;
			return r1;
		}

	}

	private Random priorityGenerator;
	private Node<E> root;

	/**
	 * constuctor
	 */
	public Treap() {
		root = null;
		priorityGenerator = new Random();
	}

	/**
	 * constructor
	 * @param seed
	 */
	public Treap(long seed) {
		root = null;
		priorityGenerator = new Random(seed);
	}

	/**
	 * @param key
	 * @return
	 */
	boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}

	/**
	 * add the node 
	 * @param key
	 * @param priority
	 */
	boolean add(E key, int priority) {
		Stack<Node<E>> s1 = new Stack<Node<E>>();
		if (root == null) {
			root = new Node<E>(key, priority);
			return true;
		} else {
			Node<E> c1 = root;
			while (c1 != null) {
				s1.push(c1);
				if (key.compareTo(c1.data) < 0) {
					if (c1.left == null) {
						Node<E> n1 = new Node<E>(key, priority);
						c1.left = n1;
						reheap(n1, s1);
						return true;
					} else {
						c1 = c1.left;
					}
				} else if (key.compareTo(c1.data) == 0) {
					return false;
				} else {
					if (c1.right == null) {
						Node<E> n1 = new Node<E>(key, priority);
						c1.right = n1;
						reheap(n1, s1);
						return true;
					} else {
						c1 = c1.right;
					}
					
				}
			}

		}
		return false;
	}

	/** 
	 * priority queue method
	 * @param n1
	 * @param s1
	 */
	public void reheap(Node<E> n1, Stack<Node<E>  >   s1) {
		while (!s1.isEmpty()) {
			Node<E> p1 = s1.pop();
			if (p1.priority < n1.priority) {

				if (n1.data.compareTo(p1.data) < 0) 
				{
					n1 = p1.rotateRight();
					if (s1.isEmpty()) {
						root = n1;
					} else {
						Node<E> t1 = s1.peek();
						if (n1.data.compareTo(t1.data) < 0) {
							t1.left = n1;
						} else {
							t1.right = n1;
						}
					}
				} else {
					n1 = p1.rotateLeft();
					if (!s1.isEmpty()) {
						Node<E> t1 = s1.peek();
						if (n1.data.compareTo(t1.data) > 0) {
							t1.right = n1;
						} else {
							t1.left = n1;
						}						
					} else {
						root = n1;
					}
				}
			}
		}

	}

	/**
	 * @param root
	 * @param key
	 * @return
	 */
	private boolean find(Node<E> root, E key) {
		Node<E> c1 = root;
		if (c1 == null   ||   key == null)
			return false;
		if (c1.data.compareTo(key) == 0)
			return true;
		if (key.compareTo(c1.data)   >   0)
			return find(c1.right, key);
		else
			return find(c1.left, key);
	}

	/**
	 * for finding node 
	 * @param key
	 * @return
	 */
	boolean find(E key) {
		boolean result = find(root, key);
		return result;
	}

	boolean delete(E key) {
		if (key == null || find(key) == false)
			return false;
		else {
			root = delete(key, root);
			return true;
		}
	}

	/**
	 * for delete the perticular node in the tree
	 * @param key
	 * @param root
	 * @return
	 */
	private Node<E> delete(E key, Node<E> root) {
		if (root == null)
			return null;
		else {

			if (key.compareTo(root.data) == 0) {
				if (root.right == null && root.left != null) {
					root = root.rotateRight();
					root.right = delete(key, root.right);
				} else if (root.left != null && root.right != null) {
					if (root.right.priority < root.left.priority) {
						root = root.rotateRight();
						root.right = delete(key, root.right);
					} else {
						root = root.rotateLeft();
						root.left = delete(key, root.left);
					}
				} else if (root.right != null && root.left == null) {
					root = root.rotateLeft();
					root.left = delete(key, root.left);
				} else {
					root = null;
				}
			} else if (key.compareTo(root.data) > 0) {
				root.right = delete(key, root.right);
			} else {
				root.left = delete(key, root.left);
			}
		}
		return root;
	}
	
	/**
	 * 
	 * @param t
	 * @param depth
	 * @param s1
	 */
	private void toAdd(Node<E> t, int depth, StringBuilder s1) {
		for (int i = 1; i < depth; i++) {
			s1.append("  ");
		}
		if (t != null) {
			s1.append("( key = " + t.data + ", " + " Priority =" + t.priority + " ) " + "\n");
			toAdd(t.left, depth + 1, s1);
			toAdd(t.right, depth + 1, s1);
		} else {
			s1.append("null" + "\n");
		}
	}

	
	public String toString() {
		StringBuilder s1 = new StringBuilder();
		toAdd(root, 0, s1);
		return s1.toString();
	}

	public static void main(String[] args) {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		testTree.add(4, 19);
		testTree.add(6, 70);
		testTree.add(1, 84);
		System.out.println(testTree.toString() + "\n");
//		System.out.println(testTree.find(9));
		System.out.println("find : " + testTree.find(2));
		System.out.println("\n");
		testTree.add(2, 31);
		System.out.println(testTree.toString() + "\n");
		System.out.println("delete : " + testTree.delete(1));
		System.out.println("\n");
		System.out.println(testTree.toString());
	}
}
