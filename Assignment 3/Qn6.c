#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(void){
	double randomnumber,arr[10];
	int seed = 82332;
	srand(seed);
	int lamda = 1;
	int r=0;
	printf("SEED is %d \n",seed);
	printf("RAND_MAX is %d \n",RAND_MAX);
	for(r=0;r<10;r++)
		{
		    randomnumber = (rand()/(double)(RAND_MAX));
            arr[r]= (-1)*(1/lamda)*log(1-randomnumber);
            randomnumber = 0;
			printf("   %f \n",arr[r]);
		}
	return 0;
}