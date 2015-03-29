package com.webhomecover.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webhomecover.beans.ClaimBean;
import com.webhomecover.model.Claim;
import com.webhomecover.model.Policy;
import com.webhomecover.model.ModelFacade;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClaimAction extends Action {

    private static Log log = LogFactory.getLog(ClaimAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) {
        if (log.isInfoEnabled()) {
            log.info("In ClaimAction");
        }
        System.out.println("test");
        ClaimBean claimBean = (ClaimBean) form;
        Claim claim = ModelFacade.createClaim(claimBean);
        request.setAttribute("claim", claim);
        if (claim == null) {
            return mapping.findForward("notfound");
        } else {
            Policy policy = claim.getPolicy();
            request.setAttribute("policy", policy);
            return mapping.findForward("found");
        }
    }
}
