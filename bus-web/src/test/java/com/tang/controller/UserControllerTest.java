package com.tang.controller;

import com.tang.BasePowerMock;
import com.tang.dao.user.UserDao;
import com.tang.entity.User;
import com.tang.response.ErrorCode;
import com.tang.response.RestResponse;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author he
 * @since 2022-01.12-23:39
 */
public class UserControllerTest extends BasePowerMock {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserDao userDao;

    @Before
    public void setup() {
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

    @Test
    public void test_mock_new() throws Exception {
        RestResponse response = new RestResponse(ErrorCode.SUCCESS, "");
        PowerMockito.whenNew(RestResponse.class).withAnyArguments().thenReturn(response);
        User user = new User();
        RestResponse<List<User>> result = userController.query(user);
        System.out.println(result.getCode());
    }
}