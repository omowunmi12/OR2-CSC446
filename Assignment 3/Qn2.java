import java.lang.Math;
import java.util.Arrays;
import java.util.Random;


public class Qn2
{
	public static void main(String args[])
	{
		int count1 = 1000;
		RandomNumberTest ch_test = new RandomNumberTest(count1);
		double[] chi_no = ch_test.myRandomNumber();
		double calpha = 16.9;
		ch_test.ChiTest(calpha,chi_no);    

	}
}