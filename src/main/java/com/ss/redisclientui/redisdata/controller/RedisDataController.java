package com.ss.redisclientui.redisdata.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ss.redisclientui.base.controller.BaseController;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
@RequestMapping(value = "/redisData")
public class RedisDataController extends BaseController {
	
	@Autowired
	JedisPool pool;
	
	@RequestMapping(value = "/listKeys", method = RequestMethod.GET)
	public String listKeys(Model model) {
		
		Jedis jedis = pool.getResource();
		
		Set<byte[]> keySet = jedis.keys("*T_TRS*".getBytes());
		
		model.addAttribute("hello", "Hello");
		return "redisData/listRedisData";
	}
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String getEvent(Model model) {
		
		return "hello";
	}
}
