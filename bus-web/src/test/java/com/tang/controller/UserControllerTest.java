package com.tang.controller;

import com.tang.BasePowerMockTestCase;
import com.tang.dao.user.UserDao;
import com.tang.entity.User;
import com.tang.response.ErrorCode;
import com.tang.response.RestResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author he
 * @since 2022-01.12-23:39
 */
// 所有需要测试的类列在此处，适用于模拟final类或有final, private, static, native方法的类,也可以写在方法上
@PrepareForTest({UserController.class})
public class UserControllerTest extends BasePowerMockTestCase {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserDao userDao;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_mock() {
        User user = new User();
        PowerMockito.when(userDao.queryAll(user)).thenReturn(new ArrayList<>(Arrays.asList(null, null)));
        RestResponse<List<User>> result = userController.query(user);
        Assertions.assertEquals(result.getData().size(), 2);
        Assertions.assertEquals(result.getCode(), ErrorCode.SUCCESS.value());
    }

    // 要使用junit的test,不能使用jupiter的test，否则有些mock不生效
    @Test
    public void test_mock_new() throws Exception {
        RestResponse response = new RestResponse(ErrorCode.ERROR, "");
        PowerMockito.whenNew(RestResponse.class).withAnyArguments().thenReturn(response);
        User user = new User();
        RestResponse<List<User>> result = userController.query(user);
        Assertions.assertEquals(ErrorCode.ERROR.value(), result.getCode());
    }
}