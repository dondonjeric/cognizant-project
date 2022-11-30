package com.academy.project.validation;

import com.academy.project.exception.IllegalArgumentException;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.NumberFormatException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.websocket.server.PathParam;
import java.util.Optional;


@Component
public class Validator
{
    @Autowired
    private CommunityAdminAndManagerRepository repository;
    private static final String NAME =  "[a-zA-Z]+[a-zA-Z-, .Ññ]+";
    private static final String SPECIAL_CHARACTERS = "[-, .Ññ]+[a-zA-Z0-9-, .Ññ]+";
    private static final String EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD =  "[a-zA-Z]+[a-zA-Z0-9]+";
    private static final String PASSWORD1 =  "[0-9]+[a-zA-Z]+[a-zA-Z0-9]+";
    private static final String PARAMETER = "[0-9]+";


    public CommunityAdminAndManager checkIfValidId(Long id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record not found!"));
    }

    public void checkUpdateIfValid(CommunityAdminAndManager updateManager) throws InvalidInputException, RecordNotFoundException {
        CommunityAdminAndManager manager = checkIfValidId(updateManager.getId());
        if(!manager.getIsactive()){
            throw new RecordNotFoundException("Record not found!");
        }
        checkNameIfValid(updateManager.getName());
    }
    public void checkCreateIfValid(CommunityAdminAndManager manager) throws InvalidInputException, RecordNotFoundException {
        checkIfValid(manager);
    }
    public void checkIfValid(CommunityAdminAndManager manager) throws InvalidInputException {
        checkNameIfValid(manager.getName());
        checkEmailIfValid(manager.getEmail());
        checkCognizantIdIfValid(manager.getCognizantId());
        checkPasswordIfValid(manager.getPassword());
        checkRoleTypeIfValid(manager.getRoleType());
    }

    public void checkNameIfValid(String name) throws InvalidInputException {
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
    public void checkEmailIfValid(String email) throws InvalidInputException {
        if(email == null){
            throw new InvalidInputException("Email is required!");
        }
        if(email.length() > 50){
            throw new InvalidInputException("Email length should not exceed 50!");
        }
        if(!email.matches(EMAIL)){
            throw new InvalidInputException("Invalid email format!");
        }
        Optional<String> existing= repository.findByEmail(email);
        if(existing.isPresent()){
            throw new InvalidInputException("Email must be unique");
        }
    }
    public void checkPasswordIfValid(String password) throws InvalidInputException {
        if(password == null || password.isBlank()){
            throw new InvalidInputException("Password is required!");
        }
        if(password.length() > 100){
            throw new InvalidInputException("Password length should not exceed 100 characters!");
        }

        if(!password.matches(PASSWORD)&& !password.matches(PASSWORD1)){
            throw new InvalidInputException("Password should not contain invalid characters!");
        }

    }
    public void checkCognizantIdIfValid(String cognizantId) throws InvalidInputException {
        if(cognizantId == null || cognizantId.isBlank()){
            throw new InvalidInputException("CognizantId is required!");
        }
        if(cognizantId.length() > 10){
            throw new InvalidInputException("CognizantId length should not exceed of 10 characters!");
        }
        if(!cognizantId.matches(NAME)){
            throw new InvalidInputException("CognizantId should not contain invalid characters!");
        }
        Optional<String> existing= repository.findByCognizantId(cognizantId);
        if(existing.isPresent()){
            throw new InvalidInputException("CognizantId must be unique");
        }
    }
    public void checkRoleTypeIfValid(String roleType) throws InvalidInputException {
        if(roleType == null || roleType.isBlank()){
            throw new InvalidInputException("Roletype is required!");
        }
        if(roleType.length() > 10){
            throw new InvalidInputException("Roletype length should not exceed 10 characters!");
        }
        if(!"Admin".equals(roleType) && !"Manager".equals(roleType)){
            throw new InvalidInputException("Invalid roletype given!");
        }
    }
    public void checkIfParameterIsValid(@PathParam("offset")Integer offset, @PathParam("size")Integer size) throws IllegalStateException, IllegalArgumentException, NumberFormatException, RecordNotFoundException {
        String offsetStr = Integer.toString(offset);
        String sizeStr = Integer.toString(size);
//        if(pageable==null){
//            throw new RecordNotFoundException("No record to display!");
//        }
        if(offset==null){
            return;
        }
        if(!offsetStr.matches(PARAMETER)){
            throw new IllegalStateException("Invalid offset input!");
        }
        if(!sizeStr.matches(PARAMETER)){
            throw new IllegalArgumentException("Invalid size input!");
        }
//        if(!sizeStr.matches(PARAMETER)){
//            throw new NumberFormatException("Invalid size input!");
//        }
    }

}
