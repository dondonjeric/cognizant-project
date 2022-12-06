package com.academy.project;


import com.academy.project.dto.CreateCommunityAdminAndManagerRest;
import com.academy.project.dto.UpdateCommunityAdminAndManagerRest;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.service.CommunityAdminAndManagerService;
import com.academy.project.validation.Validator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CommunityAdminAndManagerControllerTest {
    @MockBean
    private CommunityAdminAndManagerService communityAdminAndManagerService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("" +
            "Given CommunityManager with the setup above " +
            "When addComManager(CommunityManager.class) is executed " +
            "Then result should return communityAdminAndManager1")
    public void testGivenUpdatedEmployee_PositiveCase() throws Exception {

        Validator validator = new Validator();
        //ARRANGE
        UpdateCommunityAdminAndManagerRest request = new UpdateCommunityAdminAndManagerRest();
        request.setName("von");
        request.setId(1L);
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("von");
        response.setId(1L);
        response.setRoleType("Admin");
        response.setEmail("admin@gmail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");

        //ACT
        when(communityAdminAndManagerService.updateCommunityManagerAndAdmin(any(CommunityAdminAndManager.class))).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(put("/community/manager/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        // ASSERT
        String result = mvcResult.getResponse().getContentAsString();
        String expected = "Successfully updated!";
        assertEquals(expected, result);


    }
    @Test
    public void testGivenUpdatedEmployee_WhenNoGivenName() throws Exception {
        //Act
        UpdateCommunityAdminAndManagerRest request = new UpdateCommunityAdminAndManagerRest();
        request.setName("");
        request.setId(1L);
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("von");
        response.setId(1L);
        response.setRoleType("Admin");
        response.setEmail("admin@gmail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");

        //Arrange
        String expected = "Name is required!";
        when(communityAdminAndManagerService.updateCommunityManagerAndAdmin(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException(expected));
        //Assert
        mockMvc.perform(put("/community/manager/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();


    }
    @Test
    public void testGivenUpdatedEmployee_WhenItHasLessThanMinimumCharacters() throws Exception {
        //ACT
        UpdateCommunityAdminAndManagerRest request = new UpdateCommunityAdminAndManagerRest();
        request.setName("A");
        request.setId(1L);
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("von");
        response.setId(1L);
        response.setRoleType("Admin");
        response.setEmail("admin@gmail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");

        //ARRANGE
        String expected ="Name length should exceed 2 characters!";
        when(communityAdminAndManagerService.updateCommunityManagerAndAdmin(any(CommunityAdminAndManager.class))).thenThrow(new InvalidInputException(expected));
        //Assert
        mockMvc.perform(put("/community/manager/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();





    }
    @Test
    public void testGivenUpdatedEmployee_WhenLengthExceeds100() throws Exception {
        //Act
        Validator validator = new Validator();
        UpdateCommunityAdminAndManagerRest request = new UpdateCommunityAdminAndManagerRest();
        //103 letters
        request.setName("askdjasladaksdlaaksdalaskdalaskdalaksaldkasaldkasaldkasaldkasladaklsfnakjdfbakjsbaskjbckajbsckjabsckajb");
        request.setId(1L);
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("von");
        response.setId(1L);
        response.setRoleType("Admin");
        response.setEmail("admin@gmail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");

        //Arrange
        String expected ="Name length should not exceed 100 characters!";
        when(communityAdminAndManagerService.updateCommunityManagerAndAdmin(any(CommunityAdminAndManager.class))).thenThrow(new InvalidInputException(expected));
        //Assert
        mockMvc.perform(put("/community/manager/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

    }
    @Test
    public void testGivenUpdatedEmployee_WhenSpecialCharactersAreInput() throws Exception {
        Validator validator = new Validator();
        UpdateCommunityAdminAndManagerRest request = new UpdateCommunityAdminAndManagerRest();
        //Act
        request.setName("*&!Alexander");
        request.setId(1L);
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("von");
        response.setId(1L);
        response.setRoleType("Admin");
        response.setEmail("admin@gmail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");

        //Arrange
        String expected ="Name should not contain invalid characters!";
        when(communityAdminAndManagerService.updateCommunityManagerAndAdmin(any(CommunityAdminAndManager.class))).thenThrow(new InvalidInputException(expected));
        //Assert
        mockMvc.perform(put("/community/manager/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();


    }
    @Test
    public void testGivenUpdatedEmployee_WhenNameInput_DoesNotMatchName() throws Exception {
        //Act
        Validator validator = new Validator();
        UpdateCommunityAdminAndManagerRest request = new UpdateCommunityAdminAndManagerRest();
        //103 letters
        request.setName("_don");
        request.setId(1L);
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("von");
        response.setId(1L);
        response.setRoleType("Admin");
        response.setEmail("admin@gmail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");

        //Arrange
        String expected ="Name should not contain invalid characters!";
        when(communityAdminAndManagerService.updateCommunityManagerAndAdmin(any(CommunityAdminAndManager.class))).thenThrow(new InvalidInputException(expected));
        //Assert
        mockMvc.perform(put("/community/manager/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void testAddCommunityAndAdminManager_WhenValidManager_ShouldAddManagerName() throws Exception {

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("Admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin1234");
        request.setCognizantId("admin1234");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        String expected ="Successfully registered!";
        assertEquals(expected,content);
    }

    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_Name_NotNull() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin12343455");
        request.setCognizantId("admin1234");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("Name is required!"));
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_Name_Length_ShouldNotExceed2() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("a");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin12343455");
        request.setCognizantId("admin1234");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("Name length should exceed 2 characters!"));
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_Name_LengthLessShouldNotExceed100() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("alivondonjoshanjehalalexhsdjghsdhsdghhdgjkhsdjhsdgjkhdsjkhdsjkhdsjkhbvjkvbsjkbvdvgvicoroaydfghjhjdgsfhjksdfgdfsuigdfuigdfsiudfdbgsdhjgsdfhgsdfsdfsdfldfhs");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin12343455");
        request.setCognizantId("admin1234");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("Name length should not exceed 100 characters!"));
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_Name_ShouldNotContainInvalidCharacters() throws Exception {

        Validator validator =new Validator();
        CommunityAdminAndManager request = new CommunityAdminAndManager();
        request.setName("}{}{fgfdg");
        request.setId(1L);
        request.setRoleType("admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin12343455");
        request.setCognizantId("admin1234");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("@113");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");

        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("Name should not contain invalid characters!"));
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }


    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_Name_ShouldNotContainInvalidCharacter() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("_|||");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin12343455");
        request.setCognizantId("admin1234");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("Name should not contain invalid characters!") );
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_CognizantId_ShouldNotNull() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin12343455");
        request.setCognizantId("");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("CognizantId is required!") );
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }


    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_CognizantId_ShouldNotBeExceedOf10() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin12343455");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("CognizantId length should not exceed of 10 characters!"));
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_EmailIfValid_ShouldNotBeNull() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("");
        request.setPassword("admin12343455");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("Email is required!"));
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_EmailIfValid_ForEmailFormat() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant.com");
        request.setPassword("admin12343455");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("Invalid email format!"));
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_NoPasswordShouldNull() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant.com");
        request.setPassword("");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("Password is required!"));
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_PasswordShouldNotExceed100() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant.com");
        request.setPassword("dfjbsdfgfdghfdsajksfjkafsjkasdfjksdf786234762347698rcgdfsggdfsghasfdhgasdfhggasdfgfsdhgfdshgasdu7yuiydfydjfshhfdsusfdyhfdsuhsduihfr");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("Password length should not exceed 100 characters!"));
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_RoleTypeShouldNotNull() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("");
        request.setEmail("Cognizant.com");
        request.setPassword("admin1234");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("Roletype is required!"));
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_RoleTypeShouldNotExceed10() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("adminadminadmin");
        request.setEmail("Cognizant.com");
        request.setPassword("admin1234");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("Roletype length should be a maximum of 10 characters!"));
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_RoleTypeAdmin() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant.com");
        request.setPassword("admin1234");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("Invalid roletype given!"));
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_RoleTypeManager() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("manager");
        request.setEmail("Cognizant.com");
        request.setPassword("admin1234");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoleType("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException("Invalid roletype given!"));
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

    }
    @Test
    public void testDeleteCommunityAndAdminManager() throws Exception {
       doNothing().when(communityAdminAndManagerService).deleteCommunityManagerAndAdmin(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/community/manager/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }




}
