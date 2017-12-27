package com.zz.ccy.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zz.ccy.service.OrderService;

@RequestMapping("/order")
@Controller
public class OrderHandler {
    
	@Autowired
	private OrderService orderService;
	
	@ResponseBody
	@RequestMapping(value="/orderManager/{userId}/{code}/{universalCount}/{uniqueCount}",method=RequestMethod.GET)
	public boolean orderManager(@PathVariable("userId") Integer userId,@PathVariable("code") String code,
			@PathVariable("universalCount") Integer universalCount,@PathVariable("uniqueCount") Integer uniqueCount){
		boolean b=orderService.orderManager(userId,code,universalCount,uniqueCount);
		return b;
	}
}
