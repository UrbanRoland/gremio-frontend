package com.gremio.service;

import com.gremio.model.dto.UserDetailsDto;
import com.gremio.persistence.entity.User;
import com.gremio.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @InjectMocks
    private UserServiceImpl userService;
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private ConversionService conversionService;
    
    @Test
    public void testGetAllUser_WithValidPageable_ReturnsUserDetailsPage() {
            Pageable pageable = PageRequest.of(0, 10);
            List<User> users = Arrays.asList(new User(), new User());
            Page<User> userPage = new PageImpl<>(users);
            List<UserDetailsDto> userDetails = Arrays.asList(new UserDetailsDto(), new UserDetailsDto());
            Page<UserDetailsDto> expectedPage = new PageImpl<>(userDetails);
            
            Mockito.when(userRepository.findAll(pageable)).thenReturn(userPage);
            
            Mockito.when(conversionService.convert(Mockito.any(User.class), Mockito.eq(UserDetailsDto.class)))
                    .thenReturn(new UserDetailsDto());
            
            Page<UserDetailsDto> actualPage = userService.getAllUser(pageable);
            
            Assertions.assertEquals(expectedPage, actualPage);
            
            Mockito.verify(userRepository).findAll(pageable);
            
            Mockito.verify(conversionService, Mockito.times(users.size()))
                    .convert(Mockito.any(User.class), Mockito.eq(UserDetailsDto.class));
    
    }
    
}