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

	turtleObject.forward(SIZE)
	turtleObject.left(90)
	turtleObject.forward(SIZE)
	turtleObject.left(90)
	turtleObject.forward(SIZE)
	turtleObject.left(90)
	turtleObject.forward(SIZE)
	turtleObject.left(135)
	turtleObject.forward(math.sqrt(SIZE**2+SIZE**2))
	turtleObject.left(90)
	turtleObject.forward(math.sqrt((SIZE/2)**2+(SIZE/2)**2))
	turtleObject.left(90)
	turtleObject.forward(math.sqrt((SIZE/2)**2+(SIZE/2)**2))
	turtleObject.left(90)
	turtleObject.forward(math.sqrt(SIZE**2+SIZE**2))
	turtleObject.left(45)


turtle.onkey(printChalupa, "c")
turtle.onkey(drawHouse, "d")
turtle.listen()

drawHouse()

turtle.mainloop()
