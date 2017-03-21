/**
* Given two arrays, write a function to compute their intersection.
* 
* Example:
* Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
* 
* Note:
* Each element in the result should appear as many times as it shows in both arrays.
* The result can be in any order.
* Follow up:
* What if the given array is already sorted? How would you optimize your algorithm?
* What if nums1's size is small compared to nums2's size? Which algorithm is better?
* What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
*/

/*
solution 1: HashMap
*/

public class IntersectionOfTwoArraysII {
  public int[] intersect(int[] nums1, int[] nums2) {
    List<Integer> candidates = new ArrayList<Integer>();
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    // using HashMap to store values in nums1
    for (int i = 0; i < nums1.length; i++) {
	  if (!map.containsKey(nums1[i])) {
	    map.put(nums1[i], 1);
	  } else {
	    map.put(nums1[i], map.get(nums1[i])+1);
	  }
    }
    // modify map when equal keys found in nums2 
    for (int i = 0; i < nums2.length; i++) {
	  if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
	    candidates.add(nums2[i]);
		map.put(nums2[i], map.get(nums2[i])-1);
	  }
	}
    // convert candidates to int[]
    int[] res = new int[candidates.size()];
    for (int i = 0; i < res.length; i++) {
	  res[i] = candidates.get(i);
	}
    return res; 	
  }
} 

/*
solution 2: sort + three pointers
*/

public class IntersectionOfTwoArraysII {
  public int[] intersect(int[] nums1, int[] nums2) {
    int idx = 0;
    int len1 = nums1.length;
    int len2 = nums2.length;
    int[] res = new int[len1];
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int i = 0;
    int j = 0;
    while (i < len1 && j < len2) {
	  if (nums1[i] < nums2[j]) {
	    i++;
	  } else if (nums1[i] == nums2[j]) {
	    res[idx] = nums1[i];
		i++;
		j++;
		idx++;
	  } else {
		j++;
	  }
	}
    return Arrays.copyOf(res, idx);    
  }
} 

/*
Follow up solutions:
If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.
  
If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.
*/