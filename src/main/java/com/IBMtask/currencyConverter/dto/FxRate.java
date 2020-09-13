package com.IBMtask.currencyConverter.dto;


import com.IBMtask.currencyConverter.dto.CcyAmt;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FxRate
{

    @JsonProperty("Dt")
    private String Dt;

    @JsonProperty("CcyAmt")
    private com.IBMtask.currencyConverter.dto.CcyAmt[] CcyAmt;

    @JsonProperty("Tp")
    private String Tp;

    public String getDt ()
    {
        return Dt;
    }

    public void setDt (String Dt)
    {
        this.Dt = Dt;
    }

    public CcyAmt[] getCcyAmt ()
    {
        return CcyAmt;
    }

    public void setCcyAmt (CcyAmt[] CcyAmt)
    {
        this.CcyAmt = CcyAmt;
    }

    public String getTp ()
    {
        return Tp;
    }

    public void setTp (String Tp)
    {
        this.Tp = Tp;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Dt = "+Dt+", CcyAmt = "+CcyAmt+", Tp = "+Tp+"]";
    }
}