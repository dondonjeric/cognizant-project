package com.academy.project;

import com.academy.project.exception.InvalidStringFormatException;
import com.academy.project.model.CommunityAdminAndManager;

public class Validations {

  private static final String Email="[a-zA-Z-,.Ññ]{2,100}";

    public CommunityAdminAndManager checkIfValidEmail (CommunityAdminAndManager verifyEmail ) throws InvalidStringFormatException {


        if(!checkIfValidEmail(verifyEmail)){
            throw new InvalidStringFormatException("Invalid format for name!");
        }

        if(!communityAdminAndManager.getCsvemail().matches(Email)){

        }
        return true;
    }
}
