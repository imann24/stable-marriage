//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//Samia Qader
//252a-az
//Final Project: The Marriage Algorithm
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~THE MARRIAGE ALGORITHM~~~~~~~~~~~~~~~~~~~~~~~~~~~
//The code for this program has been taken from the pseudocode in Don Knuth's
//book titled "Stable Marriage and Its Relation to Other Combinatorial
//            "Problems"
//The pseudocode is also written in my Power Point Presentation.
//I used a 2-dimensional array to store the values of the rank ordered list
//(ROL). This program does not check for errors such as repeating an entry
//or numbers that are out of range. For example, if there are 3 men, this
//program does not check if the choices have values of <3.
//It also does not check if the user has enterrred a specific choice twice.
//This is a simple C++ program. All code is within the main() function.
//Also, I have not tested this algorithm for large input values (n>5) but
//it should work for larger values.
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// Key for variables:
// m[][]= array storing ROL for men's choices
// w[][]= array storing ROL for men's choices
// man = numerical value associated with man
// woman = numerical value associated with man
// index_m = index of man on woman's ROL
// index_w = index of woman on man's ROL
// index_f = array of indices for women's fiance
//         e.g. if woman 0 is engaged to man 2 index_f[0]=2
// Note: Every woman is temporarily engaged to the ugly man
//       I have given him a value of num+1. He is not stored
//       in the women's ROL.
#include<stdlib.h>
#include<iostream.h>
main()
{
int choices =0, num=0, n =1, i, j;
cout<<"~~~~Welcome to the Marriage Algorithm Program~~~~"<<endl;
cout<<"The rules for this program are as follows:"<<endl;
cout<<"1. Enter the choices for each man/woman"<<endl;
cout<<"   The choices must be enterred with a space between each value"<<endl;
cout<<"2. Do not enter a choice that is not on your list"<<endl;
cout<<"   This program does not check for such errors"<<endl;
cout<<"3. Do not repeat a choice for the same man/woman"<<endl;
cout<<"4. Complete each Rank ordered List (ROL)"<<endl;
cout<<endl;
cout<<endl;
// Read in the number of men/women
cout<<"How many men/women ?"<<endl;
cin >> num;
while (num>5)
  {
     cout<<"Please don't enter a number greater than 5"<<endl;
    cout<<"Re-enter number:"<<endl;
    cin>>num;
  }//end while
int m[num][num+1], w[num][num+1];
//Read choices for men
cout<<"Enter your choices for: "<<endl;
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
//Read choices for women
for (i=0; i<num; i++)
  {
    cout<<"Woman "<<i<<": ";
    for (j=0; j<num; j++)
      {
        cin>> choices;
        w[i][j+1]=choices;
      }// end for
  } //end for
//Marriage Algorithm
int man= -1, woman= -1, ugly_man = num +1, temp;
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
cout<<"~~~~~~~~RESULTS~~~~~~~~"<<endl;
for (i = 0; i<couples; i++)
  cout<<"Couple"<<i+1<<" Man "<<w[i][index_f[i]]<<" & Woman "<<i<<endl;
}//end main
