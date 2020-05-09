package binarynumber;

import binarynumber.IDLList;

public class IDLListTest {

	public static void main(String[] args) {
		IDLList<Integer> list = new IDLList<Integer>();
		list.add(41);
		list.add(51);
		list.add(16);
		list.add(100);
		System.out.println(list.toString());
		
		list.append(34);
		System.out.println(list.toString());
		
		list.add(3, 56);
		System.out.println(list.toString());
		
		System.out.println(list.remove());
		System.out.println(list.toString());
		
		System.out.println(list.removeLast());
		System.out.println(list.toString());
		
		System.out.println(list.removeAt(2));
		System.out.println(list.toString());
		
		System.out.println(list.remove(16));
		System.out.println(list.toString());

	}

}