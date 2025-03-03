package likelion.univ.domain.user.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.UserNotFoundException;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.domain.user.repository.searchcondition.UserSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class UserAdaptor {
    private final UserRepository userRepository;
    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException());
    }
    public User findByIdWithUniversity(Long id){
        return userRepository.findByIdWithUniversity(id)
                .orElseThrow(() -> new UserNotFoundException());
    }
    public User findByEmail(String email){
        return userRepository.findByAuthInfoEmail(email)
                .orElseThrow(() -> new UserNotFoundException());
    }
    public Boolean checkEmail(String email){
        return userRepository.existsByAuthInfoEmail(email);
    }
    public User save(User user){
        return userRepository.save(user);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public List<User> findDynamicUsers(UserSearchCondition condition) {
        return userRepository.findDynamicUsers(condition);
    }
    public Slice<User> findFollowingUsersByFollowerID(Long userId, Pageable pageable){
        return userRepository.findFollowingUsersByFollowerID(userId, pageable);
    }
    public Slice<User> findFollowerUsersByFollowingID(Long userId, Pageable pageable){
        return userRepository.findFollowerUsersByFollowingID(userId, pageable);
    }
    public List<User> findMyFollowingUsersByFollowingIdIn(Long follwerId, List<Long> followingIdList){
        return userRepository.findMyFollowingUsersByFollowingIdIn(follwerId, followingIdList);
    }
    public Slice<User> searchByName(String name, Pageable pageable){
        return userRepository.searchByName(name, pageable);
    }
}
