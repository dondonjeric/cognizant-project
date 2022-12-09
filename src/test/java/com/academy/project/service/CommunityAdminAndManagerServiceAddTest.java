package com.academy.project.service;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.academy.project.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommunityAdminAndManagerServiceAddTest {

    @Mock(name = "repository")
    private CommunityAdminAndManagerRepository repository;

    @Mock(name = "validator")
    private Validator validator;
    @InjectMocks
    private CommunityAdminAndManagerServiceImpl service;


    //(1L, "Dondon Vic Ali", "123321", "veripro@gmail.com", "admin1", "admin", true);
    @Test
    @DisplayName("" +
            "Given CommunityManager with the setup above " +
            "When addCommunityAdminAndManager is executed " +
            "Then result should return communityAdminAndManager4")
    public void addCommunityAdminAndManager(){
        //arrange
        CommunityAdminAndManager create = new CommunityAdminAndManager(1L, "Dondon Vic Ali", "123321", "veripro@gmail.com", "admin1", "admin", true);

        //act
        when(repository.save(any(CommunityAdminAndManager.class))).thenReturn(create);
        CommunityAdminAndManager result = service.addCommunityAdminAndManager(create);
        //assert

        assertEquals(create, result);
    }

    //(1L, "Dondon Vic Ali", "123321", "veripro@gmail.com", "admin1", "admin", true);
    @Test
    @DisplayName("" +
            "Given CommunityManager with the setup above " +
            "When addAnExisitingCommunityAdminAndManagerShouldThrowInvalidInputException is executed " +
            "Then result should throw InvalidInputException")
    public void addAnExisitingCommunityAdminAndManagerShouldThrowInvalidInputException(){
        //arrange
        CommunityAdminAndManager create = new CommunityAdminAndManager(1L, "Dondon Vic Ali", "123321", "veripro@gmail.com", "admin1", "admin", true);

        //act
        doThrow(InvalidInputException.class).when(validator).checkCreateIfValid(create);
        //assert

        assertThrows(InvalidInputException.class, () -> {
            service.addCommunityAdminAndManager(create);
        });
    }
}
