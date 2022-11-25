package com.academy.project;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.academy.project.service.CommunityAdminAndManagerServiceImpl;
import com.academy.project.validation.Validator;
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
    @Mock(name = "repository")
    private CommunityAdminAndManagerRepository communityManagerRepository;

    @Mock(name = "validator")
    private Validator validator;
    @InjectMocks
    private CommunityAdminAndManagerServiceImpl communityAdminAndManagerService;

    private CommunityAdminAndManager communityAdminAndManager1 = new CommunityAdminAndManager(1L,"Dondon Vic Ali", "veripro@gmail.com", "veripro", "admin1", "admin", true);
    private CommunityAdminAndManager communityAdminAndManager2 = new CommunityAdminAndManager(1L,"Marvin Jerome Shawn", "veripro@gmail.com", "veripro", "admin1", "admin", true);
    private CommunityAdminAndManager communityAdminAndManager3 = new CommunityAdminAndManager(1L,"Baki Kel Lyoys", "veripro@gmail.com", "veripro", "admin1", "admin", true);
    private CommunityAdminAndManager communityAdminAndManager4 = new CommunityAdminAndManager(1L,"", "veripro@gmail.com", "veripro", "admin1", "admin", true);

    private List<CommunityAdminAndManager> allCommunityAdminAndManager;
    @BeforeEach
    public void setup(){
        allCommunityAdminAndManager = List.of(communityAdminAndManager1, communityAdminAndManager2, communityAdminAndManager3, communityAdminAndManager4);

    }

    @Test
    @DisplayName("" +
            "Given CommunityManager with the setup above " +
            "When addComManager(CommunityManager.class) is executed " +
            "Then result should return communityAdminAndManager4")
    public void addComManager() throws InvalidInputException, RecordNotFoundException {
        //arrange
        when(communityManagerRepository.save(any(CommunityAdminAndManager.class))).thenReturn(communityAdminAndManager4);
        //act
        CommunityAdminAndManager result = communityAdminAndManagerService.addCommunityAdminAndManager(communityAdminAndManager4);
        //assert
        verify(communityManagerRepository).save(any(CommunityAdminAndManager.class));
        assertEquals(communityAdminAndManager4, communityAdminAndManager4);
    }



    //(1L,"Dondon Vic Ali", "veripro@gmail.com", "veripro", "admin1", "admin", true);
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen updateCommunityAdminAndManager(CommunityAdminAndManager, Long) is executed " +
            "\nThen result should return updated jeric")
    public void updateCommunityAdminAndManager() throws RecordNotFoundException, InvalidInputException {
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L,"Dondon Vic Ali", "veripro@gmail.com", "veripro", "admin1", "admin", true);
        expected.setName("jeric");
        when(communityManagerRepository.findById(anyLong())).thenReturn(Optional.of(communityAdminAndManager1));
        when(communityManagerRepository.save(any(CommunityAdminAndManager.class))).thenReturn(expected);
        //ACT
        CommunityAdminAndManager result = communityAdminAndManagerService.updateCommunityManagerAndAdmin(communityAdminAndManager1);
        //ASSERT
        verify(communityManagerRepository).save(any(CommunityAdminAndManager.class));
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nupdateCommunityAdminAndManagerWithAcceptedSpecialCharacters is executed ")
    public void updateCommunityAdminAndManagerWithAcceptedSpecialCharacters() throws RecordNotFoundException, InvalidInputException {
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L,"Dondon Vic Ali", "veripro@gmail.com", "veripro", "admin1", "admin", true);
        expected.setName("DoÑ-d.ñ,");
        when(communityManagerRepository.findById(anyLong())).thenReturn(Optional.of(communityAdminAndManager1));
        when(communityManagerRepository.save(any(CommunityAdminAndManager.class))).thenReturn(expected);
        //ACT
        CommunityAdminAndManager result = communityAdminAndManagerService.updateCommunityManagerAndAdmin(communityAdminAndManager1);
        //ASSERT
        verify(communityManagerRepository).save(any(CommunityAdminAndManager.class));
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen updateCommunityAdminAndManagerWithNoInput is executed ")
    public void updateCommunityAdminAndManagerWithNoInput() throws RecordNotFoundException, InvalidInputException {
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L,"Dondon Vic Ali", "veripro@gmail.com", "veripro", "admin1", "admin", true);
        expected.setName("");
        when(communityManagerRepository.findById(anyLong())).thenReturn(Optional.of(communityAdminAndManager1));
        when(communityManagerRepository.save(any(CommunityAdminAndManager.class))).thenReturn(expected);
        //ACT
        CommunityAdminAndManager result = communityAdminAndManagerService.updateCommunityManagerAndAdmin(communityAdminAndManager1);
        //ASSERT
        verify(communityManagerRepository).save(any(CommunityAdminAndManager.class));
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen updateCommunityAdminAndManagerExceeding100Characters is executed ")
    public void updateCommunityAdminAndManagerExceeding100Characters() throws RecordNotFoundException, InvalidInputException {
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L,"askdjasladaksdlaaksdalaskdalaskdalaksaldkasaldkasaldkasaldkasladaklsfnakjdfbakjsbaskjbckajbsckjabsckajb", "veripro@gmail.com", "veripro", "admin1", "admin", true);
        expected.setName("");
        when(communityManagerRepository.findById(anyLong())).thenReturn(Optional.of(communityAdminAndManager1));
        when(communityManagerRepository.save(any(CommunityAdminAndManager.class))).thenReturn(expected);
        //ACT
        CommunityAdminAndManager result = communityAdminAndManagerService.updateCommunityManagerAndAdmin(communityAdminAndManager1);
        //ASSERT
        verify(communityManagerRepository).save(any(CommunityAdminAndManager.class));
        assertEquals(expected, result);
    }
}
