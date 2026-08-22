class Solution {
    public boolean checkDivisibility(int n) {
        int temp=n;
        int sum=0;
        int p=1;
        while(temp>0)
        {
            int d=temp%10;
             sum=sum+d;
            p=p*d;
            temp=temp/10;
        }
        return n%(sum+p)==0;

    }
}