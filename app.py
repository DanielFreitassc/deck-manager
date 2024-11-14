import streamlit as st
import pandas as pd
import sklearn.linear_model import LinearRegression()
df = pd.read_csv("pizzas.csv")


from sklearn import linear_model

modelo = LinearRegression()
x = df[["diametro"]]
y = df[["preco"]]

modelo.fit(x,y)