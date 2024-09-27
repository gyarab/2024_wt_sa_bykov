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

files_to_load = ["referat.md", "color.md"]

print("Found", len(lines), "repos")

for line in lines:
    line = line.replace("\n", "")
    for f in files_to_load:
        url = "https://raw.githubusercontent.com/gyarab/"+line+"/refs/heads/main/"+f
        print("Loading", url)

        # save file
        response = httpx.get(url)
        if response.status_code != 200:
            print("Error! Status code", response.status_code)
            continue 

        doc = response.text

        if not os.path.exists("temp/"+line+"-"+f):
            file = open("temp/"+line+"-"+f, "x+", encoding='utf-8')
        file.write(doc)
        file.close()

        print("Done!")