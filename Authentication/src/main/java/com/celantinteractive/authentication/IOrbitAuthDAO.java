package main.java.com.celantinteractive.authentication;

public interface IOrbitAuthDAO {

    /**
     * Given the users UUID get their hashed password.
     *
     * @param UUID The UUID of the user
     * @return Hashed password for the user
     */
    public String getPasswordFromUUID(String UUID);

    /**
     * Given the users Username get their UUID
     *
     * @param username The username of the user
     * @return Thumbnail information, or null if the thumbnail could not be
     * found.
     */
    public String getUUIDFromUsername(String username);

    /**
     * Given the UUID, accessToken, and clientToken create a new session
     *
     * @param UUID The UUID of the user
     * @param accessToken The client's unique accessToken
     * @param clientToken The clientToken of the users client
     */
    public void createSession(String UUID,String accessToken, String clientToken);
    
    /**
     * Given the newAccesstoken, oldAccessToken, and clientToken refresh the active session
     *
     * @param newAccesstoken The new accessToken for the user
     * @param oldAccessToken The old accessToken for the user which will be replaced
     * @param clientToken The clientToken of the users client
     */
    public void refreshSession(String newAccesstoken, String oldAccessToken, String clientToken);
    
    public Boolean sessionIsValid(String accessToken);
    
    /**
     * Logs an error into the database
     * 
     * @param errorCode The error code thrown
     * @param timeDate The time and date of the error
     */
    public void logError(Exception e, Long timeDate);
    
}
