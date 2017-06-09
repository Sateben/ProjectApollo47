package com.example.krrishdholakia.projectapollox1;

/**
 * Created by krrishdholakia on 3/12/16.
 */
public class Age {

    String age1;
    String sex1;
    String chest_pain_type_1;
    String resting_Blood_Pressure1;
    String serum_Cholestoral1;
    //_in_mg/dl
    String fasting_Blood_Sugar1;
    //>120mg/dl
    String resting_Electrocardiographic_Results1;
    //1=true;0=false
    String maximum_Heart_Rate_Achieved1;
    String exercise_Induced_Angina1;
    //1=yes,0=no
    String oldPeak1;
    //ST depression induced by exercise relative to rest
    String slope1;
                /*
                the slope of the peak exercise ST segment
                -- Value 1: upsloping
                -- Value 2: flat
                -- Value 3: downsloping
                 */
                /*databaseRef.child("CA").push().setValue(row[11]);
                //number of major vessels (0-3) colored by flourosopy
                databaseRef.child("THAL").push().setValue(row[12]);
                //3 = normal; 6 = fixed defect; 7 = reversable defect

                databaseRef.child("DIAGNOSIS").push().setValue(value2);
                /*
                (angiographic disease status)
                -- Value 0: < 50% diameter narrowing
                -- Value 1: > 50% diameter narrowing
                 */
    String ca1;
    String thal1;
    String diagnosis1;

    public Age() {
    }


    public Age(String age1, String sex1, String chest_pain_type_1, String resting_Blood_Pressure1, String serum_Cholestoral1, String fasting_Blood_Sugar1, String resting_Electrocardiographic_Results1, String maximum_Heart_Rate_Achieved1, String exercise_Induced_Angina1, String oldPeak1, String slope1, String ca1, String thal1, String diagnosis1) {
        this.age1 = age1;
        this.sex1 = sex1;
        this.chest_pain_type_1 = chest_pain_type_1;
        this.resting_Blood_Pressure1 = resting_Blood_Pressure1;
        this.serum_Cholestoral1 = serum_Cholestoral1;
        this.fasting_Blood_Sugar1 = fasting_Blood_Sugar1;
        this.resting_Electrocardiographic_Results1 = resting_Electrocardiographic_Results1;
        this.maximum_Heart_Rate_Achieved1 = maximum_Heart_Rate_Achieved1;
        this.exercise_Induced_Angina1 = exercise_Induced_Angina1;
        this.oldPeak1 = oldPeak1;
        this.slope1 = slope1;
        this.ca1 = ca1;
        this.thal1 = thal1;

        this.diagnosis1 = diagnosis1;
    }

    public String getThal1() {
        return thal1;
    }

    public void setThal1(String thal1) {
        this.thal1 = thal1;
    }
    public String getCa1() {
        return ca1;
    }

    public void setCa1(String ca1) {
        this.ca1 = ca1;
    }

    public String getDiagnosis1() {
        return diagnosis1;
    }

    public void setDiagnosis1(String diagnosis1) {
        this.diagnosis1 = diagnosis1;
    }



    public String getChest_pain_type_1() {
        return chest_pain_type_1;
    }

    public void setChest_pain_type_1(String chest_pain_type_1) {
        this.chest_pain_type_1 = chest_pain_type_1;
    }

    public String getExercise_Induced_Angina1() {
        return exercise_Induced_Angina1;
    }

    public void setExercise_Induced_Angina1(String exercise_Induced_Angina1) {
        this.exercise_Induced_Angina1 = exercise_Induced_Angina1;
    }

    public String getFasting_Blood_Sugar1() {
        return fasting_Blood_Sugar1;
    }

    public void setFasting_Blood_Sugar1(String fasting_Blood_Sugar1) {
        this.fasting_Blood_Sugar1 = fasting_Blood_Sugar1;
    }

    public String getMaximum_Heart_Rate_Achieved1() {
        return maximum_Heart_Rate_Achieved1;
    }

    public void setMaximum_Heart_Rate_Achieved1(String maximum_Heart_Rate_Achieved1) {
        this.maximum_Heart_Rate_Achieved1 = maximum_Heart_Rate_Achieved1;
    }

    public String getOldPeak1() {
        return oldPeak1;
    }

    public void setOldPeak1(String oldPeak1) {
        this.oldPeak1 = oldPeak1;
    }

    public String getResting_Blood_Pressure1() {
        return resting_Blood_Pressure1;
    }

    public void setResting_Blood_Pressure1(String resting_Blood_Pressure1) {
        this.resting_Blood_Pressure1 = resting_Blood_Pressure1;
    }

    public String getResting_Electrocardiographic_Results1() {
        return resting_Electrocardiographic_Results1;
    }

    public void setResting_Electrocardiographic_Results1(String resting_Electrocardiographic_Results1) {
        this.resting_Electrocardiographic_Results1 = resting_Electrocardiographic_Results1;
    }

    public String getSerum_Cholestoral1() {
        return serum_Cholestoral1;
    }

    public void setSerum_Cholestoral1(String serum_Cholestoral1) {
        this.serum_Cholestoral1 = serum_Cholestoral1;
    }

    public String getSlope1() {
        return slope1;
    }

    public void setSlope1(String slope1) {
        this.slope1 = slope1;
    }

    public String getSex1() {
        return sex1;
    }

    public void setSex1(String sex1) {
        this.sex1 = sex1;
    }

    public String getAge1() {
        return age1;
    }

    public void setAge1(String age1) {
        this.age1 = age1;
    }
}
