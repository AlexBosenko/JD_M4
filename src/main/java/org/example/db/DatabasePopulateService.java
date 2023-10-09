package org.example.db;

import org.example.properties.PropertyReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabasePopulateService {
    public void populateDb(Database database) {
        try {
            String populateDbFileName = PropertyReader.getInstance().getPopulateDbFilePath();
            String sql = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(populateDbFileName))
            );
            database.executeUpdate(sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Database database = Database.getInstance();

        new DatabasePopulateService().populateDb(database);

        database.closeConnection();
    }
}
