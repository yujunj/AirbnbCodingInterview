package Airbnb;
import java.util.*;
/***********************************
 * 
 * 他们公司list价格分成好几个部分，但是都是整数，如果在美金是整数，到了欧洲的网页显示汇率转换之后就变成了floating point，然后要round成整数，但是全部加起来round，和单独round再加起来，结果会不一样
# base price 100 => 131.13 => 131
# cleaning fee 20 => 26.23 => 26
# service fee 10 => 13.54 => 14
# tax 5 => 6.5 => 7
# => 177.4E => 178E
# sum 135$ => 178.93E => 179E 

那么问题就来了，给个input list of floating points, 要求output list of integers, 满足以下两个constraint， 就是和跟Round(x1+x2+… +xn)的结果一样，但是minimize output 和input的绝对值差之和
#Input: A = 
# Sum T = Round(x1+x2+… +xn) ; 178.93E => 179
# Output: B = 

# Constraint #1: y1+y2+…+yn = T
# Constraint #2: minimize sum(abs(diff(xi - yi)))

举例
# A = 
# Round(1.2 + 2.3 + 3.4) = 6.9 => 7
# 1 + 2 + 3 => 6

# 1 + 3 + 3 => 7
# 0.2 + 0.7 + 0.4 = 1.3

# 1 + 2 + 4 => 7
# 0.2 + 0.3 + 0.6 = 1.1
 * ********************************************/
public class RoundNumber {
    public static int[] getNearlyArrayWithSameSum(double[] arr) {
            NumWithDiff[] arrWithDiff = new NumWithDiff[arr.length];
            double sum = 0.0;
            int floorSum = 0;
            for (int i = 0; i < arr.length; i++) {
                    int floor = (int)arr[i];
                    int ceil = floor;
                    if (floor < arr[i]) ceil++; 
                    floorSum += floor;
                    sum += arr[i];
                    arrWithDiff[i] = new NumWithDiff(ceil, ceil - arr[i]); 
            }
            int num = (int) Math.round(sum);
            int diff = num - floorSum;
            Arrays.sort(arrWithDiff, new Comparator<NumWithDiff>() {
                    public int compare(NumWithDiff n1, NumWithDiff n2) {
                        return Double.compare(n1.diffWithCeil,n2.diffWithCeil);
                    } 
            });
            int[] res = new int[arr.length];
            int i = 0;
            for (; i < diff; i++) {
                    res[i] = arrWithDiff[i].num; 
            }
            for (; i < arr.length; i++) {
                    res[i] = arrWithDiff[i].num - 1; 
            }
            return res;
    }
    public static void main(String[] args) {
            double[] arr = { 1.2, 3.7, 2.3, 3.8 };
            int[] res = getNearlyArrayWithSameSum(arr);
            for (int i : res) System.out.print(i + " ");
            
    }
}
class NumWithDiff {
    int num;
    double diffWithCeil;
    public NumWithDiff(int n, double c) {
            this.num = n;
            this.diffWithCeil = c;
    }
}
