import java.lang.Math;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class GenerateStableMarriageProblem {


  public static void main (String [] args) {
    if (args.length == 2) {
      int numberOfCouples = 0;
      try {
        numberOfCouples = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        System.out.println("Please provide a valid number of couples. Exiting...");
        System.exit(0);
      }

      PrintWriter writer = null;

      try {
        writer = new PrintWriter(args[0], "UTF-8");
      } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e);
        System.exit(0);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Unsupported encoding: " + e);
        System.exit(0);
      }

      int[] singlePersonOrdering = orderedArray(numberOfCouples);

      int[][] allMenOrdering = arrayClone(singlePersonOrdering, numberOfCouples);
      int[][] allWomenOrdering = arrayClone(singlePersonOrdering, numberOfCouples);

      allMenOrdering = shuffleJaggedArray(allMenOrdering);
      allWomenOrdering = shuffleJaggedArray(allWomenOrdering);

      writer.print(numberOfCouples+ "\n");
      writer.print("M\n");
      writer.print(jaggedArrayToString(allMenOrdering));
      writer.print("W\n");
      writer.print(jaggedArrayToString(allWomenOrdering));
      writer.close();

    } else {
      System.out.println("Please provide a file name and the number of couples to generate the problem with. Exiting...");
      System.exit(0);
    }
  }

  // Code adpated from http://stackoverflow.com/questions/363681/generating-random-integers-in-a-specific-range
  static int randomRange (int min, int max) {
    return min + (int)(Math.random() * max);
  }

  static int[] orderedArray (int length) {
      int[] array = new int[length];

      for (int i = 0; i < length; i++) {
        array[i] = i;
      }

      return array;
  }

  // Creates a set number of copies of the pased array
  static int[][] arrayClone (int[] source, int copyCount) {
    int[][] clones = new int[copyCount][source.length];

    for (int i = 0; i < copyCount; i++) {
      for (int j = 0; j < source.length; j++) {
        clones[i][j] = source[j];
      }
    }

    return clones;
  }

  static int[] shuffle (int[] source) {

    for (int i = 0; i < source.length; i++) {
      int newIndex = randomRange(0, source.length);
      int temp = source[i];
      source[i] = source[newIndex];
      source[newIndex] = temp;
    }

    return source;
  }

  static int[][] shuffleJaggedArray (int[][] source) {
    for (int i = 0; i < source.length; i++) {
      source[i] = shuffle(source[i]);
    }

    return source;
  }

  static String jaggedArrayToString (int[][] source) {
    String jaggedArrayAsString = "";

    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[i].length; j++) {
        jaggedArrayAsString += source[i][j];

        if (j < source[i].length - 1) {
          jaggedArrayAsString += ",";
        }

      }

      jaggedArrayAsString += "\n";
    }

    return jaggedArrayAsString;
  }

}
