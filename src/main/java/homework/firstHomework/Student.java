package homework.firstHomework;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    String firstName;
    String secondName;
    String mail;
    Integer credit;
    Double mark;

    public Student(String firstName, String secondName, String mail, Integer credit, Double mark) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.mail = mail;
        this.credit = credit;
        this.mark = mark;
    }

    public String toString() {
        return firstName + "," + secondName + "," + mail + "," + credit + "," + mark;
    }
}
