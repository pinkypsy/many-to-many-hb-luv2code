package DBinteraction.add;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;

public class AddCourseAndReviews {
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

            Course tempCourse = new Course("324 spreading from A to Z");

            tempCourse.addReview(new Review("Yea"));
            tempCourse.addReview(new Review("Cool"));
            tempCourse.addReview(new Review("Awesome"));

            Course tempCourse1 = new Course("another course");

            tempCourse1.addReview(new Review("1"));
            tempCourse1.addReview(new Review("2"));
            tempCourse1.addReview(new Review("3"));

            System.out.println("saving reviews...");
            System.out.println(tempCourse.getReviews());

            session.save(tempCourse);
            session.save(tempCourse1);

            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            session.close();
            sessionFactory.close();
        }


    }
}
