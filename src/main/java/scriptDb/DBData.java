package scriptDb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBData {
    public static void main(String[] args) {

        final String origenUrl;
        final String destinoUrl;
        final String password;
        final String user;


        try{
            InputStream input = new FileInputStream("src/main/resources/dbconfig.properties");
            Properties properties = new Properties();
            properties.load(input);

            origenUrl = properties.getProperty("db.origen.url");
            destinoUrl = properties.getProperty("db.destino.url");
            password = properties.getProperty("db.password");
            user = properties.getProperty("db.username");

            System.out.println(origenUrl +" "+user);
        } catch (IOException e) {
            throw new RuntimeException("Error obtaining properties. Error -> "+e.getMessage(),e);
        }

        try {
            Connection connection = DriverManager.getConnection(origenUrl,user,password);
            Connection connectionDestiny = DriverManager.getConnection(destinoUrl,user,password);

            final String query = "SELECT * FROM country";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            PreparedStatement statementDestino = connectionDestiny.prepareStatement("INSERT INTO country(country,last_update) VALUES(?,?)");
            while (resultSet.next()){
                for(int i=1;i<=2;i++){
                    statementDestino.setObject(i,resultSet.getObject(i+1));
                }
                statementDestino.executeUpdate();
            }

            connectionDestiny.close();
            connection.close();
            statementDestino.close();
            statement.close();
            resultSet.close();
            System.out.println("complete transfer");

        } catch (SQLException e) {
            throw new RuntimeException("Error to insert data. Error -> "+e.getMessage(),e);
        }

    }
}
