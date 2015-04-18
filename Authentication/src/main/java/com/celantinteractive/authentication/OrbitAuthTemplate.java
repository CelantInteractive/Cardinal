/**
 * Copyright (c) 2015 Celant Interactive Ltd. All rights reserved
 */
package main.java.com.celantinteractive.authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 * Lesson Database template
 */
public class OrbitAuthTemplate implements IOrbitAuthDAO {

    DataSource dataSource;

    // Called by bean framework
    public void setDataSource(DataSource source) {
        dataSource = source;
    }

    @Override
    public String getPasswordFromUUID(String UUID) {

        Connection conn = null;
        String ret = null;

        try {

            conn = (Connection) dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("call getPasswordFromUUID(?)");

            stmt.setString(1, UUID);

            ResultSet results = stmt.executeQuery();

            if (results.first()) {
                ret = results.getString(1);
            }
        } catch (Exception ex) {
            logError(ex, System.currentTimeMillis());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    logError(ex, System.currentTimeMillis());
                }
            }
        }

        return ret;
    }

    @Override
    public String getUUIDFromUsername(String username) {

        Connection conn = null;
        String ret = null;

        try {

            conn = (Connection) dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("call getUUIDFromUsername(?)");

            stmt.setString(1, username);

            ResultSet results = stmt.executeQuery();

            if (results.first()) {
                ret = results.getString(1);
            }
        } catch (Exception ex) {
            logError(ex, System.currentTimeMillis());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    logError(ex, System.currentTimeMillis());
                }
            }
        }

        return ret;
    }

    @Override
    public void createSession(String UUID, String accessToken, String clientToken) {
        Connection conn = null;

        try {

            conn = (Connection) dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("call createSession(?,?,?)");

            stmt.setString(1, UUID);
            stmt.setString(2, clientToken);
            stmt.setString(3, accessToken);

            stmt.executeQuery();
        } catch (Exception ex) {
            logError(ex, System.currentTimeMillis());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    logError(ex, System.currentTimeMillis());
                }
            }
        }
    }

    @Override
    public void refreshSession(String newAccesstoken, String oldAccessToken, String clientToken) {
        Connection conn = null;

        try {

            conn = (Connection) dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("call refreshSession(?,?,?)");

            stmt.setString(1, newAccesstoken);
            stmt.setString(2, oldAccessToken);
            stmt.setString(3, clientToken);

            ResultSet result = stmt.executeQuery();

            String string = result.getString(1);

        } catch (Exception ex) {
            logError(ex, System.currentTimeMillis());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    logError(ex, System.currentTimeMillis());
                }
            }
        }
    }

    @Override
    public void logError(Exception e, Long timeDate) {

        Connection conn = null;
        String errorMessage = "";

        try {

            conn = (Connection) dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("call logError(?,?)");

            errorMessage = e.getMessage();

            if (errorMessage != null) {
                stmt.setString(1, errorMessage);
            } else {
                stmt.setString(1, e.getClass().getName());
            }

            stmt.setString(2, timeDate.toString());

            stmt.executeQuery();

        } catch (Exception ex) {
            Logger.getLogger(OrbitAuthTemplate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    Logger.getLogger(OrbitAuthTemplate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Boolean sessionIsValid(String accessToken) {

        Connection conn = null;
        Boolean ret = false;

        try {

            conn = (Connection) dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("call sessionIsValid(?)");

            stmt.setString(1, accessToken);

            ResultSet results = stmt.executeQuery();

            if (results.first()) {
                if (results.getString(1).equals("1")) {
                    ret = true;
                }
            }
        } catch (Exception ex) {
            logError(ex, System.currentTimeMillis());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    logError(ex, System.currentTimeMillis());
                }
            }
        }

        return ret;
    }
}
