package com.generation.HelloRESTWorld.model.repositories.implementations;

import com.generation.HelloRESTWorld.model.Student;
import com.generation.HelloRESTWorld.model.repositories.abstractions.StudentRepository;
import org.hibernate.tool.schema.spi.SqlScriptException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class JDBCStudentRepository implements StudentRepository {

    public final static String JDBC_URL = "jdbc:mysql://localhost:3306/db_example";
    public final static String JDBC_USER = "root";
    public final static String JDBC_PASSWORD = "rout";

    public final static String ALL_STUDENTS = "select id,firstname,lastname,birthdate from student";

    public final static String FIND_STUDENT_BY_ID = "select firstname,lastname,birthdate from student where id =?";
    public final static String CREATE_STUDENT = "insert into student(firstname,lastname,birthdate)values(?,?,?)";
    /*@Override
    public Iterable<Student> getAllStudents() {
        Connection con = null;
        Statement st = null;
        ResultSet res = null;

        Collection<Student> students = new ArrayList<>();
        try{
            con = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
            st = con.createStatement();
            res = st.executeQuery(ALL_STUDENTS);
            while (res.next()){
                long id = res.getLong("id");
                String firstname = res.getString("firstname");
                String lastname = res.getString("lastname");
                LocalDate birthdate = res.getDate("birthdate").toLocalDate();
                Student student = new Student(id,firstname,lastname,birthdate);
                students.add(student);
            }
            return students;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            try {
                if (res != null){
                    res.close();
                }
            }catch (SQLException e){
            //--------------------
            } try {
                if (st != null){
                    st.close();
                }
            }catch (SQLException e){

            }
            //--------------------
            try {
               if (con != null){
                   con.close();
               }
            }catch (SQLException e){

            }

        }
    }*/
    @Override
    public Iterable<Student> getAllStudents() {
        Collection<Student> students = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
        Statement st = con.createStatement();
        ResultSet res = st.executeQuery(ALL_STUDENTS);
        ){
            while (res.next()){
                long id = res.getLong("id");
                String firstname = res.getString("firstname");
                String lastname = res.getString("lastname");
                LocalDate birthdate = res.getDate("birthdate").toLocalDate();
                Student student = new Student(id,firstname,lastname,birthdate);
                students.add(student);
            }
            return students;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Student> findStudentById(long id) {
            try(Connection con = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
                PreparedStatement st = con.prepareStatement(FIND_STUDENT_BY_ID);
            ){
                st.setLong(1,id);
                try(ResultSet res = st.executeQuery()){
                    if (res.next()){
                        String firstname = res.getString("firstname");
                        String lastname = res.getString("lastname");
                        LocalDate birthdate = res.getDate("birthdate").toLocalDate();
                        Student student = new Student(id,firstname,lastname,birthdate);
                        return Optional.of(student);
                    }
                    return Optional.empty();
                }
        }catch (SQLException e){
                throw new RuntimeException(e);
            }
    }

    @Override
    public Student create(Student s) {
        try(Connection con = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
            PreparedStatement st = con.prepareStatement(CREATE_STUDENT,Statement.RETURN_GENERATED_KEYS);
        ){
            st.setString(1,s.getFirstname());
            st.setString(2,s.getLastname());
            st.setDate(3,Date.valueOf(s.getBirthdate()));
            st.executeUpdate();
            try (ResultSet res = st.getGeneratedKeys()){
                if (res.next()){
                    long id = res.getLong(1);
                    s.setId(id);
                }
            }
            return s;
        }catch (SQLException e ){
            throw new RuntimeException(e);
        }
    }
}
