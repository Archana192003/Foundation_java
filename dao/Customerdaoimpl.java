package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.Customermodel;
import util.ConnectionHelper;
 
public  class Customerdaoimpl implements CustomerDao {
 
    public Customerdaoimpl() {
        try {
            ConnectionHelper.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public Customermodel getCustomerById(int customerId) {
        String query = "SELECT * FROM Customer WHERE CustomerID = ?";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
 
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customermodel(
                        rs.getInt("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("Username"),
                        rs.getString("Passwordhash"),
                        rs.getDate("RegistrationDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Customermodel getCustomerByUsername(String username) {
        String query = "SELECT * FROM Customer WHERE Username = ?";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
 
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customermodel(
                        rs.getInt("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("Username"),
                        rs.getString("Passwordhash"),
                        rs.getDate("RegistrationDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    @Override
    public boolean registerCustomer(Customermodel customer) {
        String query = "INSERT INTO Customer (FirstName, LastName, Email, PhoneNumber, Address, Username, PasswordHash, RegistrationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
 
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhoneNumber());
            ps.setString(5, customer.getAddress());
            ps.setString(6, customer.getUsername());
            ps.setString(7, customer.getPasswordHash());
            ps.setDate(8, new java.sql.Date(customer.getRegistrationdate ().getTime()));
 
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
 
    @Override
    public boolean updateCustomer(Customermodel customer) {
        String query = "UPDATE Customer SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, Address = ?, Passwordhash = ? WHERE CustomerID = ?";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
 
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhoneNumber());
            ps.setString(5, customer.getAddress());
            ps.setString(6, customer.getPasswordHash());
            ps.setInt(7, customer.getCustomerID());
 
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
 
    @Override
    public boolean deleteCustomer(int customerId) {
        String query = "DELETE FROM Customer WHERE CustomerID = ?";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
 
            ps.setInt(1, customerId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
