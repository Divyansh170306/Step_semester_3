class AccessChecker {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS"))
                return "ALLOWED";

            return "DENIED";
        }

        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";

            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";

            return "DENIED";
        }

        if (fieldModifier.equals("public"))
            return "ALLOWED";

        return "DENIED";
    }


    static String summarizeByModifier(String[][] attempts) {

        int privateAllowed = 0;
        int privateDenied = 0;

        int defaultAllowed = 0;
        int defaultDenied = 0;

        int protectedAllowed = 0;
        int protectedDenied = 0;

        int publicAllowed = 0;
        int publicDenied = 0;


        for (String[] attempt : attempts) {

            String modifier = attempt[0];
            String context = attempt[1];

            boolean allowed =
                classifyAccess(modifier, context)
                .equals("ALLOWED");


            if (modifier.equals("private")) {

                if (allowed)
                    privateAllowed++;
                else
                    privateDenied++;

            } else if (modifier.equals("default")) {

                if (allowed)
                    defaultAllowed++;
                else
                    defaultDenied++;

            } else if (modifier.equals("protected")) {

                if (allowed)
                    protectedAllowed++;
                else
                    protectedDenied++;

            } else if (modifier.equals("public")) {

                if (allowed)
                    publicAllowed++;
                else
                    publicDenied++;
            }
        }


        return "private: " + privateAllowed +
               " allowed / " + privateDenied +
               " denied | default: " + defaultAllowed +
               " allowed / " + defaultDenied +
               " denied | protected: " + protectedAllowed +
               " allowed / " + protectedDenied +
               " denied | public: " + publicAllowed +
               " allowed / " + publicDenied +
               " denied";
    }
}


class LibraryMember {

    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;


    public LibraryMember(String membershipId,
                         String branchCode,
                         double finesOwed,
                         String displayName) {

        if (membershipId == null ||
            membershipId.trim().length() < 4) {

            throw new IllegalArgumentException(
                "construction rejected");
        }

        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}