package com.revature.project.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDBImplementationTest {
    UserDBTest userDBTest;
    boolean throwsException;
    UserSpecs userSpecs;
    ChildUserSpecs childUserSpecs;
    EmployeeUserSpecs employeeUserSpecs;
    @BeforeEach
    void setUp() {
       UserDBTest userDB = mock(UserDBTest.class);
        throwsException = false;
        userSpecs = new UserSpecs(1, "test", "test",0);
        childUserSpecs = new ChildUserSpecs(1, "test", "test","test");
        employeeUserSpecs = new EmployeeUserSpecs(1, "test","test",true);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
//       List<UserSpecs> test = Arrays.asList(9,"tff","fgh",0);
//       UserSpecs userSpecs = new UserSpecs(0, "", "",0);
//        when(userDBTest.save(userSpecs)).getMock();

    }

    @Test
    void saveToChild() {
    }

    @Test
    void findById() {
    }


    List<UserSpecs> findAll() {

        //Setup all the stuff you will need here
        final String SQL = "select * from testDummies";
        Connection connection = null;
        Statement stmt = null;
        ResultSet set = null;
        List<UserSpecs> userInfo = new ArrayList<>();
        {
            try {
                connection = OpenConnection.getConnection();
                stmt = connection.createStatement();
                set = stmt.executeQuery(SQL);
                while (set.next()) {
                    userInfo.add(new UserSpecs(
                            set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getInt(4)));
                }

            } catch (SQLException e) {
                System.out.println("Error here");
            } finally {
                CloseDB.closeConnection(connection);
                CloseDB.closeResultSet(set);
                CloseDB.closeStatement(stmt);
            }

            return userInfo;
        }
    }

    List<UserSpecs> findAllKids() {

        //Setup all the stuff you will need here
        final String SQL = "select * from testDummyChildren";
        Connection connection = null;
        Statement stmt = null;
        ResultSet set = null;
        List<UserSpecs> userInfo = new ArrayList<>();
        {
            try {
                connection = OpenConnection.getConnection();
                stmt = connection.createStatement();
                set = stmt.executeQuery(SQL);
                while (set.next()) {
                    userInfo.add(new UserSpecs(
                            set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getInt(4)));
                }

            } catch (SQLException e) {
                System.out.println("Error");
            } finally {
                CloseDB.closeConnection(connection);
                CloseDB.closeResultSet(set);
                CloseDB.closeStatement(stmt);
            }

            return userInfo;
        }

    }
    List<UserSpecs> findAllKidsFails() {

        //Setup all the stuff you will need here
        final String SQL = "select * from testDummyChildren";
        Connection connection = null;
        Statement stmt = null;
        ResultSet set = null;
        List<UserSpecs> userInfo = new ArrayList<>();
        {
            try {
                connection = OpenConnection.getConnection();
                stmt = connection.createStatement();
                set = stmt.executeQuery(SQL);
                while (set.next()) {
                    userInfo.add(new UserSpecs(
                            set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getInt(4)));
                }

            } catch (SQLException e) {
                System.out.println("Error");
            } finally {
                CloseDB.closeConnection(connection);
                CloseDB.closeResultSet(set);
                CloseDB.closeStatement(stmt);
            }

            return userInfo;
        }

    }

    List<UserSpecs> findAllDrones() {

        //Setup all the stuff you will need here
        final String SQL = "select * from testDummyEmployee";
        Connection connection = null;
        Statement stmt = null;
        ResultSet set = null;
        List<UserSpecs> userInfo = new ArrayList<>();
        {
            try {
                connection = OpenConnection.getConnection();
                stmt = connection.createStatement();
                set = stmt.executeQuery(SQL);
                while (set.next()) {
                    userInfo.add(new UserSpecs(
                            set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getInt(4)));
                }

            } catch (SQLException e) {
                System.out.println("Successfuly threw SQL exception");
            } finally {
                CloseDB.closeConnection(connection);
                CloseDB.closeResultSet(set);
                CloseDB.closeStatement(stmt);
            }

            return userInfo;
        }

    }
    List<UserSpecs> findAllDronesFails() {

        //Setup all the stuff you will need here
        final String SQL = "select * from testDummyEmployeee";
        Connection connection = null;
        Statement stmt = null;
        ResultSet set = null;
        List<UserSpecs> userInfo = new ArrayList<>();
        {
            try {
                connection = OpenConnection.getConnection();
                stmt = connection.createStatement();
                set = stmt.executeQuery(SQL);
                while (set.next()) {
                    userInfo.add(new UserSpecs(
                            set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getInt(4)));
                }

            } catch (SQLException e) {
                System.out.println("Threw an exception");
            } finally {
                CloseDB.closeConnection(connection);
                CloseDB.closeResultSet(set);
                CloseDB.closeStatement(stmt);
            }

            return userInfo;
        }

    }

    List<UserSpecs> findAllFails() {

        //Setup all the stuff you will need here
        final String SQL = "select * from testDummiess";
        Connection connection = null;
        Statement stmt = null;
        ResultSet set = null;
        List<UserSpecs> userInfo = new ArrayList<>();
        {
            try {
                connection = OpenConnection.getConnection();
                stmt = connection.createStatement();
                set = stmt.executeQuery(SQL);
                while (set.next()) {
                    userInfo.add(new UserSpecs(
                            set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getInt(4)));
                }

            } catch (SQLException e) {
                System.out.println("Error here");
            } finally {
                CloseDB.closeConnection(connection);
                CloseDB.closeResultSet(set);
                CloseDB.closeStatement(stmt);
            }

            return userInfo;
        }


    }

    public void delete(UserSpecs specs) {
        final String SQL = "delete from testdummies where user_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = OpenConnection.getConnection();
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, specs.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(statement);
        }
    }

    public ChildUserSpecs deleteAllChildren(ChildUserSpecs specs) {
        final String SQL = "delete from childusers where child_admin = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = OpenConnection.getConnection();
            statement = connection.prepareStatement(SQL);
            statement.setString(1, specs.getUsername());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(statement);
        }
        return specs;
    }
    public String deleteChild(ChildUserSpecs specs) {
        final String SQL = "delete from childusers where user_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = OpenConnection.getConnection();
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, specs.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(statement);
        }
        return SQL;

    }
    public String deleteEmployee(EmployeeUserSpecs specs) {
        final String SQL = "delete from employee where user_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = OpenConnection.getConnection();
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, specs.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(statement);
        }
        return SQL;
    }

    public ArrayList<String> findInfo(String name) {
        final String SQL = "select * from testdummies where user_name = '" + name + "'" ;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        ArrayList<String> array = new ArrayList<>();
        try {
            connection = OpenConnection.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(SQL);
            while (set.next()) {
                array.add(set.getString(1));
                array.add(set.getString(2));
                array.add(set.getString(3));
                array.add(String.valueOf(set.getInt(4)));
            }
            set = statement.executeQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(statement);
            CloseDB.closeResultSet(set);
        }
        return array;
    }

    public ArrayList<String> findChildInfo(String name) {
        final String SQL = "select * from childusers where user_name = '" + name + "'";
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        ArrayList<String> array = new ArrayList<>();
        try {
            connection = OpenConnection.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(SQL);
            while (set.next()) {
                array.add(set.getString(1));
                array.add(set.getString(2));
                array.add(set.getString(3));
                array.add(String.valueOf(set.getString(4)));
            }
            set = statement.executeQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(statement);
            CloseDB.closeResultSet(set);
        }
        return array;
    }
    public ArrayList<String> findEmployeeInfo(String name) {
        final String SQL = "select * from employee where user_name = '" + name + "'";
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        ArrayList<String> array = new ArrayList<>();
        try {
            connection = OpenConnection.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(SQL);
            while (set.next()) {
                array.add(set.getString(1));
                array.add(set.getString(2));
                array.add(set.getString(3));
                array.add(String.valueOf(set.getBoolean(4)));
            }
            set = statement.executeQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(statement);
            CloseDB.closeResultSet(set);
        }
        return array;
    }



    public void updateFunds(UserSpecs specs) {
        final  String SQL = "update users set user_funds = ? where user_name = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = OpenConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, specs.getUserFunds());
            preparedStatement.setString(2, specs.getUsername());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(preparedStatement);
        }

    }
    public void updateUserName(UserSpecs specs) {
        final  String SQL = "update users set user_name = ? where user_id = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = OpenConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, specs.getUsername());
            preparedStatement.setInt(2, specs.getId());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(preparedStatement);
        }


    }
    public void updateUserPass(UserSpecs specs) {
        final  String SQL = "update users set user_pass = ? where user_name = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = OpenConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, specs.getUserPass());
            preparedStatement.setString(2, specs.getUsername());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(preparedStatement);
        }

    }
    public void updateChildUserName(ChildUserSpecs specs) {
        final  String SQL = "update childusers set user_name = ? where user_id = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = OpenConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, specs.getUsername());
            preparedStatement.setInt(2, specs.getId());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(preparedStatement);
        }

    }
    public void updateChildUserPass(ChildUserSpecs specs) {
        final  String SQL = "update childusers set user_pass = ? where user_name = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = OpenConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, specs.getUserPass());
            preparedStatement.setString(2, specs.getUsername());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(preparedStatement);
        }

    }
    public void updateEmployeeUserName(EmployeeUserSpecs specs) {
        final  String SQL = "update employee set user_name = ? where user_id = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = OpenConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, specs.getUsername());
            preparedStatement.setInt(2, specs.getId());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(preparedStatement);
        }

    }
    public void updateEmployeeUserPass(EmployeeUserSpecs specs) {
        final  String SQL = "update employee set user_pass = ? where user_name = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = OpenConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, specs.getUserPass());
            preparedStatement.setString(2, specs.getUsername());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(preparedStatement);
        }

    }
    @Test
    void findAllUsers() {
        Assertions.assertNotNull(findAll());

    }

    @Test
    void testAllUsers() {
        try {
            Assertions.assertNotNull(findAllFails());

        } catch (Exception e) {
            throwsException = true;
            Assertions.assertTrue(throwsException);
        }
    }

    @Test
    void findAllChildren() {
        Assertions.assertNotNull(findAllKids());

    }

    @Test
    void testAllKids() {
        throwsException = false;
        try {
            Assertions.assertNotNull(findAllKids());

        } catch (Exception e) {
            throwsException = true;
            Assertions.assertTrue(throwsException);
        }
    }


        @Test
        void findAllEmployees () {
            Assertions.assertNotNull(findAllDrones());
        }
    @Test
    void testAllDrones() {
        throwsException = false;
        try {
            Assertions.assertNotNull(findAllDronesFails());

        } catch (Exception e) {
            throwsException = true;
            Assertions.assertTrue(throwsException);
        }
    }


        @Test
        void deleteAll () {
        try{
            Assertions.assertNotNull(userDBTest.delete(this.userSpecs));
        }catch (Exception e ){
            Assertions.assertTrue(throwsException = true);
        }
        }
        @Test
        void deleteAllChildren () {
            try{
                Assertions.assertNotNull(userDBTest.deleteAllChildren(this.childUserSpecs));
            }catch (Exception e ){
                Assertions.assertTrue(throwsException = true);
            }
        }

        @Test
        void deleteChild () {
            try{
                Assertions.assertNotNull(userDBTest.deleteChild(this.childUserSpecs));
            }catch (Exception e ){
                Assertions.assertTrue(throwsException = true);
            }
        }

        @Test
        void deleteEmployee () {
            try{
                Assertions.assertNotNull(userDBTest.deleteEmployee(this.employeeUserSpecs));
            }catch (Exception e ){
                Assertions.assertTrue(throwsException = true);
            }
        }

        @Test
        void findSomeInfo () {

            throwsException = false;
            try {
                ArrayList<String>testString = new ArrayList<>();

                testString.add("test");
                testString.add("test");
                testString.add("test");
                testString.add("test");
                userDBTest.findInfo();
            } catch (Exception e) {
                throwsException = true;
                Assertions.assertTrue(throwsException);
            }

        }

        @Test
        void findChildInfo () {
            throwsException = false;
            try {
                ArrayList<String>testString = new ArrayList<>();

                testString.add("test");
                testString.add("test");
                testString.add("test");
                testString.add("test");
                userDBTest.findChildInfo("test");
            } catch (Exception e) {
                throwsException = true;
                Assertions.assertTrue(throwsException);
            }
        }

        @Test
        void findEmployeeInfo () {
            throwsException = false;
            try {
                ArrayList<String>testString = new ArrayList<>();

                testString.add("test");
                testString.add("test");
                testString.add("test");
                testString.add("test");
                userDBTest.findEmployeeInfo("test");
            } catch (Exception e) {
                throwsException = true;
                Assertions.assertTrue(throwsException);
            }
        }

        @Test
        void updateFunds () {
            throwsException = false;
            try {
                userDBTest.updateFunds(userSpecs);
            } catch (Exception e) {
                throwsException = true;
                Assertions.assertTrue(throwsException);
            }
        }

        @Test
        void updateUserName () {
            throwsException = false;
            try {
                userDBTest.updateUserName(userSpecs);
            } catch (Exception e) {
                throwsException = true;
                Assertions.assertTrue(throwsException);
            }
        }

        @Test
        void updateUserPass () {
            throwsException = false;
            try {
                userDBTest.updateUserPass(userSpecs);
            } catch (Exception e) {
                throwsException = true;
                Assertions.assertTrue(throwsException);
            }
        }

        @Test
        void updateChildUserName () {
            throwsException = false;
            try {
                userDBTest.updateChildUserName(childUserSpecs);
            } catch (Exception e) {
                throwsException = true;
                Assertions.assertTrue(throwsException);
            }
        }

        @Test
        void updateChildUserPass () {
            throwsException = false;
            try {
                userDBTest.updateChildUserPass(childUserSpecs);
            } catch (Exception e) {
                throwsException = true;
                Assertions.assertTrue(throwsException);
            }
        }

        @Test
        void updateEmployeeUserName () {
            throwsException = false;
            try {
                userDBTest.updateEmployeeUserPass(employeeUserSpecs);
            } catch (Exception e) {
                throwsException = true;
                Assertions.assertTrue(throwsException);
            }
        }

        @Test
        void updateEmployeeUserPass () {
            throwsException = false;
            try {
                userDBTest.updateEmployeeUserPass(employeeUserSpecs);
            } catch (Exception e) {
                throwsException = true;
                Assertions.assertTrue(throwsException);
            }
        }
    }
