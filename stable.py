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

def read_int_from_file (file):
    return int(file.readLine())

def is_women_marker (marker):
    return marker == women_marker_in_file

def is_men_marker (marker):
    return marker == men_marker_in_file

def is_marker (marker):
    return (is_women_marker(marker) or
            is_men_marker(marker))

def read_in_choices_from_line (comma_separated_line):
    choices_as_string = comma_separated_line.split(",")

    choices = [i for i in range(choices_as_string.length + 1)]

    for i in range(0, choices_as_string.length):
        choices[i + 1] = int(choices_as_string[i])

    return choices;

def read_in_choices_from_file (choices, file):
    next_line = ""
    person_index = 0

    while (bool(next_line = file.readLine())):
        # TODO: Finish implementing this function
        return
        
# Reading in Input:
choices = 0
numberOfEachGender = 0
n = 0
i = 0
j = 0

# Markers to denote when men and women appear in the text file
men_marker_in_file = "M";
women_marker_in_file = "W";

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

    numberOfEachGender = next_int("How many men/women ?\n")
    while (numberOfEachGender > 5):
        print("Please don't enter a number greater than 5")
        numberOfEachGender = next_int("Re-enter number:\n")

    choicesOfMen = create_2d_int_array(numberOfEachGender, numberOfEachGender+1)
    choicesOfWomen = create_2d_int_array(numberOfEachGender, numberOfEachGender+1)

    for i in range (0, numberOfEachGender):
        print ("Man " + str(i) + ": ")

        for j in range (0, numberOfEachGender):
            choices = next_int()

            while (choices >= numberOfEachGender or choices < 0):
                print("Please enter a valid choice for Man " + str(i) + " between 0 and " + str(numberOfEachGender-1) + ": ")
                choices = next_int()
            choicesOfMen[i][j+1] = choices

    for i in range (0, numberOfEachGender):
        print ("Woman " + str(i) + ": ")

        for j in range (0, numberOfEachGender):
            choices = next_int()

            while (choices >= numberOfEachGender or choices < 0):
                print("Please enter a valid choice for Woman " + str(i) + " between 0 and " + str(numberOfEachGender-1) + ": ")
                choices = next_int()
            choicesOfWomen[i][j+1] = choices
else:
    # Opens the input file in read mode
    inputFile = open(sys.argv[1], 'r')

    numberOfEachGender = read_int_from_file(inputFile)

    choicesOfWomen = create_2d_int_array(
        numberOfEachGender,
        numberOfEachGender + 1
    )

    choicesOfMen = create_2d_int_array(
        numberOfEachGender,
        numberOfEachGender + 1
    )

    currentChoices = None

    marker = inputFile.readLine()


    print ("Not Yet fully implemented: Reading input from a file.\nExiting...")
    sys.exit(0)

#  Run Stable Marriage Algorithm:
man = -1
woman = -1
ugly_man = numberOfEachGender + 1
temp = 0
index_m = -1
index_w = -1
#Initializer adapated from: http://stackoverflow.com/questions/521674/initializing-a-list-to-a-known-numberOfEachGenderber-of-elements-in-python
index_f = [0] * numberOfEachGender
couples = 0
indexer = 1

for i in range (0, numberOfEachGender):
    index_f[i] = ugly_man

while (couples < numberOfEachGender):

    man = couples
    while (man != ugly_man):

        while (woman == -1):
            print(str(man) + " " + str(indexer))
            if (choicesOfMen[man][indexer] != -1):
                index_w = indexer
                woman = choicesOfMen[man][indexer]
            else:
                indexer += 1

        for i in range(0, numberOfEachGender+1):
            if (choicesOfWomen[woman][i] == man):
                index_m = i

        if (index_m < index_f[woman]):
            temp = index_f[woman]
            index_f[woman] = index_m
            index_m = temp

            if (index_m == ugly_man):
                man = ugly_man
            else:
                man = choicesOfWomen[woman][index_m]

        if (man != ugly_man):
            choicesOfMen[man][index_w] = -1

        woman = -1
        indexer = 1

    couples += 1
print("\n\n")
#       // Print out the reuslts:
print("~~~~~~~~RESULTS~~~~~~~~")
for count in range(0, couples):
    print("Couple " + str(count+1) + ": Man " + str(choicesOfWomen[count][index_f[count]]) + " & Woman " + str(count) + "\n")
