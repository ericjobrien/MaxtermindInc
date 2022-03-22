package com.revature.maxtermind.controllerTest;

import com.revature.maxtermind.controller.EmployeeController;
import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest(classes = EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    private List<Employee> employees;
    private Employee john;

    private static boolean deleteCalled = false;

    @BeforeEach
    public void setup() {

        employees = new ArrayList<>();
        Employee max = new Employee();
        max.setId(0);
        max.setPassword("password123");
        max.setFirstName("Max");
        max.setLastName("Hilky");
        max.setEmail(null);
        max.setPhoneNumber(1234567890);
        max.setPhoto(null);
        max.setStartDate(null); // Unsure of how to pass in Object Date
        max.setPosition(null);// Unsure of how to pass in object Position
        max.setNotifications(new ArrayList<>());
        max.setApplications(new HashSet<>());
        employees.add(max);

        john = new Employee();
        john.setPassword("123password");
        john.setFirstName("John");
        john.setLastName("Wayne");
        john.setEmail(null);
        john.setPhoneNumber(1234567809);
        john.setPhoto(null);
        john.setStartDate(null); // Unsure of how to pass in Object Date
        john.setPosition(null);// Unsure of how to pass in object Position
        john.setNotifications(new ArrayList<>());
        john.setApplications(new HashSet<>());

    }



    @Test
    public void findAll() throws Exception {
        when(service.findAll()).thenReturn(employees);
        this.mockMvc.perform(get("/employee"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\" : 0, \"password\" : \"password123\", \"first_name\" : \"Max\", \"last_name\" : \"Hilky\", \"email\" : \"null\", \"phone_number\" : \"1234567890\", \"photo\" : \"null\", \"start_date\" : \"null\", \"position\" : \"null\", \"notifications\" : [] }]"));
    }

    @Test
    public void getEmployeesbyName() throws Exception {
        when(service.findAllByName(any(String.class))).thenReturn(employees);
        this.mockMvc.perform(get("/name/john"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\" : 0, \"password\" : \"password123\", \"first_name\" : \"Max\", \"last_name\" : \"Hilky\", \"email\" : \"null\", \"phone_number\" : \"1234567890\", \"photo\" : \"null\", \"start_date\" : \"null\", \"position\" : \"null\", \"notifications\" : [] }]"));
    }

    @Test
    public void getAdministrators() throws Exception {
        when(service.findAllManagers()).thenReturn(employees);
        this.mockMvc.perform(get("/administrators"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\" : 0, \"password\" : \"password123\", \"first_name\" : \"Max\", \"last_name\" : \"Hilky\", \"email\" : \"null\", \"phone_number\" : \"1234567890\", \"photo\" : \"null\", \"start_date\" : \"null\", \"position\" : \"null\", \"notifications\" : [] }]"));
    }

    @Test
    public void getManagers() throws Exception {
        when(service.findAllManagers()).thenReturn(employees);
        this.mockMvc.perform(get("/managers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\" : 0, \"password\" : \"password123\", \"first_name\" : \"Max\", \"last_name\" : \"Hilky\", \"email\" : \"null\", \"phone_number\" : \"1234567890\", \"photo\" : \"null\", \"start_date\" : \"null\", \"position\" : \"null\", \"notifications\" : [] }]"));
    }

    @Test
    void getEmployeesByManager() throws Exception{
        when(service.findAllByManager(any(Integer.class))).thenReturn(employees);
        this.mockMvc.perform(get("/manager/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\" : 0, \"password\" : \"password123\", \"first_name\" : \"Max\", \"last_name\" : \"Hilky\", \"email\" : \"null\", \"phone_number\" : \"1234567890\", \"photo\" : \"null\", \"start_date\" : \"null\", \"position\" : \"null\", \"notifications\" : [] }]"));
    }

    @Test
    void getEmployeesByPosition() throws Exception{
        when(service.findAllByPosition(any(Integer.class))).thenReturn(employees);
        this.mockMvc.perform(get("/position/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\" : 0, \"password\" : \"password123\", \"first_name\" : \"Max\", \"last_name\" : \"Hilky\", \"email\" : \"null\", \"phone_number\" : \"1234567890\", \"photo\" : \"null\", \"start_date\" : \"null\", \"position\" : \"null\", \"notifications\" : [] }]"));
    }

    @Test
    void getEmployeeByLogin() throws Exception{
        when(service.findEmployeeByLogin(null, "123password")).thenReturn(john);
        this.mockMvc.perform(post("/employee/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"password\" : \"123password\", \"first_name\" : \"John\", \"last_name\" : \"Wayne\", \"email\" : \"Wayne@gmail.com\", \"phone_number\" : \"1234567809\", \"photo\" : \"null\", \"start_date\" : \"null\", \"position\" : \"null\", \"notifications\" : [] }"));
    }

    @Test
    void getEmployeeById() throws Exception{
        when(service.findByEmployeeId(any(Integer.class))).thenReturn(john);
        this.mockMvc.perform(get("0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"password\" : \"123password\", \"first_name\" : \"John\", \"last_name\" : \"Wayne\", \"email\" : \"Wayne@gmail.com\", \"phone_number\" : \"1234567809\", \"photo\" : \"null\", \"start_date\" : \"null\", \"position\" : \"null\", \"notifications\" : [] }"));
    }

    @Test
    void insertEmployee() throws Exception {
        when(service.saveEmployee(any(Employee.class))).thenReturn(john);
        this.mockMvc.perform(put("/saveEmployee")
                .content("{\"password\" : \"123password\", \"first_name\" : \"John\", \"last_name\" : \"Wayne\", \"email\" : \"Wayne@gmail.com\", \"phone_number\" : \"1234567809\", \"photo\" : \"null\", \"start_date\" : \"null\", \"position\" : \"null\", \"notifications\" : [] }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"password\" : \"123password\", \"first_name\" : \"John\", \"last_name\" : \"Wayne\", \"email\" : \"Wayne@gmail.com\", \"phone_number\" : \"1234567890\", \"photo\" : \"null\", \"start_date\" : \"null\", \"position\" : \"null\", \"notifications\" : [] }"));
    }

    void updateEmployee() throws Exception {
        when(service.updateEmployee(any(Employee.class))).thenReturn(john);
        this.mockMvc.perform(post("/saveEmployee")
                        .content("{\"password\" : \"123password\", \"first_name\" : \"John\", \"last_name\" : \"Wayne\", \"email\" : \"Wayne@gmail.com\", \"phone_number\" : \"1234567809\", \"photo\" : \"null\", \"start_date\" : \"null\", \"position\" : \"null\", \"notifications\" : [] }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"password\" : \"123password\", \"first_name\" : \"John\", \"last_name\" : \"Wayne\", \"email\" : \"Wayne@gmail.com\", \"phone_number\" : \"1234567890\", \"photo\" : \"null\", \"start_date\" : \"null\", \"position\" : \"null\", \"notifications\" : [] }"));
    }

    @Test
    void deleteEmployeeById() throws Exception {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(service).deleteEmployee(any(Integer.class));
        this.mockMvc.perform(delete("/0"))
                .andDo(print())
                .andExpect(status().isOk()); // the json parser doesn't want to parse the literal value true, so I can't test the return value of this endpoint
        assertTrue(deleteCalled);
    }









}
