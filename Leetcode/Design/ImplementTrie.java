/**
* Implement a trie with insert, search, and startsWith methods.
* 
* Note:
* You may assume that all inputs are consist of lowercase letters a-z.
*/

/*
复杂度
时间：O(L) L is the length of longest word 空间：O(26^L) 每一层分支26 共有L层

思路：
实质是实现一颗多叉树（分支因子26）的插入和查找操作 定义每个TrieNode保存char c，保存一个HashMap用于储存所有的孩子节点，
key是对应的字符，value是孩子节点，	定义flag hasWord来标记这个node上是否存在word。
对于插入操作，直接从上向下分层扫描树，如果没有对应字符节点存在就新建节点，如果有就去相应路径向下探察。
对于查询操作，前缀树的定义可以保证当我们从前向后扫描字符串时候，每一个字符都可以从上到下对应前缀树的每一层，
所以扫描过程中如果有任何一个字符不存在于当前层中，就可以立刻停止查找返回null，也就是不存在这样的word或者前缀，否则继续从对应的分支向下探察
*/

public class Trie {
	private TrieNode root;
	
	public Trie() {
	  root = new TrieNode();
	}
	
	/** Inserts a word into the trie. */
	public void insert(String word) {
	  TrieNode cur = root;
		Map<Character, TrieNode> curChildren = root.children;
		char[] wordList = word.toCharArray();
		for (int i = 0; i < wordList.length; i++) {
			char curChar = wordList[i];
			if (curChildren.containsKey(curChar)) {
				cur = curChildren.get(curChar);
			} else {
				TrieNode newNode = new TrieNode(curChar);
				curChildren.put(curChar, newNode);
				cur = newNode;
			}
			curChildren = cur.children;
			if (i == wordList.length - 1) {
				cur.hasWord = true;
			}
		}
	}
	
	/** Returns if the word is in the trie. */
	public boolean search(String word) {
	  if (searchWordNodePos(word) == null) {
			return false;
		} else if (searchWordNodePos(word).hasWord) {
			return true;
		} else {
			return false;
		}    		
	}
	
	/** Returns if there is any word in the trie that starts with the given prefix. */
	public boolean startsWith(String prefix) {
	  if (searchWordNodePos(prefix) == null) {
			return false;
		} else {
			return true;
		}		
	}
	
	private TrieNode searchWordNodePos(String s) {
		Map<Character, TrieNode> children = root.children;
		TrieNode cur = null;
		char[] sList = s.toCharArray();
		
		for (int i = 0; i < sList.length; i++) {
			char curChar = sList[i];
			if (children.containsKey(curChar)) {
				cur = children.get(curChar);
				children = cur.children;
			} else {
				return null;
			}
		}
		return cur;
	}
	
	// private helper class that defines TrieNode used in Tire structure 
	private class TrieNode {
		char c;
		Map<Character, TrieNode> children;
		boolean hasWord;
		
		public TrieNode() {
			children = new HashMap<Character, TrieNode>();
		}
		public TrieNode(char c) {
			this.c = c;
			children = new HashMap<Character, TrieNode>();
		}
	}
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */ 