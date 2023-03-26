package com.jumpstartup.Database;

import com.jumpstartup.Connection.DatabaseConnector;
import com.jumpstartup.Entrepreneur.EntrepreneurBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EntrepreneurDatabase {

    private static final Logger logger = LoggerFactory.getLogger(EntrepreneurDatabase.class);
    public boolean addEntrepreneur(EntrepreneurBean entrepreneur) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "INSERT INTO Entrepreneur (uuid,phone_number, domain) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entrepreneur.getUuid());
            statement.setString(2, entrepreneur.getPhone_number());
            statement.setString(3, entrepreneur.getDomain());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to add entrepreneur: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean addEducation(String uuid, String institution, String degree, String major, String year_of_completion) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "INSERT INTO Education (UUID, institution, degree, major, year_of_completion) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, uuid);
            statement.setString(2, institution);
            statement.setString(3, degree);
            statement.setString(4, major);
            statement.setString(5, year_of_completion);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Education added successfully for entrepreneur with UUID: {}", uuid);
                return true;
            } else {
                logger.warn("Failed to add education for entrepreneur with UUID: {}", uuid);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to add education: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean addWorkExperience(String uuid, String work_experience) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "INSERT INTO Work_Experience (UUID, work_experience) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, uuid);
            statement.setString(2, work_experience);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Work experience added successfully for entrepreneur with UUID: {}", uuid);
                return true;
            } else {
                logger.warn("Failed to add work experience for entrepreneur with UUID: {}", uuid);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to add work experience: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }
    
    public boolean updateEntrepreneur(String uuid, EntrepreneurBean entrepreneur) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();

            // update entrepreneur information
            String sql = "UPDATE Entrepreneur SET phone_number = ?, domain = ? WHERE uuid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entrepreneur.getPhone_number());
            statement.setString(2, entrepreneur.getDomain());
            statement.setString(3, uuid);

            int rowsAffected = statement.executeUpdate();

            // update education information
            String sqlEducation = "UPDATE Education SET institution = ?, degree = ?, major = ?, year_of_completion = ? WHERE uuid = ?";
            PreparedStatement statementEducation = connection.prepareStatement(sqlEducation);
            statementEducation.setString(1, entrepreneur.getInstitution());
            statementEducation.setString(2, entrepreneur.getDegree());
            statementEducation.setString(3, entrepreneur.getMajor());
            statementEducation.setString(4, entrepreneur.getYear_of_completion());
            statementEducation.setString(5, uuid);

            int rowsAffectedEducation = statementEducation.executeUpdate();

            // update work experience information
            String sqlWorkExperience = "UPDATE Work_Experience SET work_experience = ? WHERE uuid = ?";
            PreparedStatement statementWorkExperience = connection.prepareStatement(sqlWorkExperience);
            statementWorkExperience.setString(1, entrepreneur.getWork_experience());
            statementWorkExperience.setString(2, uuid);

            int rowsAffectedWorkExperience = statementWorkExperience.executeUpdate();

            // check if updates were successful
            if (rowsAffected > 0 && rowsAffectedEducation > 0 && rowsAffectedWorkExperience > 0) {
                logger.info("Entrepreneur with UUID {} has been updated successfully.", uuid);
                return true;
            } else {
                logger.warn("Failed to update entrepreneur with UUID {}.", uuid);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to update entrepreneur: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean deleteEntrepreneur(String UUID) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();

            String educationSql = "DELETE FROM Education WHERE uuid = ?";
            PreparedStatement educationStatement = connection.prepareStatement(educationSql);
            educationStatement.setString(1, UUID);
            int educationRowsAffected = educationStatement.executeUpdate();
            if (educationRowsAffected <= 0) {
                logger.warn("No education rows affected while deleting entrepreneur with UUID: {}", UUID);
                return false;
            }
            
            String workExperienceSql = "DELETE FROM Work_Experience WHERE uuid = ?";
            PreparedStatement workExperienceStatement = connection.prepareStatement(workExperienceSql);
            workExperienceStatement.setString(1, UUID);
            int workExperienceRowsAffected = workExperienceStatement.executeUpdate();
            if (workExperienceRowsAffected <= 0) {
                logger.warn("No work experience rows affected while deleting entrepreneur with UUID: {}", UUID);
                return false;
            }
            
            // Delete entrepreneur
            String sql = "DELETE FROM Entrepreneur WHERE uuid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, UUID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0 ) {
                logger.info("Entrepreneur with UUID {} deleted successfully", UUID);
                return true;
            } else {
                logger.warn("No table 'Entrepreneur' rows affected while deleting entrepreneur with UUID: {}", UUID);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to delete entrepreneur with UUID: {}, error message: {}", UUID, e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public EntrepreneurBean getEntrepreneur(String UUID) {
        EntrepreneurBean entrepreneur = null;
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Entrepreneur WHERE uuid = ?");
            statement.setString(1, UUID);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                entrepreneur = new EntrepreneurBean();
                entrepreneur.setUuid(result.getString("uuid"));
                entrepreneur.setPhone_number(result.getString("phone_number"));
                entrepreneur.setDomain(result.getString("domain"));

                statement = connection.prepareStatement("SELECT * FROM education WHERE uuid = ?");
                statement.setString(1, UUID);
                result = statement.executeQuery();
                if (result.next()) {
                    entrepreneur.setInstitution(result.getString("institution"));
                    entrepreneur.setDegree(result.getString("degree"));
                    entrepreneur.setMajor(result.getString("major"));
                    entrepreneur.setYear_of_completion(result.getString("year_of_completion"));
                }

                statement = connection.prepareStatement("SELECT * FROM work_experience WHERE uuid = ?");
                statement.setString(1, UUID);
                result = statement.executeQuery();
                if (result.next()) {
                    entrepreneur.setWork_experience(result.getString("work_experience"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        //Logging statement here System.out.println("FETCHED ENTREPRENEUR SUCCESSFULLY !!");
        return entrepreneur;
    }

}
