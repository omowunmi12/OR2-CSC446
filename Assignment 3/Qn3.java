import java.lang.Math;
import java.util.Arrays;
import java.util.Random;


public class Qn3
{
	public static void main(String args[])
	{
		int dummy = 0;
		RandomNumberTest gen = new RandomNumberTest(dummy); 
		//x0,a,m  
		gen.multCongGenerator(7,11,16);
		gen.multCongGenerator(8,11,16);
		gen.multCongGenerator(7,7,16);
		gen.multCongGenerator(8,7,16);

	}
}