package com.code.springtransaction;

import com.code.springtransaction.entities.dto.UserInsertDTO;
import com.code.springtransaction.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
class SpringtransactionApplicationTests {


    @Autowired
    UserService userService;


    @Test
    void contextLoads() {
    }

    @Test
    void deleteObj() throws SQLException {
        userService.deleteObj ( "00000000" );
    }


    @Test
    void insertObj() throws SQLException {
        UserInsertDTO userInsertDTO = new UserInsertDTO ();
        userInsertDTO.setName ( "刘德华" );
        userService.insert ( userInsertDTO );
    }
}
