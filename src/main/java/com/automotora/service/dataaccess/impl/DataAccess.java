package com.automotora.service.dataaccess.impl;

import com.automotora.service.exceptions.DAOException;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataAccess {

    private String dir = null;
    private String url = null;


    private static DataAccess dataAccess = null;


    public synchronized static DataAccess getInstance() throws DAOException {
        if(dataAccess == null) {
            dataAccess = new DataAccess();
        }
        return dataAccess;
    }

//    protected DataAccess() throws DAOException {
//        SQLiteConfig sqlConfig = new SQLiteConfig();
//        sqlConfig.setOpenMode(SQLiteOpenMode.FULLMUTEX);
//        sqlConfig.setJournalMode(SQLiteConfig.JournalMode.WAL);
//        sqlConfig.enforceForeignKeys(true);
//        properties = sqlConfig.toProperties();
//        dir = System.getProperty("user.dir") + Constants.DATABASE_RESOURCES;
//        url = "jdbc:sqlite:" + dir + ".db";
//        logger.debug(url);
//
//        createDatabase();
//    }
//
//    private void createDatabase() throws DAOException {
//        File fichero = new File(dir + ".db");
//        if (!fichero.exists()) {
//            connect();
//        }
//    }
//
//    private void connect() throws DAOException {
//        try (Connection conn = DriverManager.getConnection(url, properties)){
//            createTables();
//        }catch (SQLException e) {
//            throw new DAOException("Error al crear tablas en bd");
//        }
//    }
//
//    private void createTables() throws DAOException {
//        try (Connection conn = DriverManager.getConnection(url, properties)) {
//            try(Statement stmt = conn.createStatement()){
//                ArrayList<String> querys = parserSql(dir + ".sql");
//                for (String strQuery : querys) {
//                    stmt.execute(strQuery);
//                }
//            }
//        } catch (SQLException e) {
//            throw new DAOException("Error creando las tablas de la bd",e);
//        }
//    }


    private ArrayList<String> parserSql(String archivo) throws DAOException {
        try (
                FileReader f = new FileReader(archivo);
                BufferedReader b = new BufferedReader(f);
        ){
            ArrayList<String> querys = new ArrayList<>();
            String cadena;
            String concat = "";
            while((cadena = b.readLine())!=null) {
                if(cadena.length() > 0) {
                    if(cadena.contains(";")) {
                        concat += cadena;
                        querys.add(concat);
                        concat = "";
                    }else {
                        concat += cadena;
                    }
                }
            }
            return querys;
        } catch (FileNotFoundException e) {
            throw new DAOException("Error cargando el .sql",e);
        } catch (IOException e) {
            throw new DAOException("Error I/O en el .sql",e);
        }
    }

}
