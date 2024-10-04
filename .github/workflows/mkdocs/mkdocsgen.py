# inspiration https://github.com/gyarab/2024_wt_chalupnicek/blob/main/download_pages.py

import mkdocs
import httpx
import re
import os
import sys

sys.stdout.reconfigure(encoding="utf-8")

print("MKDOCS GENERATOR")

file = open(".github/workflows/mkdocs/repolist.txt")
lines = file.readlines()
file.close()

directories = ["referat", "color"]

print("Found", len(lines), "repos")

for d in directories:
    os.makedirs(d+"/")

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

        if not os.path.exists("docs/"+d+"/"+line+".md"):
            file = open("docs/"+d+"/"+line+".md", "x+", encoding='utf-8')
        file.write(doc)
        file.close()

        print("Done!")
