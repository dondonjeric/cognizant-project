package com.academy.project.service;

import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommunityAdminAndManagerServiceDeleteTest {

    @Mock
    private CommunityAdminAndManagerRepository repository;

    @InjectMocks
    private CommunityAdminAndManagerServiceImpl service;

    private CommunityAdminAndManager delete, save;

    @BeforeEach
    private void setup(){
        save = new CommunityAdminAndManager(1L, "Dondon", "12345", "dondon@softvision.com","password123", "Admin", true);
    }

    // (1L, "Dondon", "12345", "dondon@softvision.com","password123", "Admin", true);
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen deleteCommunityAdminAndManager is executed " +
            "\nThen isactive should update to false")
    public void deleteCommunityAdminAndManager() {
        //ARRANGE
        //when(repository.save(any(CommunityAdminAndManager.class))).thenReturn(save);
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(save));
        //ACT

        service.deleteCommunityManagerAndAdmin(1L);
        //ASSERT
        assertFalse(save.getIsactive());

    }
    // (1L, "Dondon", "12345", "dondon@softvision.com","password123", "Admin", true);
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen deleteCommunityAdminAndManager is executed and isactive is false " +
            "\nThen it should throw RecordNotFoundException")
    public void deleteSoftDeletedCommunityAdminAndManager() {
        //ARRANGE
        //when(repository.save(any(CommunityAdminAndManager.class))).thenReturn(save);
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(save));
        //ACT

        service.deleteCommunityManagerAndAdmin(1L);

        //ASSERT
        assertFalse(save.getIsactive());

        assertThrows(RecordNotFoundException.class, () -> {
            service.deleteCommunityManagerAndAdmin(1L);
        });
    }
    // (1L, "Dondon", "12345", "dondon@softvision.com","password123", "Admin", true);
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen deleteCommunityAdminAndManager is executed and id is not existing " +
            "\nThen it should throw RecordNotFoundException")
    public void deleteUnknownCommunityAdminAndManager() {
        //ARRANGE
        //ACT
        //ASSERT

        assertThrows(RecordNotFoundException.class, () -> {
            service.deleteCommunityManagerAndAdmin(10L);
        });
    }
}
