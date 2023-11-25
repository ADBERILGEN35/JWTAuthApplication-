package com.adberilgen.jwttoken.dto;

public record AuthRequest(
        String username,
        String password
) {
}