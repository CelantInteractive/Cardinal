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
public class CardinalAuthTemplate implements ICardinalAuthDAO {

    DataSource dataSource;

    // Called by bean framework
    public void setDataSource(DataSource source) {
        dataSource = source;
    }

    @Override
    public String getPasswordFromEmail(String email) {

        Connection conn = null;
        String ret = "";

        try {

            conn = (Connection) dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("call getPasswordFromEmail(?)");

            stmt.setString(1, email);

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
    public void createSession(String email, String accessToken, String clientToken) {
        Connection conn = null;

        try {

            conn = (Connection) dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("call createSession(?,?,?)");

            stmt.setString(1, email);
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
    public Boolean authenticateSession(String clientToken, String accessToken) {

        Connection conn = null;
        Boolean ret = false;

        try {

            conn = (Connection) dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("call authenticateSession(?,?)");

            stmt.setString(1, clientToken);
            stmt.setString(2, accessToken);

            ResultSet results = stmt.executeQuery();

            if (results.first()) {
                if (results.getBoolean("recent")) {
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

    @Override
    public Boolean sessionIsRecent(String accessToken) {

        Connection conn = null;
        Boolean ret = false;

        try {

            conn = (Connection) dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("call sessionIsRecent(?)");

            stmt.setString(2, accessToken);

            ResultSet results = stmt.executeQuery();

            if (results.first()) {
                if (results.getBoolean("recent")) {
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

    @Override
    public Boolean isValidClientToken(String clientToken) {

        Connection conn = null;
        Boolean ret = false;

        try {

            conn = (Connection) dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("call isValidClientToken(?)");

            stmt.setString(1, clientToken);

            ResultSet results = stmt.executeQuery();

            if (results.first()) {
                if (results.getBoolean("isValid")) {
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

    @Override
    public void invalidateSessionByEmail(String email) {

        Connection conn = null;

        try {

            conn = (Connection) dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("call invalidateSessionByEmail(?)");

            stmt.setString(1, email);

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
    public void invalidateSessionByPair(String accessToken, String clientToken) {

        Connection conn = null;

        try {

            conn = (Connection) dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("call invalidateSessionByPair(?,?)");

            stmt.setString(1, accessToken);
            stmt.setString(2, clientToken);

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
    public String getCardinalIdFromEmail(String email) {

        Connection conn = null;
        String ret = "";

        try {

            conn = (Connection) dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("call getCardinalIdFromEmail(?)");

            stmt.setString(1, email);

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
    public void logError(Exception e, Long timeDate) {

        Connection conn = null;
        String errorMessage = "";

        Logger.getLogger(CardinalAuthTemplate.class.getName()).log(Level.SEVERE, null, e);

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
            Logger.getLogger(CardinalAuthTemplate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    Logger.getLogger(CardinalAuthTemplate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
