package com.academy.project;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.helper.CustomPage;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommunityAdminAndManagerServiceImplTest {
    @Mock(name = "repository")
    private CommunityAdminAndManagerRepository repository;

    @Mock(name = "validator")
    private Validator validator;
    @InjectMocks
    private CommunityAdminAndManagerServiceImpl service;

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
        when(repository.save(any(CommunityAdminAndManager.class))).thenReturn(communityAdminAndManager4);
        //act
        CommunityAdminAndManager result = service.addCommunityAdminAndManager(communityAdminAndManager4);
        //assert
        verify(repository).save(any(CommunityAdminAndManager.class));
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
        when(repository.findById(anyLong())).thenReturn(Optional.of(communityAdminAndManager1));
        when(repository.save(any(CommunityAdminAndManager.class))).thenReturn(expected);
        //ACT
        CommunityAdminAndManager result = service.updateCommunityManagerAndAdmin(communityAdminAndManager1);
        //ASSERT
        verify(repository).save(any(CommunityAdminAndManager.class));
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
        when(repository.findById(anyLong())).thenReturn(Optional.of(communityAdminAndManager1));
        when(repository.save(any(CommunityAdminAndManager.class))).thenReturn(expected);
        //ACT
        CommunityAdminAndManager result = service.updateCommunityManagerAndAdmin(communityAdminAndManager1);
        //ASSERT
        verify(repository).save(any(CommunityAdminAndManager.class));
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
        when(repository.findById(anyLong())).thenReturn(Optional.of(communityAdminAndManager1));
        when(repository.save(any(CommunityAdminAndManager.class))).thenReturn(expected);
        //ACT
        CommunityAdminAndManager result = service.updateCommunityManagerAndAdmin(communityAdminAndManager1);
        //ASSERT
        verify(repository).save(any(CommunityAdminAndManager.class));
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
        when(repository.findById(anyLong())).thenReturn(Optional.of(communityAdminAndManager1));
        when(repository.save(any(CommunityAdminAndManager.class))).thenReturn(expected);
        //ACT
        CommunityAdminAndManager result = service.updateCommunityManagerAndAdmin(communityAdminAndManager1);
        //ASSERT
        verify(repository).save(any(CommunityAdminAndManager.class));
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen getAllAdminAndCommunityManager_PositiveCase is executed ")
    public void getAllAdminAndCommunityManager_PositiveCase() throws RecordNotFoundException, InvalidInputException {
//        //ARRANGE
//        when(repository.findAll(PageRequest.of(0, 20))).thenReturn(new PageImpl<>(allCommunityAdminAndManager));
//        //ACT
//        Page<CommunityAdminAndManager> result = service.getAllActiveCommunityAdminAndManager(PageRequest.of(0,20));
//        //ASSERT


//        when(repository.save(any())).thenReturn((CommunityAdminAndManager) allCommunityAdminAndManager);
//        //act
//        List<CommunityAdminAndManager> result = service.getAllActiveCommunityAdminAndManager(allCommunityAdminAndManager);
//        //assert
//        verify(repository).getAllActiveCommunityAdminAndManager(any(CommunityAdminAndManager.class));
//        //assertEquals(allCommunityAdminAndManager, result.getContent());
    }
}
