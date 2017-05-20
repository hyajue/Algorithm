/**
* The API: int read4(char *buf) reads 4 characters at a time from a file.
* 
* The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
* 
* By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
* 
* Note:
* The read function may be called multiple times.
*/

/* The read4 API is defined in the parent class Reader4. 
      int read4(char[] buf); */  
  
/*
复杂度
时间O(n) 空间O(n)

思路：
用Queue保存之前多读的character。每次读时，先看Queue里的够不够，如果不够，先读到够为止
*/
	
public class ReadNCharactersGivenRead4II extends Reader4 {  
  /** 
   * @param buf Destination buffer 
   * @param n   Maximum number of characters to read 
   * @return    The number of characters read 
   */  
  
	private boolean endOfFile = false;
	private Queue<Character> buff = new LinkedList<Chracater>();
	
	public int read(char[] buf, int n) {  
	  if (n == 0) return 0;
		int totalRead = 0;
		
		while (this.buff.size() < n && !this.endOfFile) {
			char[] tmp = new char[4];
			int curReadNum = read4(tmp);
			if (curReadNum < 4) {
				this.endOfFile = true;
			}
			for (int i = 0; i < curReadNum; i++) {
				this.buff.add(tmp[i]);
			}
		}
		//if !endOfFile: buff.size() >= n 
		int minLen = Math.min(this.buff.size(), n);
		for (int i = 0; i < minLen; i++) {
			buf[i] = this.buff.poll();
			totalRead++;
		}
		return totalRead;
  }  
}   