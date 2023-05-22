package com.zavyalov.kiselev.coursework;

import com.zavyalov.kiselev.coursework.entity.PermissionEntity;
import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;
import com.zavyalov.kiselev.coursework.entity.RoleEntity;
import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.repository.PermissionRepository;
import com.zavyalov.kiselev.coursework.repository.PostNeo4jRepository;
import com.zavyalov.kiselev.coursework.repository.RoleRepository;
import com.zavyalov.kiselev.coursework.service.IPostService;
import com.zavyalov.kiselev.coursework.service.PermissionService;
import com.zavyalov.kiselev.coursework.service.SimplePostService;
import com.zavyalov.kiselev.coursework.view.PostView;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.EnableNeo4jAuditing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableNeo4jAuditing
public class CourseworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseworkApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(SimplePostService service, PostNeo4jRepository repository) {
        return args -> {
            //Создание тестовых постов
            service.deleteAll();
            PostForm first = new PostForm("Main", "Main post", -1L);
            PostForm p2 = new PostForm("p2", " dfpost", service.save(first).getId());
            PostForm p3 = new PostForm("p3", " dfpost", service.save(p2).getId());
            Long parId = service.save(p3).getId();
            PostForm p4 = new PostForm("p4", " fdpost", parId);
            PostForm p5 = new PostForm("p5", "sd post", parId);
            service.save(p4);
            service.save(p5);
        };
    }

    @Bean
    CommandLineRunner demoPermissionsAndRoles(PermissionRepository permissionRepository, RoleRepository roleRepository) {
        return args -> {
            permissionRepository.deleteAll();
            roleRepository.deleteAll();

            List<PermissionEntity> pe = new ArrayList<>();
            Long permissionId = 0L;
            pe.add(new PermissionEntity(permissionId, "READ_PERSONAL_NOTES", null));
            permissionId++;
            pe.add(new PermissionEntity(permissionId, "READ_ALL_NOTES", null));
            permissionId++;
            pe.add(new PermissionEntity(permissionId, "WRITE_PERSONAL_NODES", null));
            permissionId++;
            pe.add(new PermissionEntity(permissionId, "WRITE_ALL_NODES", null));
            permissionId++;
            pe.add(new PermissionEntity(permissionId, "UPDATE_PERSONAL_NODES", null));
            permissionId++;
            pe.add(new PermissionEntity(permissionId, "UPDATE_PUBLIC_NODES", null));
            permissionId++;
            pe.add(new PermissionEntity(permissionId, "DELETE_PRIVATE_NODES", null));
            permissionId++;
            pe.add(new PermissionEntity(permissionId, "DELETE_PUBLIC_NODES", null));
            permissionRepository.saveAll(pe);

            Long id = 0L;
            RoleEntity mereMortalUser = new RoleEntity(id, "TEST_USER_ROLE", null);
            Set<PermissionEntity> userPermissionsSet = new HashSet<>();
            userPermissionsSet.add(permissionRepository.findById(0L).get());
            userPermissionsSet.add(permissionRepository.findById(2L).get());
            userPermissionsSet.add(permissionRepository.findById(4L).get());
            userPermissionsSet.add(permissionRepository.findById(6L).get());
            mereMortalUser.setPermissionsSet(userPermissionsSet);

            RoleEntity allMightyAdmin = new RoleEntity(id + 1, "TEST_ADMIN_ROLE", null);
            Set<PermissionEntity> adminPermissionsSet = new HashSet<>();
            adminPermissionsSet.add(permissionRepository.findById(1L).get());
            adminPermissionsSet.add(permissionRepository.findById(3L).get());
            adminPermissionsSet.add(permissionRepository.findById(5L).get());
            adminPermissionsSet.add(permissionRepository.findById(7L).get());
            allMightyAdmin.setPermissionsSet(adminPermissionsSet);

            roleRepository.save(mereMortalUser);
            roleRepository.save(allMightyAdmin);
        };
    }
}
