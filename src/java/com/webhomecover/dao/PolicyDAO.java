package com.webhomecover.dao;

import com.webhomecover.model.Policy;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PolicyDAO {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement prepStatement = null;
    private ResultSet results = null;
    private static final String DATASOURCE_NAME = "java:comp/env/jdbc/webhomecoverds";
    private static final String INSERT_STATEMENT = "INSERT INTO policy (policyNumber,startDate,annualPremium,paidUp) VALUES (?,?,?,?)";

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

    public void createPolicy(Policy policy) {
        try {
            getConnection();
            prepStatement = connection.prepareStatement(INSERT_STATEMENT);
            prepStatement.setString(1, policy.getPolicyNumber());

            java.sql.Date sqlDate = new java.sql.Date(policy.getStartDate().getTime());
            prepStatement.setDate(2, sqlDate);
            prepStatement.setDouble(3, policy.getAnnualPremium());
            prepStatement.setBoolean(4, policy.getPaidUp());
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

    public Collection<Policy> readPolicies(String whereClause) {
        Collection<Policy> policies = new ArrayList<Policy>();
        try {
            getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM policy " + whereClause);
            int id = 0;
            String policyNumber = null;
            Date startDate = null;
            double annualPremium = 0.0;
            boolean paidUp = false;
            Policy policy = null;
            while (results.next()) {
                id = results.getInt("policyID_PK");
                policyNumber = results.getString("policyNumber");
                startDate = results.getDate("startDate");
                annualPremium = results.getDouble("annualPremium");
                paidUp = results.getBoolean("paidUp");
                policy = new Policy();
                policy.setPolicyNumber(policyNumber);
                policy.setStartDate(startDate);
                policy.setAnnualPremium(annualPremium);
                policy.setPaidUp(paidUp);
                policies.add(policy);
                System.out.println("Read policy number" + policyNumber);
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
        return policies;
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
