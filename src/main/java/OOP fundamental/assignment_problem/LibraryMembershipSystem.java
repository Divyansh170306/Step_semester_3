class BrokenLibraryMember {
    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}

class LibraryMember {
    private String name;
    private String memberId;
    private int booksIssued;

    static String libraryName = "SRM Library";
    static int memberCount = 1000;

    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + memberCount;
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + (memberCount - 1000));
    }
}

public class LibraryMembershipSystem {

    public static void main(String[] args) {

        // Broken version:
        BrokenLibraryMember m1 =
                new BrokenLibraryMember("Aditi", "LM-1001", 2);

        BrokenLibraryMember m2 =
                new BrokenLibraryMember("Rohan", "LM-1002", 3);

        System.out.println("Broken version:");
        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);

        /*
         * name, memberId and booksIssued should NOT be static
         * because each member needs separate data.
         *
         * libraryName and memberCount SHOULD be static
         * because they are shared by the whole library.
         */

        // Fixed version:
        LibraryMember a = new LibraryMember("Aditi", 2);
        LibraryMember b = new LibraryMember("Rohan", 3);

        System.out.println("Fixed version:");
        a.printMemberCard();
        b.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}