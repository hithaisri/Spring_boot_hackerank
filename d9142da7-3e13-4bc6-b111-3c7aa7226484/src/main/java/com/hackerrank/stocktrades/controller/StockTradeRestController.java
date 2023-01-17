package com.hackerrank.stocktrades.controller;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PatchMapping;
import java.util.Optional;

@RestController
public class StockTradeRestController {
	
	@Autowired
	public StockTradeRepository repo;
	
	@PostMapping("/trades")
	public ResponseEntity<StockTrade> addStockTrades(@RequestBody StockTrade stock){
		StockTrade newStock=repo.save(stock);
		return new ResponseEntity<StockTrade>(newStock, HttpStatus.CREATED);
	}
	
	@GetMapping("/trades/{id}")
	public ResponseEntity<Optional<StockTrade>> getStockTradeById(@PathVariable Integer id){
	
	Optional<StockTrade> stock=repo.findById(id);
		if(stock.isPresent())
			return new ResponseEntity<Optional<StockTrade>>(stock, HttpStatus.OK);		
		else 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/trades")
	public ResponseEntity<List<StockTrade>> getAllStocks(@RequestParam(value="type",required=false) String type
			,@RequestParam(value="userId",required=false) Integer id){
		List<StockTrade> stocks=new ArrayList<StockTrade>();
		if(type!=null && !type.isEmpty() && id!=null && id>0)
			stocks=repo.findByTypeAndUserId(type,id);
		else if(type!=null && !type.isEmpty())
			stocks=repo.findByType(type);
		else if(id!=null && id>0)
			stocks=repo.findByUserId(id);
		else
			stocks=repo.findAll();
		
		return new ResponseEntity<List<StockTrade>>(stocks,HttpStatus.OK);	
	}
	
	@DeleteMapping("/trades/{id}")
	public ResponseEntity deleteStock(@PathVariable Integer id){
		return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@PutMapping("/trades/{id}")
	public ResponseEntity updateStock(@PathVariable Integer id){
		return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@PatchMapping("/trades/{id}")
	public ResponseEntity patchStock(@PathVariable Integer id){
		return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
	}
}