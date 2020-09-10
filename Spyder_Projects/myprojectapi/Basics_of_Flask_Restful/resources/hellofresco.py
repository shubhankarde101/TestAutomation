# -*- coding: utf-8 -*-
"""
Created on Sun Apr 19 14:22:06 2020

@author: SHUBHANKAR"""

from flask_restful import Resource
class HelloFresco(Resource):
  def get(self):
    return {'message': 'First API testing using Flask Restful'}


