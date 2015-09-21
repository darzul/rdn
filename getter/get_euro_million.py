import string
import requests
import re

url = "http://www.lotto.net/euromillions/results/"
patternBalls = "class=\"ball (ball|lucky-star)\"><span>([0-9]{1,2})"
patternEuro = "€([0-9,]+)"
patternDate = "<span>[A-Za-z]+</span>([A-Za-z 0-9]+)"

def buildUrl(year):
    return url + year
    
fileEuroM = open('euro_million.csv', 'w')
for year in range(2004,2016):
    targetUrl = buildUrl(str(year))
    r = requests.get(targetUrl)

    balls = re.findall(patternBalls, r.text)
    euroList = re.findall(patternEuro, r.text)
    dateList = re.findall(patternDate, r.text)
    count = 0;
    
    print(len(euroList))
    print(len(dateList))
    for ball in balls:
        if count % 7 == 0:
            fileEuroM.write(euroList[int(count / 7)] + ';')
            fileEuroM.write(dateList[int(count / 7)] + ';')
        fileEuroM.write(str(ball[1]) + ' ')
        count = count + 1
        if count % 7 == 0:
            fileEuroM.write('\n')
        
    print(year)
    
fileEuroM.close()
