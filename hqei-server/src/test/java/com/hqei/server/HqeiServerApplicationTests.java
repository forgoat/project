package com.hqei.server;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HqeiServerApplicationTests {

	public static final int HASH_INTERATIONS = 1024;
	private RandomNumberGenerator rng = new SecureRandomNumberGenerator();

	@Test
	public void contextLoads() {
	}

	@Test
	public void pwd(){
		ByteSource salt = rng.nextBytes();
		String pwd = encryptPassword("123456", salt);
		System.out.println("salt:" + salt + ", pwd:" + pwd);
	}

	private String encryptPassword(String password, ByteSource salt) {
		return new Sha256Hash(password, salt, HASH_INTERATIONS).toBase64();
	}
}
