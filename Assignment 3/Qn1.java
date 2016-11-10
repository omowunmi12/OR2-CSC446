import java.lang.Math;
import java.util.Arrays;
import java.util.Random;


public class Qn1
{
	public static void main(String args[])
	{
		int count = 10;
		double dalpha = 0.490;
		RandomNumberTest kS_TEST = new RandomNumberTest(count);
		double[] kS_Ri 	= kS_TEST.generate_number();
		kS_TEST.KSTEST(dalpha,kS_Ri);
		int count1 = 1000;
		double calpha = 16.9;
		RandomNumberTest ch_test = new RandomNumberTest(count1);
		double[] chi_no = ch_test.generate_number();
		ch_test.ChiTest(calpha,chi_no);    

	}
}