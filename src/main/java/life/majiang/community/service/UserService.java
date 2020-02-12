package life.majiang.community.service;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * Created by codedrinker on 2019/11/14.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            // 插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            //更新
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser, example);
        }
    }

    public int add(User user) {

        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountIdEqualTo(user.getName());
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 0) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            //zhanhu判断是否已经存在
            int insert = userMapper.insert(user);
            return insert;
        }else{
            return 0;
        }
    }

    public List<User> yanzhen(String username, String password) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountIdEqualTo(username)
                .andNameEqualTo(password);
        List<User> users = userMapper.selectByExample(example);
//        if(users.isEmpty()){
//             return "shibai";
//        }else{
//
//        }return "chenggong";
//    }
//}
        return users;
    }

    public List<User> select1(String username) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountIdEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        return users;
    }
}