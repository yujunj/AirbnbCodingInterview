package Airbnb;
import java.util.*;
public class KEditDistance {
	 public class TrieNode{
	        TrieNode[] children;
	        String word;
	        public TrieNode(){
	            this.children = new TrieNode[26];
	            this.word = null;
	        }
	    }
	    public void insert(TrieNode root,String word){
	        TrieNode cur = root;
	        char[] w = word.toCharArray();
	        for(char c : w){
	            if(cur.children[c-'a'] == null){
	                cur.children[c-'a'] = new TrieNode();
	            }
	            cur = cur.children[c-'a'];
	        }
	        cur.word = word;
	    }
	    List<String> res;
	    public List<String> kDistance(String[] words, String target, int k) {
	        // Write your code here
	        res = new LinkedList<>();
	        if(words == null||words.length == 0)        
	        return res;
	        TrieNode root = new TrieNode();
	        for(String word : words){
	            insert(root,word);
	        }
	        int[] dp = new int[target.length() + 1];
	        for(int i = 0;i <= target.length();i++){
	            dp[i] = i;
	        }
	        dfs(root,dp,target,k,0);
	        return res;
	    }
	    private void dfs(TrieNode root,int[] dp,String target,int k,int steps){
	        if(root.word != null && dp[dp.length-1] <= k){
	            res.add(root.word);
	        }
	        TrieNode cur = root;
	        for(int i = 0;i < 26;i++){
	            if(cur.children[i] != null){
	                char c = (char)('a' + i);
	                int[] next = new int[target.length() + 1];
	                next[0] = steps + 1;
	                //dp[i] is the distance from substring without c to the first ith chars of target
	                //Now we are building the distance from current string to the first ith chars of target
	                //dp[i-1][j-1]
	                //dp[i][j-1]
	                //dp[i-1][j]
	                for(int j = 1;j <= target.length();j++){
	                    int same = dp[j-1];
	                    if(c != target.charAt(j-1)){
	                        same++;
	                    }
	                    next[j] = Math.min(same,Math.min(next[j-1],dp[j]) + 1);
	                }
	                dfs(cur.children[i],next,target,k,steps+1);
	            }
	        }
	    } 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] input = new String[]{"abc", "abd", "abcd", "adc"};
	    String target = "ac";
	    int k = 1;
	    List<String> result = new KEditDistance().kDistance(input, target, k);
	    for (String s : result) {
	      System.out.println(s);
	    }
	    List<String> result2 = new KEditDistance().kDistance(input, target, 2);
	    for (String s : result2) {
	      System.out.println(s);
	    }
	}
}
