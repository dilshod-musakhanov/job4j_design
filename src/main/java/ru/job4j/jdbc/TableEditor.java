package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    private static final Logger LOG = LoggerFactory.getLogger(TableEditor.class.getName());

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            LOG.error("Invalid class passed to Class.forName():", e);
        }
        try {
            connection =  DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password")
            );
        } catch (Exception e) {
            LOG.error("Invalid properties:", e);
        }
    }

    private void getStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            LOG.error("Execution failed:", e);
        }
    }

    private void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists %s (%s, %s);",
                tableName,
                "id serial primary key",
                "name text"
        );
        getStatement(sql);
        System.out.println(getTableScheme(connection, tableName));
        close();
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format(
                "drop table %s",
                tableName
        );
        getStatement(sql);
        close();
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "alter table %s add %s %s",
                tableName, columnName, type
        );
        getStatement(sql);
        System.out.println(getTableScheme(connection, tableName));
        close();
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "alter table %s drop column %s",
                tableName, columnName
        );
        getStatement(sql);
        System.out.println(getTableScheme(connection, tableName));
        close();
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "alter table %s rename column %s to %s",
                tableName, columnName, newColumnName
        );
        getStatement(sql);
        System.out.println(getTableScheme(connection, tableName));
        close();
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = StatementDemo.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            TableEditor tableEditor = new TableEditor(config);
            tableEditor.createTable("Task");
            tableEditor.addColumn("Task", "completed", "boolean");
            tableEditor.renameColumn("Task", "completed", "assigned");
            tableEditor.dropColumn("Task", "assigned");
            tableEditor.dropTable("Task");
        } catch (Exception e) {
            LOG.error("Property file is not valid or execution failed", e);
        }
    }

}
