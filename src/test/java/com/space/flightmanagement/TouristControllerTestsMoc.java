package com.space.flightmanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.space.flightmanagement.model.Tourist;
import com.space.flightmanagement.repository.TouristRepository;
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
public class TouristControllerTestsMoc {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TouristRepository touristRepository;

    @Test
    public void givenTourist_whenAdd_thenReturnNewTourist() throws Exception {
        Tourist newTourist = getTourist(10L, "Alex", "Smith");
        when(touristRepository.save(any(Tourist.class))).thenReturn(newTourist);

        mockMvc.perform(post("/tourist")
                .content(OBJECT_MAPPER.writeValueAsString(newTourist))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.touristId", is(10)))
                .andExpect(jsonPath("$.name", is("Alex")))
                .andExpect(jsonPath("$.surname", is("Smith")))
                .andExpect(jsonPath("$.gender", is("FEMALE")))
                .andExpect(jsonPath("$.country", is("Poland")))
                .andExpect(jsonPath("$.notes", is("anything")))
                .andExpect(jsonPath("$.dateBirth", is("12-11-2000")));
    }

    @Test
    public void whenFindAll_thenReturnTouristList() throws Exception {
        when(touristRepository.findAll()).thenReturn(getTouristList());

        mockMvc.perform(get("/tourist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void whenFindAll_thenReturnEmptyList() throws Exception {
        when(touristRepository.findAll()).thenReturn(Lists.newArrayList());

        mockMvc.perform(get("/tourist"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenTourist_whenUpdate_thenReturnUpdatedTourist() throws Exception {
        Tourist updated = getTourist(1L, "Jacob", "Smith");
        when(touristRepository.save(any(Tourist.class))).thenReturn(updated);

        Tourist toUpdate = getTourist(1L, "Thomas", "Max");
        mockMvc.perform(put("/tourist/1")
                .content(OBJECT_MAPPER.writeValueAsString(toUpdate))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.touristId", is(1)))
                .andExpect(jsonPath("$.name", is("Jacob")))
                .andExpect(jsonPath("$.surname", is("Smith")))
                .andExpect(jsonPath("$.gender", is("FEMALE")))
                .andExpect(jsonPath("$.country", is("Poland")))
                .andExpect(jsonPath("$.notes", is("anything")))
                .andExpect(jsonPath("$.dateBirth", is("12-11-2000")));
    }

    @Test
    public void givenNothing_whenDelete_thenReturnId() throws Exception {
        doNothing().when(touristRepository).deleteById(1L);

        mockMvc.perform(delete("/tourist/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("1"));
    }

    private List<Tourist> getTouristList() {
        return Lists.newArrayList(
                getTourist(1L, "Oliver", "Black"),
                getTourist(2L, "Jack", "Hillary"),
                getTourist(3L, "Harry", "Potter")
        );
    }

    private Tourist getTourist(Long touristId, String name, String surname) {
        return Tourist.builder()
                .touristId(touristId)
                .name(name)
                .surname(surname)
                .gender(Tourist.Gender.FEMALE)
                .country("Poland")
                .notes("anything")
                .dateBirth("12-11-2000")
                .build();
    }
}