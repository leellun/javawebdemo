package com.newland.manager.service;

import com.newland.manager.manager.UserManager;
import com.newland.manager.mapper.MenuMapper;
import com.newland.manager.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailService")
public class MUserDetailsService implements UserDetailsService {
    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.newland.manager.domain.User user = userManager.getUser(username);
        if (user == null) return null;
        Set<String> roles = userManager.getRoles(username);
        Set<String> permissions = userManager.getPermissions(username);
        Set<String> authoritys = new HashSet<>();
        for (String role : roles) {
            authoritys.add(String.format("ROLE_%s", role));
        }
        for (String permission : permissions) {
            authoritys.add(permission);
        }
        List<GrantedAuthority> auths = AuthorityUtils.createAuthorityList(authoritys.toArray(new String[]{}));
        return new User(username, new BCryptPasswordEncoder().encode(user.getPASSWORD()), auths);
    }
}
