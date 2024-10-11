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

directories = ["referat", "color", "sites"]
files = ["referat.md", "color.md", "index.html"]

print("Found", len(lines), "repos")

for d in directories:
    os.makedirs("docs/"+d+"/")

for line in lines:
    line = line.replace("\n", "")
    for d, f in zip(directories, files):
        url = "https://raw.githubusercontent.com/gyarab/"+line+"/refs/heads/main/"+f
        print("Loading", url)

        # save file
        response = httpx.get(url)
        if response.status_code != 200:
            print("Error! Status code", response.status_code)
            continue 

        doc = response.text      

        # sanitize - remove diacritics and space
        filename = line
        extension = f.split(".")[1]

        # get first header as name (if markdown)
        if extension == "md": 
            for fileline in doc.splitlines():
                if(len(fileline) >= 1):
                    if(fileline.lstrip()[0] == '#'):
                        filename = fileline[1:].lstrip()
                        break

        # sanitize
        filename = filename.replace("\"", "").replace("\'", "")

        if os.path.exists("docs/"+d+"/"+filename+".md"):
            os.remove("docs/"+d+"/"+filename+".md")

        file = open("docs/"+d+"/"+filename+".md", "x+", encoding='utf-8')

        # add source
        file.write("*Zdroj referátu: [odkaz](https://github.com/gyarab/"+line+"/refs/heads/main/"+f+")*\n\n")
        # add date of retrieval
        file.write("*Datum: "+datetime.datetime.strftime(datetime.datetime.now(), "%e.%m.%Y, %H:%M:%S UTC")+"*\n\n***\n\n")

        file.write(doc)

        file.close()

        print("Done!")
