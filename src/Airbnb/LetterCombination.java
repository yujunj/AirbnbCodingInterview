package Airbnb;
import java.util.*;
public class LetterCombination {
	public void letterCombination(String word){
		dfs(word,0,"");
	}
	private void dfs(String word,int pos,String cur){
		if(cur.length() == word.length()){
			System.out.println(cur);
			return;
		}
		for(int i = pos;i < word.length();i++){
			char c = word.charAt(i);
			char upper = Character.toUpperCase(c);
			char lower = Character.toLowerCase(c);
			dfs(word,i+1,cur+upper);//it is (i+1)!!!!!!! Not (pos + 1)!
			dfs(word,i+1,cur+lower);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String word = "word";
		new LetterCombination().letterCombination(word);
	}

}
