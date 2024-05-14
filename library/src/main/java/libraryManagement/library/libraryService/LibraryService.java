package libraryManagement.library.libraryService;

import libraryManagement.library.entity.Admin;
import libraryManagement.library.entity.Books;
import libraryManagement.library.entity.Readers;
import libraryManagement.library.entity.UserInfo;
import libraryManagement.library.repository.AdminRepository;
import libraryManagement.library.repository.BookRepository;
import libraryManagement.library.repository.ReadersRepository;
import libraryManagement.library.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
@Autowired
    private AdminRepository adminRepository;
@Autowired
    private ReadersRepository readersRepository;
@Autowired
    private BookRepository  bookRepository;
@Autowired
private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<UserInfo> getAllUserInfoDetails() {
        return userInfoRepository.findAll();
    }

    public List<Readers> getAllReaders() {
        return readersRepository.findAll();
    }
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    public Readers getReaderDetailById(int id){
        return readersRepository.findById(id).orElse(null);
    }
    public Admin getAdmindetailById(int id){
        return adminRepository.findById(id).orElse(null);
    }
    public Books getBookDetailById(int id){
        return bookRepository.findById(id).orElse(null);
    }

    public Readers getReaderDetailByName(String name){
        return readersRepository.findByName(name);
    }
    public Admin getAdminDetailByName(String name){
        return adminRepository.findByName(name);
    }
    public Books getBookDetailByName(String name){
        return bookRepository.findByName(name);
    }
    public UserInfo saveUserInfoDetails(UserInfo userinfo){
        return userInfoRepository. save(userinfo);
    }
    public Readers saveReaderDetails(Readers readers){
        return readersRepository. save(readers);
    }
    public Admin saveAdminDetails(Admin admin){
        return adminRepository. save(admin);
    }
    public Books saveBookDetails(Books book){
        return bookRepository. save(book);
    }

    public List<UserInfo> saveAllUserInfoDetails(List<UserInfo> userinfo){
        return userInfoRepository.saveAll(userinfo);
    }
    public List<Readers> saveAllReadersDetails(List<Readers> readers){
        return readersRepository.saveAll(readers);
    }
    public List<Admin> saveAllAdminDetails(List<Admin> admins){
        return adminRepository.saveAll(admins);
    }
    public List<Books> saveAllBookDetails(List<Books> books){
        return bookRepository.saveAll(books);
    }

    public UserInfo updateUserInfoDetail(UserInfo userinfo) {
        UserInfo u = userInfoRepository.findById(userinfo.getId()).orElse(null);
        u.setName(userinfo.getName());
        u.setEmail(userinfo.getEmail());
        u.setPassword(userinfo.getPassword());
        u.setRoles(userinfo.getRoles());
        return userInfoRepository.save(u);
    }
    public Readers updateReaderDetail(Readers readers) {
        Readers r = readersRepository.findById(readers.getId()).orElse(null);
        r.setName(readers.getName());
        r.setAddress(readers.getAddress());
        r.setBookName(readers.getBookName());
        return readersRepository.save(r);
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
        userInfoRepository.deleteById(id);
        return "deleted successfully"+id;

    }

    public String deleteReaderDetails(int id){
        readersRepository.deleteById(id);
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
    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "user added to database";
    }

}
