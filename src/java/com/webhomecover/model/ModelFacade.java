package com.webhomecover.model;

import com.webhomecover.util.ReferenceGenerator;
import com.webhomecover.beans.ClaimBean;
import com.webhomecover.dao.ClaimDAO;
import com.webhomecover.dao.PolicyDAO;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.beanutils.PropertyUtils;
import java.util.Date;

public class ModelFacade {

    public static Claim createClaim(ClaimBean claimBean) {
        Claim claim = null;
// get the policy number and look for a matching policy in the database
        String policyNumber = claimBean.getPolicyNumber();
        PolicyDAO policyDAO = new PolicyDAO();
        Collection<Policy> c = policyDAO.readPolicies("WHERE policyNumber=\'" + policyNumber + "\'");
        Iterator<Policy> iter = c.iterator();
// if we find a match, create a Claim and link it to the policy
        if (iter.hasNext()) {
            claim = new Claim();
            try {
                PropertyUtils.copyProperties(claim, claimBean);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            } catch (java.lang.reflect.InvocationTargetException e) {
                e.printStackTrace();
                return null;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            }
            // finish populating the bean
            claim.setApproved(false);
            claim.setClaimDate(new java.sql.Date(new java.util.Date().getTime()));
            String reference = ReferenceGenerator.getReference(claim);
            claim.setReference(reference);

// get the policy
            Policy policy = iter.next();

            // write the claim to the database
            ClaimDAO claimDAO = new ClaimDAO();
            claimDAO.createClaim(claim);
            // set up the foreign key in the database
            claimDAO.linkToPolicy(claim, policy);
            // link the two objects (bidirectional association)
            claim.setPolicy(policy);
            policy.addClaim(claim);
        }
        // return the claim, associated with the policy
        return claim;
    }

    public static Collection<Claim> getClaims(String whereClause) {
        // if no 'where' clause string is passed in, create an empty string
        if (whereClause == null) {
            whereClause = new String();
        }
        ClaimDAO cdao = new ClaimDAO();
        Collection<Claim> claimCollection = cdao.readClaims(whereClause);
        return claimCollection;
    }
}
