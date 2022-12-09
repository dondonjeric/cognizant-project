package com.academy.project.service;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.academy.project.helper.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommunityAdminAndManagerServiceGetAllTest {

    @Mock
    private CommunityAdminAndManagerRepository repository;
    @Mock
    private Validator validator;
    @InjectMocks
    private CommunityAdminAndManagerServiceImpl service;
    private List<CommunityAdminAndManager> container;
    @BeforeEach
    public void setup(){
       container = List.of(
                new CommunityAdminAndManager(1L, "Dondon Vic Ali", "123321", "veripro1@gmail.com", "admin1", "admin", true),
                new CommunityAdminAndManager(2L, "Marvin Jerome Shawn", "123322", "veripro2@gmail.com", "admin1", "admin", true),
                new CommunityAdminAndManager(3L, "Baki Kel Lyoys", "123323", "veripro3@gmail.com", "admin1", "admin", true),
                new CommunityAdminAndManager(4L, "Vonn", "123324", "veripro4@gmail.com", "admin1", "admin", true),
                new CommunityAdminAndManager(5L, "Dondon Vic Ali", "123325", "veripro5@gmail.com", "admin1", "admin", true),
                new CommunityAdminAndManager(6L, "Marvin Jerome Shawn", "123326", "veripro6@gmail.com", "admin1", "admin", true),
                new CommunityAdminAndManager(7L, "Baki Kel Lyoys", "123327", "veripro7@gmail.com", "admin1", "admin", true),
                new CommunityAdminAndManager(8L, "Vonn", "123328", "veripro8@gmail.com", "admin1", "admin", true)
       );
    }
    //(1L, "Dondon Vic Ali", "123321", "veripro1@gmail.com", "admin1", "admin", true),
    //(2L, "Marvin Jerome Shawn", "123322", "veripro2@gmail.com", "admin1", "admin", true),
    //(3L, "Baki Kel Lyoys", "123323", "veripro3@gmail.com", "admin1", "admin", true),
    //(4L, "Vonn", "123324", "veripro4@gmail.com", "admin1", "admin", true),
    //(5L, "Dondon Vic Ali", "123325", "veripro5@gmail.com", "admin1", "admin", true),
    //(6L, "Marvin Jerome Shawn", "123326", "veripro6@gmail.com", "admin1", "admin", true),
    //(7L, "Baki Kel Lyoys", "123327", "veripro7@gmail.com", "admin1", "admin", true),
    //(8L, "Vonn", "123328", "veripro8@gmail.com", "admin1", "admin", true)
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen getAllAdminAndCommunityManagerWithOffsetOf0AndSizeOf4 is executed " +
            "\nThen result should return 4 Object of CommunityAdminAndManager")
    public void getAllAdminAndCommunityManagerWithOffsetOf0AndSizeOf4() {
        //ARRANGE
        List<CommunityAdminAndManager> expected = new ArrayList<>();
        int offset = 0;
        int size = 4;
        for(int i = offset; i < size; i++){
            expected.add(container.get(i));
        }
        when(repository.getAllActiveCommunityAdminAndManager(anyInt(), anyInt())).thenReturn(expected);
        doNothing().when(validator).checkFilter(anyInt(), anyInt());

        //ACT
        List<CommunityAdminAndManager> result = service.getAllActiveCommunityAdminAndManager(size, offset);
        //ASSERT
        assertEquals(expected, result);
    }
    //(1L, "Dondon Vic Ali", "123321", "veripro1@gmail.com", "admin1", "admin", true),
    //(2L, "Marvin Jerome Shawn", "123322", "veripro2@gmail.com", "admin1", "admin", true),
    //(3L, "Baki Kel Lyoys", "123323", "veripro3@gmail.com", "admin1", "admin", true),
    //(4L, "Vonn", "123324", "veripro4@gmail.com", "admin1", "admin", true),
    //(5L, "Dondon Vic Ali", "123325", "veripro5@gmail.com", "admin1", "admin", true),
    //(6L, "Marvin Jerome Shawn", "123326", "veripro6@gmail.com", "admin1", "admin", true),
    //(7L, "Baki Kel Lyoys", "123327", "veripro7@gmail.com", "admin1", "admin", true),
    //(8L, "Vonn", "123328", "veripro8@gmail.com", "admin1", "admin", true)
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen getAllAdminAndCommunityManagerWithNoOffsetAndSize is executed " +
            "\nThen result should return all Object of CommunityAdminAndManager")
    public void getAllAdminAndCommunityManagerWithNoOffsetAndSize() {
        //ARRANGE
        Integer offset = null;
        Integer size = null;

        when(repository.getAllIsActive()).thenReturn(container);

        //ACT
        List<CommunityAdminAndManager> result = service.getAllActiveCommunityAdminAndManager(size, offset);
        //ASSERT
        assertEquals(container, result);
    }
    //(1L, "Dondon Vic Ali", "123321", "veripro1@gmail.com", "admin1", "admin", true),
    //(2L, "Marvin Jerome Shawn", "123322", "veripro2@gmail.com", "admin1", "admin", true),
    //(3L, "Baki Kel Lyoys", "123323", "veripro3@gmail.com", "admin1", "admin", true),
    //(4L, "Vonn", "123324", "veripro4@gmail.com", "admin1", "admin", true),
    //(5L, "Dondon Vic Ali", "123325", "veripro5@gmail.com", "admin1", "admin", true),
    //(6L, "Marvin Jerome Shawn", "123326", "veripro6@gmail.com", "admin1", "admin", true),
    //(7L, "Baki Kel Lyoys", "123327", "veripro7@gmail.com", "admin1", "admin", true),
    //(8L, "Vonn", "123328", "veripro8@gmail.com", "admin1", "admin", true)
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen getAllAdminAndCommunityManagerWithInvalidOffset is executed " +
            "\nThen result should throw InvalidInputException")
    public void getAllAdminAndCommunityManagerWithInvalidOffset() {
        //ARRANGE
        Integer offset = null;
        Integer size = 1;

        doThrow(InvalidInputException.class).when(validator).checkFilter(size, offset);

        //ACT
        //ASSERT
        assertThrows(InvalidInputException.class, () -> {
            service.getAllActiveCommunityAdminAndManager(size, offset);
        });
    }

    //(1L, "Dondon Vic Ali", "123321", "veripro1@gmail.com", "admin1", "admin", true),
    //(2L, "Marvin Jerome Shawn", "123322", "veripro2@gmail.com", "admin1", "admin", true),
    //(3L, "Baki Kel Lyoys", "123323", "veripro3@gmail.com", "admin1", "admin", true),
    //(4L, "Vonn", "123324", "veripro4@gmail.com", "admin1", "admin", true),
    //(5L, "Dondon Vic Ali", "123325", "veripro5@gmail.com", "admin1", "admin", true),
    //(6L, "Marvin Jerome Shawn", "123326", "veripro6@gmail.com", "admin1", "admin", true),
    //(7L, "Baki Kel Lyoys", "123327", "veripro7@gmail.com", "admin1", "admin", true),
    //(8L, "Vonn", "123328", "veripro8@gmail.com", "admin1", "admin", true)
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen getAllAdminAndCommunityManagerWithInvalidSize is executed " +
            "\nThen result should throw InvalidInputException")
    public void getAllAdminAndCommunityManagerWithInvalidSize() {
        //ARRANGE
        Integer offset = 1;
        Integer size = null;

        doThrow(InvalidInputException.class).when(validator).checkFilter(size, offset);

        //ACT
        //ASSERT
        assertThrows(InvalidInputException.class, () -> {
            service.getAllActiveCommunityAdminAndManager(size, offset);
        });
    }
}
