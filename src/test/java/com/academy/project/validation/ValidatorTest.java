package com.academy.project.validation;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    @DisplayName("" +
            "Given a CommunitAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should return true")
    public void checkIfManagerIsValid() throws InvalidInputException, RecordNotFoundException {

   }

}
