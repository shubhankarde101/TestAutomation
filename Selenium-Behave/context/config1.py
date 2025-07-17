import os
import json

settings = None

class Settings(object):
    """Simple singleton class for managing and accessing settings"""
    def __init__(self):
        with open(os.path.join(os.path.dirname(os.path.abspath(__file__)), 'registration.json')) as f:
            settings = json.load(f)
            self.fn = settings['First Name']
            self.ln = settings['Last Name']
            print(settings['First Name'])
            print(settings['Last Name'])




settings = Settings()
