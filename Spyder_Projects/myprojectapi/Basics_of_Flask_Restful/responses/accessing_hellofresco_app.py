# -*- coding: utf-8 -*-
"""
Created on Mon Apr 20 16:39:43 2020

@author: SHUBHANKAR
"""
import urllib
from urllib import request


request1 = request.Request('http://127.0.0.1:5000/index/')

try:
    response1 = request.urlopen(request1)
    print(response1.read())

except urllib.error.HTTPError as e:
    print(e.code, e.read())