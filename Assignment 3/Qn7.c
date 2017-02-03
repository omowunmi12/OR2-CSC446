#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(void){
	double randomnumber,array[1000];
	int freq[11];
	double relativefreq[11];
	double cumulative[11];
	double exponential_theoretical[11];
	int seed = 99952;
	srand(seed);
	int lamda = 1;
	int r=0,i=0,j=0,k=0;
	printf("SEED is %d \n",seed);
	//printf("RAND_MAX is %d \n",RAND_MAX);
	for(r=0;r<1000;r++)
		{
		    randomnumber = (rand()/(double)(RAND_MAX));
            array[r]= (-1)*(1/lamda)*log(1-randomnumber);
            printf("Numbers");
            printf(" %f\n",array[r]);
            randomnumber = 0;
		}
	for(j=0;j<11;j++)
	{
		freq[j] = 0;

	}
    for(i=0;i<1000;i++)
    {
    	double w = 0.0;
        for(k=0;k<11;k++)
        {
         if(array[i]>w && array[i]<=w+0.5)
            freq[k] = freq[k]+1;
         w += 0.5;
       }
       if(array[i]>5.0)
       	freq[10] = freq[10] + 1;
    }

    printf("The relativefreq are :\n");
    for(i=0;i<11;i++)
    {
    	relativefreq[i] = freq[i]/((double)(1000));
        printf(" %f\n",relativefreq[i]);
    }

    printf("exponential_theoretical_density: \n");
    double w = 0.25;
    for(i=0;i<11;i++)
    {
    	exponential_theoretical[i] = exp(-w);
        printf(" %f\n",exponential_theoretical[i]);
        w = w+0.5;
    }

    printf("The cumulativefreq are :\n");
    for(i=0;i<11;i++)
    {
    	if (i == 0)
    		cumulative[i] = relativefreq[i];
    	else
    		cumulative[i] = relativefreq[i] + cumulative[i-1];
        printf(" %f\n",cumulative[i]);
    }

    printf("exponential_theoretical_cumulative: \n");
    for(i=0;i<11;i++)
    {
        printf(" %f\n",1-exponential_theoretical[i]);
    }

	return 0;


}