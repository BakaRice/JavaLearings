package Leetcode;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description: https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 * User: ricemarch
 * Date: 2020/10/31
 * Time: 下午1:33
 */
public class LeetCode381 {
    //copy!
    class RandomizedCollection {

        Map<Integer, Set<Integer>> idx;
        List<Integer> nums;

        /**
         * Initialize your data structure here.
         */
        public RandomizedCollection() {
            idx = new HashMap<>();
            nums = new ArrayList<>();
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
         */
        public boolean insert(int val) {
            nums.add(val);
            Set<Integer> set = idx.getOrDefault(val, new HashSet<>());
            set.add(nums.size() - 1);
            idx.put(val, set);
            return set.size() == 1;
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained the specified element.
         */
        public boolean remove(int val) {
            if (!idx.containsKey(val)) {
                return false;
            }
            Iterator<Integer> it = idx.get(val).iterator();
            int i = it.next();
            int lastNum = nums.get(nums.size() - 1);
            nums.set(i, lastNum);
            idx.get(val).remove(i);
            idx.get(lastNum).remove(nums.size() - 1);
            if (i < nums.size() - 1) {
                idx.get(lastNum).add(i);
            }
            if (idx.get(val).size() == 0) {
                idx.remove(val);
            }
            nums.remove(nums.size() - 1);
            return true;
        }

        /**
         * Get a random element from the collection.
         */
        public int getRandom() {
            return nums.get((int) (Math.random() * nums.size()));
        }
    }

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
