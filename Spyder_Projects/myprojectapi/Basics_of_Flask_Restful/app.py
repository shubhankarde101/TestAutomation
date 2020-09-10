# -*- coding: utf-8 -*-
"""
Created on Sun Apr 19 14:22:06 2020

@author: SHUBHANKAR
"""

from flask import Flask
from flask_restful import Api
from Basics_of_Flask_Restful.resources.hellofresco import HelloFresco

app = Flask(__name__)
api = Api(app)

api.add_resource(HelloFresco, '/', '/index/')





if __name__ == '__main__':
    app.run()