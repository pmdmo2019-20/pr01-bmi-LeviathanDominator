package es.iessaladillo.pedrojoya.pr01.bmi;

import java.text.DecimalFormat;

/**
 * Allow Bmi calculation and clasification
 */
public class BmiCalculator {

    /**
     * @param weightInKgs    Weight of the person in kgs
     * @param heightInMeters Height of the person in meters
     * @return The body mass index (BMI)
     */
    public float calculateBmi(float weightInKgs, float heightInMeters) {
        if (weightInKgs <= 0) {
            throw new IllegalArgumentException("Weight must be above zero");
        } else if (heightInMeters <= 0) {
            throw new IllegalArgumentException("Height must be above zero");
        } else {
            return weightInKgs / (heightInMeters * heightInMeters);
        }
    }


    /**
     * @param bmi Body mass index to get clasification from
     * @return A BmiClasification enum with the clasification of BMI
     */
    public BmiClasification getBmiClasification(float bmi) {
        if (bmi < 18.5) {
            return BmiClasification.LOW_WEIGHT;
        } else if (bmi >= 18.5 && bmi < 25) {
            return BmiClasification.NORMAL_WEIGHT;
        } else if (bmi >= 25 && bmi < 30) {
            return BmiClasification.OVERWWEIGHT;
        } else if (bmi >= 30 && bmi < 35) {
            return BmiClasification.OBESITY_GRADE_1;
        } else if (bmi >= 35 && bmi < 40) {
            return BmiClasification.OBESITY_GRADE_2;
        } else {
            return BmiClasification.OBESITY_GRADE_3;
        }
    }

    public enum BmiClasification {
        LOW_WEIGHT, NORMAL_WEIGHT, OVERWWEIGHT, OBESITY_GRADE_1, OBESITY_GRADE_2, OBESITY_GRADE_3
    }
}
