package com.example.passwordencoding.service;

import com.example.passwordencoding.model.Team;
import com.example.passwordencoding.model.request.TeamRequest;


import org.springframework.security.core.userdetails.UserDetails;

public interface ITeamService {
    //UserDetails loadUserByUserName(String email);

   
    Object saveOrUpdateTeam(TeamRequest teamRequest);

    Object getAllTeam(Long teamId) throws Exception;

    UserDetails loadUserByUserName(String email);

    Team findByEmail(String username);



}
