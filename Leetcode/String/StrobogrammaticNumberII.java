/**
* A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
* 
* Find all strobogrammatic numbers that are of length = n.
* 
* For example, Given n = 2, return ["11","69","88","96"].
*/


public class StrobogrammaticNumberII {
  public List<String> findStrobogrammatic(int n) {
    List<String> res = new ArrayList<String>();
	if (n <= 0) return res;
	char[] table = {'0', '1', '8', '6', '9'};
	build(n, "", table, res);
	return res;
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
	  // insert char c and its corresponding symetic char 
	  append(last, c, newTmp);
	  build(n, newTmp.toString(), table, res);
	}
  }
  private void append(boolean last, char c, StringBuilder newTmp) {
	if (c == '6') {
	  newTmp.insert(newTmp.length()/2, "69");
	} else if (c == '9') {
	  newTmp.insert(newTmp.length()/2, "96");
	} else {
	  newTmp.insert(newTmp.length()/2, last ? c : ""+c+c);
	}
  }
}
 