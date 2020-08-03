package DBinteraction.add;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;

public class AddCourseForStudent {
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

            int studentId = 2;

            Student student = session.get(Student.class, studentId);

            System.out.println("Get student: " + student.toString() + "\n" +
                     "with courses: " + student.getCourses());
            Course course = new Course("3 Course for certain student");
            Course course2 = new Course("4 Course for certain student");

            course.addStudent(student);
            course2.addStudent(student);

            session.save(course);
            session.save(course2);

            session.getTransaction().commit();

            System.out.println("New courses added for " + student.getFirstName() +
                    " see list below:\n" + student.getCourses());

            System.out.println("Done!");

        }finally {
            session.close();
            sessionFactory.close();
        }


    }
}
