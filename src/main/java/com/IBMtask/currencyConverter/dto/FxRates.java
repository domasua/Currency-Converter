package com.IBMtask.currencyConverter.dto;
import com.IBMtask.currencyConverter.dto.FxRate;
import com.fasterxml.jackson.annotation.JsonProperty;


public class FxRates
{

    @JsonProperty("FxRate")
    private com.IBMtask.currencyConverter.dto.FxRate[] FxRate;

    public FxRate[] getFxRate ()
    {
        return FxRate;
    }

    public void setFxRate(FxRate[] FxRate)
    {
        this.FxRate = FxRate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [FxRate = "+FxRate+"]";
    }

}
