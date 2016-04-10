class Stable {

  public static void main (String[] argus) {
      System.out.println("Hello world");


      // Create Input variables to hold number of men and women:
      // int choices =0, num=0, n =1, i, j;

      // Print a nice intro the user user

      /* cout<<"~~~~Welcome to the Marriage Algorithm Program~~~~"<<endl;
      cout<<"The rules for this program are as follows:"<<endl;
      cout<<"1. Enter the choices for each man/woman"<<endl;
      cout<<"   The choices must be enterred with a space between each value"<<endl;
      cout<<"2. Do not enter a choice that is not on your list"<<endl;
      cout<<"   This program does not check for such errors"<<endl;
      cout<<"3. Do not repeat a choice for the same man/woman"<<endl;
      cout<<"4. Complete each Rank ordered List (ROL)"<<endl;
      cout<<endl;
      cout<<endl; */

      // Not sure what this five thing is about, probably worth looking into
      // Read in the number of men/women
      /* cout<<"How many men/women ?"<<endl;
      cin >> num;
      while (num>5)
        {
           cout<<"Please don't enter a number greater than 5"<<endl;
          cout<<"Re-enter number:"<<endl;
          cin>>num;
        }//end while
      */

      // Create two jagged arrays, one for men, one for women
      // int m[num][num+1], w[num][num+1];


      //Read choices for men
      /* cout<<"Enter your choices for: "<<endl;
      for (i=0; i<num; i++)
        {
          cout<<"Man "<<i<<": ";
          for (j=0; j<num; j++)
            {
              cin>> choices;
              m[i][j+1]=choices;
            }// end for
        } //end for
      cout<<endl;
      */


      //Read choices for women
      /*for (i=0; i<num; i++)
        {
          cout<<"Woman "<<i<<": ";
          for (j=0; j<num; j++)
            {
              cin>> choices;
              w[i][j+1]=choices;
            }// end for
        } //end for */

      // Actual run the stable matching algorithm:
      //Marriage Algorithm
      /* int man= -1, woman= -1, ugly_man = num +1, temp;
      int index_m= -1, index_w = -1, index_f[num];
      int couples =0;
      for (i=0; i<num; i++)
        index_f[i] = ugly_man;
      i = 1;
      while(couples<num)
      {
       man = couples;
       while (man != ugly_man)
         {
      // used later
      //woman = best choice on man's list
      while (woman == -1)
        {
          if (m[man][i]!= -1)
            {
             index_w = i;
             woman = m[man][i];
      } else
             i++;
        }// end while woman
      // index of woman on man's list
      for (i=0; i<=num; i++)
        {
          if (w[woman][i]== man)
      index_m = i; }
      // if woman prefers man to her fiance
      if (index_m <index_f[woman])
        {
          temp = index_f[woman];
          index_f[woman]= index_m;
          index_m = temp;
          if (index_m == ugly_man)
              man = ugly_man;
       // temp = old fiance
       // fiance = new man
      // man = old fiance
       // man = ugly man
      else
          man = w[woman][index_m];  // man = old fiance
             }// end if index_m
           if (man != ugly_man)
             m[man][index_w] = -1;
           woman = -1;
           i = 1;
         }// end while index_m
       couples++;
      }// end while couples
       // withdraw w from m's list
      //set the woman = -1 (no one)
      //increment the number of couples
      cout<<endl;
      cout<<endl;
      */


      // Print out the reuslts:
      /* cout<<"~~~~~~~~RESULTS~~~~~~~~"<<endl;
      for (i = 0; i<couples; i++)
        cout<<"Couple"<<i+1<<" Man "<<w[i][index_f[i]]<<" & Woman "<<i<<endl;
      }//end main */
  }

}
