package com.luluShop.customerservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // Deaktiviert die automatische Datenbankersetzung
public class DatabaseIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDatabaseConnection() {
        // Führe eine einfache Abfrage aus, um die Datenbankverbindung zu testen
        int result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);

        // Überprüfe, ob die Abfrage erfolgreich war (erwartet wird 1)
        assertThat(result).isEqualTo(1);
    }
}
