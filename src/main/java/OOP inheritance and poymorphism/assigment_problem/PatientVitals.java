import java.util.ArrayList;

class PatientVitals {

    private ArrayList<Double> readings;


    public PatientVitals(double[] initialReadings) {

        readings = new ArrayList<>();

        if (initialReadings != null) {

            for (double reading : initialReadings) {
                recordReading(reading);
            }
        }
    }


    public void recordReading(double reading) {

        if (reading <= 0 || reading > 45)
            return;

        if (readings.size() < 500)
            readings.add(reading);
    }


    public double getAverage() {

        if (readings.size() == 0)
            return 0.0;

        double sum = 0;

        for (double reading : readings) {
            sum += reading;
        }

        return sum / readings.size();
    }


    public double[] getAllReadings() {

        double[] result = new double[readings.size()];

        for (int i = 0; i < readings.size(); i++) {
            result[i] = readings.get(i);
        }

        return result;
    }
}