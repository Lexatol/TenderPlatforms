package ru.lexp00.platform.tpusers.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lexp00.platform.tpcommon.dtos.users.RoleDto;
import ru.lexp00.platform.tpusers.services.RoleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/roles")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public List<RoleDto> findAllUserRoles() {
        return roleService.findAllWithoutAdmin();
    }

}
