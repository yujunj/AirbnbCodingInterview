package Airbnb;
import java.util.*;
public class PyramidStringMatrix {
	/************
	 * 给一个满二叉树的所有叶子，比如 A B C D E F， 然后给一个map，记录了左右孩子分别给了的时候，父亲节点可能的值。
	 * 例如 左 A 右 B =》 AC，意味着倒数第二层第一个节点可以是A或者是C。
	 * 然后要求是给几个字母，问这个树的root节点是否可能是这几个字母之一。
	 * follow up是加速，记忆化搜索（不是很好写）。
	 * ******************/
	public static HashMap<String,Set<String>> map = new HashMap<>();
	public static List<List<String>> res = new LinkedList<>();
	public boolean generateValidResult(String s,String[][] matrix,List<String> oneres){
		if(s.length() == 1){
			res.add(new LinkedList<String>(oneres));
			return true;
		}
		int n = s.length();
		//map.put(s,new HashSet<String>());
		List<String> candidates = new LinkedList<>();
		for(int i = 0;i < n - 1;i++){
			char left = s.charAt(i);
			char right = s.charAt(i + 1);
			String next = matrix[left-'A'][right-'A'];
			//System.out.println(next);
			if(next == null){
				return false;
			}
			LinkedList<String> temp = new LinkedList<String>();
			//System.out.println(next);
			for(int j = 0;j < next.length();j++){
				//System.out.println(next.charAt(j));
				if(i == 0){
					candidates.add(String.valueOf((char)next.charAt(j)));
					continue;
				}
				for(int k = 0;k < candidates.size();k++){
					temp.add(candidates.get(k) + String.valueOf((char)(next.charAt(j))));
				}
			}
			if(i > 0){
				candidates = temp;
			}
		}
		boolean found = false;
		for(String candidate : candidates){
			oneres.add(candidate);
			if(generateValidResult(candidate,matrix,oneres)){
				found = true;
			}
			oneres.remove(oneres.size()-1);
		}
		return found;
		//generate all the possibility for next string input,which is the combination of chars in the candidates list
	}
	public static void main(String[] args){
		String[][] matrix = new String[4][4];
		  for (int i = 0; i < 4; i++){
			   Arrays.fill(matrix[i], null);
			  }
			  matrix[0][0] = "B";
			  matrix[0][1] = "AC";
			  matrix[0][2] = "D";
			  matrix[0][3] = "A";
			  matrix[1][0] = "D";
			  matrix[1][1] = "BC";
			  matrix[1][2] = "A";
			  matrix[2][3] = "B";
	     String s = "ABCD";
	     new PyramidStringMatrix().generateValidResult(s, matrix, new LinkedList<String>());
	     for(List<String> oneres : res){
	    	 System.out.println("oneres");
	    	 for(String str : oneres){
	    		 System.out.print(str);
	    		 System.out.print(" ");
	    	 }
	    	 System.out.println();
	     }
	}
}
