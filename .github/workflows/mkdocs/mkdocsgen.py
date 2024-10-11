# inspiration https://github.com/gyarab/2024_wt_chalupnicek/blob/main/download_pages.py

import httpx
import os
import sys
import datetime
from unidecode import unidecode

sys.stdout.reconfigure(encoding="utf-8")

print("MKDOCS GENERATOR")

file = open(".github/workflows/mkdocs/repolist.txt")
lines = file.readlines()
file.close()

directories = ["referat", "color"]

print("Found", len(lines), "repos")

for d in directories:
    os.makedirs("docs/"+d+"/")
    # sanitize D - remove diacritics and space
    d = unidecode(d)

for line in lines:
    line = line.replace("\n", "")
    for d in directories:
        url = "https://raw.githubusercontent.com/gyarab/"+line+"/refs/heads/main/"+d+".md"
        print("Loading", url)

        # save file
        response = httpx.get(url)
        if response.status_code != 200:
            print("Error! Status code", response.status_code)
            continue 

        doc = response.text      

        filename = line
        # get first header as name  
        for fileline in doc.splitlines():
            if(len(fileline) >= 1):
                if(fileline.lstrip()[0] == '#'):
                    filename = fileline[1:]
                    break

        if os.path.exists("docs/"+d+"/"+filename+".md"):
            os.remove("docs/"+d+"/"+filename+".md")

        file = open("docs/"+d+"/"+filename+".md", "x+", encoding='utf-8')

        # add source
        file.write("*Zdroj referátu: [odkaz](https://github.com/gyarab/"+line+"/refs/heads/main/"+d+".md)*\n\n")

        # add date of retrieval
        file.write("*Datum: "+datetime.datetime.strftime(datetime.datetime.now(), "%e.%m.%Y, %H:%M:%S")+"*\n\n***\n\n")

        file.write(doc)
        file.close()

        print("Done!")
