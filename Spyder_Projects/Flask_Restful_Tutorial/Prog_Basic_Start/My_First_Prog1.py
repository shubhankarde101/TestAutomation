# -*- coding: utf-8 -*-
"""
Created on Fri Apr 17 21:58:53 2020
@author: SHUBHANKAR
"""

from flask import Flask
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)

class HelloFresco(Resource):
  def get(self):
    return {'message': 'Welcome to Fresco Play!!!'}

api.add_resource(HelloFresco, '/')

if __name__ == '__main__':
    app.run()


