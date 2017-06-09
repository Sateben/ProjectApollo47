package com.example.krrishdholakia.projectapollox2v4;

/**
 * Created by krrishdholakia on 4/12/16.
 */
public class Simple_Probability {
    Double probability_of_occ;
    Double total_number;

    public Simple_Probability() {
    }

    public Simple_Probability(Double probability_of_occ, Double total_number) {
        this.probability_of_occ = probability_of_occ;
        this.total_number = total_number;
    }

    public Double getProbability_of_occ() {
        return probability_of_occ;
    }

    public void setProbability_of_occ(Double probability_of_occ) {
        this.probability_of_occ = probability_of_occ;
    }

    public Double getTotal_number() {
        return total_number;
    }

    public void setTotal_number(Double total_number) {
        this.total_number = total_number;
    }
}
