package com.luluShop.customerservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luluShop.customerservice.login.entity.User;
import com.luluShop.customerservice.login.repo.UserRepo;
import com.luluShop.customerservice.login.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // Deaktiviert die automatische Datenbankersetzung
public class DatabaseIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @MockBean // Wenn Sie Mock-Beans benötigen, können Sie sie hier hinzufügen
    private UserService userService;

    @MockBean
    private UserRepo userRepository;
    @Test
    public void testDatabaseConnection() {
        // Führe eine einfache Abfrage aus, um die Datenbankverbindung zu testen
        int result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);

        // Überprüfe, ob die Abfrage erfolgreich war (erwartet wird 1)
        assertThat(result).isEqualTo(1);
    }


}
