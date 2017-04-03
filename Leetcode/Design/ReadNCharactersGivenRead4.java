/**
* The API: int read4(char *buf) reads 4 characters at a time from a file.
* 
* The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
* 
* By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
* 
* Note:The read function will only be called once for each test case.
*/




public class ReadNCharactersGivenRead4 extends Reader4 {
  public int read(char[] buf, int n) {
		boolean readEnd = false;
		int totalRead = 0;
		char[] tmp = new char[4];
		while (!end && totalRead < n) {
			int cnt = read4(tmp);
			end = cnt < 4; //如果实际文件已经读完 则end=true
			cnt = Math.min(cnt, n-totalRead); //需要读的文件个数已达到
			for (int i = 0; i < cnt; i++) {
				buf[totalRead++] = tmp[i];
			}
		}
		return totalRead;
  }
} 