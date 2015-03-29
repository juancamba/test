package com.webhomecover.util;

import com.webhomecover.model.Claim;

public class ReferenceGenerator {

    public static String getReference(Claim claim) {
        StringBuffer reference = new StringBuffer();
        String type = claim.getType();
        if (type.startsWith("c") || type.startsWith("C")) {
            reference.append('C');
        } else {
            reference.append('B');
        }
        String date = claim.getClaimDate().toString();
        reference.append(date);
        int rand = (int) Math.floor(Math.random() * 999);
        reference.append(rand);
        return reference.toString();
    }
}
