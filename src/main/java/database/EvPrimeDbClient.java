package database;

import models.request.EvPrimePostUpdateEventRequestModelPostPut;
import utils.EvPrimeConfiguration;

import java.sql.*;

public class EvPrimeDbClient {

    private Connection conn;

    private void connect() throws SQLException {
        conn = DriverManager.getConnection
                (EvPrimeConfiguration.DB_URL,
                        EvPrimeConfiguration.DB_USER,
                            EvPrimeConfiguration.DB_PASSWORD);
    }

    public EvPrimePostUpdateEventRequestModelPostPut getEventFromDbById(String id) throws SQLException {
        connect();
        String sql = "SELECT * FROM public.events WHERE id = '" + id + "'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        conn.close();

        rs.next();

        return EvPrimePostUpdateEventRequestModelPostPut.builder()
                .title(rs.getString("title"))
                .image(rs.getString("image"))
                .date(rs.getString("date"))
                .location(rs.getString("location"))
                .description(rs.getString("description"))
                .build();
    }

    public boolean doesEventExistInDbById(String id) throws SQLException {
        connect();

        String sql = "SELECT * FROM events WHERE id = ?"; //Define the SQL query to select all columns from the 'events' table where the 'id' matches the provided parameter.
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { //Create a PreparedStatement to execute the SQL query. This helps prevent SQL injection and allows for efficient execution.
            pstmt.setString(1, id); //Set the first parameter (the event ID) in the prepared statement to the provided ID value.
            ResultSet rs = pstmt.executeQuery();  //Execute the query and store the result in a ResultSet object, which contains the results returned by the database.
            return rs.next(); //Check if the ResultSet has any rows. If it does, that means an event with the specified ID exists, so return true; otherwise, return false.
        } finally {
            conn.close(); //Ensure the connection is closed
        }
    }
}
