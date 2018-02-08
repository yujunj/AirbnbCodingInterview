package Airbnb;
import java.util.*;
public class Flatten2DVector implements Iterator<Integer>{
	Iterator<List<Integer>> itr;
    Iterator<Integer> cur;
    public Flatten2DVector(List<List<Integer>> vec2d) {
        this.itr = vec2d.iterator();
    }

    @Override
    public Integer next() {
        if(!hasNext()){
            return null;
        }
        return cur.next();
    }

    @Override
    public boolean hasNext() {
        while(cur == null||!cur.hasNext()){
            if(!itr.hasNext()){
                return false;
            }
            cur = itr.next().iterator();
        }
        return true;
    }
    @Override
    public void remove(){
        cur.remove();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> vec2d = new ArrayList<>();
		List<Integer> row1 = new ArrayList<Integer>();
		row1.add(1);
		row1.add(2);
		row1.add(3);
		row1.add(4);
		row1.add(5);
		List<Integer> row2 = new ArrayList<Integer>();
		row2.add(6);
		row2.add(7);
		List<Integer> row3 = new ArrayList<Integer>();
		row3.add(8);
		row3.add(9);
		row3.add(10);
		vec2d.add(row1);
		vec2d.add(row2);
		vec2d.add(row3);
		Flatten2DVector itr = new Flatten2DVector(vec2d);
		while(itr.hasNext()){
			System.out.print(itr.next());
		}
		System.out.println("\n");
		System.out.println("removal");
		itr = new Flatten2DVector(vec2d);
		int next = itr.next();
		itr.remove();
		while(itr.hasNext()){
			System.out.print(itr.next());
		}
	}

}