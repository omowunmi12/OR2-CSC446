// Original Code modified to incorporate actual inter arrival time and Service time


import java.util.*;
class Sim {

// Class Sim variables
public static double Clock, MeanInterArrivalTime, MeanServiceTime, SIGMA, LastEventTime,
        TotalBusy, MaxQueueLength, SumResponseTime;

public static double InterArrivalTime;
public static double TotServiceTime;
public static double ServTime;
public static double TotArrivalTime;


// Added For security Counter problem
public static double Avg_Num_Cust_Queue = 0.0;
public static double Avg_Num_Cust_Service = 0.0;
public static double Avg_Num_Cust_System = 0.0;
public static double Avg_wait_time_Queue = 0.0;
public static double Avg_wait_time_Service = 0.0;
public static double Avg_wait_time_System = 0.0;




public static long  NumberOfCustomers, QueueLength, NumberInService,
        TotalCustomers, NumberOfDepartures, LongService;

public final static int arrival = 1;
public final static int departure = 2;

public static EventList FutureEventList;
public static Queue Customers;
public static Random stream;

public static void main(String argv[]) {

  MeanInterArrivalTime = 2.0; MeanServiceTime = 1.6; 
  SIGMA                = 0.6; TotalCustomers  = 50000;
  long seed            = Long.parseLong(argv[0]);

  stream = new Random(seed);           // initialize rng stream
  FutureEventList = new EventList();
  Customers = new Queue();
 
  Initialization();

  // Loop until first "TotalCustomers" have departed
  while(NumberOfDepartures < TotalCustomers ) {
    Event evt = (Event)FutureEventList.getMin();  // get imminent event
    FutureEventList.dequeue();                    // be rid of it
    Clock = evt.get_time();                       // advance simulation time
    if( evt.get_type() == arrival ) 
	{
		ProcessArrival(evt); TotArrivalTime += InterArrivalTime;
	}
    else  {
		ProcessDeparture(evt);
		TotServiceTime += ServTime;
	}	
    }
  ReportGeneration();
 }

 // seed the event list with TotalCustomers arrivals
 public static void Initialization()   { 
  Clock = 0.0;
  QueueLength = 0;
  NumberInService = 0;
  LastEventTime = 0.0;
  TotalBusy = 0 ;
  MaxQueueLength = 0;
  SumResponseTime = 0;
  NumberOfDepartures = 0;
  LongService = 0;
  InterArrivalTime = 0;
  ServTime = 0; 
  TotServiceTime = 0;
  TotArrivalTime = 0;
  // create first arrival event
  Event evt = new Event(arrival, exponential( stream, MeanInterArrivalTime));
  FutureEventList.enqueue( evt );
 }

 public static void ProcessArrival(Event evt) {
  Customers.enqueue(evt); 
  QueueLength++;
  // if the server is idle, fetch the event, do statistics
  // and put into service
  if( NumberInService == 0) ScheduleDeparture();
  else TotalBusy += (Clock - LastEventTime);  // server is busy

  // adjust max queue length statistics
  if (MaxQueueLength < QueueLength) MaxQueueLength = QueueLength;
  // Changing the distribution to exponential
  InterArrivalTime = exponential(stream, MeanInterArrivalTime);
  // schedule the next arrival
  Event next_arrival = new Event(arrival, Clock+InterArrivalTime);
  FutureEventList.enqueue( next_arrival );
  LastEventTime = Clock;
 }

