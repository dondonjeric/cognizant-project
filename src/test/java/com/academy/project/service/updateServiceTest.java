package com.academy.project.service;

import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.academy.project.repository.CommunityRepository;
import com.academy.project.repository.PeopleRepository;
import com.academy.project.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class updateServiceTest {
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
}
