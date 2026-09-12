class DischargeSummary {

    private static String systemName;

    static {
        systemName = "MediTrack";
    }

    private final String patientId;
    private final String[] medicationCodes;


    public DischargeSummary(String patientId,
                             String[] medicationCodes) {

        if (medicationCodes == null) {
            throw new IllegalArgumentException("construction rejected");
        }

        for (String code : medicationCodes) {

            if (code == null ||
                !code.matches("MED-[A-Z]")) {

                throw new IllegalArgumentException(
                    "construction rejected");
            }
        }

        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
    }


    public String[] getMedicationCodes() {
        return medicationCodes.clone();
    }


    public DischargeSummary withCorrectedMedication(
            int index, String newCode) {

        if (index < 0 ||
            index >= medicationCodes.length ||
            newCode == null ||
            !newCode.matches("MED-[A-Z]")) {

            throw new IllegalArgumentException(
                "invalid correction");
        }

        String[] copy = medicationCodes.clone();
        copy[index] = newCode;

        return new DischargeSummary(patientId, copy);
    }


    static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int critical = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {

            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summary instanceof CriticalCareDischargeSummary)
                critical++;
            else
                routine++;
        }

        return processed + " processed | "
             + nullSkipped + " null skipped | "
             + critical + " critical-care | "
             + routine + " routine";
    }
}


class CriticalCareDischargeSummary
        extends DischargeSummary {

    private final int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }
}