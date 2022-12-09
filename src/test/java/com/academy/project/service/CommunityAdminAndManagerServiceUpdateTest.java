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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommunityAdminAndManagerServiceUpdateTest {

    @Mock(name = "repository")
    private CommunityAdminAndManagerRepository repository;

    @Mock(name = "validator")
    private Validator validator;
    @InjectMocks
    private CommunityAdminAndManagerServiceImpl service;
    private CommunityAdminAndManager delete, save;

    //(1L, "Dondon Vic Ali", "123321", "veripro@gmail.com", "admin1", "admin", true);
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen updateCommunityAdminAndManager(CommunityAdminAndManager, Long) is executed " +
            "\nThen result should return updated jeric")

    public void updateCommunityAdminAndManager() {
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L, "Dondon Vic Ali", "123321", "veripro@gmail.com", "admin1", "admin", true);

        CommunityAdminAndManager updated = new CommunityAdminAndManager(1L, "Dondon", null, null, null, null, true);
        when(repository.findById(1L)).thenReturn(Optional.of(expected));
        //ACT
        service.updateCommunityManagerAndAdmin(updated);

        //ASSERT
        verify(repository).save(any(CommunityAdminAndManager.class));
        assertEquals("Dondon", expected.getName());
    }
    //(1L, "Dondon Vic Ali", "123321", "veripro@gmail.com", "admin1", "admin", true);
    @Test
    public void updateInvalidNameCommunityAdminAndManager() {
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L, "Dondon Vic Ali", "123321", "veripro@gmail.com", "admin1", "admin", true);

        CommunityAdminAndManager updated = new CommunityAdminAndManager(1L, "123Dondon", null, null, null, null, true);
        //ACT
        doThrow(InvalidInputException.class).when(validator).checkUpdateIfValid(updated);
        //ASSERT
        assertThrows(InvalidInputException.class, () -> {
            service.updateCommunityManagerAndAdmin(updated);
        });
    }
    //(1L, "Dondon Vic Ali", "123321", "veripro@gmail.com", "admin1", "admin", true);
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen updateCommunityAdminAndManagerWithNoInput is executed ")
    public void updateCommunityAdminAndManagerWithNoInput() {
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L, "Dondon Vic Ali", "123321", "veripro@gmail.com", "admin1", "admin", true);

        CommunityAdminAndManager updated = new CommunityAdminAndManager(1L, "", null, null, null, null, true);
        //ACT
        doThrow(InvalidInputException.class).when(validator).checkUpdateIfValid(updated);

        //ASSERT
        assertThrows(InvalidInputException.class, () -> {
            service.updateCommunityManagerAndAdmin(updated);
        });
    }
    //(1L, "Dondon Vic Ali", "123321", "veripro@gmail.com", "admin1", "admin", true);
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen updateCommunityAdminAndManagerExceeding100Characters is executed " +
            "\nThen it should throw InvalidInputException")
    public void updateCommunityAdminAndManagerExceeding100Characters(){
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L, "Dondon Vic Ali", "123321", "veripro@gmail.com", "admin1", "admin", true);

        CommunityAdminAndManager updated = new CommunityAdminAndManager(1L,
                "abcdefghijklmnoqrstuvwxyzabcdefghijklmnoqrstuvwxyzabcdefghijklmnoqrstuvwxyzabcdefghijklmnoqrstuvwxyzabcdefghijklmnoqrstuvwxyz",
                null, null, null, null, true);

        //ACT
        doThrow(InvalidInputException.class).when(validator).checkUpdateIfValid(updated);
        //ASSERT
        assertThrows(InvalidInputException.class, () -> {
            service.updateCommunityManagerAndAdmin(updated);
        });
    }
}
