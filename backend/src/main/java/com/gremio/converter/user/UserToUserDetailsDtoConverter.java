package com.gremio.converter.user;

import com.gremio.model.User;
import com.gremio.model.dto.UserDetailsDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDetailsDtoConverter implements Converter<User, UserDetailsDto> {

    @Override
    public UserDetailsDto convert(final User source) {
        return UserDetailsDto
                .builder()
                .email(source.getEmail())
                .role(source.getRole())
                .build();
    }
}