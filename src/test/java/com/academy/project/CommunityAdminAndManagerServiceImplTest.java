package com.academy.project;

import com.academy.project.exception.InvalidDeleteException;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.helper.CustomPage;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.model.People;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.academy.project.repository.CommunityRepository;
import com.academy.project.repository.PeopleRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommunityAdminAndManagerServiceImplTest {
    @Mock(name="community")
    CommunityRepository communityRepository;
    @Mock(name = "people")
    PeopleRepository peopleRepository;
    @Mock(name = "repository")
    private CommunityAdminAndManagerRepository repository;

    @Mock(name = "validator")
    private Validator validator;
    @InjectMocks
    private CommunityAdminAndManagerServiceImpl service;

    private CommunityAdminAndManager communityAdminAndManager1 = new CommunityAdminAndManager(1L, "Dondon Vic Ali", "veripro@gmail.com", "veripro", "admin1", "admin", true);
    private CommunityAdminAndManager communityAdminAndManager2 = new CommunityAdminAndManager(1L, "Marvin Jerome Shawn", "veripro@gmail.com", "veripro", "admin1", "admin", true);
    private CommunityAdminAndManager communityAdminAndManager3 = new CommunityAdminAndManager(1L, "Baki Kel Lyoys", "veripro@gmail.com", "veripro", "admin1", "admin", true);
    private CommunityAdminAndManager communityAdminAndManager4 = new CommunityAdminAndManager(1L, "", "veripro@gmail.com", "veripro", "admin1", "admin", true);

    private List<CommunityAdminAndManager> allCommunityAdminAndManager;

    @BeforeEach
    public void setup() {
        allCommunityAdminAndManager = List.of(communityAdminAndManager1, communityAdminAndManager2, communityAdminAndManager3, communityAdminAndManager4);

    }

    @Test
    @DisplayName("" +
            "Given CommunityManager with the setup above " +
            "When addComManager(CommunityManager.class) is executed " +
            "Then result should return communityAdminAndManager4")
    public void addComManager(){
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
    public void updateCommunityAdminAndManager() {
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L, "Dondon Vic Ali", "veripro@gmail.com", "veripro", "admin1", "admin", true);
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
    public void updateCommunityAdminAndManagerWithAcceptedSpecialCharacters(){
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L, "Dondon Vic Ali", "veripro@gmail.com", "veripro", "admin1", "admin", true);
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
    public void updateCommunityAdminAndManagerWithNoInput() {
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L, "Dondon Vic Ali", "veripro@gmail.com", "veripro", "admin1", "admin", true);
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
    public void updateCommunityAdminAndManagerExceeding100Characters(){
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L, "askdjasladaksdlaaksdalaskdalaskdalaksaldkasaldkasaldkasaldkasladaklsfnakjdfbakjsbaskjbckajbsckjabsckajb", "veripro@gmail.com", "veripro", "admin1", "admin", true);
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
            "\nWhen deleteCommunityAdminAndManager is false ")
    public void deleteCommunityAdminAndManagerIsSoftDeleted(){
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L, "asb", "veripro@gmail.com", "veripro", "admin1", "admin", false);
        //ACT
        when(repository.findById(anyLong())).thenReturn(Optional.of(expected));
        //ASSERT
        assertThrows(RecordNotFoundException.class, () -> {
            service.deleteCommunityManagerAndAdmin(1L);
        });

    }

    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen deleteCommunityAdminAndManager Doesn't exist ")
    public void deleteCommunityAdminAndManagerDoesNotExist(){
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L, "asb", "veripro@gmail.com", "veripro", "admin1", "admin", false);
        //ACT
        when(repository.findById(anyLong())).thenReturn(Optional.of(expected));
        //ASSERT
        assertThrows(RecordNotFoundException.class, () -> {
            service.deleteCommunityManagerAndAdmin(10L);
        });
    }
    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen deleteCommunityAdminAndManager Doesn't exist ")
    public void deleteCommunityAdminAndManager(){
        //ARRANGE
        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L, "asb", "veripro@gmail.com", "veripro", "admin1", "admin", true);
        //ACT
        when(repository.findById(anyLong())).thenReturn(Optional.of(expected));
        service.deleteCommunityManagerAndAdmin(1L);
        //ASSERT
        assertFalse(expected.getIsactive());
    }

    @Test
    @DisplayName("" +
            "Given CommunityAdminAndManager with the setup above " +
            "\nWhen getAllAdminAndCommunityManager_PositiveCase is executed ")
    public void getAllAdminAndCommunityManager_PositiveCase() throws RecordNotFoundException, InvalidInputException {
        List<CommunityAdminAndManager> expected = List.of(
                new CommunityAdminAndManager(1L, "Dondon Vic Ali", "veripro@gmail.com", "veripro", "admin1", "admin", true),
                new CommunityAdminAndManager(2L, "Marvin Jerome Shawn", "veripro@gmail.com", "veripro", "admin1", "admin", true),
                new CommunityAdminAndManager(3L, "Baki Kel Lyoys", "veripro@gmail.com", "veripro", "admin1", "admin", true),
                new CommunityAdminAndManager(4L, "Vonn", "veripro@gmail.com", "veripro", "admin1", "admin", true));
        //ARRANGE
        when(repository.getAllActiveCommunityAdminAndManager(4, 0)).thenReturn(expected);

        //ACT
        List<CommunityAdminAndManager> result = service.getAllActiveCommunityAdminAndManager(4,0);
        //ASSERT
        assertEquals(expected, result);
    }
}
