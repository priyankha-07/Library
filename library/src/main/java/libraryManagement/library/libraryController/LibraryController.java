package libraryManagement.library.libraryController;

import libraryManagement.library.entity.Admin;
import libraryManagement.library.entity.AuthRequest;
import libraryManagement.library.entity.Books;
import libraryManagement.library.entity.User;
import libraryManagement.library.libraryService.JwtService;
import libraryManagement.library.libraryService.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private AuthenticationManager authenticationManager;

    @PostMapping("/addDetailsOfUser")
    @PreAuthorize("hasRoles('Admin')")
    public User addUserDetails(@RequestBody User users) {
        return service.saveUserDetails(users);
    }

    @PostMapping("/addDetailsOfAdmin")
    @PreAuthorize("hasRoles('Admin')")
    public Admin addAdminDetail(@RequestBody Admin admins) {
        return service.saveAdminDetails(admins);
    }

    @PostMapping("/addDetailsOfBook")
    @PreAuthorize("hasRoles('Admin')")
    public Books addBooksdetail(@RequestBody Books books) {
        return service.saveBookDetails(books);
    }

    @PostMapping("/addlistofUsers")
    @PreAuthorize("hasRoles('Admin')")
    public List<User> addUserDetails(@RequestBody List<User> users) {
        return service.saveAllUserDetails(users);
    }

    @PostMapping("/addlistofAdmins")
    @PreAuthorize("hasRoles('Admin')")
    public List<Admin> addAdminDetails(@RequestBody List<Admin> admins) {
        return service.saveAllAdminDetails(admins);
    }

    @PostMapping("/addlistofBooks")
    @PreAuthorize("hasRoles('Admin')")
    public List<Books> addBooksDetails(@RequestBody List<Books> books) {
        return service.saveAllBookDetails(books);
    }

    @GetMapping("/Welcome")
    public String welcome() {
        return "Welcome to the Library Management System!";
    }

    @GetMapping("/displayAllUsers")
    public List<User> findAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/displayAllAdmins")
    public List<Admin> findAllAdminDetails() {
        return service.getAllAdmin();
    }

    @GetMapping("/displayAllBooks")
    public List<Books> findAllBookDetails() {
        return service.getAllBooks();
    }

    @GetMapping("/getUserById/{id}")
    public User findUserDetailById(@PathVariable int id) {
        return service.getUserDetailById(id);
    }

    @GetMapping("/getAdminById/{id}")
    public Admin findAdmindetailById(@PathVariable int id) {
        return service.getAdmindetailById(id);
    }

    @GetMapping("/getBookByid/{id}")
    public Books findBookdetailById(@PathVariable int id) {
        return service.getBookDetailById(id);
    }

    @GetMapping("/getUserByName/{name}")
    public User findUserDetailByName(@PathVariable String name) {
        return service.getUserDetailByName(name);
    }

    @GetMapping("/getAdminByName/{name}")
    public Admin findAdmindetailByName(@PathVariable String name) {
        return service.getAdminDetailByName(name);
    }

    @GetMapping("/getBookByName/{name}")
    public Books findBookdetailByName(@PathVariable String name) {
        return service.getBookDetailByName(name);
    }

    @PutMapping("/updateUser")
    public User updateUserdetail(@RequestBody User user) {
        return service.updateUserDetail(user);
    }

    @PutMapping("/updateAdmin")
    public Admin updateAdmindetail(@RequestBody Admin admin) {
        return service.updateAdminDetail(admin);
    }

    @PutMapping("/updateBook")
    public Books updateBookdetail(@RequestBody Books book) {
        return service.updateBookDetail(book);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUserDetailById(@PathVariable int id) {
        service.deleteUserDetails(id);
        return "Successfully deleted";
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public String deleteAdminDetailById(@PathVariable int id) {
        service.deleteAdminDetails(id);
        return "Successfully deleted";
    }

    @DeleteMapping("/deleteBook/{id}")
    public String deleteBookDetailById(@PathVariable int id) {
        service.deleteBookDetails(id);
        return "Successfully deleted";
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUserName());
        } else {
               throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
