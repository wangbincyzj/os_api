package tech.wangbin.domain.service.impl;

import tech.wangbin.domain.entity.T03User;
import tech.wangbin.domain.mapper.T03UserMapper;
import tech.wangbin.domain.service.IT03UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WangBin
 * @since 2021-04-20
 */
@Service
public class T03UserServiceImpl extends ServiceImpl<T03UserMapper, T03User> implements IT03UserService {

}
