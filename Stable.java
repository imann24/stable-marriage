/*
 * Authors: Isaiah Mann, Kaylynn Crawford, Zoe Kendall
 * Description: Java Implementation of Stable Marriage problem
 * Adapted from: C++ code by Samia Qader
 */

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

class Stable {

  // Markers to denote when men and women appear in the text file
  static final String menMarkerInFile = "M";
  static final String womenMarkerInFile = "W";

  public static void main (String[] args) {
      Scanner in = null;

      // Create Input variables to hold numberOfEachGender ber of choicesOfMen and choicesOfWomen:
      int choices =0, numberOfEachGender =0, n =1, i, j;
      int [][] choicesOfMen = null, choicesOfWomen = null;

      // Print a nice intro the user user
      System.out.println("~~~~Welcome to the Marriage Algorithm Program~~~~");
      System.out.println("The rules for this program are as follows:");
      System.out.println("1. Enter the choices for each man/woman");
      System.out.println("   The choices must be enterred with a space between each value");
      System.out.println("2. Do not enter a choice that is not on your list");
      System.out.println("   This program does not check for such errors");
      System.out.println("3. Do not repeat a choice for the same man/woman");
      System.out.println("4. Complete each Rank ordered List (ROL)\n\n");

       if(args.length == 0) {
         in = new Scanner(System.in);

          // Not sure what this five thing is about, probably worth looking into
          // Read in the numberOfEachGender ber of choicesOfMen/choicesOfWomen
          System.out.println("How many men/women ?");

          numberOfEachGender = in.nextInt();

          // Ensure the numberOfEachGender ber is no greater than five
          while (numberOfEachGender  > 5) {
             System.out.println("Please don't enter a number greater than 5");
             System.out.println("Re-enter number:");
             numberOfEachGender  = in.nextInt();
          }


          // Create two jagged arrays, one for choicesOfMen, one for choicesOfWomen
          choicesOfMen = new int[numberOfEachGender ][numberOfEachGender +1];
          choicesOfWomen = new int[numberOfEachGender ][numberOfEachGender +1];


          //Read choices for choicesOfMen
          System.out.println("Enter your choices for: ");
          for (i = 0; i < numberOfEachGender ; i++) {

              System.out.println("Man " + i + ": ");


              for (j = 0; j < numberOfEachGender ; j++) {
                  choices = in.nextInt();

                  while (choices >= numberOfEachGender  || choices < 0) {
                    System.out.println("Please enter a valid choice for Man " + i + " between 0 and " + (numberOfEachGender -1) + ": ");
                    choices = in.nextInt();
                  }

                  choicesOfMen[i][j+1] = choices;
              }

            }

            System.out.println("\n");



            // Read choices for choicesOfWomen
            for (i = 0; i < numberOfEachGender ; i++) {
                System.out.println("Woman " + i + ": ");

                for (j = 0; j < numberOfEachGender ; j++) {
                    choices = in.nextInt();

                    while (choices >= numberOfEachGender  || choices < 0) {
                      System.out.println("Please enter a valid choice for Woman " + i + " between 0 and " + (numberOfEachGender -1) + ": ");
                      choices = in.nextInt();
                    }
                    choicesOfWomen[i][j+1] = choices;
                }
            }
          } else {

              try {
                in = new Scanner(new FileReader(args[0]));
              } catch (FileNotFoundException e) {
                System.out.println("File: " + args[0] + " not found. Exiting program...");
                System.exit(0);
              }

              numberOfEachGender = Integer.parseInt(in.nextLine());

              choicesOfMen = new int [numberOfEachGender][numberOfEachGender + 1];
              choicesOfWomen = new int [numberOfEachGender][numberOfEachGender + 1];

              int[][] currentChoices = null;

              String marker = in.nextLine();
              // Allows for arbitrary ordering: choicesOfMen or choicesOfWomen can be first based off markers
              if (isWomenMarker(marker)) {
                currentChoices = choicesOfWomen;
              } else if (isMenMarker(marker)) {
                currentChoices = choicesOfMen;
              } else {
                System.out.println("Invalid marker: " + marker + ". Exiting the program...");
                System.exit(0);
              }

              String proceedingLine = readInChoicesFromScanner(currentChoices, in);

              if (isWomenMarker(proceedingLine)) {
                currentChoices = choicesOfWomen;
              } else if (isMenMarker(proceedingLine)) {
                currentChoices = choicesOfMen;
              } else {
                System.out.println("Invalid marker: " + proceedingLine + ". Exiting the program...");
                System.exit(0);
              }

              readInChoicesFromScanner(currentChoices, in);
          }

      int[][] proposers = null;
      int[][] acceptors = null;

      System.out.println("Who should propose:");
      System.out.println("1). The Women?");
      System.out.println("2). The Men?");
      in = new Scanner(System.in);

      int proposerChoice = in.nextInt();

      while (proposerChoice != 1 && proposerChoice != 2) {
        System.out.println("Please enter a valid number (1 or 2)");
        proposerChoice = in.nextInt();
      }

      if (proposerChoice == 1) {
        proposers = choicesOfWomen;
        acceptors = choicesOfMen;
      } else if (proposerChoice == 2) {
        proposers = choicesOfMen;
        acceptors = choicesOfWomen;
      }


      // Actually run the stable matching algorithm:
      //Marriage Algorithm
      int proposer= -1, acceptor= -1, uglyProposer = numberOfEachGender  + 1, temp;
      int indexOfProposer = -1, indexOfAcceptor = -1;
      int [] indexesOfAcceptors = new int [numberOfEachGender];
      int couples = 0, indexer = 1;

      for (i = 0; i < numberOfEachGender ; i++) {
          indexesOfAcceptors[i] = uglyProposer;
      }

      while(couples < numberOfEachGender ) {

         proposer = couples;
         while (proposer != uglyProposer) {
           // used later
           // acceptor = best choice on proposer's list
           while (acceptor == -1) {
              if (proposers[proposer][indexer]!= -1) {
                  indexOfAcceptor = indexer;
                  acceptor = proposers[proposer][indexer];
              } else {
                  indexer++;
              }
            }// end while acceptor

            // index of acceptor on proposer's list
            for (i = 0; i <= numberOfEachGender ; i++) {
                if (acceptors[acceptor][i]== proposer) {
                  indexOfProposer = i;
                }
            }

            // if acceptor prefers proposer to her fiance
            if (indexOfProposer < indexesOfAcceptors[acceptor]) {
                temp = indexesOfAcceptors[acceptor]; // temp = old fiance
                indexesOfAcceptors[acceptor] = indexOfProposer; // fiance = new proposer
                indexOfProposer = temp; // proposer = old fiance

                if (indexOfProposer == uglyProposer) {
                    proposer = uglyProposer;  // proposer = ugly proposer
                } else {
                    proposer = acceptors[acceptor][indexOfProposer];  // proposer = old fiance
                }

            } // end if indexOfProposer

            if (proposer != uglyProposer) {
              proposers[proposer][indexOfAcceptor] = -1;
            }

            acceptor = -1;
            indexer = 1;
         }// end while indexOfProposer

       couples++;

      }// end while couples
       // withdraw w from m's list
      //set the acceptor = -1 (no one)
      //increment the numberOfEachGender ber of couples
      System.out.println("\n\n");

      // Print out the reuslts:
      System.out.println("~~~~~~~~RESULTS~~~~~~~~");
      if (proposerChoice == 1) {
        for (i = 0; i < couples; i++) {
          System.out.println("Couple " + (i+1) + ": Woman " + choicesOfMen[i][indexesOfAcceptors[i]] + " & Man " + i + "\n");
        }
      } else if (proposerChoice == 2) {
        for (i = 0; i < couples; i++) {
          System.out.println("Couple " + (i+1) + ": Man " + choicesOfWomen[i][indexesOfAcceptors[i]] + " & Woman " + i + "\n");
        }
      }

      //end main
  }

  // Returns the last line it reads in (presumably a marker for choicesOfMen or choicesOfWomen)
  static String readInChoicesFromScanner (int[][] choices, Scanner scanner) {
      String nextLine = "";
      int personIndex = 0;
      while (scanner.hasNextLine() && !isMarker(nextLine = scanner.nextLine())) {

        choices[personIndex] = readInChoicesFromLine(nextLine);

        personIndex++;

      }

      return nextLine;
  }

  static int[] readInChoicesFromLine (String commaSeparatedLine) {
    String[] choicesAsString = commaSeparatedLine.split(",");

    // Allow for an offset of one to match the current style
    int[] choices = new int [choicesAsString.length + 1];

    for (int i = 0; i < choicesAsString.length; i++) {
      choices[i + 1] = Integer.parseInt(choicesAsString[i]);
    }

    return choices;

  }

  static boolean isWomenMarker (String marker) {
    return (marker.equals(womenMarkerInFile));
  }

  static boolean isMenMarker (String marker) {
    return (marker.equals(menMarkerInFile));
  }

  static boolean isMarker (String marker) {
    return isMenMarker(marker) || isWomenMarker(marker);
  }
}
