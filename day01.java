import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
public class day01 {

public static void readNumbersFromFile(String fileName, List<Integer> left, List<Integer> right) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into two numbers
                String[] parts = line.trim().split("\\s+"); // Split by space(s)
                if (parts.length == 2) {
                    // Parse numbers and add to respective lists
                    left.add(Integer.parseInt(parts[0]));
                    right.add(Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
        }
    }


    private static int getSimilarity(int value, List<Integer> list) {
       return Collections.frequency(list, value); 
    }

  public static void main(String args[]) {
    List<Integer> left = new ArrayList<Integer>();
    List<Integer> right = new ArrayList<Integer>();
    int distance = 0;
    int sol2 = 0;
    // Read numbers from file

    readNumbersFromFile("./day01.txt", left, right);

    Collections.sort(left); 
    Collections.sort(right);
    
    for (int i = 0; i < left.size(); i++) { 
        distance += Math.abs(left.get(i) - right.get(i));
    }

    for (int i = 0; i < left.size(); i++) {
       sol2 += left.get(i) * getSimilarity(left.get(i), right);
    } 
    
    System.out.println(distance + "\n" + sol2);
  }
}
