/*--------------------------------------------------------------------------------------*/
/* BS.java  -  Using binary and linear searches to locate a phone number from a text    */
/*             file                                                                     */
/*--------------------------------------------------------------------------------------*/
/*  Author: Enoch Wong                                                                  */
/*  Date: November 23, 2015                                                             */
/*--------------------------------------------------------------------------------------*/
/*  Input: Text file; phone number to search for                                        */
/*  Output: Location of phone number and comparisons required for BS and linear search  */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class BS
{
    //global variable to count the amount of comparisons for the binary search
    public static int comparisons = 0;

    static void read (String[] phonenumber, String line, int[] count) throws IOException
    {
	//read the .txt file and store into an array
	BufferedReader reader = new BufferedReader (new FileReader ("phone.txt"));
	while ((line = reader.readLine ()) != null)
	{
	    phonenumber [count [0]] = line;
	    count [0]++;
	}
    }


    //binary search method
    static int binarysearch (String[] phonenumber, String search, int low, int high)
    {
	//calculate the middle of the array (average the high and the low)
	int middle = (low + high) / 2;

	//SPECIAL BASE CASE: to end the search because "low" is one less than "high" and cannot be divided evenly (due to rounding error) (exempting the first number)
	if (low + 1 == high && high != 1)
	{
	    //adding one to the total comparisons
	    comparisons++;
	    return (-1);
	}

	//BASE CASE: to end the search if the number cannot be found
	else if (low < high)
	{
	    //BASE CASE: if the number is found
	    if (search.equals (phonenumber [middle]))
	    {
		//adding one to the total comparisons
		comparisons++;
		//return the position of the found number
		return middle;
	    }

	    //if the number that is being searched for is greater than the middle number
	    else if (search.compareTo (phonenumber [middle]) > 0)
	    {
		//the new "low" number would be the middle term (that was just searched)
		low = middle;
		//adding one to the total comparisons
		comparisons++;
		//RECURSION with the new "low" number
		return binarysearch (phonenumber, search, low, high);
	    }

	    //if the number that is being searched for is less than the middle number
	    else if (search.compareTo (phonenumber [middle]) < 0)
	    {
		//the new "high" number would be the middle term (that was just searched)
		high = middle;
		//adding one to the total comparisons
		comparisons++;
		//RECURSION with the new "high" number
		return binarysearch (phonenumber, search, low, high);
	    }
	}

	//adding one to the total comparisons
	comparisons++;
	//return a negative number to indicate that the number was not found
	return (-1);
    }


    //linear search method
    static void linearsearch (String[] phonenumber, int[] count, String search)
    {
	//declare that the number has not yet been found
	boolean found = false;

	//for loop to run through the entire array to search for the number
	for (int y = 0 ; y < count [0] ; y++)
	{
	    //if the number is found...
	    if (search.equals (phonenumber [y]))
	    {
		//output for user
		System.out.println ("The number " + search + " was found in line " + (y + 1) + " and required " + (y + 1) + " comparisons using a linear search.");
		//to end the loop
		y = count [0];
		//indicate that the number has been found
		found = true;
	    }
	}

	//if the number has not been found...
	if (found == false)
	{
	    //output for user
	    System.out.println ("The number " + search + " was not found in the list by linear search after " + comparisons + " comparisons.");
	}
    }


    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");

	//declare variables needed for reading the file
	String[] phonenumber = new String [2000];
	String line = null, search;
	int[] count = new int [1];
	count [0] = 0;

	//***READ METHOD***
	//call read method
	read (phonenumber, line, count);

	//ask user for input (what number to find)
	System.out.print ("What is the phone number are you searching for? (xxx-xxx-xxxx): ");
	search = stdin.readLine ();

	//***BINARY METHOD***
	//declare variables needed for binary method
	int low = 0, high = count [0], index;
	//call binary method
	index = binarysearch (phonenumber, search, low, high);

	//output for user...
	if (index >= 0)
	{
	    //location and number of comparisons (if the number was found)
	    System.out.println ("The number " + search + " was found in line " + (index + 1) + " and required " + comparisons + " comparisons using a binary search.");
	}

	else
	{
	    //number not found
	    System.out.println ("The number " + search + " was not found in the list by binary search after " + comparisons + " comparisons.");
	}

	//***LINEAR METHOD***
	//call linear method
	linearsearch (phonenumber, count, search);

	//manners are important
	System.out.println ();
	System.out.println ("Have a nice day.");
    }
}


