package com.academy.project;

import com.academy.project.dto.UpdateAdminAndManagerRest;
import com.academy.project.model.CommunityAdminAndManager;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectApplication {

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();

		PropertyMap<CommunityAdminAndManager, UpdateAdminAndManagerRest> update = new PropertyMap<CommunityAdminAndManager, UpdateAdminAndManagerRest>() {
			@Override
			protected void configure() {
				map().setCommunityadminangmanagerid(source.getId());
				map().setCommunitadminandmanagername(source.getName());
			}

		};
		modelMapper.addMappings(update);

		return modelMapper;
	}
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
