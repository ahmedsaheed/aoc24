import java.util.*;
import java.io.*;
public class day02 {

    public static boolean fallsInDifferenceRange(int a, int b){
        // diff of a & b is at least 1 or at most 3
        return Math.abs(a - b) >= 1 && Math.abs(a - b) <= 3;
    }

public static List<ArrayList<Integer>> readDatasetFromFile(String filePath) {
        List<ArrayList<Integer>> dataset = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] numbers = line.trim().split("\\s+");

                ArrayList<Integer> report = new ArrayList<>();
                for (String num : numbers) {
                    report.add(Integer.parseInt(num));
                }
                dataset.add(report);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return dataset;
    }
    
    public static void main(String args[]) {
        enum Trend {
            DESC, INC, NONE
        }
        
       List<ArrayList<Integer>> dataset = readDatasetFromFile("day02.txt"); 
       List<ArrayList<Integer>> safeReports = new ArrayList<>();
       // dataset.add(new ArrayList<Integer>(Arrays.asList(7, 6, 4, 2, 1)));
       // dataset.add(new ArrayList<Integer>(Arrays.asList(1, 2, 7, 8, 9)));
       // dataset.add(new ArrayList<Integer>(Arrays.asList(9,7,6,2,1)));
       // dataset.add(new ArrayList<Integer>(Arrays.asList(1,3,2,4,5)));
       // dataset.add(new ArrayList<Integer>(Arrays.asList(8,6,4,4,1)));
       // dataset.add(new ArrayList<Integer>(Arrays.asList(1,3,6,7,9)));
        
        for (ArrayList<Integer> inp : dataset) {
            var trend = Trend.NONE;
            // check the trend!
            if (inp.get(0) > inp.get(1)) {
                trend = Trend.DESC;
            } else if (inp.get(0) < inp.get(1)) {
                trend = Trend.INC;
            }
            boolean safe = true; 

            for (int i = 0; i < inp.size() - 1 ; i++) {
                if (inp.get(i) == inp.get(i + 1) || 
                    (trend == Trend.DESC && (inp.get(i) < inp.get(i + 1) || !fallsInDifferenceRange(inp.get(i), inp.get(i + 1)))) ||
                    (trend == Trend.INC && (inp.get(i) > inp.get(i + 1) || !fallsInDifferenceRange(inp.get(i), inp.get(i + 1))))) {
                       safe = false;
                        break;
                    }
            }

                if (safe) {
                    safeReports.add(inp);
                }
            
        }

        System.out.println(safeReports.size());
        

    }
}
