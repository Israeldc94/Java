package com.example.capstone_mtg_collection;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class CapstoneMtgCollectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneMtgCollectionApplication.class, args);

	}
	@Bean
	CommandLineRunner logDb(JdbcTemplate jdbc, DataSource ds) {
		return args -> {
			String db  = jdbc.queryForObject("SELECT DATABASE()", String.class);
			String url = (ds instanceof HikariDataSource h) ? h.getJdbcUrl() : "unknown";

			System.out.println("=== DB INFO ===");
			System.out.println("DATABASE(): " + db);
			System.out.println("JDBC URL   : " + url);

			try {
				Integer cnt = jdbc.queryForObject("SELECT COUNT(*) FROM deck", Integer.class);
				System.out.println("deck rows  : " + cnt);
			} catch (Exception ex) {
				System.out.println("deck row check failed (table missing?): " + ex.getMessage());
			}
			System.out.println("================");
		};
	}

	@Bean
	CommandLineRunner dbFingerprint(JdbcTemplate jdbc) {
		return args -> {
			var row = jdbc.queryForMap("""
            SELECT
              @@hostname      AS host,
              @@port          AS port,
              @@server_uuid   AS server_uuid,
              @@version       AS version,
              @@datadir       AS datadir,
              DATABASE()      AS db,
              USER()          AS user_func
        """);
			System.out.println("=== SPRING DB FINGERPRINT ===");
			row.forEach((k,v) -> System.out.println(k + ": " + v));
			Integer cnt = jdbc.queryForObject("SELECT COUNT(*) FROM deck", Integer.class);
			System.out.println("deck rows: " + cnt);
			System.out.println("=============================");
		};
	}
}

