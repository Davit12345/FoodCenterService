package com.example.foodcenter.service.impl;

import com.example.foodcenter.controller.forJsonModels.CartInfo;
import com.example.foodcenter.exceptions.AccessDeniedException;
import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.model.Cart;
import com.example.foodcenter.model.User;
import com.example.foodcenter.repository.CartRepository;
import com.example.foodcenter.repository.UserRepository;
import com.example.foodcenter.service.CartService;
import com.example.foodcenter.util.Constants;
import com.example.foodcenter.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addCart(CartInfo cartInfo, String email) throws InternalErrorException, DuplicateDataException {
        try {
//static addUser cart

            if (cartRepository.getCartByCartCode(cartInfo.getCartCode()) != null) {
                throw new DuplicateDataException("There are user whit this card ");
            }

            Cart cart = new Cart();
            cart.setCartCode(cartInfo.getCartCode());

            cart.setCartPassword(Util.getEncoded(cartInfo.getCartPassword()));
            cart.setUser(userRepository.getByEmail(email));
            //random

            Random r = new Random();
            int sum = r.nextInt(100) + 25;
            cart.setAmount(sum);



            cartRepository.save(cart);
        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }

    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED)
    public void deleteCart(String email, String cartCode) throws InternalErrorException, NotFoundException, AccessDeniedException {
        try {
            User user = userRepository.getByEmail(email);
            Cart cart = cartRepository.getCartByCartCode(cartCode);

            if(cart==null){
                throw  new AccessDeniedException("You write  wrong code ");
            }

            else if (cart.getUser().equals(user)) {
                cartRepository.delete(cart);

            }else{
                throw  new NotFoundException("You write  wrong code ");
            }

        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }

    }


    @Override
    public List<Cart> getCartByUser(String email) throws InternalErrorException {
        try{

        return cartRepository.getCartByUser(userRepository.getByEmail(email));

    } catch (RuntimeException e) {
        throw new InternalErrorException(Constants.ERROR_MESSAGE);
    }
    }
}
