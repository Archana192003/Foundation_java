package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Adminmodel;
import exception.*;
import util.ConnectionHelper;

public class Admindaoimpl implements Admindao{
	@Override
    public Adminmodel getAdminById(int adminId) throws AdminNotFoundException{
        String query = "SELECT * FROM Admin WHERE AdminID = ?";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, adminId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Adminmodel(
                        rs.getInt("AdminID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getDate("JoinDate")
                );
            } else {
                throw new AdminNotFoundException("Admin not found with ID: " + adminId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    @Override
    public Adminmodel getAdminByUsername(Adminmodel user) throws AdminNotFoundException{
        String query = "SELECT * FROM Admin WHERE Username = ?";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Adminmodel(
                        rs.getInt("AdminID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getDate("JoinDate")
                );
            } else {
                throw new AdminNotFoundException("Admin not found with Username: " + user.getUsername());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    @Override
    public boolean registerAdmin(Adminmodel admin) {
        String query = "INSERT INTO Admin (FirstName, LastName, Email, PhoneNumber, Username, PasswordHash, JoinDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, admin.getFirstName());
            stmt.setString(2, admin.getLastName());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPhoneNumber());
            stmt.setString(5, admin.getUsername());
            stmt.setString(6, admin.getPasswordHash());
            stmt.setDate(7, new java.sql.Date(admin.getDate().getTime()));
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
 
    @Override
    public boolean updateAdmin(Adminmodel admin) {
        String query = "UPDATE Admin SET FirstName=?, LastName=?, Email=?, PhoneNumber=?, PasswordHash=? WHERE AdminID=?";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, admin.getFirstName());
            stmt.setString(2, admin.getLastName());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPhoneNumber());
            stmt.setString(5, admin.getPasswordHash());
            stmt.setInt(6, admin.getAdminID());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
 
    @Override
    public boolean deleteAdmin(int adminId) {
        String query = "DELETE FROM Admin WHERE AdminID=?";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, adminId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
