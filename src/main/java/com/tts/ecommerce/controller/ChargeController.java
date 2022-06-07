package com.tts.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.tts.ecommerce.UserService;
import com.tts.ecommerce.entity.ChargeRequest;
import com.tts.ecommerce.entity.ChargeRequest.Currency;
import com.tts.ecommerce.model.User;
import com.tts.ecommerce.service.StripeService;

public class ChargeController {

	@Autowired
	private StripeService paymentsService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/charge")
	public String charge(ChargeRequest chargeRequest, Model model) throws StripeException {
			chargeRequest.setDescription("Example charge");
			chargeRequest.setCurrency(Currency.USD);
			Charge charge = paymentsService.charge(chargeRequest);
			model.addAttribute("id", charge.getId());
			model.addAttribute("status", charge.getStatus());
			model.addAttribute("ChargeId", charge.getId());
			model.addAttribute("balance_transaction", charge.getBalanceTransaction());
			
			User user = userService.getLoggedInUser();
			return "checkout";
	}
}
