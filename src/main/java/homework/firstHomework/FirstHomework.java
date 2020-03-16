package homework.firstHomework;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FirstHomework {

    static String[] getTxt(String path) throws IOException {
        Path basePath = FileSystems.getDefault().getPath(path);

        return Files.walk(basePath)
                .filter(name -> name.toString().contains("studenti"))
                .map(Path::toString)
                .toArray(String[]::new);
    }

    static void write(List<Student> list, String destination) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(destination, true))) {
            for (Student s : list) {
                writer.write(s.toString());
                writer.newLine();
            }
        }
    }

    static List<Student> readAndSortStudents(String source) throws IOException {
        List<Student> students = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new FileReader(new File(source)))) {
            String str;
            while ((str = in.readLine()) != null) {
                String[] tokens = str.split(",");
                int credit = Integer.parseInt(tokens[3]);
                double mark = Double.parseDouble(tokens[4]);
                students.add(new Student(tokens[0], tokens[1], tokens[2], credit, mark));
            }
        }
        //compar după credite și apoi nume
        students.sort(Comparator.comparing(Student::getCredit).thenComparing(Student::getSecondName));
        return students;
    }

}
