package libraryManagement.library.libraryController;

import libraryManagement.library.entity.*;
import libraryManagement.library.libraryService.JwtService;
import libraryManagement.library.libraryService.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private LibraryService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/add/DetailsOfUser")

    public String addUserInfoDetails(@RequestBody UserInfo userinfo) {
        return service.addUser(userinfo);
    }


    @PostMapping("/add/DetailsOfReaders")
    public Readers addReaderDetails(@RequestBody Readers readers) {
        return service.saveReaderDetails(readers);
    }

    @PostMapping("/add/DetailsOfAdmin")
    public Admin addAdminDetail(@RequestBody Admin admins) {
        return service.saveAdminDetails(admins);
    }

    @PostMapping("/add/DetailsOfBook")
    public Books addBooksdetail(@RequestBody Books books) {
        return service.saveBookDetails(books);
    }

    @PostMapping("/add/ListOfUserInfo")
    public List<UserInfo> addUserInfoDetails(@RequestBody List<UserInfo> userinfo) {
        return service.saveAllUserInfoDetails(userinfo);
    }

    @PostMapping("/add/ListofUsers")
    public List<Readers> addReadersDetails(@RequestBody List<Readers> readers) {
        return service.saveAllReadersDetails(readers);
    }

    @PostMapping("/add/ListOfAdmins")
    public List<Admin> addAdminDetails(@RequestBody List<Admin> admins) {
        return service.saveAllAdminDetails(admins);
    }

    @PostMapping("/add/ListOfBooks")
    public List<Books> addBooksDetails(@RequestBody List<Books> books) {
        return service.saveAllBookDetails(books);
    }

    @GetMapping("/Welcome")
    public String welcome() {
        return "Welcome to the Library Management System!";
    }

    @GetMapping("/display/AllUserInfo")
    public List<UserInfo> findAllUserInfo() {
        return service.getAllUserInfoDetails();
    }

    @GetMapping("/display/AllReaders")
    public List<Readers> findAllReaders() {
        return service.getAllReaders();
    }

    @GetMapping("/display/AllAdmins")
    public List<Admin> findAllAdminDetails() {
        return service.getAllAdmin();
    }

    @GetMapping("/display/AllBooks")
    public List<Books> findAllBookDetails() {
        return service.getAllBooks();
    }

    @GetMapping("/display/ReaderById/{id}")
    public Readers findReaderDetailById(@PathVariable int id) {
        return service.getReaderDetailById(id);
    }

    @GetMapping("/display/AdminById/{id}")
    public Admin findAdmindetailById(@PathVariable int id) {
        return service.getAdmindetailById(id);
    }

    @GetMapping("/display/BookByid/{id}")
    public Books findBookdetailById(@PathVariable int id) {
        return service.getBookDetailById(id);
    }

    @GetMapping("/display/UserByName/{name}")
    public Readers findReaderDetailByName(@PathVariable String name) {
        return service.getReaderDetailByName(name);
    }

    @GetMapping("/display/AdminByName/{name}")
    public Admin findAdmindetailByName(@PathVariable String name) {
        return service.getAdminDetailByName(name);
    }

    @GetMapping("/display/BookByName/{name}")
    public Books findBookdetailByName(@PathVariable String name) {
        return service.getBookDetailByName(name);
    }

    @PutMapping("/update/UserInfo")
    public UserInfo updateUserInfodetail(@RequestBody UserInfo userinfo) {
        return service.updateUserInfoDetail(userinfo);
    }

    @PutMapping("/update/Reader")
    public Readers updateReadersdetail(@RequestBody Readers reader) {
        return service.updateReaderDetail(reader);
    }

    @PutMapping("/update/Admin")

    public Admin updateAdmindetail(@RequestBody Admin admin) {
        return service.updateAdminDetail(admin);
    }

    @PutMapping("/update/Book")

    public Books updateBookdetail(@RequestBody Books book) {
        return service.updateBookDetail(book);
    }

    @DeleteMapping("/delete/User/{id}")

    public String deleteUserDetailById(@PathVariable int id) {
        service.deleteUserDetails(id);
        return "Successfully deleted";
    }

    @DeleteMapping("/delete/Reader/{id}")

    public String deleteReaderDetailById(@PathVariable int id) {
        service.deleteReaderDetails(id);
        return "Successfully deleted";
    }

    @DeleteMapping("/delete/Admin/{id}")

    public String deleteAdminDetailById(@PathVariable int id) {
        service.deleteAdminDetails(id);
        return "Successfully deleted";
    }

    @DeleteMapping("/delete/Book/{id}")

    public String deleteBookDetailById(@PathVariable int id) {
        service.deleteBookDetails(id);
        return "Successfully deleted";
    }

    @PostMapping("/authenticate")
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")

//    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            authRequest.getUsername(),
//                            authRequest.getPassword()));
//
//            if (authentication.isAuthenticated()) {
//                return jwtService.generateToken(authRequest.getUsername());
//            } else {
//                throw new BadCredentialsException("Invalid credentials");
//            }
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Invalid credentials");
//        }
    public JwtResponseDTO authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.generateToken(authRequest.getUsername()))
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
    }
}








