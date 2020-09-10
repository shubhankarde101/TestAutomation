# -*- coding: utf-8 -*-
"""
Created on Wed Aug 19 16:28:05 2020

@author: SHUBHANKAR
"""

from prettytable import PrettyTable
from scipy.stats import chi2_contingency

t = PrettyTable(['Marital Status','Middle school', 'High School','Bachelor','Masters','PhD'])
t.add_row(['Single',18,36,21,9,6])
t.add_row(['Married',12,36,45,36,21])
t.add_row(['Divorced',6,9,9,3,3])
t.add_row(['Widowed',3,9,9,6,3])
print(t)

arr = [[18,36,21,9,6],[12,36,45,36,21],[6,9,9,3,3],[3,9,9,6,3]]
#print(arr)
stat, p, dof, expected = chi2_contingency(arr)

print(stat)
print(dof)
print(p)
# interpret p-value
alpha = 0.05
if p <= alpha:
    print('Reject the Null Hypothesis')
else:
    print('Failed to reject the Null Hypothesis')


from scipy import stats
averagepass=10/1
probability=stats.poisson.pmf(15, averagepass)
print(f'{probability:.25f}')




#n=9
#p=0.80
#k=6
from scipy import stats
probability=stats.binom.pmf(0,4,0.60)
print(1-probability)


from scipy import stats
import numpy as np
import statistics
dic =[9.2,10.7,6.8,9,3.4,5.7,5.7]
print("Mean")
print(np.mean(dic))
print("Median")
print(np.median(dic))
print("Standard Deviation")
print(np.std(dic))
print("Variance")
print(np.var(dic))
print("Mode")
print(statistics.mode(dic))



import math
boy=math.factorial(6)/((math.factorial(3))*math.factorial(3))
girl=math.factorial(5)/((math.factorial(3))*math.factorial(2))
print(boy*girl)


from itertools import combinations
from itertools import permutations
import numpy as np
import math
arr=np.array(['A','B','C','D'])
print(len(list(combinations(arr, 2)) ))
print(len(list(permutations(arr,2) )))











