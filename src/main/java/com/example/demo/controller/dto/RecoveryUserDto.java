package com.example.demo.controller.dto;

import com.example.demo.entity.Role;

import java.util.List;

public record RecoveryUserDto(
        Long id,
        String email,
        List<Role> roles

) {
}