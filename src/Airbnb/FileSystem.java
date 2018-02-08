package Airbnb;
import java.util.*;
public class FileSystem {
/****************
 * 新题是一个OO Design，很简单，就是实现一个tree。 具体如下：
configuration tree：
三个method：create(path, value), set_value(path, value), get_value(path)
让你实现一个长成这样的tree：

       root
     /        \
   NA       EU
  /    \
CA  US

其中root是没有name和value，剩下的每个点都有name和value

create（path，value）：给你一个path，比如“NA/MX”，和value，比如“3”。那么你就在NA下面创建一个点叫MX，值是3。. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
set_value(path, value): 给你一个path，找到path的叶子，然后set value，如果叶子不存在，返回false；
get_value(path): 给你一个path，返回叶子的值，没有叶子的话返回NULL。

非常简单一个OOD，跟Trie很像。
 * *********************/
	public static class TrieNode{
		Integer val;
		Map<String,TrieNode> children;
		public TrieNode(int val){
			this.val = val;
			this.children = new HashMap<>();
		}
		public TrieNode(){
			this.children = new HashMap<>();
		}
	}
	public static TrieNode root = new TrieNode();
	public static void createPath(String path,int val){
		TrieNode cur = root;
		String[] p = path.trim().split("/");
		for(String el : p){
			if(!cur.children.containsKey(el)){
				cur.children.put(el,new TrieNode());
			}
			cur = cur.children.get(el);
		}
		cur.val = val;
	}
	public static boolean setPath(String path,int val){
		TrieNode cur = root;
		String[] p = path.trim().split("/");
		for(String el : p){
			if(!cur.children.containsKey(el)){
				return false;
			}
			cur = cur.children.get(el);
		}
		cur.val = val;
		return true;
	}
	public static Integer getValue(String path){
		TrieNode cur = root;
		String[] p = path.trim().split("/");
		for(String el : p){
			if(!cur.children.containsKey(el)){
				return null;
			}
			cur = cur.children.get(el);
		}
		return cur.val;
	}
	public static void main(String[] args){
		createPath("NA/CA",1);
		createPath("EU",2);
		createPath("NA/US",3);
		System.out.println(getValue("NA/CA"));
		setPath("NA/US",4);
		System.out.println(getValue("NA/US"));
	}
}
