package Airbnb;
import java.util.*;
public class Pagelist {
	public void sort(ArrayList<String> list){
		Queue<String> q = new LinkedList<>();
		HashSet<String> set = new HashSet<>();
		int i = 0;
		/**********This is only for allowing duplicates in the last page*************/
		int used = 0;
		int count = list.size();
		/**********This is only for allowing duplicates in the last page*************/
		int pageCount = 1;
		//System.out.println(count);
		System.out.println("\n");
		System.out.print("page" + " " + pageCount++);
		System.out.println("\n");
		while(i < list.size()||!q.isEmpty()){
			//temp holds the elements from queue that cannot be used this time. These elements should be considered first in the next loop
			Queue<String> temp = new LinkedList<>();
			//Now we try to print one whole page with 12 items
			for(int j = 0;j < 12;j++){
				//read data from q which holds data from previous loop which has priority due to sorting
				boolean fromq = false;
				//keep trying until the q is empty
				while(q.size() > 0){
					String curEl = q.poll();
					String curHost = curEl.substring(0,curEl.indexOf(','));
					//Successfully poll an element from the q
					//print new element and exit this iteration
					if(!set.contains(curHost)){
						System.out.println(curEl);
						/**********This is only for allowing duplicates in the last page*************/
						used++;
						/**********This is only for allowing duplicates in the last page*************/
						set.add(curHost);
						fromq = true;
						break;
					}
					else{
						//add the old elements to the temp
						temp.add(curEl);
					}
				}
				//if we couldn't get any element from the queue, then we loop the input
				if(!fromq){
					while(true){
						if(i == list.size()) break;
						String el = list.get(i);
						//System.out.println(el);
						String host = el.substring(0,el.indexOf(','));
						//Add new duplicates to the end of queue
						if(set.contains(host)){
							q.add(el);
						}
						else{
							//print new element and exit this iteration
							System.out.println(el);
							/**********This is only for allowing duplicates in the last page*************/
							used++;
							/**********This is only for allowing duplicates in the last page*************/
							set.add(host);
							i++;
							break;
						}
						i++;
					}
				}
			}
			System.out.println("\n");
			System.out.print("page" + " " + pageCount++);
			System.out.println("\n");
			//One page complete,clear up the set
			set.clear();
			//concatenate new unused elements to the temp along with those from previous loop
			temp.addAll(q);
			//change reference
			q = temp;
			/**********This is only for allowing duplicates in the last page*************/
			if(used + 12 >= count){
				//System.out.println(used);
				System.out.println("Now we allow Duplicates!");
				break;
			}
			/**********This is only for allowing duplicates in the last page*************/
		}
		/**********This is only for allowing duplicates in the last page*************/
		for(int k = 0;k < 24;k++){
			if(k == 12){
				System.out.println("\n");
				System.out.print("page" + " " + pageCount++);
				System.out.println("\n");
			}
			if(!q.isEmpty()){
				System.out.println(q.poll());
				continue;
			}
			if(i == count) break;
			System.out.println(list.get(i++));
		}
		/**********This is only for allowing duplicates in the last page*************/
	}
	public void displayPages(List<String> input) {
		  int size = input.size();
		  int count = 0;
		  HashSet<String> hashSet = new HashSet<String>();
		  System.out.println("one page: ");
		  MyIter iter = new MyIter(input);
		  while (!iter.finish()) {
			  while (!iter.isEnd()) {
				  String line = iter.next();
				  String[] strs = line.split(",");
				  String hostId = strs[0];
				  if (!hashSet.contains(hostId)) {
					  System.out.println(line);
					  hashSet.add(hostId);
					  iter.remove();
					  count++;
					  if (count == 12) {
						  hashSet.clear();
						  count = 0;
						  iter.fresh();
						  System.out.println("one page");
					  }
				  } 
				  else {
					  iter.reserve(line);
				  }
			  }
			  hashSet.clear();
			  count = 0;
			  iter.fresh();
			  System.out.println("one page");
		  	}
		 }
		 private class MyIter {
		  LinkedList<String> headList;
		  List<String> bigList;
		  Iterator<String> bigListIter;
		  int pos;
		  int flag; // 1 for headList, 2 for bigList
		  MyIter(List<String> list) {
			  bigList = list;
			  headList = new LinkedList<String>();
			  bigListIter = bigList.iterator();
			  pos = 0;
			  flag = 2;
		  }
		  public boolean isEnd() {
			  if (flag == 2 && !bigListIter.hasNext()) {
				  return true;
			  }
			  else if (flag == 1 && !bigListIter.hasNext() && pos == headList.size()) {
				  return true;
			  } 
			  else {
				  return false;
			  }
		  }
		  public String next() {
		   if (flag == 2) {
			   return bigListIter.next();
		   } 
		   else if (pos < headList.size()) {
			   pos++;
			   return headList.get(pos - 1);
		   }
		   if (bigListIter.hasNext()) {
			   flag = 2;
			   return bigListIter.next();
		   }
		   else {
			   return null;
		   	}
		  }
		  public void remove() {
		   if (flag == 1) {
			   headList.remove(pos - 1);
			   pos--;
		   	}
		  }
		  public void reserve(String line) {
		   if (flag == 2) {
			   headList.add(line);
		   	}
		  }
		  public boolean finish() {
			  return (headList.isEmpty() && !bigListIter.hasNext());
		  }
		  public void fresh() {
			  pos = 0;
			  flag = 1;
		  	}
		 }
	public static void main(String[] args) {
		ArrayList<String> input = new ArrayList<String>();
		input.add("1,28,300.1,San Francisco");
		input.add("4,5,209.1,San Francisco");
		input.add("20,7,208.1,San Francisco");
		input.add("23,8,207.1,San Francisco");
		input.add("16,10,206.1,Oakland");
		input.add("1,16,205.1,San Francisco");
		input.add("6,29,204.1,San Francisco");
		input.add("7,20,203.1,San Francisco");
		input.add("8,21,202.1,San Francisco");
		input.add("2,18,201.1,San Francisco");
		input.add("2,30,200.1,San Francisco");
		input.add("15,27,109.1,Oakland");
		input.add("10,13,108.1,Oakland");
		input.add("11,26,107.1,Oakland");
		input.add("12,9,106.1,Oakland");
		input.add("13,1,105.1,Oakland");
		input.add("22,17,104.1,Oakland");
		input.add("1,2,103.1,Oakland");
		input.add("28,24,102.1,Oakland");
		input.add("18,14,11.1,San Jose");
		input.add("6,25,10.1,Oakland");
		input.add("19,15,9.1,San Jose");
		input.add("3,19,8.1,San Jose");
		input.add("3,11,7.1,Oakland");
		input.add("27,12,6.1,Oakland");
		input.add("1,3,5.1,Oakland");
		input.add("25,4,4.1,San Jose");
		input.add("5,6,3.1,San Jose");
		input.add("29,22,2.1,San Jose");
		input.add("30,23,1.1,San Jose");
		new Pagelist().sort(input);
		//new Pagelist().displayPages(input);
		//String curEl = "30,23,1.1,San Jose";
		//System.out.println(curEl.substring(0,curEl.indexOf(',')));
	}

}