package com.quantity;

import com.quantity.dto.QuantityDTO;
import com.quantity.dto.QuantityInputDTO;
import com.quantity.dto.QuantityMeasurementDTO;
import com.quantity.service.IQuantityMeasurementService;
import com.quantity.unit.LengthUnit;
import com.quantity.unit.TemperatureUnit;
import com.quantity.unit.VolumeUnit;
import com.quantity.unit.WeightUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

        @Autowired
        private MockMvc mockMvc;

        private final ObjectMapper objectMapper = new ObjectMapper();

        @MockitoBean
        private IQuantityMeasurementService service;

        private QuantityInputDTO validCompareInput;
        private QuantityMeasurementDTO validCompareResponse;

        @BeforeEach
        void setUp() {

            QuantityDTO thisQty = new QuantityDTO();
            thisQty.setValue(1.0);
            thisQty.setUnit("FEET");
            thisQty.setMeasurementType("LengthUnit");

            QuantityDTO thatQty = new QuantityDTO();
            thatQty.setValue(12.0);
            thatQty.setUnit("INCHES");
            thatQty.setMeasurementType("LengthUnit");

            validCompareInput = new QuantityInputDTO();
            validCompareInput.setThisQuantityDTO(thisQty);
            validCompareInput.setThatQuantityDTO(thatQty);

            validCompareResponse = new QuantityMeasurementDTO();
            validCompareResponse.setThisValue(1.0);
            validCompareResponse.setThisUnit("FEET");
            validCompareResponse.setThisMeasurementType("LengthUnit");
            validCompareResponse.setThatValue(12.0);
            validCompareResponse.setThatUnit("INCHES");
            validCompareResponse.setThatMeasurementType("LengthUnit");
            validCompareResponse.setOperation("compare");
            validCompareResponse.setResultString("true");
            validCompareResponse.setError(false);
        }

        @Test
        void testCompareQuantities_Success() throws Exception {

            when(service.compareQuantities(any(QuantityInputDTO.class)))
                    .thenReturn(validCompareResponse);

            mockMvc.perform(post("/api/v1/quantities/compare")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(validCompareInput)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.resultString").value("true"))
                    .andExpect(jsonPath("$.error").value(false));

            verify(service, times(1))
                    .compareQuantities(any(QuantityInputDTO.class));
        }

        @Test
        void testAddQuantities_Success() throws Exception {

            QuantityMeasurementDTO addResponse =
                    new QuantityMeasurementDTO();

            addResponse.setOperation("add");
            addResponse.setResultValue(2.0);
            addResponse.setResultUnit("FEET");
            addResponse.setError(false);

            when(service.addQuantities(any(QuantityInputDTO.class)))
                    .thenReturn(addResponse);

            mockMvc.perform(post("/api/v1/quantities/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(validCompareInput)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.resultValue").value(2.0))
                    .andExpect(jsonPath("$.resultUnit").value("FEET"))
                    .andExpect(jsonPath("$.error").value(false));

            verify(service, times(1))
                    .addQuantities(any(QuantityInputDTO.class));
        }

        @Test
        void testGetOperationHistory_Success() throws Exception {

            when(service.getOperationHistory("compare"))
                    .thenReturn(
                            Collections.singletonList(validCompareResponse)
                    );

            mockMvc.perform(
                            get("/api/v1/quantities/history/operation/compare"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].operation")
                            .value("compare"));

            verify(service, times(1))
                    .getOperationHistory("compare");
        }

        @Test
        void testGetOperationCount_Success() throws Exception {

            when(service.getOperationCount("COMPARE"))
                    .thenReturn(5L);

            mockMvc.perform(
                            get("/api/v1/quantities/count/COMPARE"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("5"));

            verify(service, times(1))
                    .getOperationCount("COMPARE");
        }

}