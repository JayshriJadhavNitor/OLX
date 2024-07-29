
package com.TradeSpot.services;


import com.TradeSpot.DTO.UserDTO;
import com.TradeSpot.DTO.UserRespDTO;
import com.TradeSpot.DTO.UserUpdateDTO;
import com.TradeSpot.customException.CustomException;
import com.TradeSpot.entities.Roles;
import com.TradeSpot.entities.User;

import com.TradeSpot.repositories.BroughtItemsRepo;
import com.TradeSpot.repositories.SellItemsRepo;
import com.TradeSpot.repositories.UserRepository;




import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private BroughtItemsRepo broughtItemsRepo;

    @Autowired
    private SellItemsRepo sellItemsRepo;

    public User addUser(UserDTO userDTO) {

        if(userRepository.existsByEmail(userDTO.getEmail())){
            return null;
        }
        User user= mapper.map(userDTO, User.class);

        //hash the password
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setRole(Roles.USER);
        return userRepository.save(user);
    }

    public List<UserDTO> listOfAllUsers() {

        List<User> users= userRepository.findAll();
        return users.stream().map(user-> mapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    public UserDTO findUser(Long id) {

        User user= userRepository.findById(id).orElseThrow();
        return mapper.map(user, UserDTO.class);
    }

    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    public List<User> findByName(String name) {

        return userRepository.getByName(name);
    }

    public UserRespDTO findByEmail(String email){
        User user=userRepository.findByEmail(email).orElseThrow(()->new CustomException("User with email not exits"));
        return  mapper.map(user, UserRespDTO.class) ;
    }

    public long findBuyerCount() {

        return broughtItemsRepo.findBuyerCount();
    }

    public long findSellerCount() {

        return sellItemsRepo.findSellerCount();
    }

    public List<UserRespDTO> getRecentUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .sorted(Comparator.comparing(User::getId).reversed())
                .limit(5)
                .map(e->mapper.map(e,UserRespDTO.class))
                .collect(Collectors.toList());
    }


    public User updateUser(long userId, UserUpdateDTO user) {

        User newuser= userRepository.findById(userId).orElseThrow(() -> new CustomException("User is not found with id "+ userId));
        newuser.setAddress(user.getAddress());
        newuser.setFirstName(user.getFirstName());
        newuser.setEmail(user.getEmail());
        newuser.setLastName(user.getLastName());
        System.out.println(newuser);
        return userRepository.save(newuser);



    }
}
