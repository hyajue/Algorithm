/**
* Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
* 
* You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
* 
* Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
* 
* For the last line of text, it should be left justified and no extra space is inserted between words.
* 
* For example,
* words: ["This", "is", "an", "example", "of", "text", "justification."]
* L: 16.
* 
* Return the formatted lines as:
* [
*    "This    is    an",
*    "example  of text",
*    "justification.  "
* ]
* Note: Each word is guaranteed not to exceed L in length.
* 
* Corner Cases:
* A line other than the last line might contain only one word. What should you do in this case?
* In this case, that line should be left-justified.
*/


public class TextJustification {
  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> res = new ArrayList<String>();
    if (words == null || words.length == 0) {
	    return res;
	  }	
	  int len = words.length;
	  int ptrL = 0;
	  while (ptrL < len) {
	    int size = 0;
      size += words[ptrL].length();
      int ptrR = ptrL + 1;
      while (ptrR < len && size + 1 + words[ptrR].length() <= maxWidth) {
		    size += words[ptrR].length() + 1; // one word length plus one space 
		    ptrR++;
	    }
      String line = words[ptrL];
      // case 1: current line is the last line 
      // from left to right fill, then fill spaces if there's width left 
      if (ptrR == len) {
		    for (int k = ptrL + 1; k < ptrR; k++) {
		    line += " " + words[k];
		    }
		    while (line.length() < maxWidth) {
		      line += " ";
		    }
		    res.add(line);
	    } else {
			  // case 2: if there's only one word in one line
			  // left-justified, fill spaces if width left 
		      if (ptrR - ptrL == 1) {
		        while (line.length() < maxWidth) {
			        line += " ";
		        }
		        res.add(line);
		        // case 3: normal line. 
		      } else {
		        int interval = ptrR - ptrL - 1; // interval number 
            int lenOfWords = size - interval; // width taken by words 
            int spaces = maxWidth - lenOfWords; // spaces number in this line 
            int x = spaces / interval;
            int y = spaces % interval;
            int cnt = 1;
		        for (int k = ptrL + 1; k < ptrR; k++) {
		          for (int m = 0; m < x; m++) {
			          line += " ";
			        }
              if (cnt <= y) {
			          line += " ";
			        }
			        cnt++;
			        line += words[k];
		        }
		        res.add(line);
		      }
	    }
      ptrL = ptrR; // 交换指针 进入下一行	  
	  }
	  return res;
  }
} 
