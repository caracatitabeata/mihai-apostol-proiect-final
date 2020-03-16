package homework.firstHomework;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String studentFile2 =  "C:\\Users\\Lenovo\\Desktop\\IT\\Java\\mihaiapostolproiectfinal\\src\\main\\java\\homework\\firsthomework\\chestiiTema\\studentiTotal.txt";
        String studentFile3 =  "C:\\Users\\Lenovo\\Desktop\\IT\\Java\\mihaiapostolproiectfinal\\src\\main\\java\\homework\\firsthomework\\chestiiTema\\facultate";

        String[] array = FirstHomework.getTxt(studentFile3);
        for (String source: array) {
            List<Student> studentList = FirstHomework.readAndSortStudents(source);
            FirstHomework.write(studentList, studentFile2);
        }

        List<Student> studentList = FirstHomework.readAndSortStudents(studentFile2);
        FirstHomework.write(studentList, studentFile2);

    }
}
