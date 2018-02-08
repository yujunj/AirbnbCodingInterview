package Airbnb;
import java.util.*;
import java.util.*;
public class ringBuffer {
	int[] ring;
	int head;
	int unconsumed;
	int capacity;
	public ringBuffer(){
		this.ring = new int[capacity];
		this.head = 0;
		this.unconsumed = 0;
	}
	public int size(){
		return this.capacity;
	}
	public synchronized void add(int num) throws InterruptedException{
		
		while(unconsumed == ring.length){
			wait();
		}
		ring[head] = num;
		unconsumed++;
		head = (head + 1)%ring.length;
		notifyAll();
	}
	public synchronized int peek() throws InterruptedException {
		while(unconsumed == 0){
			wait();
		}
		return ring[(head+capacity-unconsumed)%capacity];
	}
	public synchronized int get() throws InterruptedException{
		int res = peek();
		unconsumed--;
		notifyAll();
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
