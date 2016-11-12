/*
Question # 1

This problem is to test the random number generator used in either java or C programs.
Use standard libraries in either of these languages and produce ten random numbers. Then use
Kolmogorv-Smirnov method with a 0.05 level of significance (α) to test whether the random number
generator passes the uniformity test or not by generating ten numbers from your program. What can
you conclude from this? Generate 1000 numbers next and perform a uniformity test by conducting
a Chi-Square test for the same level of significance. 

*/

import java.lang.Math;
import java.util.Arrays;
import java.util.Random;


public class RandomNumberTest

{
	int count;
	public RandomNumberTest(int count)
	{
		this.count = count;
	}
	public double[] generate_number()
	{
		int count = this.count;
		//Variables for KS test
		double[] array 	= new double[count];
		//Declare the Random Generator
		Random randgen = new Random();	

		//Generate Random Numbers 
		for(int i=0;i<count;i++)
		{
			array[i] = randgen.nextDouble();

		}
		Arrays.sort(array);
		return array;
	}

	public double[] myRandomNumber()
	{
		int count = this.count;
		double[] array 	= new double[count];
		
		Random randgen = new Random();
		array[0] = randgen.nextDouble();
		array[1] = randgen.nextDouble();
		System.out.println("X0:" +array[0]);
		System.out.println("X1:" +array[1]);
		int i = 2;
		while(i<=999)
		{
			array[i] = array[i-1] + array[i-2];
 			if(array[i] > 1.0)
 			{
     		array[i] = array[i] - 1;

			}
			i++;
		}
		Arrays.sort(array);
		return array;
	}
	public void KSTEST(double dalpha,double[] kS_Ri)
	{

		//Variables for KS test
		double[] kS_IbyN 	= new double[10];
		double[] kS_Dplus = new double[10];
		double[] kS_Dminus = new double[10];

		//Generate Cumulative Frequencies KS_IbyN
		for(int i=0;i<count;i++)
		{
			kS_IbyN[i]  = (double)(i+1)/count;
		}
		for(int i=0;i<10;i++)
		{
			if((kS_IbyN[i] - kS_Ri[i]) > 0)
				kS_Dplus[i] = kS_IbyN[i] - kS_Ri[i]; 
			else
				kS_Dplus[i] = 0;

			if (i==0)
				kS_Dminus[i] = kS_Ri[i];	
			if (i>0)
			{
				if((kS_Ri[i] -kS_IbyN[i - 1] ) > 0)
					kS_Dminus[i] = kS_Ri[i] -kS_IbyN[i - 1];
				else
					kS_Dminus[i] = 0;
			}
		}
		double max_Dplus = 0.0;
		double max_Dminus= 0.0;
		for(int i=0;i<count;i++)
		{
			if(kS_Dplus[i] > max_Dplus)
				max_Dplus  = kS_Dplus[i];
			if(kS_Dminus[i] >max_Dminus)
				max_Dminus = kS_Dminus[i];
		}
		double max = 0.0;
		if(max_Dplus > max_Dminus)
			max = max_Dplus;
		else
			max = max_Dminus;
		//print all Data
		System.out.println("Ri" +Arrays.toString(kS_Ri));
		System.out.println("i/N" +Arrays.toString(kS_IbyN));
		System.out.println("D+"+Arrays.toString(kS_Dplus));
		System.out.println("D-"+Arrays.toString(kS_Dminus));
		
		if (max > dalpha)
		{
			System.out.println("max(D+,D-): " +max);
			System.out.println("Dalpha: " +dalpha);
			System.out.println("Ho is Rejected");
		}
		else
		{
			System.out.println("max(D+,D-): " +max);
			System.out.println("Dalpha: " +dalpha);
			System.out.println("Ho is not Rejected");
		}
	}
	public void ChiTest(double dalpha,double[] chi_N)
	{
		double[] observedValues = new double[10];
		double[] expectedvalues = new double[10];
		for(int i=0;i<10;i++)
		{
			observedValues[i] = 0;
			expectedvalues[i] = 100;
		}
		for(int n=0;n<count;n++)
		{
			
				if ( chi_N[n]>= 0 && chi_N[n] < 0.1)
					observedValues[0] +=1;
				else if ( chi_N[n]>= 0.1 && chi_N[n] < 0.2)
					observedValues[1] +=1;
				else if ( chi_N[n]>= 0.2 && chi_N[n] < 0.3)
					observedValues[2] +=1;
				else if ( chi_N[n]>= 0.3 && chi_N[n] < 0.4)
					observedValues[3] +=1;
				else if ( chi_N[n]>= 0.4 && chi_N[n] < 0.5)
					observedValues[4] +=1;
				else if ( chi_N[n]>= 0.5 && chi_N[n] < 0.6)
					observedValues[5] +=1;
				else if ( chi_N[n]>= 0.6 && chi_N[n] < 0.7)
					observedValues[6] +=1;
				else if ( chi_N[n]>= 0.7 && chi_N[n] < 0.8)
					observedValues[7] +=1;
				else if ( chi_N[n]>= 0.8 && chi_N[n] < 0.9)
					observedValues[8] +=1;
				else if   ( chi_N[n]>= 0.9 && chi_N[n] < 1.0)
					observedValues[9] +=1;
			
		}
		double xoSquare = 0.0;
		for(int i = 0;i<10;i++)
		{
			System.out.println("Observed["+i+"]) = " + observedValues[i]);
			xoSquare += (Math.pow((observedValues[i]- expectedvalues[i]),2))/expectedvalues[i];
		}
		
		System.out.println("The X0 for Chi-Square test is : " +xoSquare);
		System.out.println("The critical value is: " +dalpha);
		if (xoSquare > dalpha)
			System.out.println("Hypothesis is Rejected");
		else
			System.out.println("Hypothesis is not Rejected");


	}

	public void multCongGenerator(int x0, int a, int m)
	{
		int[] value = new int[m/4];
		value[0] = x0;
		int j =0;
		for(j=1;j<(m/4);j++)
		{
			value[j] = (a * value[j-1])%m;
			if (value[j] == value[0])
				{
				break;
				}
		}
				
		
		System.out.println("Given x0= "+x0+",a= "+a+",m= "+m);
		System.out.println("The Sequence is: " );
		for(int i =0;i<j;i++)
			System.out.println(value[i]);
		
	}

}

