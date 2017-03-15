/**
* A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
* 
* Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
* 
* For example,Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
* 
* Note:Because the range might be a large number, the low and high numbers are represented as string.
*/

/*
以Strobogrammatic Number II为基础，先求出所有长度满足的数，
再通过与low,high的compare比较，选出最终的结果并count
*/

public class StrobogrammaticNumberIII {
  public int strobogrammaticInRange(String low, String high) {
    int cnt = 0;
    List<String> res = new ArrayList<String>();
	List<String> subRes = new ArrayList<String>();	
	char[] table = {'0', '1', '8', '6', '9'};
	for (int i = low.length(); i <= high.length(); i++) {
	  build(i, "", table, subRes);
	  res.addAll(subRes);
	}
    for (String str : res) {
	  if (str.length() == low.length() && str.compareTo(low) < 0 || str.length == high.length() && str.compareTo(high) > 0) {
	    continue;
	  }
	  cnt++;
	}
    return cnt;	
  }
  private void build(int n, String tmp, char[] table, List<String> res) {
	if (n == tmp.length()) {
	  res.add(tmp);
	  return;
	}
	boolean last = false;
	if (n - tmp.length() == 1) last = true;
	for (int i = 0; i < table.length; i++) {
	  char c = table[i];
	  // the first char cannot be zero unless n = 1. 
	  // 6 and 9 cannot be inserted if there's only one char left to insert 
	  if((n != 1 && tmp.length() == 0 && c == '0') || (last && (c=='6' || c=='9'))) {
		continue;
	  }
	  StringBuilder newTmp = new StringBuilder(tmp);
	  // insert char c and its corresponding symmetric char 
	  insert(last, c, newTmp);
	  build(n, newTmp.toString(), table, res);
	}
  }
  private void insert(boolean last, char c, StringBuilder newTmp) {
	if (c == '6') {
	  newTmp.insert(newTmp.length()/2, "69");
	} else if (c == '9') {
	  newTmp.insert(newTmp.length()/2, "96");
	} else {
	  newTmp.insert(newTmp.length()/2, last ? c : ""+c+c);
	}
  }
}