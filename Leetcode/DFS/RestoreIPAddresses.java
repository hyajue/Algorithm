/**
* Given a string containing only digits, restore it by returning all possible valid IP address combinations.
* 
* For example:
* Given "25525511135",
* 
* return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/

/*
solution 1
复杂度
时间:O(2^n) 空间：O(n)

思路：回溯法
IP address最多12位,最少4位，每个数在[0,255]之间.
在每层递归中,枚举可能切取的长度[1,3].然后判断当前切出的部分是否为合法IP数：
合法:则将该数加到已有的数字后面,传到下一层继续递归
非法:剪枝.选择下一个切取的长度.
*/

public class RestoreIPAddresses {
  public List<String> restoreIpAddresses(String s) {
    List<String> res = new ArrayList<String>();
    if (s == null || s.length() == 0) return res;
    helper(res, s, "", 4);
    return res;	
  }
	
  private void helper(List<String> res, String s, String str, int interval) {
    if (s == null || s.length() > interval*3 || s.length() < interval) {
	    return;
 	  }
	  if (s.length() == 0 && interval == 0) {
	    res.add(str);
	    return;
	  }
	  for (int i = 1; i <= 3 && i <= s.length(); i++) {
	    String curStr = s.substring(0, i);
      String resStr = s.substring(i);
      if (isValid(curStr)) {
		    StringBuilder sb = new StringBuilder(str);
		    sb.append(curStr);
		    if (interval >= 2) {
		      sb.append(".");   
		    }
	      helper(res, resStr, sb.toString(), interval-1);
	    }
	  }
  }
	
  private boolean isValid(String str) {
    if (str.charAt(0) == '0') {
	    return str.equals("0");
	  } else {
	    int tmp = Integer.parseInt(str);
	    return tmp > 0 && tmp <= 255;
	  }
  }
}