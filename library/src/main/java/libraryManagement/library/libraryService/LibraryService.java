package libraryManagement.library.libraryService;

import libraryManagement.library.entity.Admin;
import libraryManagement.library.entity.Books;
import libraryManagement.library.entity.User;
import libraryManagement.library.repository.AdminRepository;
import libraryManagement.library.repository.BookRepository;
import libraryManagement.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
@Autowired
    private AdminRepository adminRepository;
    private UserRepository  userRepository;
    private BookRepository  bookRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    public User getUserDetailById(int id){
        return userRepository.findById(id).orElse(null);
    }
    public Admin getAdmindetailById(int id){
        return adminRepository.findById(id).orElse(null);
    }
    public Books getBookDetailById(int id){
        return bookRepository.findById(id).orElse(null);
    }

    public User getUserDetailByName(String name){
        return userRepository.findByName(name);
    }
    public Admin getAdminDetailByName(String name){
        return adminRepository.findByName(name);
    }
    public Books getBookDetailByName(String name){
        return bookRepository.findByName(name);
    }

    public User saveUserDetails(User user){
        return userRepository. save(user);
    }
    public Admin saveAdminDetails(Admin admin){
        return adminRepository. save(admin);
    }
    public Books saveBookDetails(Books book){
        return bookRepository. save(book);
    }

    public List<User> saveAllUserDetails(List<User> users){
        return userRepository.saveAll(users);
    }
    public List<Admin> saveAllAdminDetails(List<Admin> admins){
        return adminRepository.saveAll(admins);
    }
    public List<Books> saveAllBookDetails(List<Books> books){
        return bookRepository.saveAll(books);
    }


    public User updateUserDetail(User user) {
        User u = userRepository.findById(user.getId()).orElse(null);
        u.setName(user.getName());
        u.setAddress(user.getAddress());
        u.setBookName(user.getBookName());
        return userRepository.save(u);
    }
    public Admin updateAdminDetail(Admin admin) {
        Admin a = adminRepository.findById(admin.getId()).orElse(null);
        a.setName(admin.getName());
        a.setEmail(admin.getEmail());
        return adminRepository.save(a);
    }
    public Books updateBookDetail(Books book) {
        Books b = bookRepository.findById(book.getId()).orElse(null);
        b.setName(book.getName());
        b.setQuantity(book.getQuantity());
        return bookRepository.save(b);
    }


    public String deleteUserDetails(int id){
        userRepository.deleteById(id);
        return "deleted successfully"+id;

    }
    public String deleteAdminDetails(int id){
        adminRepository.deleteById(id);
        return "deleted successfully"+id;
    }
    public String deleteBookDetails(int id){
        bookRepository.deleteById(id);
        return "deleted successfully"+id;

    }

}
