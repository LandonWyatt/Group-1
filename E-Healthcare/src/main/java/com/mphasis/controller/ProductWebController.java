package com.mphasis.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mphasis.model.Product;


/*
 * Web Controller that will redirect all mappings for any product related method calls
 */
@Controller
public class ProductWebController {
	
	@Autowired
	private ProductController productController;
	private int numEntries = 5; // initial number of entries
	private String searchStr = ""; // initial search string, which will show every entry
	
	// Determine if visiting as an Admin or not
	private boolean admin = false;
	
	/*
	 * directs the user to the two different product pages, based on being admin or not, on initial visit
	 */
	@GetMapping("/product")
	public ModelAndView getProducts(Map<String, Object> model, Model pageModel, @RequestParam(required = false) String firstName) {
		List<Product> productsList = productController.getAllProductSearch(searchStr);
		
		if(!admin)
			productsList = productsList.stream().filter((Product prod) -> {return prod.isActivate();}).collect(Collectors.toList());
		
		model.put("numChosen", numEntries);
		model.put("products", productsList);
		
		if(admin) {
			pageModel.addAttribute("firstName", firstName);
			return new ModelAndView("admin_product");
		} else {
			pageModel.addAttribute("firstName", firstName);
			return new ModelAndView("user_product");
		}
			
	}
	
	/*
	 * directs the user to the two different product pages, based on being admin or not,
	 * only after an update to the filter/search results have changed on the product pages
	 *  - admin_product_table and user_product_table are both Thymeleaf fragments that
	 *    are called to then be placed in the admin_product and user_product pages
	 */
	@GetMapping("/productChangeEntries")
	public ModelAndView getProductChangeEntries(Map<String, Object> model) {
		List<Product> productsList = productController.getAllProductSearch(searchStr);
		
		if(!admin)
			productsList = productsList.stream().filter((Product prod) -> {return prod.isActivate();}).collect(Collectors.toList());
		
		model.put("numChosen", numEntries);
		model.put("products", productsList);
		
		if(admin)
			return new ModelAndView("admin_product_table.html :: product");
		else
			return new ModelAndView("user_product_table.html :: product");
	}
	
	/*
	 * When user changes entry count on product page, this will be called setting the number of entries
	 */
	@PostMapping("/productChangeEntries")
	public ModelAndView postProductChangeEntries(Map<String, Object> model, @RequestBody Map<String, String> data) {
		numEntries = Integer.parseInt(data.get("numEntries"));
		return new ModelAndView("redirect:/productChangeEntries");
	}
	
	/*
	 * As user makes changes in search bar on product page, this will be called setting the search results
	 */
	@PostMapping("/productChangeSearch")
	public ModelAndView postProductChangeSearch(Map<String, Object> model, @RequestBody Map<String, String> data) {
		searchStr = data.get("searchStr");
		return new ModelAndView("redirect:/productChangeEntries");
	}
	
	/*
	 * When an admin is adding a product, this method call will redirect them to said page
	 */
	@GetMapping("/add_product")
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "add_product";
	}
	
	/*
	 * Working in tandem with the above method, this will add the product to DB
	 * as well as return admin to add_product page if in input is invalid
	 */
	@PostMapping("/save_product")
	public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		if(result.hasErrors())
			return "add_product";
		
		productController.addProduct(product);
		return "redirect:/product";
	}
	
	/*
	 * When an admin is updating a product, this will direct them to the update_product
	 * page with the attribute of the specific product
	 */
	@GetMapping("/update_product/{id}")
	public String updateProduct(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product",productController.getProduct(id));
		return "update_product";
	}
	
	/*
	 * Working in tandem with the above method, this will save the updated product
	 * to the DB and return admin to update_product page if input is invalid
	 */
	@PostMapping("/save_update")
	public String saveUpdateProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		if(result.hasErrors())
			return "update_product";
		
		productController.updateProduct(product, product.getId());
		return "redirect:/product";
	}
	
	/*
	 * From within the update_product page, the admin is able delete the product
	 * this will be called to the method to then delete product from DB and redirect
	 */
	@PostMapping("/delete_product/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		System.out.println("Delete visited");
		productController.deleteProduct(id);
		return "redirect:/product";
	}
	
}
