package com.example.passwordencoding.Controller;

import com.example.passwordencoding.config.SwaggerConfig;
import com.example.passwordencoding.model.Team;
import com.example.passwordencoding.model.request.LoginRequest;
import com.example.passwordencoding.model.request.TeamRequest;
import com.example.passwordencoding.model.response.LoginResponse;
import com.example.passwordencoding.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;



@RestController
public class TeamController {

    @Autowired
    private ITeamService iTeamService;

    @Autowired
    private SwaggerConfig.TokenProvider jwtToken;

    @Autowired
    AuthenticationManager authenticationManager;

/*
    //@Autowired
    @Qualifier
    public AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            UserDetails userDetails = iTeamService.loadUserByUserName(loginRequest.getEmail());

           // String token = tokenProvider.generateToken(userDetails.getUsername());
            String token="bjificuiaehr893648ggvskxucg78q347923ujoednawbcig78w457843w9hcb9y3f879y2r";
            System.out.println(token);

            Team team = iTeamService.findByEmail(userDetails.getUsername());

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setTeamId(team.getTeamId());
            loginResponse.setName(team.getFirstName() + " " + team.getLastName());
            loginResponse.setUserType(team.getUserType().toString());
            loginResponse.setAcessType(team.getAcessType().toString());

            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
 */

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LoginRequest loginRequest) {
        try {
            final Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            UserDetails userDetails = iTeamService.loadUserByUserName(loginRequest.getEmail());
            //String token = "binocular893648ginsburg78q347923untenable78w457843w9hcb9y3f879y2r";
            String token = jwtToken.generateToken(authentication);
            Team team = iTeamService.findByEmail(userDetails.getUsername());

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setTeamId(team.getTeamId());
            loginResponse.setName(team.getFirstName() + " " + team.getLastName());
            loginResponse.setUserType(team.getUserType().toString());
            loginResponse.setAccessType(team.getAccessType().toString());

            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Please Check Username and Password", HttpStatus.UNAUTHORIZED);
        }
    }

    private Authentication authenticate(String email, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (Exception e) {
            throw new Exception("Please Check Username and Password", e);
        }
    }

    private Authentication authenticate(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return authenticationManager.authenticate(authenticate(usernamePasswordAuthenticationToken));
    }

    @PostMapping("/saveOrUpdateTeam")
    public ResponseEntity<?> saveOrUpdateTeam(@ModelAttribute TeamRequest teamRequest) {
        try {
            return new ResponseEntity(iTeamService.saveOrUpdateTeam(teamRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping ("/getByTeamId")
    public ResponseEntity<?>getAllTeam(@RequestParam Long teamId ) {
        try {
            return new ResponseEntity(iTeamService.getAllTeam(teamId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
/*
    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            // throw new Exception("INVALID_CREDENTIALS", e);
            throw new Exception("Please Check Username and Password", e);
        }catch (Exception e) {
            // throw new Exception("INVALID_CREDENTIALS", e);
            throw new Exception("Please Check Username and Password", e);
        }
    }

 */

}
