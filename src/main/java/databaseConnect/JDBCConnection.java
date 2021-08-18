package databaseConnect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import org.jooq.impl.DSL;

public class JDBCConnection {

    private final static String url;
    private final static String user;
    private final static String password;

    private static Connection con = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    private static final Logger logger = LogManager.getLogger(JDBCConnection.class);

    static {
        Properties property = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/configdb.properties")) {
            property.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = property.getProperty("jdbc-connect.url");
        user = property.getProperty("jdbc-connect.user");
        password = property.getProperty("jdbs-connect.password");
    }

    public static Connection connectDB() {
        logger.info("Connect to DB " + url);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } catch (SQLException sqlExp) {
            logger.error("Connection is failed! " + sqlExp.getMessage());
        }
        return con;
    }

    public static void closeConnect(){
        if(con != null){

            try {
                con.close();
                logger.info("Connection closed successfully");
            } catch (SQLException sqlExp) {
                logger.error("Connection wasn't close! " + sqlExp.getMessage());
            }
        }

        if(statement !=null){

            try {
                statement.close();
                logger.info("Statement closed successfully");
            } catch (SQLException sqlExp) {
                logger.error("Connection wasn't close! " + sqlExp.getMessage());
            }
        }

        if(resultSet != null){
            try {
                resultSet.close();
                logger.error("ResultSet closed successfully");
            } catch (SQLException sqlExp) {
                logger.error("Connection wasn't close! " + sqlExp.getMessage());
            }
        }
    }

    public static void createTable(String query) {
        try {
            statement = connectDB().prepareStatement(query);
            logger.info("Send request to DB: " + query);
            statement.executeUpdate(query);
            logger.info("Table was created successfully");
        } catch (SQLException se) {
            logger.error("Table was not created. Reason:\n" + se.getMessage());
        }
    }

    public static void dropTable(String tableName) {
        String query = "DROP TABLE " + tableName;
        try {
            statement = connectDB().prepareStatement(query);
            logger.info("Send request to DB: " + query);
            statement.executeUpdate(query);
            logger.info("Table was deleted successfully");
        } catch (SQLException se) {
            logger.error("Table was not deleted. Reason:\n" + se.getMessage());
        }
    }

    public static ResultSet selectFromTable(String query) {
        try {
            statement = connectDB().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            logger.info("Send request to DB: " + query);
            resultSet = statement.executeQuery(query);
            resultSet.next();
            addSQLRequestResultsToLog(query);
        } catch (SQLException se) {
            logger.error(se.getMessage());
        }
        return resultSet;
    }

    private static void addSQLRequestResultsToLog(String query) {
        StringBuilder builder = new StringBuilder();
        DSL.using(connectDB()).fetchStream(query)
                .forEach(r -> builder.append(r.format()));
        logger.info("Request results:\n" + builder);
    }

    public static void insertIntoTable(String query) {
        try {
            statement = connectDB().createStatement();
            logger.info("Send request to DB: " + query);
            statement.executeUpdate(query);
            logger.info("New data was inserted into table successfully");
        } catch (SQLException se) {
            logger.error("New data was not inserted into table. Reason:\n" + se.getMessage());
        }
    }

    public static void updateInTable(String query) {
        try {
            statement = connectDB().createStatement();
            logger.info("Send request to DB: " + query);
            statement.executeUpdate(query);
            logger.info("Data in table was updated successfully");
        } catch (SQLException se) {
            logger.error("Data in table was not updated. Reason:\n" + se.getMessage());
        }
    }

    public static void deleteFromTable(String query) {
        try {
            logger.info("Send request to DB: " + query);
            statement = connectDB().createStatement();
            statement.executeUpdate(query);
            logger.info("Data from table was deleted successfully");
        } catch (SQLException se) {
            logger.error("Data from table was not deleted. Reason:\n" + se.getMessage());
        }
    }

}
