package com.space.flightmanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.space.flightmanagement.model.Flight;
import com.space.flightmanagement.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightControllerTestsMoc {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightRepository flightRepository;

    @Test
    public void givenFlight_whenAdd_thenReturnNewFlight() throws Exception {
        Flight newFlight = getFlight(10L, 5, 142.23);
        when(flightRepository.save(any(Flight.class))).thenReturn(newFlight);

        mockMvc.perform(post("/flight")
                .content(OBJECT_MAPPER.writeValueAsString(newFlight))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.flightId", is(10)))
                .andExpect(jsonPath("$.departure", is("23-01-2020 17:39")))
                .andExpect(jsonPath("$.arrival", is("29-01-2020 18:13")))
                .andExpect(jsonPath("$.numberSeats", is(5)))
                .andExpect(jsonPath("$.ticketPrice", is(142.23)));
    }

    @Test
    public void whenFindAll_thenReturnFlightList() throws Exception {
        when(flightRepository.findAll()).thenReturn(getFlightList());

        mockMvc.perform(get("/flight"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void whenFindAll_thenReturnEmptyList() throws Exception {
        when(flightRepository.findAll()).thenReturn(Lists.newArrayList());

        mockMvc.perform(get("/flight"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenFlight_whenUpdate_thenReturnUpdatedFlight() throws Exception {
        Flight updated = getFlight(1L, 6, 152.45);
        when(flightRepository.save(any(Flight.class))).thenReturn(updated);

        Flight toUpdate = getFlight(1L, 8, 123.86);
        mockMvc.perform(put("/flight/1")
                .content(OBJECT_MAPPER.writeValueAsString(toUpdate))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightId", is(1)))
                .andExpect(jsonPath("$.departure", is("23-01-2020 17:39")))
                .andExpect(jsonPath("$.arrival", is("29-01-2020 18:13")))
                .andExpect(jsonPath("$.numberSeats", is(6)))
                .andExpect(jsonPath("$.ticketPrice", is(152.45)));
    }

    @Test
    public void givenNothing_whenDelete_thenReturnId() throws Exception {
        doNothing().when(flightRepository).deleteById(1L);

        mockMvc.perform(delete("/flight/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("1"));
    }

    private List<Flight> getFlightList() {
        return Lists.newArrayList(
                getFlight(1L, 5, 128.04),
                getFlight(2L, 8, 145.44),
                getFlight(3L, 7, 170.87)
        );
    }

    private Flight getFlight(Long flightId, int numberSeats, double ticketPrice) {
        return Flight.builder()
                .flightId(flightId)
                .departure("23-01-2020 17:39")
                .arrival("29-01-2020 18:13")
                .numberSeats(numberSeats)
                .ticketPrice(ticketPrice)
                .build();
    }
}