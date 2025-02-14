# https://docs.python.org/3/library/turtle.html

import turtle
import math
import random

def printChalupa():
	print("chalupa")

turtleObject = turtle.Turtle()
turtleObject.hideturtle()

SIZE = 50

def drawHouse():
	SCREEN_SIZE = turtle.screensize()
	turtleObject.teleport(random.randint(int(-SCREEN_SIZE[0]/2), int(SCREEN_SIZE[0]/2)), random.randint(int(-SCREEN_SIZE[1]/2), int(SCREEN_SIZE[1]/2)))
	SIZE = random.randint(50, 100) # different sizes of houses

	for i in range(4):
		turtleObject.forward(SIZE)
		turtleObject.left(90)

	turtleObject.left(45)
	turtleObject.forward(math.sqrt(SIZE**2+SIZE**2))
	turtleObject.left(90)

	for i in range(2):
		turtleObject.forward(math.sqrt((SIZE/2)**2+(SIZE/2)**2))
		turtleObject.left(90)

	turtleObject.forward(math.sqrt(SIZE**2+SIZE**2))
	turtleObject.left(45)


turtle.onkey(printChalupa, "c")
turtle.onkey(drawHouse, "d")
turtle.onkey(exit, "Escape")
turtle.listen()

drawHouse()

turtle.mainloop()
