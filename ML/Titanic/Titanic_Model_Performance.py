# -*- coding: utf-8 -*-
"""
Created on Sat Aug  1 21:18:31 2020

@author: SHUBHANKAR

My first predictive model to learn from Titanic dataset

"""
import numpy as np # linear algebra
import pandas as pd # data processing, CSV file I/O (e.g. pd.read_csv)
import matplotlib.pyplot as plt
import seaborn as sns

# Reading the CSV file
dt = pd.read_csv("train.csv")
print(dt.shape)

'''data = pd.read_csv("train.csv", index_col ="Fare")
# retrieving multiple columns by indexing operator
first = data[["Pclass", "Cabin", "Age"]]
print(first.shape)'''




# Exoloratory data analysis
dt['Cabin']=dt['Cabin'].fillna(0)
x =dt['Cabin']
for i in range(0,len(x)):
    if(x[i]!=0):
       x[i]=1
dt['Cabin'] = x

cols_to_drop = ['PassengerId','Name','Ticket']
data_clean = dt.drop(columns=cols_to_drop,axis=1)

def impute_age(cols):
    Age = cols[0]
    Pclass = cols[1]

    if pd.isnull(Age):

        if Pclass == 1:
            return 37

        elif Pclass == 2:
            return 29

        else:
            return 24
    else:
        return Age


input_cols = ['Pclass',"Sex","Age","SibSp","Parch","Fare","Embarked", "Cabin"]
output_cols = ["Survived"]
data_clean_x = data_clean[input_cols]
data_clean_y = data_clean[output_cols]

data_clean_x['Age'] = data_clean_x[['Age','Pclass']].apply(impute_age,axis=1)
data_clean_x['Fare'] = data_clean_x.fillna(data_clean_x['Fare'].mean())['Fare']
data_clean_x = data_clean_x.fillna(data_clean_x['Embarked'].value_counts().index[0])

sex = pd.get_dummies(data_clean_x['Sex'],drop_first=True)
embark = pd.get_dummies(data_clean_x['Embarked'],drop_first=True)
data_clean_x.drop(['Sex','Embarked'],axis=1,inplace=True)
data_clean_x = pd.concat([data_clean_x,sex,embark],axis=1)
data_clean_x.head()
data_clean_x.info()
data_clean_y.head()
data_clean_y.info()

#apply SelectKBest class to extract top 10 best features
from sklearn.feature_selection import SelectKBest
from sklearn.feature_selection import chi2
bestfeatures = SelectKBest(score_func=chi2, k=6)
fit = bestfeatures.fit(data_clean_x,data_clean_y)
dfscores = pd.DataFrame(fit.scores_)
dfcolumns = pd.DataFrame(data_clean_x.columns)
#concat two dataframes for better visualization
featureScores = pd.concat([dfcolumns,dfscores],axis=1)
featureScores.columns = ['Specs','Score']  #naming the dataframe columns
print(featureScores.nlargest(6,'Score'))  #print 10 best features


# Splitting the dataset into the Training set and Test set
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(data_clean_x, data_clean_y, test_size = 0.25, random_state = 0)

'''from sklearn.preprocessing import LabelEncoder
le = LabelEncoder()
data_clean_x_train['Sex'] = le.fit_transform(data_clean_x_train['Sex'])
data_clean_test['Sex'] = le.fit_transform(data_clean_test['Sex'])'''


'''from sklearn.compose import ColumnTransformer
from sklearn.preprocessing import OneHotEncoder
ct = ColumnTransformer(transformers=[('encoder', OneHotEncoder(), [-1])], remainder='passthrough')
data_clean_x_train = np.array(ct.fit_transform(data_clean_x_train))
data_clean_x_train = data_clean_x_train[1:]
data_clean_test =    np.array(ct.fit_transform(data_clean_test))
data_clean_test = data_clean_test[1:]'''

# Feature Scaling
from sklearn.preprocessing import StandardScaler
sc = StandardScaler()
X_train = sc.fit_transform(X_train)
X_test = sc.transform(X_test)
print(X_train)
print(X_test)

# Modelling and Training
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.linear_model import LogisticRegression

# Decission Tree
dtc = DecisionTreeClassifier(criterion='entropy')

# Random Forest
#max_depth=5,criterion='entropy',n_estimators=10
rfc = RandomForestClassifier()

# Hyperparameter Optimization using Randomized Search CV
print(rfc.get_params())
# Number of trees in random forest
n_estimators = [int(x) for x in np.linspace(start = 200, stop = 2000, num = 10)]
# Number of features to consider at every split
max_features = ['auto', 'sqrt']
# Maximum number of levels in tree
max_depth = [int(x) for x in np.linspace(10, 110, num = 11)]
max_depth.append(None)
# Minimum number of samples required to split a node
min_samples_split = [2, 5, 10]
# Minimum number of samples required at each leaf node
min_samples_leaf = [1, 2, 4]
# Method of selecting samples for training each tree
bootstrap = [True, False]
# Create the random grid
random_grid = {'n_estimators': n_estimators,
               'max_features': max_features,
               'max_depth': max_depth,
               'min_samples_split': min_samples_split,
               'min_samples_leaf': min_samples_leaf,
               'bootstrap': bootstrap}
print(random_grid)
from sklearn.model_selection import RandomizedSearchCV
rf_random = RandomizedSearchCV(estimator = rfc, param_distributions = random_grid, n_iter = 10, cv = 3, verbose=2, random_state=0, n_jobs = -1)

# Logistic Regression
lr = LogisticRegression(random_state = 0)

# Training the model
dtc.fit(X_train,y_train)
dtc.score(X_train,y_train)


rf_random.fit(X_train,y_train)



lr.fit(X_train,y_train)
lr.score(X_train,y_train)

y_pred_dt = dtc.predict(X_test)
y_pred = rf_random.best_estimator_.predict(X_test)
y_pred_lr= lr.predict(X_test)

from sklearn.metrics import confusion_matrix
accuracy=confusion_matrix(y_test,y_pred)
print(accuracy)

from sklearn.metrics import accuracy_score
accuracy=accuracy_score(y_test,y_pred)
print("Accuracy is: ")
print(accuracy*100)

# Applying K Fold Cross validation
'''from sklearn.model_selection import cross_val_score
accuracies = cross_val_score(estimator = rfc, X = X_train, y = y_train, cv = 10)
print(accuracies)
print("Accuracy: {:.2f} %".format(accuracies.mean()*100))
print("Standard Deviation: {:.2f} %".format(accuracies.std()*100))'''


'''from sklearn.model_selection import cross_val_score
accuracies = cross_val_score(estimator = dtc, X = X_train, y = y_train, cv = 10)
print(accuracies)
print("Accuracy: {:.2f} %".format(accuracies.mean()*100))
print("Standard Deviation: {:.2f} %".format(accuracies.std()*100))


from sklearn.model_selection import cross_val_score
accuracies = cross_val_score(estimator = lr, X = X_train, y = y_train, cv = 10)
print(accuracies)
print("Accuracy: {:.2f} %".format(accuracies.mean()*100))
print("Standard Deviation: {:.2f} %".format(accuracies.std()*100))'''
























































