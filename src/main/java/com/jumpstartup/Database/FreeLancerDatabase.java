package com.jumpstartup.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jumpstartup.Freelancer.FreelancerBean;

public class FreeLancerDatabase {
    public boolean addFreelancer(FreelancerBean freelancer) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:file:./jsudb.h2.db", "sa", "");
            String sql = "INSERT INTO Freelancer (uuid,phone_number, skills, linkedin_link) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,   freelancer.getUuid_f());
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error while trying to close database connection: " + e.getMessage());
                }
            }
        }
    }

    public boolean addEducation(String uuid, String institution, String degree, String major, int year_of_completion) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:file:./jsudb.h2.db", "sa", "");
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error while trying to close database connection: " + e.getMessage());
                }
            }
        }
    }

    public boolean addWorkExperience(String uuid, String work_experience) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:file:./jsudb.h2.db", "sa", "");
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error while trying to close database connection: " + e.getMessage());
                }
            }
        }
    }

    public boolean updateFreelancer(int id, FreelancerBean freelancer) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:file:./jsudb.h2.db", "sa", "");
            String sql = "UPDATE Freelancer SET phone_number = ?, skills = ?, linkedin_link = ?, institution = ?, degree = ?, major = ?, year_of_completion = ?, work_experience = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, freelancer.getPhone_number());
            statement.setString(2, freelancer.getSkills());
            statement.setString(3, freelancer.getLinkedin_link());
            statement.setString(4, freelancer.getInstitution());
            statement.setString(5, freelancer.getDegree());
            statement.setString(6, freelancer.getMajor());
            statement.setInt(7, freelancer.getYear_of_completion());
            statement.setString(8, freelancer.getWork_experience());
            statement.setInt(9, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to update freelancer: " + e.getMessage());
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error while trying to close database connection: " + e.getMessage());
                }
            }
        }
    }

    public boolean deleteFreelancer(int id) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:file:./jsudb.h2.db", "sa", "");
            String sql = "DELETE FROM Freelancer WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to delete freelancer: " + e.getMessage());
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error while trying to close database connection: " + e.getMessage());
                }
            }
        }
    }
}