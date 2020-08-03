package DBinteraction.add;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;

public class GetCourseForStudent {
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

            System.out.println("For student " + student.getFirstName() +
                    " see list below:\n" + student.getCourses());

            System.out.println("Done!");

        }finally {
            session.close();
            sessionFactory.close();
        }


    }
}
