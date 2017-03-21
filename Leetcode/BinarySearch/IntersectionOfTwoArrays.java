/**
* Given two arrays, write a function to compute their intersection.
* 
* Example:
* Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
* 
* Note:
* Each element in the result must be unique.
* The result can be in any order.
*/

/*
solution 1: HashSet
*/

public class IntersectionOfTwoArrays {
  public int[] intersection(int[] nums1, int[] nums2) {
    Set<Integer> set1 = new HashSet<Integer>();
    Set<Integer> set2 = new HashSet<Integer>();
    for (int i = 0; i < nums1.length; i++) {
	  set1.add(nums1[i]);
	}	
	for (int i = 0; i < nums2.length; i++) {
	  if (set1.contains(nums2[i])) {
	    set2.add(nums2[i]);
	  }
	}
	int[] res = new int[set2.size()];
	int idx = 0;
	for (Integer i : set2) {
	  res[idx++] = i;
	}
	return res;
  }
} 

/*
solution 2: sort + tow pointers 
*/

public class IntersectionOfTwoArrays {
  public int[] intersection(int[] nums1, int[] nums2) {
    Set<Integer> candidates = new HashSet<Integer>();
    Arrays.sort(nums1);
	Arrays.sort(nums2);
	int i = 0; 
	int j = 0;
	while (i < nums1.length && j < nums2.length) {
	  if (nums1[i] > nums2[j]) {
	    j++;
	  } else if (nums1[i] < nums2[j]) {
	    i++;
	  } else {
	    candidates.add(nums1[i]);
		i++;
		j++;
	  }
	}
	int[] res= new int[candidates.size()];
	int idx = 0;
	for (Integer item : candidates) {
	  res[idx++] = item;
	}
	return res;
  }
} 