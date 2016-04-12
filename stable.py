# Authors: Isaiah Mann, Kaylynn Crawford, Zoe Kendall
# Description: Python Implementation of Stable Marriage problem
# Adapted from: C++ code by Samia Qader

import re
import sys

# UTILITY METHODS:

# Code adapted from http://stackoverflow.com/questions/2397141/how-to-initialize-a-two-dimensional-array-in-python
def create_2d_int_array (x, y):
    return [[0 for i in range(y)] for j in range(x)]

def next_int (message = None):
    if (message == None):
        return int(input())
    else:
        return int(input(message))

# Reading in Input:
choices = 0
num = 0
n = 0
i = 0
j = 0

print("~~~~Welcome to the Marriage Algorithm Program~~~~")
print("The rules for this program are as follows:")
print("1. Enter the choices for each man/woman")
print("   The choices must be enterred with a space between each value")
print("2. Do not enter a choice that is not on your list")
print("   This program does not check for such errors")
print("3. Do not repeat a choice for the same man/woman")
print("4. Complete each Rank ordered List (ROL)\n\n")

# If there is no file passed to the program
if(len(sys.argv) <= 1):

    num = next_int("How many men/women ?\n")
    while (num > 5):
        print("Please don't enter a number greater than 5")
        num = next_int("Re-enter number:\n")

    men = create_2d_int_array(num, num+1)
    women = create_2d_int_array(num, num+1)

    for i in range (0, num):
        print ("Man " + str(i) + ": ")

        for j in range (0, num):
            choices = next_int()

            while (choices >= num or choices < 0):
                print("Please enter a valid choice for Man " + str(i) + " between 0 and " + str(num-1) + ": ")
                choices = next_int()
            men[i][j+1] = choices

    for i in range (0, num):
        print ("Woman " + str(i) + ": ")

        for j in range (0, num):
            choices = next_int()

            while (choices >= num or choices < 0):
                print("Please enter a valid choice for Woman " + str(i) + " between 0 and " + str(num-1) + ": ")
                choices = next_int()
            women[i][j+1] = choices
else:
    print ("Not Yet implemented: Reading input from a file.\nExiting...")
    sys.exit(0)

#  Run Stable Marriage Algorithm:
man = -1
woman = -1
ugly_man = num + 1
temp = 0
index_m = -1
index_w = -1
#Initializer adapated from: http://stackoverflow.com/questions/521674/initializing-a-list-to-a-known-number-of-elements-in-python
index_f = [0] * num
couples = 0
indexer = 1

for i in range (0, num):
    index_f[i] = ugly_man

while (couples < num):

    man = couples
    while (man != ugly_man):

        while (woman == -1):
            print(str(man) + " " + str(indexer))
            if (men[man][indexer] != -1):
                index_w = indexer
                woman = men[man][indexer]
            else:
                indexer += 1

        for i in range(0, num+1):
            if (women[woman][i] == man):
                index_m = i

        if (index_m < index_f[woman]):
            temp = index_f[woman]
            index_f[woman] = index_m
            index_m = temp

            if (index_m == ugly_man):
                man = ugly_man
            else:
                man = women[woman][index_m]

        if (man != ugly_man):
            men[man][index_w] = -1

        woman = -1
        indexer = 1

    couples += 1
print("\n\n")
#       // Print out the reuslts:
print("~~~~~~~~RESULTS~~~~~~~~")
for count in range(0, couples):
    print("Couple " + str(count+1) + ": Man " + str(women[count][index_f[count]]) + " & Woman " + str(count) + "\n")
