package Airbnb;
import java.util.*;
public class FixedSizeQueue {
	int capacity;
	ListNode headBlock;
	ListNode tailBlock;
	int removePtr;
	int addPtr;
	long count;
	long used;
	public class ListNode{
		int[] arr;
		ListNode next;
		public ListNode(){
			arr = new int[capacity];
		}
	}
	public FixedSizeQueue(){
		this.capacity = 5;
		this.headBlock = new ListNode();
		this.tailBlock = headBlock;
		this.removePtr = 0;
		this.addPtr = 0;
		this.count = (long)0;
		this.used = (long)0;
	}
	public void enqueue(int el){
		if(addPtr >= 5){
			//apply for new block
			tailBlock.next = new ListNode();
			tailBlock = tailBlock.next;
		}
		addPtr %= 5;
		tailBlock.arr[addPtr] = el;
		addPtr++;
		count++;
	}
	public int dequeue(){
		if(this.isEmpty()){
			return Integer.MIN_VALUE;
		}
		if(removePtr >= 5){
			ListNode next = headBlock.next;
			//headBlock = null;
			headBlock = next;
		}
		removePtr %= 5;
		int res = headBlock.arr[removePtr];
		removePtr++;
		used++;
		return res;
	}
	public boolean isEmpty(){
		return used >= count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FixedSizeQueue q = new FixedSizeQueue();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		//System.out.println(q.dequeue());//1
		//System.out.println(q.dequeue());//2
		q.enqueue(5);
		q.enqueue(6);
		q.enqueue(7);
		q.enqueue(8);
		q.enqueue(9);
		//System.out.println(q.dequeue());//3
		//System.out.println(q.dequeue());//4
		while(!q.isEmpty()){
			System.out.println(q.dequeue());//3
		}
		System.out.println(q.dequeue());
	}

}
