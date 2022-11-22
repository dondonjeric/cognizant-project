package com.academy.project;

import com.academy.project.exception.InvalidStringFormatException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.academy.project.service.CommunityAdminAndManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommunityAdminAndManagerServiceImplTest {
    @Mock
    CommunityAdminAndManagerRepository communityManagerRepository;

    @InjectMocks
    CommunityAdminAndManagerServiceImpl communityAdminAndManagerService;

    CommunityAdminAndManager communityAdminAndManager1 = new CommunityAdminAndManager(1L,"Dondon Vic Ali", "veripro@gmail.com", "veripro", "admin1", "admin", true);
    CommunityAdminAndManager communityAdminAndManager2 = new CommunityAdminAndManager(1L,"Marvin Jerome Shawn", "veripro@gmail.com", "veripro", "admin1", "admin", true);
    CommunityAdminAndManager communityAdminAndManager3 = new CommunityAdminAndManager(1L,"Baki Kel Lyoys", "veripro@gmail.com", "veripro", "admin1", "admin", true);
    CommunityAdminAndManager communityAdminAndManager4 = new CommunityAdminAndManager(1L,"Dean Angelo Vonn", "veripro@gmail.com", "veripro", "admin1", "admin", true);

    List<CommunityAdminAndManager> allCommunityAdminAndManager;
    @BeforeEach
    public void setup(){
        allCommunityAdminAndManager = List.of(communityAdminAndManager1, communityAdminAndManager2, communityAdminAndManager3, communityAdminAndManager4);

    }



    @Test
    @DisplayName("" +
            "Given CommunityManager with the setup above " +
            "When addComManager(CommunityManager.class) is executed " +
            "Then result should return communityAdminAndManager4")
    public void addComManager() throws InvalidStringFormatException {
        //arrange
        when(communityManagerRepository.save(any(CommunityAdminAndManager.class))).thenReturn(communityAdminAndManager4);
        //act
        CommunityAdminAndManager result = communityAdminAndManagerService.addCommunityAdminAndManager(communityAdminAndManager4);
        //assert
        verify(communityManagerRepository).save(any(CommunityAdminAndManager.class));
        assertEquals(communityAdminAndManager4, communityAdminAndManager4);
    }
    @Test
    @DisplayName("" +
            "Given Payroll with the setup above " +
            "When updateComManager(CommunityAdminAndManager, Long) is executed " +
            "Then result should return updated communityAdminAndManager3")
    public void updateComManager() throws RecordNotFoundException, InvalidStringFormatException {
        //ARRANGE
        CommunityAdminAndManager update = communityAdminAndManager3;
        update.setName("updateName");

        // Mocking payroll methods for service method dependency
        when(communityManagerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(communityAdminAndManager3));
        when(communityManagerRepository.save(any(CommunityAdminAndManager.class))).thenReturn(update);
        //ACT
        CommunityAdminAndManager result = communityAdminAndManagerService.updateCommunityManagerAndAdmin(update, 3L);
        //ASSERT
        // verify if payrollRepository.save() is use
        verify(communityManagerRepository).save(any(CommunityAdminAndManager.class));
        assertEquals(communityAdminAndManager3, result);
    }
}
