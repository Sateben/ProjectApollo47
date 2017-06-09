package com.example.krrishdholakia.projectapollox2v4;

/**
 * Created by krrishdholakia on 4/12/16.
 */
public class Normal_Distributions {
    Double sum_of_Var;
    Double sum_of_Var_squared;
    Double number_of_Var;

    public Normal_Distributions() {
    }

    public Normal_Distributions(Double sum_of_Var, Double sum_of_Var_squared, Double number_of_Var) {
        this.sum_of_Var = sum_of_Var;
        this.sum_of_Var_squared = sum_of_Var_squared;
        this.number_of_Var = number_of_Var;
    }

    public Double getSum_of_Var() {
        return sum_of_Var;
    }

    public void setSum_of_Var(Double sum_of_Var) {
        this.sum_of_Var = sum_of_Var;
    }

    public Double getSum_of_Var_squared() {
        return sum_of_Var_squared;
    }

    public void setSum_of_Var_squared(Double sum_of_Var_squared) {
        this.sum_of_Var_squared = sum_of_Var_squared;
    }

    public Double getNumber_of_Var() {
        return number_of_Var;
    }

    public void setNumber_of_Var(Double number_of_Var) {
        this.number_of_Var = number_of_Var;
    }
}
