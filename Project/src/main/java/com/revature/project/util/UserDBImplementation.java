package com.revature.project.util;

import com.revature.project.MainDisplay;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBImplementation implements UserDB {
    @Override
    public void save(UserSpecs specs) {
        final String SQL = "insert into users values (default, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = OpenConnection.getConnection();
            statement = connection.prepareStatement(SQL);
            statement.setString(1, specs.getUsername());
            statement.setString(2, specs.getUserPass());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(statement);
        }
    }

    public void saveToChild(UserSpecs specs) {
        final String SQL = "insert into childUsers values (default, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = OpenConnection.getConnection();
            statement = connection.prepareStatement(SQL);
            statement.setString(1, specs.getUsername());
            statement.setString(2, specs.getUserPass());
            statement.setString(3, MainDisplay.getUsername());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(statement);
        }
    }

    @Override
    public UserSpecs findById(int id) {
        final String SQL = "select * from users where user_id = "
                + id;
        UserSpecs userSpecs = null;
        Connection connection = null;
        ResultSet set = null;
        Statement statement = null;

        try {
            connection = OpenConnection.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(SQL);
            if (set.next()) {
                userSpecs = new UserSpecs(set.getInt(1),
                        set.getString(2),
                        set.getString(3),
                        set.getInt(4)
                );

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(statement);
            CloseDB.closeResultSet(set);
        }
        return userSpecs;
    }

    //Implementation using List. Deprecated because this stores all values into one index.
//    @Override
//    public List<String> findByName(String name) {
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet set = null;
//        UserSpecs specs = new UserSpecs();
//        List<String> specsList = new ArrayList<>();
//        final String SQL = "select * from users where user_name = '" + name + "'";
//        try {
//            connection = OpenConnection.getConnection();
//            statement = connection.createStatement();
//            set = statement.executeQuery(SQL);
//            while (set.next()) {
//
//
//                specsList.add(String.valueOf(new UserSpecs(
//                        set.getInt(1),
//                        set.getString(2),
//                        set.getString(3))));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            CloseDB.closeConnection(connection);
//            CloseDB.closeResultSet(set);
//            CloseDB.closeStatement(statement);
//        }
//
//        return specsList;
//    }

    @Override
    public List<UserSpecs> findAll() {
        //Setup all the stuff you will need here
        final String SQL = "select * from users";
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
                e.printStackTrace();
            } finally {
                CloseDB.closeConnection(connection);
                CloseDB.closeResultSet(set);
                CloseDB.closeStatement(stmt);
            }

            return userInfo;
        }

    }
    @Override
    public List<ChildUserSpecs> findAllChildren() {
        //Setup all the stuff you will need here
        final String SQL = "select * from childusers";
        Connection connection = null;
        Statement stmt = null;
        ResultSet set = null;
        List<ChildUserSpecs> userInfo = new ArrayList<>();
        {
            try {
                connection = OpenConnection.getConnection();
                stmt = connection.createStatement();
                set = stmt.executeQuery(SQL);
                while (set.next()) {
                    userInfo.add(new ChildUserSpecs(
                            set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getString(4)));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                CloseDB.closeConnection(connection);
                CloseDB.closeResultSet(set);
                CloseDB.closeStatement(stmt);
            }

            return userInfo;
        }

    }
    @Override
    public List<EmployeeUserSpecs> findAllEmployees() {
        //Setup all the stuff you will need here
        final String SQL = "select * from employee";
        Connection connection = null;
        Statement stmt = null;
        ResultSet set = null;
        List<EmployeeUserSpecs> userInfo = new ArrayList<>();
        {
            try {
                connection = OpenConnection.getConnection();
                stmt = connection.createStatement();
                set = stmt.executeQuery(SQL);
                while (set.next()) {
                    userInfo.add(new EmployeeUserSpecs(
                            set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getBoolean(4)));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                CloseDB.closeConnection(connection);
                CloseDB.closeResultSet(set);
                CloseDB.closeStatement(stmt);
            }

            return userInfo;
        }

    }

    @Override
    public void update(UserSpecs specs) {
        Connection connection = null;
        PreparedStatement statement = null;
    }


    @Override
    public void delete(UserSpecs specs) {
        final String SQL = "delete from users where user_id = ?";
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
    @Override
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
    @Override
    public ChildUserSpecs deleteChild(ChildUserSpecs specs) {
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
        return specs;
    }
    @Override
    public EmployeeUserSpecs deleteEmployee(EmployeeUserSpecs specs) {
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
        return specs;
    }

    @Override
    public ArrayList<String> findInfo(String name) {
        final String SQL = "select * from users where user_name = '" + name + "'";
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
    @Override
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
    @Override
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



    @Override
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
//@Override
//public UserSpecs findInfo(UserSpecs specs) {
////        final String SQL ="select * from users where user_name = '" + name + "'";
//    final String SQL ="select user_password from users where user_name = ?";
//    Connection connection = null;
//    Statement statement = null;
//    ResultSet set = null;
//    PreparedStatement preparedStatement = null;
//    UserSpecs userSpecs = new UserSpecs();
//    ArrayList<String> array = new ArrayList<>();
//    try {
//        connection = OpenConnection.getConnection();
//        preparedStatement = connection.prepareStatement(SQL);
//        preparedStatement.setString(1, specs.getUserPass());
////            connection = OpenConnection.getConnection();
////            statement = connection.createStatement();
////            set = statement.executeQuery(SQL);
////            while (set.next()) {
////                    array.add(set.getString(1));
////                    array.add(set.getString(2));
////                    array.add(set.getString(3));
////            }
////            set = statement.executeQuery(SQL);

//    } catch (SQLException e) {
//        e.printStackTrace();
//    }finally {
//        CloseDB.closeConnection(connection);
//        CloseDB.closeStatement(preparedStatement);
//    }
//    return  specs;
//}

}
