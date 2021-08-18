package tests;

import databaseConnect.JDBCConnection;
import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataBaseTest extends SetupTest {

    @Test
    @Order(1)
    @DisplayName("Create table Students in DB")
    public void testCreateTable() {
        String query = "CREATE TABLE students ("
                + "ID int primary key auto_increment,"
                + "FIRST_NAME VARCHAR(50) NOT NULL,"
                + "LAST_NAME VARCHAR(50) NOT NULL,"
                + "TOWN VARCHAR(50),"
                + "FACULTY VARCHAR (50))";

        JDBCConnection.createTable(query);
    }

    @Test
    @Order(2)
    @DisplayName("Sending an INSERT request")
    public void insertRequestTest() {
        String insertQuery = "INSERT INTO students (FIRST_NAME, LAST_NAME, TOWN, FACULTY) VALUE ('Maria', 'Fox', 'Madrid', 'Math')";
        JDBCConnection.insertIntoTable(insertQuery);

        String selectQuery = "SELECT * FROM students WHERE FIRST_NAME = 'Maria'";
        ResultSet resultSet = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return inserted data",
                () -> assertEquals("Maria", resultSet.getString("FIRST_NAME")),
                () -> assertEquals("Fox", resultSet.getString("LAST_NAME")),
                () -> assertEquals("Madrid", resultSet.getString("TOWN")),
                () -> assertEquals("Math", resultSet.getString("FACULTY")));
    }

    @Test
    @Order(3)
    @DisplayName("Sending UPDATE request")
    public void updateRequestTest() throws SQLException {
        String query = "UPDATE students SET FACULTY = 'BIOLOGY' WHERE FIRST_NAME='MARIA'";
        JDBCConnection.updateInTable(query);
        String selectQuery = "SELECT FACULTY FROM STUDENTS WHERE FIRST_NAME='MARIA'";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        String expectedTown = "BIOLOGY";
        String actualTown = rs.getString("FACULTY");
        assertEquals(expectedTown, actualTown, "Expected and actual result is different");
    }

    @Test
    @Order(4)
    @DisplayName("Sending DELETE request")
    public void deleteRequestTest() {
        String query = "DELETE FROM students";
        JDBCConnection.deleteFromTable(query);
    }

    @Test
    @Order(5)
    @DisplayName("Delete table from the DB")
    public void dropTableTest() {
        JDBCConnection.dropTable("students");
    }

    @Test
    @Order(6)
    @DisplayName("Sending SELECT request - checking name")
    public void testSelectRequest_checkAddress() throws SQLException {
        String query = "SELECT * FROM category WHERE name LIKE '%i'";
        ResultSet resultSet = JDBCConnection.selectFromTable(query);
        String expectedName = "Sci-Fi";
        String actualName = resultSet.getString("name");
        assertEquals(expectedName, actualName, "Expected and actual result is different");
    }

    @Test
    @Order(7)
    @DisplayName("Sending JOIN request - checking film in appropriate category")
    public void selectWithJoinRequest_CheckFilmAndCategoryTest() throws SQLException {
        String query = "SELECT \n" +
                "    ft.film_id, c.name, ft.title\n" +
                "FROM\n" +
                "    film_text ft\n" +
                "        JOIN\n" +
                "    film_category fc\n" +
                "        JOIN\n" +
                "    category c ON ft.film_id = fc.film_id\n" +
                "        AND fc.category_id = c.category_id\n" +
                "        AND c.name LIKE 'G%'\n" +
                "        AND ft.title LIKE '%w'";
        ResultSet resultSet = JDBCConnection.selectFromTable(query);
        String expectedName = "Games";
        String expectedTitle = "AUTUMN CROW";
        String actualName = resultSet.getString("name");
        String actualTitle = resultSet.getString("title");
        assertEquals(expectedName, actualName, "Expected name and actual result is different");
        assertEquals(expectedTitle, actualTitle, "Expected title and actual result is different");
    }

    @Test
    @Order(8)
    @DisplayName("Sending JOIN request - checking first last name and address")
    public void selectWithJoinRequest_CheckLastNameAndAddress() throws SQLException {
        String query = "SELECT \n" +
                "    cstmr.last_name, adrs.address\n" +
                "FROM\n" +
                "    address adrs\n" +
                "        JOIN\n" +
                "    customer cstmr ON adrs.address_id = cstmr.address_id";
        ResultSet resultSet = JDBCConnection.selectFromTable(query);
        resultSet.first();
        String expectedLastName = "SMITH";
        String expectedAddress = "1913 Hanoi Way";
        String actualLastName = resultSet.getString("last_name");
        String actualAddress = resultSet.getString("address");
        assertEquals(expectedLastName, actualLastName, "Expected last name and actual result is different");
        assertEquals(expectedAddress, actualAddress, "Expected address and actual result is different");
    }

}
