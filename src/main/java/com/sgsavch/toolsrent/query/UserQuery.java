package com.sgsavch.toolsrent.query;

public interface UserQuery {
    String INSERT_USER_QUERY = "INSERT INTO Users (first_name, last_name, email, password) VALUES (:firstName, :lastName, :email, :password)";
    String COUNT_USER_EMAIL_QUERY = "SELECT COUNT(*) FROM Users WHERE email = :email";
    String INSERT_ACCOUNT_VERIFICATION_URL_QUERY = "INSERT INTO AccountVerifications (user_id, url) VALUES (:userId, :url)";
    String SELECT_USER_BY_EMAIL_QUERY = "SELECT * FROM Users WHERE email = :email";
    String DELETE_VERIFICATION_CODE_BY_USER_ID = "DELETE FROM TwoFactorVerification WHERE user_id = : id";
    String INSERT_VERIFICATION_CODE_QUERY = "INSERT INTO TwoFactorVerification (user_id, code, expiration_date) VALUES (:userId, :code, expirationDate)";
}
