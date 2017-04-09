/**
* Reverse bits of a given 32 bits unsigned integer.
* 
* For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).
* 
* Follow up:
* If this function is called many times, how would you optimize it?
*/

/*
复杂度
时间O(1) 空间O(1)

思路：位运算
不断拿最低位后右移 结果被赋值最低位后不停滴左移
*/

public class ReverseBits {
  // you need treat n as an unsigned value
  public int reverseBits(int n) {
    int res = 0;
		for (int i = 0; i < 32; i++) {
			res = (n & 1) | (res << 1);
			n >>= 1;
		}
		return res;
  }
} 

/*
about follow-up：
反复要用到的东西记下来，所以用Map记录之前反转过的数字和结果
更好的优化方法是将其按照Byte分成4段存储，节省空间
*/

public class ReverseBits {
  // you need treat n as an unsigned value
  private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();
	
	public int reverseBits(int n) {
		byte[] bytes = new byte[4];
		for (int i = 0; i < 4; i++) {
			bytes[i] = (byte)((n >>> 8*i) & 0xFF); //1 byte = 8 bits 
		}	
		int res = 0;
		for (int i = 0; i < 4; i++) {
			res += reverseByte(bytes[i]); //reverse per byte 
			if (i < 3) {
				res <<= 8;
			}	
		}
		return res;
	}
	
	private int reverseByte(byte b) {
		Integer value = cache.get(b); // look up if it's cached
		if (value != null) {
			return value;
		}
		value = 0;
		// reverse bit by bit 
		for (int i = 0; i < 8; i++) {
			value += ((b>>>i) & 1);
			if (i < 7) {
				value <<= 1;
			}
		}
		cache.put(b, value);
		return value;
	}
}
