import numpy as np
import pandas as pd
import requests
from bs4 import BeautifulSoup
requests.packages.urllib3.disable_warnings()
import warnings
warnings.filterwarnings("ignore")

snapshot_url = 'http://www.profil.mk/categories/wineries_wine/266'
snapshot = requests.get(snapshot_url)

raw_html = snapshot.text
soup = BeautifulSoup(raw_html,'html.parser')

wineries = soup.select('#SearchListContainer .searchItem')
wineries = soup.select('#SearchListContainer .searchItem')

winery_data = []

for winery in wineries:
    winery_info = {}
    winery_info['name'] = winery.select_one('h4 a').text.strip()
    winery_info['address'] = winery.select_one('b:contains("Адреса:")').next_sibling.strip()
    winery_info['city'] = winery.select_one('b:contains("Град:")').next_sibling.strip()
    winery_info['phone'] = winery.select_one('b:contains("Телефон:")').next_sibling.strip()
    winery_info['website'] = winery.select_one('b:contains("Web site:") + a').text.strip()
    winery_info['email'] = winery.select_one('b:contains("E-mail:") + a').text.strip()
    winery_data.append(winery_info)

# Creating a DataFrame using pandas
df = pd.DataFrame(winery_data)

# Save the DataFrame to a CSV file
df.to_csv('winery_data.csv', index=False)
