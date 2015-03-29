package com.webhomecover.dao;

import com.webhomecover.model.*;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;

public class ClaimDAO {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement prepStatement = null;
    private ResultSet results = null;
    private static final String DATASOURCE_NAME = "java:comp/env/jdbc/webhomecoverds";
    private static final String INSERT_STATEMENT = "INSERT INTO Claim (reference, description, amount, approved, claimDate) VALUES (?,?,?,?,?)";

    private void getConnection() {
        if (connection == null) {
            try {
                Context initialContext = new InitialContext();
                DataSource ds = (DataSource) initialContext.lookup(DATASOURCE_NAME);
                connection = ds.getConnection();
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createClaim(Claim claim) {
        try {
            getConnection();
            prepStatement = connection.prepareStatement(INSERT_STATEMENT);
            prepStatement.setString(1, claim.getReference());
            prepStatement.setString(2, claim.getDescription());
            prepStatement.setDouble(3, claim.getAmount());
            prepStatement.setBoolean(4, claim.getApproved());
            long time = claim.getClaimDate().getTime();
            java.sql.Date date = new java.sql.Date(time);
            prepStatement.setDate(5, date);
            prepStatement.executeUpdate();
            prepStatement.close();
            prepStatement = null;
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanUp();
        }
    }

    public void linkToPolicy(Claim claim, Policy policy) {
        String reference = claim.getReference();
        String policyNumber = policy.getPolicyNumber();
        try {
            getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT policyID_PK FROM policy WHERE policyNumber=\'" + policyNumber + "\'");
            int key = 0;
            while (results.next()) {
                key = results.getInt("policyID_PK");
            }
            results.close();
            results = null;
            statement.close();
            statement = null;
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE claim SET claim.policyID_FK = " + key + " WHERE claim.reference=\'" + reference + "\'");
            statement.close();
            statement = null;
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanUp();
        }
    }

    public Collection<Claim> readClaims(String whereClause) {
        Collection<Claim> claims = new ArrayList<Claim>();
        try {
            getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM claim " + whereClause);
            String reference = null;
            String description = null;
            double amount = 0.0;
            boolean approved = false;
            java.sql.Date claimDate = null;
            Claim claim = null;
            while (results.next()) {
                reference = results.getString("reference");
                description = results.getString("description");
                amount = results.getDouble("amount");
                approved = results.getBoolean("approved");
                claimDate = results.getDate("claimDate");
                claim = new Claim();
                claim.setReference(reference);
                claim.setDescription(description);
                claim.setAmount(amount);
                claim.setApproved(approved);
                claim.setClaimDate(claimDate);
                claims.add(claim);
            }
            results.close();
            results = null;
            statement.close();
            statement = null;
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanUp();
        }
        return claims;
    }

    private void cleanUp() {
        // always make sure result sets, statements and connections are closed,
        if (results != null) {
            try {
                results.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            results = null;
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }
        if (prepStatement != null) {
            try {
                prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            prepStatement = null;
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }
}
