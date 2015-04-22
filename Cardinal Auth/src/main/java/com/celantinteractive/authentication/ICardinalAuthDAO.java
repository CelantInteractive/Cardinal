package main.java.com.celantinteractive.authentication;

public interface ICardinalAuthDAO {

    /**
     * Given the users email get their hashed password.
     *
     * @param email The email address of the user
     * @return Hashed password for the user
     */
    public String getPasswordFromEmail(String email);

    /**
     * Given the email, accessToken, and clientToken create a new session
     *
     * @param email The email of the user
     * @param accessToken The client's unique accessToken
     * @param clientToken The clientToken of the users client
     */
    public void createSession(String email,String accessToken, String clientToken);
    
    /**
     * Given the newAccesstoken, oldAccessToken, and clientToken refresh the active session
     *
     * @param newAccesstoken The new accessToken for the user
     * @param oldAccessToken The old accessToken for the user which will be replaced
     * @param clientToken The clientToken of the users client
     */
    public void refreshSession(String newAccesstoken, String oldAccessToken, String clientToken);
    
    
    /**
     * 
     * @param clientToken
     * @param accessToken
     * @return true if the sessionId is the most recent, false otherwise
     */
    public Boolean sessionIsRecent(String clientToken, String accessToken);
    
    /**
     * 
     * @param clientToken The client token to check
     * @return True if is valid client token, false otherwise
     */
    public Boolean isValidClientToken(String clientToken);
    
    /**
     * Logs an error into the database
     * 
     * @param e The Exception thrown
     * @param timeDate The time and date of the error
     */
    public void logError(Exception e, Long timeDate);
    
}
