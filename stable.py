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

def read_int_from_file (inputFile):
    return int(inputFile.readLine())

def is_women_marker (marker):
    return marker == women_marker_in_file

def is_men_marker (marker):
    return marker == men_marker_in_file

def is_marker (marker):
    return (is_women_marker(marker) or
            is_men_marker(marker))

def read_in_choices_from_line (comma_separated_line):
    choices_as_string = comma_separated_line.split(",")

    choices = [i for i in range(len(choices_as_string) + 1)]

    for i in range(0, len(choices_as_string)):
        choices[i + 1] = int(choices_as_string[i])

    return choices;

def read_in_choices_from_file (choices, inputFile):
    next_line = ""
    person_index = 0

    next_line = inputFile.pop(0)
    while (bool(next_line) and not is_marker(next_line)):
        choices[person_index] = read_in_choices_from_line(next_line)

        person_index += 1
        next_line = inputFile.pop(0)

    return next_line


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

    choices_of_men = create_2d_int_array(numberOfEachGender, numberOfEachGender+1)
    choices_of_women = create_2d_int_array(numberOfEachGender, numberOfEachGender+1)

    for i in range (0, numberOfEachGender):
        print ("Man " + str(i) + ": ")

        for j in range (0, numberOfEachGender):
            choices = next_int()

            while (choices >= numberOfEachGender or choices < 0):
                print("Please enter a valid choice for Man " + str(i) + " between 0 and " + str(numberOfEachGender-1) + ": ")
                choices = next_int()
            choices_of_men[i][j+1] = choices

    for i in range (0, numberOfEachGender):
        print ("Woman " + str(i) + ": ")

        for j in range (0, numberOfEachGender):
            choices = next_int()

            while (choices >= numberOfEachGender or choices < 0):
                print("Please enter a valid choice for Woman " + str(i) + " between 0 and " + str(numberOfEachGender-1) + ": ")
                choices = next_int()
            choices_of_women[i][j+1] = choices
else:
    # Opens the input file in read mode
    input_file = open(sys.argv[1], 'r')

    input_text = input_file.read()

    input_lines = input_text.split("\n")

    numberOfEachGender = int(input_lines.pop(0))

    choices_of_women = create_2d_int_array(
        numberOfEachGender,
        numberOfEachGender + 1
    )

    choices_of_men = create_2d_int_array(
        numberOfEachGender,
        numberOfEachGender + 1
    )

    current_choices = None

    marker = input_lines.pop(0)

    if (is_women_marker(marker)):
        current_choices = choices_of_women
    elif (is_men_marker(marker)):
        current_choices = choices_of_men
    else:
        print("Invalid Marker: " + marker + ". Exiting program...")
        sys.exit(0)

    marker = read_in_choices_from_file(current_choices, input_lines)

    if (is_women_marker(marker)):
        current_choices = choices_of_women
    elif (is_men_marker(marker)):
        current_choices = choices_of_men
    else:
        print("Invalid Marker: " + marker + ". Exiting program...")
        sys.exit(0)

    read_in_choices_from_file(current_choices, input_lines)

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
            if (choices_of_men[man][indexer] != -1):
                index_w = indexer
                woman = choices_of_men[man][indexer]
            else:
                indexer += 1

        for i in range(0, numberOfEachGender+1):
            if (choices_of_women[woman][i] == man):
                index_m = i

        if (index_m < index_f[woman]):
            temp = index_f[woman]
            index_f[woman] = index_m
            index_m = temp

            if (index_m == ugly_man):
                man = ugly_man
            else:
                man = choices_of_women[woman][index_m]

        if (man != ugly_man):
            choices_of_men[man][index_w] = -1

        woman = -1
        indexer = 1

    couples += 1
print("\n\n")
#       // Print out the reuslts:
print("~~~~~~~~RESULTS~~~~~~~~")
for count in range(0, couples):
    print("Couple " + str(count+1) + ": Man " + str(choices_of_women[count][index_f[count]]) + " & Woman " + str(count) + "\n")
