/**
* Write a program to find the n-th ugly number.
* 
* Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
* 
* Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
*/ 

/*
maintain a ugly number array.
A ugly number must be obtained by one of a smaller ugly number multiplied by 2, 3, or 5. 
ugly number = A * B; where A from {2, 3, 5} and B from array uglyNum[i]; i from array idx[].
*/

public class UglyNumberII {
    public int nthUglyNumber(int n) {
        int[] idx = new int[3];
	    int[] uglyNum = new int[n];
		uglyNum[0] = 1;
		
		for (int i = 1; i < n; i++) {
			int numA = uglyNum[idx[0]] * 2;
			int numB = uglyNum[idx[1]] * 3;
			int numC = uglyNum[idx[2]] * 5;
			int min = Math.min(numA, Math.min(numB, numC));
			if (min == numA) {
				idx[0]++;
			}
			if (min == numB) {
				idx[1]++;
			}
			if (min == numC) {
				idx[2]++;
			}
			uglyNum[i] = min;
			
		}
		return uglyNum[n-1];
    }
}






