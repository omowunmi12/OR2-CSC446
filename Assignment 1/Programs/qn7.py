import random

Probablity_head= 0.3           
Probablity_tail = 1 - Probablity_head
total_head_count  = 0
total_tail_count  = 0
Total_simulation_count = 10000
for count in range(Total_simulation_count):
    toss = random.random()
    if (toss >= 0 and toss <= Probablity_head):
        total_head_count += 1
    elif (toss >= Probablity_head and toss <= 1):
        total_tail_count += 1
    else:
        continue


Parameter_head_estimated = (total_head_count)/float(Total_simulation_count)
Normalized_Estimated_Error = abs(Probablity_head - Parameter_head_estimated)/ Probablity_head
print "Probability of Head				:" + str(Probablity_head)
print "Probability of Tail				:" + str(Probablity_tail)
print "Count of simulations				:" + str(Total_simulation_count)
print "Simulated Frequency - Head		:" + str(total_head_count)
print "Simulated Frequency - Tail       :" + str(total_tail_count)
print "Estimated Parameter 'p'			:" + str(Parameter_head_estimated)
print "Normalized estimation error		:" + str(Normalized_Estimated_Error)




 
