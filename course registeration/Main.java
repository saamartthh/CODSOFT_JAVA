import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<Student> registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(student);
            return true;
        } else {
            return false; // Course is full
        }
    }

    public boolean removeStudent(Student student) {
        return registeredStudents.remove(student);
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }
}

class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (!registeredCourses.contains(course) && course.registerStudent(this)) {
            registeredCourses.add(course);
            return true;
        } else {
            return false; // Either already registered or course is full
        }
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course) && course.removeStudent(this)) {
            registeredCourses.remove(course);
            return true;
        } else {
            return false; // Either not registered or error in dropping
        }
    }
}

class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;
    private Scanner scanner;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayCourseListing() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course.getCourseCode() + " - " + course.getTitle() +
                    " (Slots: " + course.getAvailableSlots() + "/" + course.getCapacity() + ")");
        }
    }

    public void displayRegisteredCourses(Student student) {
        System.out.println("Registered Courses for " + student.getName() + ":");
        for (Course course : student.getRegisteredCourses()) {
            System.out.println(course.getCourseCode() + " - " + course.getTitle());
        }
    }

    public void registerCourse(Student student) {
        displayCourseListing();
        System.out.print("Enter course code to register: ");
        String courseCode = scanner.nextLine();
        Course selectedCourse = findCourseByCode(courseCode);
        if (selectedCourse != null && student.registerCourse(selectedCourse)) {
            System.out.println("Successfully registered for " + selectedCourse.getTitle());
        } else {
            System.out.println("Failed to register for the course.");
        }
    }

    public void dropCourse(Student student) {
        displayRegisteredCourses(student);
        System.out.print("Enter course code to drop: ");
        String courseCode = scanner.nextLine();
        Course selectedCourse = findCourseByCode(courseCode);
        if (selectedCourse != null && student.dropCourse(selectedCourse)) {
            System.out.println("Successfully dropped " + selectedCourse.getTitle());
        } else {
            System.out.println("Failed to drop the course.");
        }
    }

    private Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null; // Course not found
    }
}

public class Main {
    public static void main(String[] args) {
        // Create course registration system
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Define courses
        Course c1 = new Course("CSE101", "Introduction to Computer Science", "Basic concepts of programming", 30, "Mon/Wed 9:00 AM - 10:30 AM");
        Course c2 = new Course("MTH201", "Calculus I", "Limits, derivatives, and integrals", 25, "Tue/Thu 11:00 AM - 12:30 PM");
        Course c3 = new Course("PHY301", "Physics I", "Mechanics, motion, and forces", 20, "Mon/Wed/Fri 1:00 PM - 2:30 PM");
        Course c4 = new Course("CHE201", "Chemistry I", "Chemical reactions and properties", 25, "Tue/Thu 9:00 AM - 10:30 AM");

        // Add courses to the system
        system.addCourse(c1);
        system.addCourse(c2);
        system.addCourse(c3);
        system.addCourse(c4);

        // Define students with Indian names
        Student s1 = new Student(1001, "Amit Patel");
        Student s2 = new Student(1002, "Priya Gupta");

        // Add students to the system
        system.addStudent(s1);
        system.addStudent(s2);

        // Example: Register a student for a course
        system.registerCourse(s1);

        // Example: Drop a course for a student
        system.dropCourse(s1);
    }
}
