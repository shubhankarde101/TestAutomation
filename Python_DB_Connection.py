# -*- coding: utf-8 -*-
"""
Created on Fri Sep 11 17:50:12 2020

@author: SHUBHANKAR
"""
import sqlite3
# establishing the connection
con = sqlite3.connect('D:\\TEST.db')
# preparing a cursor object
cursor = con.cursor()
# preparing sql statement
rec = (456789, 'Frodo', 45, 'M', 100000.00)
sql = '''
      INSERT INTO EMPLOYEE VALUES ( ?, ?, ?, ?, ?)'''
# executing sql statement using try ... except blocks
try:
    cursor.execute( '''CREATE TABLE EMPLOYEE (
       EMPID INT(6) NOT NULL,
       NAME CHAR(20) NOT NULL,
       AGE INT,
       SEX CHAR(1),
       INCOME FLOAT
       )''')
    cursor.execute(sql, rec)
    cursor.execute('select * from EMPLOYEE')
    con.commit()
except Exception as e:
    print("Error Message :", str(e))
    con.rollback()
records = cursor.fetchall()
# cl# Displaying the records
for record in records:
    print(record)
#closing the database connection

con.close()


def multipliers():
    return [lambda x : i * x for i in range(4)]

print([m(1) for m in multipliers()])


def bind(func):
    func.data = 9
    return func

@bind
def add(x, y):
    return x + y

print(add(3, 10))
print(add.data)


from functools import wraps

def decorator_func(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        return func(*args, **kwargs)
    return wrapper

@decorator_func
def square(x):
    return x**2

print(square.__name__)





