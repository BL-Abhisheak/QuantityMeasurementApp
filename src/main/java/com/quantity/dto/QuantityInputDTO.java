package com.quantity.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


public class QuantityInputDTO {

    @Valid
    @NotNull(message = "First quantity context is required")
    private QuantityDTO thisQuantityDTO;

    public QuantityInputDTO(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        this.thisQuantityDTO = thisQuantityDTO;
        this.thatQuantityDTO = thatQuantityDTO;
    }

    public QuantityInputDTO() {
    }

    @Override
    public String toString() {
        return "QuantityInputDTO{" +
                "thisQuantityDTO=" + thisQuantityDTO +
                ", thatQuantityDTO=" + thatQuantityDTO +
                '}';
    }

    @Valid
    @NotNull(message = "Second quantity context is required")
    private QuantityDTO thatQuantityDTO;

    public QuantityDTO getThisQuantityDTO() {
        return thisQuantityDTO;
    }

    public void setThisQuantityDTO(QuantityDTO thisQuantityDTO) {
        this.thisQuantityDTO = thisQuantityDTO;
    }

    public QuantityDTO getThatQuantityDTO() {
        return thatQuantityDTO;
    }

    public void setThatQuantityDTO(QuantityDTO thatQuantityDTO) {
        this.thatQuantityDTO = thatQuantityDTO;
    }
}
