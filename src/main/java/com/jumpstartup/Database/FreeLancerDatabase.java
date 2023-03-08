package com.jumpstartup.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jumpstartup.Connection.DatabaseConnector;

import com.jumpstartup.Freelancer.FreelancerBean;


public class FreeLancerDatabase {
    public boolean addFreelancer(FreelancerBean freelancer) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "INSERT INTO Freelancer (uuid,phone_number, skills, linkedin_link) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, freelancer.getUuid());
            statement.setString(2, freelancer.getPhone_number());
            statement.setString(3, freelancer.getSkills());
            statement.setString(4, freelancer.getLinkedin_link());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to add freelancer: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean addEducation(String uuid, String institution, String degree, String major, int year_of_completion) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "INSERT INTO Education (UUID, institution, degree, major, year_of_completion) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, uuid);
            statement.setString(2, institution);
            statement.setString(3, degree);
            statement.setString(4, major);
            statement.setInt(5, year_of_completion);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to add education: " + e.getMessage());
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
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to add work experience: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean updateFreelancer(String uuid, FreelancerBean freelancer) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            // update freelancer information
            String sqlFreelancer = "UPDATE Freelancer SET phone_number = ?, skills = ?, linkedin_link = ? WHERE uuid = ?";
            PreparedStatement statementFreelancer = connection.prepareStatement(sqlFreelancer);
            statementFreelancer.setString(1, freelancer.getPhone_number());
            statementFreelancer.setString(2, freelancer.getSkills());
            statementFreelancer.setString(3, freelancer.getLinkedin_link());
            statementFreelancer.setString(4, uuid);

            int rowsAffectedFreelancer = statementFreelancer.executeUpdate();

            // update education information
            String sqlEducation = "UPDATE Education SET institution = ?, degree = ?, major = ?, year_of_completion = ? WHERE uuid = ?";
            PreparedStatement statementEducation = connection.prepareStatement(sqlEducation);
            statementEducation.setString(1, freelancer.getInstitution());
            statementEducation.setString(2, freelancer.getDegree());
            statementEducation.setString(3, freelancer.getMajor());
            statementEducation.setInt(4, freelancer.getYear_of_completion());
            statementEducation.setString(5, uuid);

            int rowsAffectedEducation = statementEducation.executeUpdate();

            // update work experience information
            String sqlWorkExperience = "UPDATE Work_Experience SET work_experience = ? WHERE uuid = ?";
            PreparedStatement statementWorkExperience = connection.prepareStatement(sqlWorkExperience);
            statementWorkExperience.setString(1, freelancer.getWork_experience());
            statementWorkExperience.setString(2, uuid);

            int rowsAffectedWorkExperience = statementWorkExperience.executeUpdate();

            // check if all updates were successful
            if (rowsAffectedFreelancer > 0 && rowsAffectedEducation > 0 && rowsAffectedWorkExperience > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to update freelancer: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public static boolean deleteFreelancer(String UUID) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            // Delete education
            String educationSql = "DELETE FROM Education WHERE uuid = ?";
            PreparedStatement educationStatement = connection.prepareStatement(educationSql);
            educationStatement.setString(1, UUID);
            int educationRowsAffected = educationStatement.executeUpdate();
            if (educationRowsAffected <= 0) {
                System.out.print("educationRowsAffected");
                return false;
            }

            // Delete work experience
            String workExperienceSql = "DELETE FROM Work_Experience WHERE uuid = ?";
            PreparedStatement workExperienceStatement = connection.prepareStatement(workExperienceSql);
            workExperienceStatement.setString(1, UUID);
            int workExperienceRowsAffected = workExperienceStatement.executeUpdate();
            if (workExperienceRowsAffected <= 0) {
                System.out.print("WORKRowsAffected");
                return false;
            }

            // Delete freelancer
            String freelancerSql = "DELETE FROM Freelancer WHERE uuid = ?";
            PreparedStatement freelancerStatement = connection.prepareStatement(freelancerSql);
            freelancerStatement.setString(1, UUID);
            int freelancerRowsAffected = freelancerStatement.executeUpdate();
            if (freelancerRowsAffected <= 0) {
                System.out.print("FREELANCERRowsAffected");
                return false;
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Error while trying to delete freelancer: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }


    public FreelancerBean getFreelancer(String UUID) {
        FreelancerBean freelancer = null;
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM freelancer WHERE uuid = ?");
            statement.setString(1, UUID);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                freelancer = new FreelancerBean();
                freelancer.setUuid(result.getString("uuid"));
                freelancer.setPhone_number(result.getString("phone_number"));
                freelancer.setSkills(result.getString("skills"));
                freelancer.setLinkedin_link(result.getString("linkedin_link"));

                statement = connection.prepareStatement("SELECT * FROM education WHERE uuid = ?");
                statement.setString(1, UUID);
                result = statement.executeQuery();
                if (result.next()) {
                    freelancer.setInstitution(result.getString("institution"));
                    freelancer.setDegree(result.getString("degree"));
                    freelancer.setMajor(result.getString("major"));
                    freelancer.setYear_of_completion(result.getInt("year_of_completion"));
                }

                statement = connection.prepareStatement("SELECT * FROM work_experience WHERE uuid = ?");
                statement.setString(1, UUID);
                result = statement.executeQuery();
                if (result.next()) {
                    freelancer.setWork_experience(result.getString("work_experience"));
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return freelancer;

    }

}