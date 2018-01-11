package ca.uwindsor.ices.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import ca.uwindsor.ices.jpa.Role;
import ca.uwindsor.ices.jpa.User;
import ca.uwindsor.ices.jpa.UserRole;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = -3935478823828531263L;

    private User user;

    private List<String> roles;

    public UserDetailsImpl(User user){
        super();
        this.user = user;
        this.roles = user.getUserRoles().stream()
                       .map(UserRole::getRole)
                       .map(Role::getNameRole)
                       .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.
                commaSeparatedStringToAuthorityList(this.convertToString(this.roles));
    }

    private String convertToString(List<String> roles){
        return StringUtils.collectionToCommaDelimitedString(roles);
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "A".equalsIgnoreCase(this.user.getStatus());
    }

}
