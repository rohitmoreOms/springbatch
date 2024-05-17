package com.example.passwordencoding.service.impl;

import com.example.passwordencoding.model.Team;
import com.example.passwordencoding.model.request.TeamRequest;
import com.example.passwordencoding.repository.TeamRepository;
import com.example.passwordencoding.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class TeamService implements ITeamService {


    @Autowired
    private TeamRepository teamRepository;


   @Override
    public UserDetails loadUserByUserName(String email) {
        Team team =teamRepository.findByEmailIgnoreCase(email);
        return new User(team.getEmail(),
                team.getPassword(),
                getAuthority(team));
    }

    @Override
    public Team findByEmail(String username) {

        return teamRepository.findByEmailIgnoreCase(username);
    }


    @Override
    public Object saveOrUpdateTeam(TeamRequest teamRequest) {
        if (teamRepository.existsById(teamRequest.getTeamId())) {
            Team team = teamRepository.findById(teamRequest.getTeamId()).get();
            team.setEmail(teamRequest.getEmail());
            team.setFirstName(teamRequest.getFirstName());
            team.setLastName(teamRequest.getLastName());
            team.setPassword(teamRequest.getPassword());
            team.setAccessType(teamRequest.getAccessType());
            team.setUserType(teamRequest.getUserType());
            teamRepository.save(team);
            return "updated";
        } else {
            Team team = new Team();
            team.setEmail(teamRequest.getEmail());
            team.setPassword(teamRequest.getPassword());
            team.setLastName(teamRequest.getLastName());
            team.setFirstName(teamRequest.getFirstName());
            team.setAccessType(teamRequest.getAccessType());
            team.setUserType(teamRequest.getUserType());
            teamRepository.save(team);
            return "saved";
        }
    }

    @Override
    public Object getAllTeam(Long teamId) throws Exception {
        if (teamRepository.existsById(teamId)) {
           Team team =teamRepository.findById(teamId).get();
           return team;
        }else{
         throw new Exception("teamId not exists");
        }
    }


    private Collection<? extends GrantedAuthority> getAuthority(Team user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + "Admin"));
        return authorities;
    }

}
