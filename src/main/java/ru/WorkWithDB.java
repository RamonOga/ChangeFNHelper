package ru;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public class WorkWithDB {

    Logger LOG = LogManager.getLogger(WorkWithDB.class);
    static Connection co;


    public void lookDB() {

        try {
            Statement statement = co.createStatement();
            String query =
                    "SELECT id, name, datefn" +
                            " FROM clients" +
                            " ORDER BY id";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String datefn = rs.getString("datefn");
                System.out.println(id + "\t|| " + name + "\t|| " + datefn);
            }
            System.out.println("\n");
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void open() {
        try {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection("jdbc:sqlite:clients.db");
            System.out.println("Connection successful!\n");
        } catch (Exception e) {
            LOG.error("Гля, чота база не открылась ", e);
        }
    }

    public void addData(String name, String fn) {
        try {
            int datalength = fn.length();

            if (datalength == 10) {
                String query = "INSERT INTO clients (name, datefn)\n" +
                        "VALUES ('" + name + "','" + fn + "');";


                Statement statement = co.createStatement();
                statement.executeUpdate(query);

                System.out.println("\n");
                System.out.println("Your data are added!\n");
                statement.close();
            } else {
                System.out.println("\n");
                System.out.println("Enter correct data dd.mm.yyyy");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void close() {
        try {
            co.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void useData() {
        System.out.println("============ Start Checking ==============");
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date nowdate = new Date();
            Statement statement = co.createStatement();
            String query =
                    "SELECT id, name, datefn" +
                            " FROM clients" +
                            " ORDER BY id";

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String datefn = rs.getString("datefn");
                long twoweeks;
                Date date1 = dateFormat.parse(datefn);
                twoweeks = date1.getTime() - nowdate.getTime();
                if (twoweeks < 2419200000l && twoweeks > 0) {
                    System.out.println("Client: " + name + " Date of exchange FN: " + datefn);
                    // System.out.println("For Check: " + date1 + "\n"); // Это для проверки делал

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("============ Finish Checking ==============");
    }

    public void deldata(int id) {
        try {
            String query = "DELETE FROM clients\n" +
                    "WHERE id = " + id + ";";


            Statement statement = co.createStatement();
            statement.executeUpdate(query);

            System.out.println("\n");
            System.out.println("Client from id number " + id + " has been deleted!\n");
            statement.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void lookingFor(String look) {
        System.out.println("============ Start Checking ==============");
        try {
            Statement statement = co.createStatement();
            String query =
                    "SELECT * FROM clients\n" +
                            "WHERE name like '%" + look + "%';";

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String datefn = rs.getString("datefn");

                System.out.println("Client: " + name + " Date of exchange FN: " + datefn);

            }
            System.out.println("============ Finish Checking ==============");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
