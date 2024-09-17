package com.example.BookStore.config;

import com.example.BookStore.models.BookModel;
import com.example.BookStore.models.CartModel;
import com.example.BookStore.models.user.UserModel;
import com.example.BookStore.models.user.UserRole;
import com.example.BookStore.repositories.BookRepository;
import com.example.BookStore.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository userRepository, BookRepository bookRepository) {
        return args -> {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if(userRepository.findByLogin("forlanAdmin") == null) {
                UserModel newUser = new UserModel("forlanAdmin", encoder.encode("123456"), UserRole.ADMIN);
                CartModel cart = new CartModel(newUser);
                newUser.setCart(cart);
                userRepository.save(newUser);
            }

            if(userRepository.findByLogin("forlanCustomer") == null) {
                UserModel newUser = new UserModel("forlanCustomer", encoder.encode("123456"), UserRole.CUSTOMER);
                CartModel cart = new CartModel(newUser);
                newUser.setCart(cart);

                userRepository.save(newUser);
            }

            if(bookRepository.count() == 0) {
                BookModel book1 = new BookModel();
                book1.setName("Game of Thrones");
                book1.setPrice(BigDecimal.valueOf(80.00));
                book1.setDescription("An incredible world!");
                book1.setCategory("Fantasy");
                book1.setBookType("physical");
                bookRepository.save(book1);

                BookModel book2 = new BookModel();
                book2.setName("Game of Thrones");
                book2.setPrice(BigDecimal.valueOf(50.00));
                book2.setDescription("An incredible world!");
                book2.setCategory("Fantasy");
                book2.setBookType("digital");
                bookRepository.save(book2);

                BookModel book3 = new BookModel();
                book3.setName("The Hobbit");
                book3.setPrice(BigDecimal.valueOf(60.00));
                book3.setDescription("A fantasy adventure!");
                book3.setCategory("Fantasy");
                book3.setBookType("digital");
                bookRepository.save(book3);

                BookModel book4 = new BookModel();
                book4.setName("Duna");
                book4.setPrice(BigDecimal.valueOf(90.00));
                book4.setDescription("Paul Atreides is a brilliant young man, master of a destiny beyond his understanding. He must travel to the most dangerous planet in the universe to secure the future of his people.");
                book4.setCategory("Sci-fi");
                book4.setBookType("physical");
                bookRepository.save(book4);

                BookModel book5 = new BookModel();
                book5.setName("Dom Casmurro");
                book5.setPrice(BigDecimal.valueOf(40.00));
                book5.setDescription("the narrator Bento Santiago revisits the childhood he spent on Rua de Matacavalos and tells the story of the love and misadventures he experienced with Capitu");
                book5.setCategory("Romance");
                book5.setBookType("physical");
                bookRepository.save(book5);

                BookModel book6 = new BookModel();
                book6.setName("Dom Casmurro");
                book6.setPrice(BigDecimal.valueOf(20.00));
                book6.setDescription("the narrator Bento Santiago revisits the childhood he spent on Rua de Matacavalos and tells the story of the love and misadventures he experienced with Capitu");
                book6.setCategory("Romance");
                book6.setBookType("digital");
                bookRepository.save(book6);

                BookModel book7 = new BookModel();
                book7.setName("Guardiola: Confidential");
                book7.setPrice(BigDecimal.valueOf(70.00));
                book7.setDescription("A year at Bayern Munich closely following the coach who changed football forever.");
                book7.setCategory("Biography");
                book7.setBookType("digital");
                bookRepository.save(book7);
            }
        };
    }
}
