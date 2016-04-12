/*
 * Authors: Isaiah Mann, Kaylynn Crawford, Zoe Kendall
 * Description: Java Implementation of Stable Marriage problem
 * Adapted from: C++ code by Samia Qader
 */

import java.util.Scanner;

class Stable {

  public static void main (String[] args) {
      Scanner in = new Scanner(System.in);

      // Create Input variables to hold number of men and women:
      int choices =0, num=0, n =1, i, j;
      int [][] men = null, women = null;
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
          // Not sure what this five thing is about, probably worth looking into
          // Read in the number of men/women
          System.out.println("How many men/women ?");
          num = in.nextInt();
          while (num > 5) {
             System.out.println("Please don't enter a number greater than 5");
             System.out.println("Re-enter number:");
             num = in.nextInt();
          }


          // Create two jagged arrays, one for men, one for women
          men = new int[num][num+1];
          women = new int[num][num+1];


          //Read choices for men
          System.out.println("Enter your choices for: ");
          for (i = 0; i < num; i++) {

              System.out.println("Man " + i + ": ");


              for (j = 0; j < num; j++) {
                  choices = in.nextInt();

                  while (choices > num || choices < 0) {
                    System.out.println("Please enter a valid choice for Man " + i + " between 0 and " + (num-1) + ": ");
                    choices = in.nextInt();
                  }

                  men[i][j+1] = choices;
              }

            }

            System.out.println("\n");



            // Read choices for women
            for (i = 0; i < num; i++) {
                System.out.println("Woman " + i + ": ");

                for (j = 0; j < num; j++) {
                    choices = in.nextInt();

                    while (choices > num || choices < 0) {
                      System.out.println("Please enter a valid choice for Woman " + i + " between 0 and " + (num-1) + ": ");
                      choices = in.nextInt();
                    }
                    women[i][j+1] = choices;
                }
            }
          } else {

              System.out.println("write the method to read input from a file");
              System.exit(0);

          }

      // Actual run the stable matching algorithm:
      //Marriage Algorithm
      int man= -1, woman= -1, ugly_man = num + 1, temp;
      int index_m = -1, index_w = -1;
      int [] index_f = new int [num];
      int couples = 0, indexer = 1;

      for (i = 0; i < num; i++) {
          index_f[i] = ugly_man;
      }

      while(couples < num) {
         man = couples;
         while (man != ugly_man) {
           // used later
           //woman = best choice on man's list
           while (woman == -1) {
              if (men[man][indexer]!= -1) {
                  index_w = indexer;
                  woman = men[man][indexer];
              } else {
                  indexer++;
              }
            }// end while woman

            // index of woman on man's list
            for (i = 0; i <= num; i++) {
                System.out.println("W: " + woman + ", " + "i: " + i);
                if (women[woman][i]== man) {
                  index_m = i;
                }
            }

            // if woman prefers man to her fiance
            if (index_m < index_f[woman]) {
                temp = index_f[woman]; // temp = old fiance
                index_f[woman] = index_m; // fiance = new man
                index_m = temp; // man = old fiance

                if (index_m == ugly_man) {
                    man = ugly_man;  // man = ugly man
                } else {
                    man = women[woman][index_m];  // man = old fiance
                }

            } // end if index_m

            if (man != ugly_man) {
              men[man][index_w] = -1;
            }

            woman = -1;
            indexer = 1;
         }// end while index_m

       couples++;

      }// end while couples
       // withdraw w from m's list
      //set the woman = -1 (no one)
      //increment the number of couples
      System.out.println("\n\n");

      // Print out the reuslts:
      System.out.println("~~~~~~~~RESULTS~~~~~~~~");
      for (i = 0; i < couples; i++) {
        System.out.println("Couple " + (i+1) + ": Man " + women[i][index_f[i]] + " & Woman " + i + "\n");
      }//end main */
  }
}
