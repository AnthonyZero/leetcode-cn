## 209. 长度最小的子数组

给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。

示例:
```   
输入：s = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。

```   

### 思路
双指针，滑动窗口的思路。右指针不断后移加元素值，直到窗口中的数组和是否超过了s,超过了就调整左指针
移动一位，继续上述步骤。取满足条件的窗口中的最小子数组的元素个数

```   
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int minLength = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        int sum = nums[0]; //当前子数组的和
        int total = 1; //当前子数组长度
        while(i < nums.length) {
            if(sum >= s) {
                if(i == 0) { //第一个元素就符合条件
                    return total;
                } else {
                    minLength = Integer.min(minLength, total);
                }
            } else {
                j += 1;
                while(j < nums.length) { //不断加上nums[j],当和超过s之后，再加也无济于事，记录当前子数组最小长度，并将i后移一位继续看和
                    sum += nums[j];
                    total += 1;
                    if(sum >= s) {
                        minLength = Integer.min(minLength, total);
                        break;
                    }
                    j++;
                }
            }
            sum -= nums[i]; //i准备后移一位，同时原先和要减掉一个数，长度同时也要-1
            total -= 1;
            i++;
        }
        if(minLength == Integer.MAX_VALUE) {
            //没有找到满足条件的子数组
            return 0;
        }
        return minLength;
    }
```