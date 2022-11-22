package com.academy.project;

import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
    public class CommunityAdminAndManagerTest {
        @Autowired
        private CommunityAdminAndManagerRepository repository;
        @Test
        @DisplayName("Given Interface Community Manager Repository" +
                "When is executed, Community manager should be save to Database " +
                "Then result return ComManager")
        public void testSave() {
            //Arrange
            CommunityAdminAndManager expected = new CommunityAdminAndManager(1L,"Administrator 1","admin1@softvision.com","123","admin1","admin",TRUE);
            //Act
            CommunityAdminAndManager result = repository.save(expected);
            //Assert
            assertEquals(expected, result);
        }

  /*  @Test
    @DisplayName("Given Interface Community Manager Repository" +
            "When is executed, Community manager should be delete to Database " +
            "Then result return ComManager")

    public void testDelete() {
        //Arrange
        CommunityAdminAndManager expected = new CommunityAdminAndManager(2L,"Administrator 2", "admin2@softvision.com", "123", "admin2", "admin", TRUE);
        repository.save(expected);
        //Act
        repository.deleteById(2L);
        CommunityAdminAndManager result = repository.findById(2L);
        //Assert
    }*/

}
