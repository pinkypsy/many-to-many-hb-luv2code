package DBinteraction.add;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;

public class AddReview {
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
            int theId1 = 18;
            int theId2 = 19;

            session.beginTransaction();

            Course tempCourse = session.get(Course.class,theId1);
            Course tempCourse2 = session.get(Course.class,theId2);

            Review tempReview = new Review("review about 1");
            Review tempReview2 = new Review("review about 2");
//            Course tempCourse2 = new Course("Sushi Sticks Master");

            tempCourse.addReview(tempReview);
            tempCourse2.addReview(tempReview2);
//            tempInstructor.addCourse(tempCourse2);


            System.out.println("Save review...");
            session.save(tempReview);
            session.save(tempReview2);
//            session.save(tempCourse2);

            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            session.close();
            sessionFactory.close();
        }


    }
}
