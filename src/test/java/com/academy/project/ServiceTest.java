package com.academy.project;

import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityManagerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock
    private CommunityManagerRepository communityManagerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @InjectMocks
    private CommunityAdminAndManager communityAdminAndManager;

    @Test
    public void updateComManager() throws RecordNotFoundException{
    }
}