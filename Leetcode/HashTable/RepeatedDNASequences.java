/**
* All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
* 
* Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
* 
* For example,
* 
* Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
* 
* Return:
* ["AAAAACCCCC", "CCCCCAAAAA"].
*/

/*
复杂度
时间:o(n) 空间O(n) 

思路：HashTable 
把位移一位后每个子串都存入哈希表中，如果哈希表中已经有这个子串，而且是第一次重复，则加入结果中。
如果已经遇到多次，则不加入结果中。如果哈希表没有这个子串，则把这个子串加入哈希表中。
*/

public class RepeatedDNASequences {
	public List<String> findRepeatedDnaSequences(String s) {
	  List<String> res = new ArrayList<String>();
    Map<String, Integer> map = new HashMap<String, Integer>();
		if (s == null || s.length() < 10) return res;
		for (int idx = 0; idx <= s.length() - 10; idx++) {
			String subS = s.substring(idx, idx+10);
			if (map.containsKey(subS)) {
				if (map.get(subS) == 1) {
					res.add(subS);
				}
				map.put(subS, map.get(subS)+1);
			} else {
				map.put(subS, 1);
			}
		}
		return res;
	}
}
 