package smarthome.controller.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import smarthome.dto.RoomDetailDTO;
import smarthome.model.Room;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.RoomRepository;
import smarthome.services.RoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class RoomCTRLTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HouseGridRepository gridRepository;

    private RoomService roomService;

    private RoomCTRL roomCtrl;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.roomService = new RoomService(this.roomRepository, this.gridRepository);
        this.roomCtrl = new RoomCTRL(roomService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(roomCtrl).build();
    }

    @Test
    @DisplayName("Get request with success")
    void findAllRooms() throws Exception {
        Room room1 = new Room("B107", "Classroom", 1, 2, 3, 1.5);
        Room room2 = new Room("B208", "Classroom", 2, 2.5, 3, 1.5);
        List<Room> list = new ArrayList<>();
        list.add(room1);
        list.add(room2);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(list);

        when(roomRepository.findAll()).thenReturn(Stream.of(room1, room2).collect(Collectors.toList()));

        this.mockMvc.perform(get("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Create a room with success")
    void createRoom() throws Exception {
        Room room = new Room("B018", "Classroom", 1, 3.0, 2.5, 2.0);
        RoomDetailDTO roomDto = new RoomDetailDTO("B018", "Classroom", 1, 3.0, 2.5, 2.0);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(roomDto);

        when(roomRepository.save(room)).thenReturn(room);
        when(roomRepository.findById("B018")).thenReturn(Optional.of(room));

        this.mockMvc.perform(post("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Try to find a room that with success")
    void findOneRoomExists() throws Exception {
        Room room = new Room("B310", "Classroom",3,2.5,2.5,2);
        when(roomRepository.existsById("B310")).thenReturn(true);
        when(roomRepository.findById("B310")).thenReturn(Optional.of(room));
        this.mockMvc.perform(get("/rooms/B310")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Try to find a room that doesn't exist and get not found")
    void findOneRoomDontExist() throws Exception {
        Room room = new Room("B310", "Classroom",3,2.5,2.5,2);
        when(roomRepository.existsById("B310")).thenReturn(true);
        when(roomRepository.findById("B310")).thenReturn(Optional.of(room));
        this.mockMvc.perform(get("/rooms/B31")).andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("Edit a room with success")
    void editRoom() throws Exception {
        Room room = new Room("B018", "Classroom", 1, 3.0, 2.5, 2.0);
        RoomDetailDTO roomDto = new RoomDetailDTO("B018", "Classroom", 1, 3.0, 2.5, 2.0);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(roomDto);

        when(roomRepository.existsById("B018")).thenReturn(true);
        when(roomRepository.save(room)).thenReturn(room);
        when(roomRepository.findById("B018")).thenReturn(Optional.of(room));

        this.mockMvc.perform(put("/rooms/B018")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isOk());
    }


}
