# -*- coding: utf-8 -*-
"""
Created on Fri Aug 21 21:51:46 2020

@author: SHUBHANKAR
"""


#Python program to scrape website
#and save quotes from website
'''import requests
from bs4 import BeautifulSoup
import csv

URL = "http://www.values.com/inspirational-quotes"
r = requests.get(URL)

soup = BeautifulSoup(r.content, 'html5lib')

quotes=[]  # a list to store quotes

table = soup.find('div', attrs = {'id':'all_quotes'})

for row in table.findAll('div',
                         attrs = {'class':'col-6 col-lg-3 text-center margin-30px-bottom sm-margin-30px-top'}):
    quote = {}
    quote['theme'] = row.h5.text
    quote['url'] = row.a['href']
    quote['img'] = row.img['src']
    quote['lines'] = row.img['alt'].split(" #")[0]
    quote['author'] = row.img['alt'].split(" #")[1]
    quotes.append(quote)

filename = 'inspirational_quotes.csv'
with open(filename, 'w', newline='') as f:
    w = csv.DictWriter(f,['theme','url','img','lines','author'])
    w.writeheader()
    for quote in quotes:
        w.writerow(quote)'''

##############################################################################################################################3

from bs4 import BeautifulSoup as soup  # HTML data structure
from urllib.request import urlopen as uReq  # Web client

# URl to web scrap from.
# in this example we web scrap graphics cards from Newegg.com
page_url = "http://www.newegg.com/Product/ProductList.aspx?Submit=ENE&N=-1&IsNodeId=1&Description=GTX&bop=And&Page=1&PageSize=36&order=BESTMATCH"

# opens the connection and downloads html page from url
uClient = uReq(page_url)

# parses html into a soup data structure to traverse html
# as if it were a json data type.
page_soup = soup(uClient.read(), "html.parser")
uClient.close()

# finds each product from the store page
containers = page_soup.findAll("div", {"class": "item-container"})

# name the output file to write to local disk
out_filename = "graphics_cards.csv"
# header of csv file to be written
headers = "brand,product_name,shipping \n"

# opens file, and writes headers
f = open(out_filename, "w")
f.write(headers)

# loops over each product and grabs attributes about
# each product
for container in containers:
    # Finds all link tags "a" from within the first div.
    make_rating_sp = container.div.select("a")

    # Grabs the title from the image title attribute
    # Then does proper casing using .title()
    brand = make_rating_sp[0].img["title"].title()

    # Grabs the text within the second "(a)" tag from within
    # the list of queries.
    product_name = container.div.select("a")[2].text

    # Grabs the product shipping information by searching
    # all lists with the class "price-ship".
    # Then cleans the text of white space with strip()
    # Cleans the strip of "Shipping $" if it exists to just get number
    shipping = container.findAll("li", {"class": "price-ship"})[0].text.strip().replace("$", "").replace(" Shipping", "")

    # prints the dataset to console
    print("brand: " + brand + "\n")
    print("product_name: " + product_name + "\n")
    print("shipping: " + shipping + "\n")

    # writes the dataset to file
    f.write(brand + ", " + product_name.replace(",", "|") + ", " + shipping + "\n")

f.close()  # Close the file
##################################################################################################################
# Fresco Project


from bs4 import BeautifulSoup
import csv
import requests
import time
import pandas as pd
import urllib
import re
import pickle
from lxml import html

headers =   ['Index','Average temperature (°F)', 'Average humidity (%)',
 'Average dewpoint (°F)', 'Average barometer (in)',
 'Average windspeed (mph)', 'Average gustspeed (mph)',
 'Average direction (°deg)', 'Rainfall for month (in)',
 'Rainfall for year (in)', 'Maximum rain per minute',
 'Maximum temperature (°F)', 'Minimum temperature (°F)',
 'Maximum humidity (%)', 'Minimum humidity (%)', 'Maximum pressure',
 'Minimum pressure', 'Maximum windspeed (mph)',
 'Maximum gust speed (mph)', 'Maximum heat index (°F)']

data = pd.DataFrame(columns = headers)

def fill_data_Index(url,l,year,month):
    r = requests.get(url)
    tree = html.fromstring(r.content)
    days = tree.xpath("//td[contains(., 'Average and Extremes') and not(contains(.,'for Month of'))]/text()")
    type(days)
    for i in range (len(days)):
        res = [int(j) for j in days[i].split() if j.isdigit()]
        days[i] = str(year)+'-'+str(month)+'-'+str(res[0])
    l.append(days)


def fill_data_Other_Attrbutes(url, xpath_param, col_param, lo):
    r = requests.get(url)
    tree = html.fromstring(r.content)
    at = tree.xpath("//td[contains(., 'Average and Extremes') and not(contains(.,'for Month of'))]//parent::tr//following-sibling::tr//td[.='"+xpath_param+"']//following-sibling::td//text()")
    type(at)
    lo.append(at)

year_diff= (2018-2009+1)
li_index=[]
li_1,li_2,li_3,li_4,li_5,li_6,li_7,li_8,li_9,li_10,li_11,li_12,li_13,li_14,li_15,li_16,li_17,li_18,li_19=[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[]
for i in range(year_diff):
    year = 2009+i
    for i in range (12):
        if(i<=8):
           yearmmonth = 'http://www.estesparkweather.net/archive_reports.php?date='+str(year)+str(0)+str(i+1)
           fill_data_Index(yearmmonth,li_index,year,str(0)+str(i+1))

        else:
           yearmmonth = 'http://www.estesparkweather.net/archive_reports.php?date='+str(year)+str(i+1)
           fill_data_Index(yearmmonth,li_index,year,str(i+1))

        fill_data_Other_Attrbutes(yearmmonth,'Average temperature', 'Average temperature (°F)',li_1)
        fill_data_Other_Attrbutes(yearmmonth,'Average humidity', 'Average humidity (%)',li_2)
        fill_data_Other_Attrbutes(yearmmonth,'Average dewpoint', 'Average dewpoint (°F)',li_3)
        fill_data_Other_Attrbutes(yearmmonth,'Average barometer', 'Average barometer (in)',li_4)
        fill_data_Other_Attrbutes(yearmmonth,'Average windspeed', 'Average windspeed (mph)',li_5)
        fill_data_Other_Attrbutes(yearmmonth,'Average gutspeed', 'Average gustspeed (mph)',li_6)

        print(yearmmonth)

        if(year == 2009 and i==5):
            break


    if(year == 2009):
         break















