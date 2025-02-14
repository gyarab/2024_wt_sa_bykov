# pip install -r requirements.txt


import httpx
import sys

sys.stdout.reconfigure(encoding="utf-8")

print("CNB Exchange rate reader")

response = httpx.get("https://www.cnb.cz/cs/financni-trhy/devizovy-trh/kurzy-devizoveho-trhu/kurzy-devizoveho-trhu/denni_kurz.txt?date=13.02.2025")
if response.status_code != 200:
    print("Error! Status code", presponse.status_code)

#remove junk
doc = response.text.split('\n')[2:-1]

currencies = []

for s in doc:
    val = s.split('|')
    currencies.append(
        (
            val[3],
            float(val[4].replace(',', '.'))/float(val[2]),
        )
    )

while True:
    val = input("EXIT or currency shortcut ")
    if val.upper() == "EXIT":
        exit()

    amnt = int(input("Amount "))

    for c in currencies:
        if c[0] == val.upper():
            print(amnt*c[1])