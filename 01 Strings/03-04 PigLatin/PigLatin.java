
// Name: J1-10
// Date: 9/20/20
import java.util.*;
import java.io.*;

public class PigLatin {

   public static void main(String[] args) {
      // part_1_using_pig();
      part_2_using_piglatenizeFile();

      /*
       * extension only String pigLatin = pig("What!?"); System.out.print(pigLatin +
       * "\t\t" + pigReverse(pigLatin)); //Yahwta!? pigLatin = pig("{(Hello!)}");
       * System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin));
       * //{(Yaholle!)} pigLatin = pig("\"McDonald???\""); System.out.println("\n" +
       * pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
       */
   }

   public static void part_1_using_pig() {
      Scanner sc = new Scanner(System.in);
      while (true) {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if (s.equals("-1")) {
            System.out.println("Goodbye!");
            System.exit(0);
         }
         String p = pig(s);
         System.out.println(p);
      }
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";

   public static String pig(String s) {

      if (s.length() == 0)
         return "";

      String vowels1 = "AEIOUaeiou";
      boolean hasPunct = false;

      String bgpunct = "";
      int bgpunctcount = 0;
      boolean bgpunctb = true;
      String edpunct = "";
      int edpunctcount = 0;
      boolean edpunctb = true;

      // remove and store the beginning punctuation
      for (int i = 0; i < s.length(); i++) {
         if (punct.contains("" + s.charAt(i))) {
            hasPunct = true;
         }
      }
      if (hasPunct) {
         if (!Character.isLetter(s.charAt(0))) {
            for (int i = 0; i < s.length(); i++) {
               if (punct.contains("" + s.charAt(i)) && bgpunctb) {
                  bgpunctcount += 1;
               } else {
                  bgpunctb = false;
               }
            }
            bgpunct = s.substring(0, bgpunctcount);
            s = s.substring(bgpunctcount);
         }
         // remove and store the ending punctuation

         if (punct.contains("" + s.charAt(s.length() - 1))) {
            for (int i = 0; i < s.length(); i++) {
               if (punct.contains("" + s.charAt(i)) && edpunctb) {
                  if (i != s.length() - 1 && Character.isLetter(s.charAt(i + 1))) {
                  } else {
                     edpunctcount = i;
                     edpunctb = false;

                  }
               }
            }
            edpunct = s.substring(edpunctcount);
            s = s.substring(0, edpunctcount);
         }
      }

      // START HERE with the basic case:
      // find the index of the first vowel
      // y is a vowel if it is not the first letter
      // qu

      boolean hasVowels = false;
      for (int i = 0; i < s.length(); i++) {
         if (vowels.contains(String.valueOf(s.charAt(i)))) {
            hasVowels = true;
         }
      }
      if (!hasVowels) {
         return "**** NO VOWEL ****";
      }
      if (vowels.contains("" + (s.charAt(0)))) {
         s = bgpunct + s + "way" + edpunct;
         return s;
      }
      if (s.charAt(0) != 'y' && s.charAt(0) != 'Y') {
         vowels1 += "Yy";
      }
      int qIndex = 0;
      int uIndex = 0;
      // if ((s.charAt(0)=='q' || s.charAt(0)=='Q') && (s.charAt(1)=='u' ||
      // s.charAt(1)=='U')) {
      if (s.contains("qu") || s.contains("Qu") || s.contains("QU") || s.contains("qU")) {
         if (s.contains("q")) {
            qIndex = s.indexOf("q");
         }
         if (s.contains("Q")) {
            qIndex = s.indexOf("Q");
         }
         if (s.contains("u")) {
            uIndex = s.indexOf("u");
         }
         if (s.contains("U")) {
            uIndex = s.indexOf("U");
         }
         if (Character.isUpperCase(s.charAt(0))) {
            s = s.substring(uIndex + 1, uIndex + 2).toUpperCase() + s.substring(uIndex + 2) + s.substring(0, uIndex + 1)
                  + "ay";
         } else {
            s = s.substring(uIndex + 1, uIndex + 2) + s.substring(uIndex + 2) + s.substring(0, uIndex + 1) + "ay";

         }

         s = bgpunct + s + edpunct;
         return s;
      }
      // no special cases
      int cCount = 0;
      boolean vowelReached = false;
      for (int i = 0; i < s.length(); i++) {
         if (!vowels1.contains("" + s.charAt(i)) && !vowelReached) {
            cCount += 1;
         } else {
            vowelReached = true;
         }
      }

      if (Character.isUpperCase(s.charAt(0))) {
         s = bgpunct + s.substring(cCount, cCount + 1).toUpperCase() + s.substring(cCount + 1)
               + s.substring(0, 1).toLowerCase() + s.substring(1, cCount) + "ay" + edpunct;

      } else {
         s = bgpunct + s.substring(cCount) + s.substring(0, cCount) + "ay" + edpunct;

      }

      // if no vowel has been found

      // is the first letter capitalized?

      // return the piglatinized word

      return s;

   }

   public static void part_2_using_piglatenizeFile() {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile(fileNameIn, fileNameOut);
      System.out.println("Piglatin done!");
   }

   /******************************
    * piglatinizes each word in each line of the input file precondition: both
    * fileNames include .txt postcondition: output a piglatinized .txt file
    ******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) {
      Scanner infile = null;
      try {
         infile = new Scanner(new File(fileNameIn));
      } catch (IOException e) {
         System.out.println("oops");
         System.exit(0);
      }

      PrintWriter outfile = null;
      try {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      } catch (IOException e) {
         System.out.println("File not created");
         System.exit(0);
      }
      // process each word in each line
      int count = 1;
      String words = "";
      while (infile.hasNextLine()) {
         words = words + infile.nextLine() + "\n";
      }

      String[] wordsArr = words.split("\n");
      // for (String a : wordsArr) {
      // System.out.println(a + "djdjkdsjf");
      // }
      String finished = "";
      for (int i = 0; i < wordsArr.length; i++) {
         String[] line = wordsArr[i].split(" ");
         for (int f = 0; f < line.length; f++) {
            finished = finished + pig(line[f]) + " ";
         }
         finished += "\n";
      }
      outfile.write(finished);
      outfile.close();
      infile.close();
   }

   /**
    * EXTENSION: Output each PigLatin word in reverse, preserving before-and-after
    * punctuation.
    */
   public static String pigReverse(String s) {

      if (s.length() == 0)
         return "";

      String vowels1 = "AEIOUaeiou";
      boolean hasPunct = false;

      String bgpunct = "";
      int bgpunctcount = 0;
      boolean bgpunctb = true;
      String edpunct = "";
      int edpunctcount = 0;
      boolean edpunctb = true;

      // remove and store the beginning punctuation
      for (int i = 0; i < s.length(); i++) {
         if (punct.contains("" + s.charAt(i))) {
            hasPunct = true;
         }
      }
      if (hasPunct) {
         if (!Character.isLetter(s.charAt(0))) {
            for (int i = 0; i < s.length(); i++) {
               if (punct.contains("" + s.charAt(i)) && bgpunctb) {
                  bgpunctcount += 1;
               } else {
                  bgpunctb = false;
               }
            }
            bgpunct = s.substring(0, bgpunctcount);
            s = s.substring(bgpunctcount);
         }
         // remove and store the ending punctuation

         if (punct.contains("" + s.charAt(s.length() - 1))) {
            for (int i = 0; i < s.length(); i++) {
               if (punct.contains("" + s.charAt(i)) && edpunctb) {
                  if (i != s.length() - 1 && Character.isLetter(s.charAt(i + 1))) {
                  } else {
                     edpunctcount = i;
                     edpunctb = false;

                  }
               }
            }
            edpunct = s.substring(edpunctcount);
            s = s.substring(0, edpunctcount);
         }
      }

      // START HERE with the basic case:
      // find the index of the first vowel
      // y is a vowel if it is not the first letter
      // qu
      String reverse = "";
      boolean hasVowels = false;
      for (int i = 0; i < s.length(); i++) {
         if (vowels.contains(String.valueOf(s.charAt(i)))) {
            hasVowels = true;
         }
      }
      if (!hasVowels) {
         return "**** NO VOWEL ****";
      }
      if (vowels.contains("" + (s.charAt(0)))) {
         s = s + "way";
         for (int i = s.length() - 1; i >= 0; i--) {
            reverse += s.charAt(i);
         }
         return bgpunct + reverse + edpunct;
      }
      if (s.charAt(0) != 'y' && s.charAt(0) != 'Y') {
         vowels1 += "Yy";
      }
      int qIndex = 0;
      int uIndex = 0;
      // if ((s.charAt(0)=='q' || s.charAt(0)=='Q') && (s.charAt(1)=='u' ||
      // s.charAt(1)=='U')) {
      if (s.contains("qu") || s.contains("Qu") || s.contains("QU") || s.contains("qU")) {
         if (s.contains("q")) {
            qIndex = s.indexOf("q");
         }
         if (s.contains("Q")) {
            qIndex = s.indexOf("Q");
         }
         if (s.contains("u")) {
            uIndex = s.indexOf("u");
         }
         if (s.contains("U")) {
            uIndex = s.indexOf("U");
         }
         if (Character.isUpperCase(s.charAt(0))) {
            s = s.substring(uIndex + 1, uIndex + 2).toUpperCase() + s.substring(uIndex + 2) + s.substring(0, uIndex + 1)
                  + "ay";
         } else {
            s = s.substring(uIndex + 1, uIndex + 2) + s.substring(uIndex + 2) + s.substring(0, uIndex + 1) + "ay";

         }

         for (int i = s.length() - 1; i >= 0; i--) {
            reverse += s.charAt(i);
         }
         return bgpunct + reverse + edpunct;
      }
      // no special cases
      int cCount = 0;
      boolean vowelReached = false;
      for (int i = 0; i < s.length(); i++) {
         if (!vowels1.contains("" + s.charAt(i)) && !vowelReached) {
            cCount += 1;
         } else {
            vowelReached = true;
         }
      }

      if (Character.isUpperCase(s.charAt(0))) {
         s = s.substring(cCount, cCount + 1).toUpperCase() + s.substring(cCount + 1) + s.substring(0, 1).toLowerCase()
               + s.substring(1, cCount) + "ay";

      } else {
         s = s.substring(cCount) + s.substring(0, cCount) + "ay";

      }

      // if no vowel has been found

      // is the first letter capitalized?

      // return the piglatinized word
      for (int i = s.length() - 1; i >= 0; i--) {
         reverse += s.charAt(i);
      }
      return bgpunct + reverse + edpunct;

   }
}
