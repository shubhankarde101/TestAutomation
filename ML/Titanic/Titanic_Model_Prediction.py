# -*- coding: utf-8 -*-
"""
Created on Sat Aug  1 21:18:31 2020

@author: SHUBHANKAR

My first predictive model to learn from Titanic dataset

"""
import numpy as np # linear algebra
import pandas as pd # data processing, CSV file I/O (e.g. pd.read_csv)
import matplotlib.pyplot as plt

# Reading the CSV file
dt = pd.read_csv("train.csv")
dt_test = pd.read_csv("test.csv")

# Exploratory Data analysis


cols_to_drop = ['PassengerId','Name','Ticket','Cabin']
data_clean = dt.drop(columns=cols_to_drop,axis=1)
data_clean_test = dt_test.drop(columns=cols_to_drop,axis=1)
input_cols = ['Pclass',"Sex","Age","SibSp","Parch","Fare","Embarked"]
output_cols = ["Survived"]
data_clean_x_train = data_clean[input_cols]
data_clean_y_train = data_clean[output_cols]
data_clean_test =  data_clean_test[input_cols]
data_clean_x_train.info()
data_clean_y_train.info()
data_clean_test.info()
from sklearn.preprocessing import LabelEncoder
le = LabelEncoder()
data_clean_x_train['Sex'] = le.fit_transform(data_clean_x_train['Sex'])
data_clean_test['Sex'] = le.fit_transform(data_clean_test['Sex'])
data_clean_x_train['Age'] = data_clean_x_train.fillna(data_clean_x_train['Age'].mean())['Age']
data_clean_x_train.info()
data_clean_test['Age'] = data_clean_test.fillna(data_clean_test['Age'].mean())['Age']
data_clean_test['Fare'] = data_clean_test.fillna(data_clean_test['Fare'].mean())['Fare']
data_clean_test.info()
data_clean_x_train.head()
data_clean_test.head()
data_clean_x_train = data_clean_x_train.fillna(data_clean_x_train['Embarked'].value_counts().index[0])
data_clean_x_train.info()
from sklearn.compose import ColumnTransformer
from sklearn.preprocessing import OneHotEncoder
ct = ColumnTransformer(transformers=[('encoder', OneHotEncoder(), [-1])], remainder='passthrough')
data_clean_x_train = np.array(ct.fit_transform(data_clean_x_train))
data_clean_test = np.array(ct.fit_transform(data_clean_test))



import seaborn as sns
def bar_chart(feature):
    survived = dt[dt['Survived']==1][feature].value_counts()
    dead = dt[dt['Survived']==0][feature].value_counts()
    df = pd.DataFrame([survived,dead])
    df.index = ['Survived','Dead']
    df.plot(kind='bar',stacked=True)

bar_chart('Sex')
bar_chart('SibSp')

co = dt.corr()
print(co)
plt.figure(figsize=(11,11))
sns.heatmap(data_clean.corr(),cmap='rainbow',annot=True)
plt.show()


# Modelling and Training

from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier
dtc = DecisionTreeClassifier(max_depth=4,criterion='entropy')
rfc = RandomForestClassifier(max_depth=5,criterion='entropy',n_estimators=16)
dtc.fit(data_clean_x_train,data_clean_y_train)
dtc.score(data_clean_x_train,data_clean_y_train)
rfc.fit(data_clean_x_train,data_clean_y_train)
rfc.score(data_clean_x_train,data_clean_y_train)
#y_pred = dtc.predict(data_clean_test)
y_pred1 = rfc.predict(data_clean_test)
#print(y_pred)
print(y_pred1)
y_test = []
y_test1 = []
for i in range(data_clean_test.shape[0]):
    #y_test.append([dt_test['PassengerId'][i],y_pred[i]])
    y_test.append([dt_test['PassengerId'][i],y_pred1[i]])
print(y_test)
dt = pd.DataFrame(y_test,columns=['PassengerId','Survived'])
#dt1 = pd.DataFrame(y_test1,columns=['PassengerId','Survived'])
dt.head()
#dt1.head()
dt.to_csv("Submit1.csv",index=False)
#dt1.to_csv("Submit2.csv",index=False)


























































