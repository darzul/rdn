import string
import requests
import re

url = "http://www.prenoms.com/future-maman-idee-prenom-"
patternF = "<TD bgcolor=\"#FEE5F9\" align='left' width='33%'><A HREF=\"/prenom/(['\-A-Z]+).html"
patternM = "<TD bgcolor=\"#D9EBFF\" align='left' width='33%'><A HREF=\"/prenom/(['\-A-Z]+).html"

def buildUrl(gender, letter, index):
    return url + gender + '-' + letter + '-' + str(index) + ".html"
    
fileF = open('fille.csv', 'w')
fileM = open('garcon.csv', 'w')
for letter in string.ascii_lowercase:

    gender = "fille"
    index = 1
    while True:
        targetUrl = buildUrl(gender, letter, index)
        r = requests.get(targetUrl)

        if not "#FEE5F9" in r.text:
            break
        
        names = re.findall(patternF, r.text)
        
        count = 0
        for name in names:
            count = count + 1
            fileF.write(name + ';')
            
        if count < 60:
            print(targetUrl)

        index = index + 1
    
    gender = "garcon"
    index = 1
    while True:
        targetUrl = buildUrl(gender, letter, index)
        r = requests.get(targetUrl)

        if not "#D9EBFF" in r.text:
            break
        
        names = re.findall(patternM, r.text)

        count = 0
        for name in names:
            count = count + 1
            fileM.write(name + ';')

        if count < 60:
            print(targetUrl)
            
        index = index + 1
        
    print(letter)
    
fileF.close()
fileM.close()
