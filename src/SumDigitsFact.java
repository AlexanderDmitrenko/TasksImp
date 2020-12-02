import java.math.BigInteger;

public class SumDigitsFact {

    private BigInteger factNum = BigInteger.valueOf(100);
    private BigInteger answer;


    public BigInteger getFactNum() {
        return factNum;
    }

    public void setFactNum(BigInteger factNum) {
        this.factNum = factNum;
    }

    public BigInteger getAnswer() {
        return answer;
    }

    //Using the recursion method, find the factorial value
    private BigInteger factorial(BigInteger num){
        if (num.compareTo(BigInteger.valueOf(1)) < 1) return BigInteger.valueOf(1);
        return num.multiply(factorial(num.subtract(BigInteger.valueOf(1))));
    }

    //Using the recursion method, find the sum of all digits of the number
    private BigInteger sumNum(BigInteger num){
        BigInteger tmp = num.divide(BigInteger.valueOf(10));
        if (tmp.compareTo(BigInteger.valueOf(1)) < 0) return num;
        return num.mod(BigInteger.valueOf(10)).add(sumNum(tmp));
    }

    public  void run(){
        BigInteger f = factorial(factNum);
        answer = sumNum(f);
        System.out.println("Sum of all digits of the number "+ factNum + "! = " + answer);
    }
}
