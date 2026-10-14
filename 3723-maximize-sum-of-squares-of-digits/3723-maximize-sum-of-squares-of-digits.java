class Solution {
    public String maxSumOfSquares(int num, int sum) {
      if(sum>9*num)
      return "";
      StringBuilder result=new StringBuilder();
      for(int i=0;i<num;i++)

      {
         int d=Math.min(9,sum);
         result.append(d);
         sum=sum-d;

      }
     return result.toString();
        
    }
}