package com.academy.project.service;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class getServiceTest {
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
