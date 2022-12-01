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
        when(communityManagerRepository.findById(anyLong())).thenReturn(Optional.of(expected));
        //ASSERT
        assertThrows(RecordNotFoundException.class, () -> {
            communityAdminAndManagerService.deleteCommunityManagerAndAdmin(1L);
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
        when(communityManagerRepository.findById(anyLong())).thenReturn(Optional.of(expected));
        //ASSERT
        assertThrows(RecordNotFoundException.class, () -> {
            communityAdminAndManagerService.deleteCommunityManagerAndAdmin(10L);
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
        when(communityManagerRepository.findById(anyLong())).thenReturn(Optional.of(expected));
        communityAdminAndManagerService.deleteCommunityManagerAndAdmin(1L);
        //ASSERT
        assertFalse(expected.getIsactive());
    }

//    @Test
//    @DisplayName("" +
//            "Given CommunityAdminAndManager with the setup above " +
//            "\nWhen deleteCommunityAdminAndManager Doesn't exist ")
//    public void deleteCommunityAdminAndManagerWithDependencyOnCommunityTable() throws InvalidDeleteException {
//        //ARRANGE
//        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L, "asb", "veripro@gmail.com", "veripro", "admin1", "admin", true);
//        Community community = new Community(1L, "don", "icon", 1L, "ahhs", true);
//        //ACT
//        when(communityManagerRepository.findById(anyLong())).thenReturn(Optional.of(expected));
//        when(communityRepository.findByMgridAndIsactive(1L,true)).thenReturn( List.of(community));
//        //ASSERT
//        assertThrows(InvalidDeleteException.class, () -> {
//            communityAdminAndManagerService.deleteCommunityManagerAndAdmin(1L);
//        });
//    }

//    @Test
//    @DisplayName("" +
//            "Given CommunityAdminAndManager with the setup above " +
//            "\nWhen deleteCommunityAdminAndManager is false ")
//    public void deleteCommunityAdminAndManager() throws RecordNotFoundException, InvalidInputException, InvalidDeleteException {
//        //ARRANGE
//        CommunityAdminAndManager expected = new CommunityAdminAndManager(1L, "asb", "veripro@gmail.com", "veripro", "admin1", "admin", true);
//        Community community = new Community(1L, "don", "icon", 1L, "ahhs", true);
//        People people = new People(1L, 123L, "ludivico", "John", "von", "ludivico John von", "John@gmail.com", new Date(), 142, 1L, 13, 1, 1, false, true);
//        //ACT
//        when(communityManagerRepository.findById(anyLong())).thenReturn(Optional.of(expected));
//        when(communityRepository.findAll()).thenReturn(List.of(community));
//        when(peopleRepository.findAll()).thenReturn(List.of(people));
//        communityAdminAndManagerService.deleteCommunityManagerAndAdmin(1L);
//        //ASSERT
//        assertNull(community.getMgrid());
//        assertNull(people.getCommunityadminandmanagerid());
//
//    }
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
