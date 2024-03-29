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
    public void createSession(String email, String accessToken, String clientToken);

    /**
     * Given the newAccesstoken, oldAccessToken, and clientToken refresh the
     * active session
     *
     * @param newAccesstoken The new accessToken for the user
     * @param oldAccessToken The old accessToken for the user which will be
     * replaced
     * @param clientToken The clientToken of the users client
     */
    public void refreshSession(String newAccesstoken, String oldAccessToken, String clientToken);

    /**
     *
     * @param clientToken
     * @param accessToken
     * @return true if the accessToken is the most recent for the clientToken,
     * false otherwise
     */
    public Boolean authenticateSession(String accessToken, String clientToken);

    /**
     *
     * @param accessToken
     * @return true if the accessToken is the most recent, false otherwise
     */
    public Boolean sessionIsRecent(String accessToken);

    /**
     *
     * @param clientToken The client token to check
     * @return True if is valid clientToken, false otherwise
     */
    public Boolean isValidClientToken(String clientToken);

    /**
     * Invalidates all sessions by email address
     *
     * @param email The email address of the user to sign out
     */
    public void invalidateSessionByEmail(String email);

    /**
     * Retrieves
     *
     * @param email The email address of the user to sign out
     * @return The user's unique CardinalId
     */
    public String getCardinalIdFromEmail(String email);

    /**
     * Invalidates all sessions by valid access/client token pair
     *
     * @param accessToken Valid accessToken of the user
     * @param clientToken The clientToken used to obtain the accessToken
     */
    public void invalidateSessionByPair(String accessToken, String clientToken);

    /**
     * Logs an error into the database
     *
     * @param e The Exception thrown
     * @param timeDate The time and date of the error
     */
    public void logError(Exception e, Long timeDate);

}
