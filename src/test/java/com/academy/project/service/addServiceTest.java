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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class addServiceTest{
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

}
