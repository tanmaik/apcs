// Name: J1-10      
// Date: 12/17/20
// This program takes a text file, creates an index (by line numbers)
// for all the words in the file and writes the index
// into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.io.*;

public class IndexMaker
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(inFileName));
      String outFileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      indexDocument(inputFile, outputFile);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
   {
      DocumentIndex index = new DocumentIndex();
      String line = null;
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      for(IndexEntry entry : index)
         outputFile.println(entry);
   }   
}
class DocumentIndex extends ArrayList<IndexEntry>
{
    //constructors
    public DocumentIndex () {
    
    }
    
  /** extracts all the words from str, skipping punctuation and whitespace 
 and for each word calls addWord().  In this situation, a good way to 
 extract while also skipping punctuation is to use String's split method, 
 e.g., str.split("[., \"!?]")       */
   public void addAllWords(String str, int lineNum) 
   {
      String[] words1 = str.split("[., \"!?]");
      for (String words : words1) {
         addWord(words, lineNum);
      }
      
   }
    
   /** calls foundOrInserted, which returns a position.  At that position,  
   updates that IndexEntry's list of line numbers with lineNum. */
   public void addWord(String word, int lineNum)
   {
      int i = foundOrInserted(word);
      this.get(i).add(lineNum); 
   }
        
    /** traverses this DocumentIndex and compares word to the words in the 
    IndexEntry objects in this list, looking for the correct position of 
    word. If an IndexEntry with word is not already in that position, the 
    method creates and inserts a new IndexEntry at that position. The 
    method returns the position of either the found or the inserted 
    IndexEntry.*/
   private int foundOrInserted(String word)
   {
      IndexEntry temp;
      for (int i = 0; i < this.size(); i ++) {
         temp = this.get(i);
         if (temp.getWord().compareTo(word.toUpperCase()) > 0) {
            this.add(i, new IndexEntry(word));
            return i;
         }
         if (temp.getWord().compareTo(word.toUpperCase()) == 0) {
            return i;
         }
      }
      this.add(new IndexEntry(word));
      return this.size()-1;
      
   }
}
   
class IndexEntry implements Comparable<IndexEntry>
{
     //fields
     private String word;
     private ArrayList<Integer> numsList; 
     //constructors
     public IndexEntry (String s) {
     //basic constructor, creates an arraylist and makes the string uppercase as it shows in the example txt file
         numsList = new ArrayList<Integer>();
         word = s.toUpperCase();
     }
   
   
     /**  appends num to numsList, but only if it is not already in that list.    
          */
   public void add(int num)
   {
   //if the number isn't in the list (if(!)) then add the number to the list, otherwise do nohting
      if (!numsList.contains(num)) {
         numsList.add(num);
      }
   }
      
   	/** this is a standard accessor method  */
   public String getWord()
   {
      //basic
      return word;
   }
      
   //have to create a helper method to simplify things
   public int compareTo (IndexEntry indexentry) {
      return word.compareTo(indexentry.getWord());
   }
   
     /**  returns a string representation of this Index Entry.  */
   public String toString()
   {
     //Make a string starting with the word. after putting the word in the string add a space and then the numbers and then subtract the last comma 
     String strTS = word + " ";
      for (int i = 0; i < numsList.size(); i ++) {
         if (i == numsList.size() - 1) {
            strTS += numsList.get(i);
         } else {
            strTS = strTS + numsList.get(i) + ", ";
         }
      }
     return strTS;
   }
}

