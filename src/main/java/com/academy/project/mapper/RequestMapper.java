package com.academy.project.mapper;


import com.academy.project.dto.CreateCommunityAdminAndManagerRest;
import com.academy.project.dto.UpdateCommunityAdminAndManagerRest;
import com.academy.project.model.CommunityAdminAndManager;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper extends ModelMapper {

    public RequestMapper(){
        this.addMappings(updateMapper());
        this.addMappings(createMapper());
    }

    private PropertyMap<CreateCommunityAdminAndManagerRest, CommunityAdminAndManager> createMapper(){
        return new PropertyMap<CreateCommunityAdminAndManagerRest, CommunityAdminAndManager>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setName(source.getName());
                map().setEmail(source.getEmail());
                map().setPassword(source.getPassword());
                map().setCognizantid(source.getCognizantId());
                map().setRoletype(source.getRoleType());
            }
        };
    }
    private PropertyMap<UpdateCommunityAdminAndManagerRest, CommunityAdminAndManager> updateMapper(){
        return new PropertyMap<UpdateCommunityAdminAndManagerRest, CommunityAdminAndManager>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setName(source.getName());
            }
        };
    }

}
