package fx.leyu.tools;

import java.util.ArrayList;

public class Heap<E> {
	
	ArrayList<E> heap;
	private int index;
	
	public Heap(){
		heap = new ArrayList<E>();
	}
	
	public void push(E c){
		heap.add(index, c);
		index++;
	}
	
	public E pop(){
		index--;
		return heap.remove(index);
	}
	
	public E top(){
		return heap.get(index-1);
	}
	
	public void print(){
		for(int i=0;i<heap.size(); i++){
			System.out.print(heap.get(i));
		}
		System.out.println();
		
	}
	
	public static void main(String[] args){

		Heap<Character> h = new Heap<Character>();
		h.push('c');
		h.push('d');
		h.push('e');
		h.print();
		h.push('f');
		System.out.println(h.top());
		h.pop();
		h.print();
		h.pop();
		h.print();
	}
}

