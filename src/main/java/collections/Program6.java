package collections;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Program6 {
    private SortedSet<Integer> numbers = new TreeSet<>(Comparator.reverseOrder());
    private Scanner scanner;

    public Program6(Scanner scanner) {
        this.scanner = scanner;
    }

    void process() {
        File file = new File("txt6.json");
        try {
            JSONArray jsonArray = (JSONArray) JSONValue.parseWithException(new FileReader(file));
            ArrayList arrayList = new ArrayList(jsonArray);
            for (Object nums : arrayList) {
                try {
                    String num = nums.toString();
                    int number = Integer.valueOf(num);
                    numbers.add(number);
                } catch (NumberFormatException ex) {
                    System.out.println(ex);
                }
            }
        } catch (Exception xe) {
            System.out.println(xe);
        }
//        try {
//            Source source = Okio.source(file);
//            BufferedSource bufferedSource = Okio.buffer(source);
//
//            while (!bufferedSource.exhausted()) {
//                String line = bufferedSource.readUtf8Line();
//                String[] array = line.split("\n");
//                for (String num : array) {
//                    int nums = Integer.valueOf(num);
//                    numbers.add(nums);
//                }
//            }
//        } catch (IOException ex) {
//            System.out.println("loh");
//        }
        System.out.println("Add number or enter 'show' to see list all numbers");
        while (true) {
            String next = scanner.nextLine();
            if (next.equals("show")) {
                System.out.println(numbers);
                continue;
            }
            if (next.equals("exit")) {
                JSONArray jsonArray = new JSONArray();
                for (Integer num : numbers) {
                    jsonArray.add(num);
                }
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(jsonArray.toJSONString());
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException x) {
                    System.out.println(x);
                }
//                try {
//                    BufferedSink bufferedSink = Okio.buffer(Okio.sink(file));
//                    for (Integer num : numbers) {
//                        bufferedSink.writeUtf8(num + "\n");
//                    }
//                    bufferedSink.flush();
//                    bufferedSink.close();
//                } catch (IOException x) {
//                    System.out.println("loh");
//                }
                return;
            }
            try {
                int num = Integer.valueOf(next);
                numbers.add(num);
            } catch (NumberFormatException e) {
                System.out.println("can't understand. enter number or 'show' to see all numbers");
            }
        }
    }
}
