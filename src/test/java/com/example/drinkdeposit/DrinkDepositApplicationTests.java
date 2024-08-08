package com.example.drinkdeposit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DrinkDepositApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertTrue(true);
		DrinkDepositApplication.main(new String[] {});
	}

}
