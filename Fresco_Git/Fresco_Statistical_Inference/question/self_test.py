# -*- coding: utf-8 -*-
"""
Created on Thu Sep  3 14:08:46 2020

@author: SHUBHANKAR
"""

import numpy as np

from scipy import stats

from matplotlib import pyplot as plt
x = 0
for i in range(8,11):
  print(i)
  ans = stats.binom.pmf(i,12,0.55)
  x = x+ans


print(x)