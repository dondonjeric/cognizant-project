package com.academy.project.validation;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Validator
{
    @Autowired
    private CommunityAdminAndManagerRepository repository;
    private static final String NAME =  "[a-zA-Z]{2,}[a-zA-Z-, .Ññ]";
    private static final String SPECIAL_CHARACTERS = "[-, .Ññ]+[a-zA-Z-, .Ññ]";
    private static final String EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public void checkIfValidId(Long id) throws RecordNotFoundException {
        repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record not found!"));
    }

    public void checkUpdateIfValid(CommunityAdminAndManager updateManager) throws InvalidInputException, RecordNotFoundException {
        checkIfValidId(updateManager.getId());
        if(!updateManager.getIsactive()){
            throw new RecordNotFoundException("Record not found!");
        }
        checkNameIfValid(updateManager.getName());
    }
    public void checkCreateIfValid(CommunityAdminAndManager manager) throws InvalidInputException, RecordNotFoundException {
        checkIfValid(manager);
    }
    private void checkIfValid(CommunityAdminAndManager manager) throws InvalidInputException {
        checkNameIfValid(manager.getName());
        checkEmailIfValid(manager.getEmail());
        checkCognizantIdIfValid(manager.getCognizantId());
        checkPasswordIfValid(manager.getPassword());
        checkRoleTypeIfValid(manager.getRoleType());
    }

    private void checkNameIfValid(String name) throws InvalidInputException {
        if(name == null || name.isBlank()){
            throw new InvalidInputException("Name is required!");
        }
        if(name.length() < 2){
            throw new InvalidInputException("Name length should exceed 2 characters!");
        }
        if(name.length() > 100){
            throw new InvalidInputException("Name length should not exceed 100 characters!");
        }
        if(name.matches(SPECIAL_CHARACTERS)){
            throw new InvalidInputException("Name should not contain invalid characters!");
        }
        if(!name.matches(NAME)){
            throw new InvalidInputException("Name should not contain invalid characters!");
        }
    }
    private void checkEmailIfValid(String email) throws InvalidInputException {
        if(email == null){
            throw new InvalidInputException("Email is required!");
        }
        if(!email.matches(EMAIL)){
            throw new InvalidInputException("Invalid email format!");
        }
    }
    private void checkPasswordIfValid(String password) throws InvalidInputException {
        if(password == null || password.isBlank()){
            throw new NullPointerException("Password is required!");
        }
        if(password.length() > 100){
            throw new InvalidInputException("Password length should be a maximum of 100 characters!");
        }
    }
    private void checkCognizantIdIfValid(String cognizantId) throws InvalidInputException {
        if(cognizantId == null || cognizantId.isBlank()){
            throw new NullPointerException("CognizantId is required!");
        }
        if(cognizantId.length() > 10){
            throw new InvalidInputException("CognizantId length should be a maximum of 10 characters!");
        }
    }
    private void checkRoleTypeIfValid(String roleType) throws InvalidInputException {
        if(roleType == null || roleType.isBlank()){
            throw new NullPointerException("Roletype is required!");
        }
        if(roleType.length() > 10){
            throw new InvalidInputException("Roletype length should be a maximum of 10 characters!");
        }
        if(!"Admin".equals(roleType) && !"Manager".equals(roleType)){
            throw new InvalidInputException("Invalid roletype given!");
        }
    }
}
