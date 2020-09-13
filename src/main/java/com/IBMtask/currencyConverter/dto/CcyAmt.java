package com.IBMtask.currencyConverter.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CcyAmt
{
    @JsonProperty("Ccy")
    private String Ccy;
    @JsonProperty("Amt")
    private String Amt;

    public String getCcy ()
    {
        return Ccy;
    }

    public void setCcy (String Ccy)
    {
        this.Ccy = Ccy;
    }

    public String getAmt ()
    {
        return Amt;
    }

    public void setAmt (String Amt)
    {
        this.Amt = Amt;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Ccy = "+Ccy+", Amt = "+Amt+"]";
    }
}