 public static void ScheduleDeparture() {
  double ServiceTime;
  // get the job at the head of the queue
  while (( ServTime = exponential(stream, MeanServiceTime)) < 0 );
  Event depart = new Event(departure,Clock+ServTime);
  FutureEventList.enqueue( depart );
  NumberInService = 1;
  QueueLength--;
 }

public static void ProcessDeparture(Event e) {
 // get the customer description
 Event finished = (Event) Customers.dequeue();
 // if there are customers in the queue then schedule
 // the departure of the next one
  if( QueueLength > 0 ) ScheduleDeparture();
  else NumberInService = 0;
  // measure the response time and add to the sum
  double response = (Clock - finished.get_time());
  SumResponseTime += response;
  if( response > 4.0 ) LongService++; // record long service
  TotalBusy += (Clock - LastEventTime );
  NumberOfDepartures++;
  LastEventTime = Clock;
 }

public static void ReportGeneration() {
double RHO   = TotalBusy/Clock;
double AVGR  = SumResponseTime/TotalCustomers;
double PC4   = ((double)LongService)/TotalCustomers;


// L parameters
Avg_Num_Cust_Queue = (Math.pow(RHO, 2))/(1-RHO);
Avg_Num_Cust_System = (RHO)/(1-RHO);
Avg_Num_Cust_Service = Avg_Num_Cust_System - Avg_Num_Cust_Queue;
double lambda = 1/(TotArrivalTime / TotalCustomers);
// W parameters
Avg_wait_time_Queue = Avg_Num_Cust_Queue/lambda;
Avg_wait_time_System = Avg_Num_Cust_System/lambda;
System.out.println((Avg_wait_time_System));
Avg_wait_time_Service = Avg_wait_time_System - Avg_wait_time_Queue;


System.out.println( "SINGLE SERVER QUEUE SIMULATION - SECURITY CHECKOUT COUNTER ");
System.out.println( "\tMEAN INTERARRIVAL TIME                         " 
	+ MeanInterArrivalTime );
System.out.println( "\tMEAN SERVICE TIME                              " 
	+ MeanServiceTime );
System.out.println( "\tSTANDARD DEVIATION OF SERVICE TIMES            " + SIGMA );
System.out.println( "\tNUMBER OF CUSTOMERS SERVED                     " + TotalCustomers );
System.out.println(); 
System.out.println( "\tSERVER UTILIZATION                             " + RHO );
System.out.println( "\tMAXIMUM LINE LENGTH                            " + MaxQueueLength );
System.out.println( "\tAVERAGE RESPONSE TIME                          " + AVGR + "  MINUTES" );
System.out.println( "\tPROPORTION WHO SPEND FOUR "); 
System.out.println( "\t MINUTES OR MORE IN SYSTEM                     " + PC4 );
System.out.println( "\tSIMULATION RUNLENGTH                           " + Clock + " MINUTES" );
System.out.println( "\tNUMBER OF DEPARTURES                           " + TotalCustomers );

// Calculated Performance metrics for Security Queue of Airport problem
System.out.println( "\t-----------------------------------------------------------");
System.out.println( "\tSIM AVERAGE INTER-ARRIVAL TIME              " + TotArrivalTime / TotalCustomers);
System.out.println( "\tSIM AVERAGE SERVICE TIME                    " + TotServiceTime / TotalCustomers);
System.out.println( "\tSIM UTILISATION RATE FOR SECURITY QUEUE     " + RHO);
System.out.println( "\tSIM TOTAL AVG NO OF CUSTOMERS IN SYSTEM(L)  " + Avg_Num_Cust_System);
System.out.println( "\tSIM TOTAL AVG NO OF CUSTOMERS IN QUEUE (LQ) " + Avg_Num_Cust_Queue);
System.out.println( "\tSIM TOTAL AVG NO OF CUSTOMERS IN SERVICE(LS)" + Avg_Num_Cust_Service);
System.out.println( "\tSIM AVERAGE TIME PER CUSTOMER IN SYSTEM(W)  " + Avg_wait_time_System);
System.out.println( "\tSIM AVERAGE TIME PER CUSTOMER IN QUEUE(WQ)  " + Avg_wait_time_Queue);
System.out.println( "\tSIM AVERAGE TIME PER CUSTOMER IN SERVICE(WS)" + Avg_wait_time_Service);
System.out.println( "\t-----------------------------------------------------------");



}

public static double exponential(Random rng, double mean) {
 return -mean*Math.log( rng.nextDouble() );
}

public static double SaveNormal;
public static int  NumNormals = 0;
public static final double  PI = 3.1415927 ;

public static double normal(Random rng, double mean, double sigma) {
        double ReturnNormal;
        // should we generate two normals?
        if(NumNormals == 0 ) {
          double r1 = rng.nextDouble();
          double r2 = rng.nextDouble();
          ReturnNormal = Math.sqrt(-2*Math.log(r1))*Math.cos(2*PI*r2);
          SaveNormal   = Math.sqrt(-2*Math.log(r1))*Math.sin(2*PI*r2);
          NumNormals = 1;
        } else {
          NumNormals = 0;
          ReturnNormal = SaveNormal;
        }
        return ReturnNormal*sigma + mean ;
 }
}

