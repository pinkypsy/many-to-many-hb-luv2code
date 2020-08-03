package DBinteraction.add;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;

public class AddCourseAndStudents {
    public static void main(String[] args) throws ParseException {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            Student student1 = new Student("Vasyl", "Vasyl", "vasyl@luv.com");
            Student student2 = new Student("Petro", "Petro", "petro@luv.com");
            Student student3 = new Student("Mango", "Jerry", "mj@luv.com");
            Student student4 = new Student("David", "Sven", "ds@luv.com");

            Course tempCourse = new Course("Course 1");

            tempCourse.addStudent(student1);
            tempCourse.addStudent(student2);

            Course tempCourse1 = new Course("Course 2");

            tempCourse1.addStudent(student3);
            tempCourse1.addStudent(student4);

            //save courses
            session.save(tempCourse);
            session.save(tempCourse1);

            //save students
            session.save(student1);
            session.save(student2);
            session.save(student3);
            session.save(student4);

            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            session.close();
            sessionFactory.close();
        }


    }
}
