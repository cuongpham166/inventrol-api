package com.inventrol.api.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/product")
	public ResponseEntity<List<ProductView>> getAllProducts(@RequestParam Optional<String> name) {
		try {
			List<ProductView> products = new ArrayList<ProductView>();
			if(name.isPresent()) {
				products = productService.searchProduct(name.get());
			}else {
				products =	productService.getAllProducts();
			}
			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<ProductDetailView>getProductById(@PathVariable("id") long id){
		Optional<Product> productData = productService.getProductById(id);
		if(productData.isPresent()) {
			Product _product = productData.get();
			if(_product.isDeleted() == false) {
				ProductDetailView _productDetail = productService.getProductDetailById(id);
				return new ResponseEntity<>(_productDetail, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/product")
	public ResponseEntity<Product> createProduct (@RequestBody Product newProduct){
		try {
			productService.createProduct(newProduct);
			return new ResponseEntity<>(HttpStatus.CREATED);
			
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
