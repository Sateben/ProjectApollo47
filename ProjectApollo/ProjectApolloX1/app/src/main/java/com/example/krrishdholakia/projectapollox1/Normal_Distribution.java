package com.example.krrishdholakia.projectapollox1;

/**
 * Created by krrishdholakia on 4/12/16.
 */
public class Normal_Distribution {
    Double sum_of_Var;
    Double sum_of_Var_Squared;
    Double number_of_Var;

    public Normal_Distribution() {
    }

    public Normal_Distribution(Double sum_of_Var, Double sum_of_Var_Squared, Double number_of_Var) {
        this.sum_of_Var = sum_of_Var;
        this.sum_of_Var_Squared = sum_of_Var_Squared;
        this.number_of_Var = number_of_Var;
    }

    public Double getSum_of_Var() {
        return sum_of_Var;
    }

    public void setSum_of_Var(Double sum_of_Var) {
        this.sum_of_Var = sum_of_Var;
    }

    public Double getSum_of_Var_Squared() {
        return sum_of_Var_Squared;
    }

    public void setSum_of_Var_Squared(Double sum_of_Var_Squared) {
        this.sum_of_Var_Squared = sum_of_Var_Squared;
    }

    public Double getNumber_of_Var() {
        return number_of_Var;
    }

    public void setNumber_of_Var(Double number_of_Var) {
        this.number_of_Var = number_of_Var;
    }
}
