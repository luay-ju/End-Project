package com.luluShop.customerservice.cart;



import com.luluShop.customerservice.FileUploadUtil;
import com.luluShop.customerservice.entity.Product;
import com.luluShop.customerservice.repository.CartRepository;
import com.luluShop.customerservice.rmq.MessageListener;
import com.luluShop.customerservice.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class CartController {

    private ProductServiceImpl productService;


    @Autowired
    private CartRepository cartRepository;
/*
    @PostMapping ("/addToCart")
    public String addToCart(@ModelAttribute("product") Product product){

        System.out.println(" product is coming ");

        // hon lazem el id taba3 el User
        int id = productService.getProductById(product.getId()).getId();
        String productName = productService.getProductById(product.getId()).getProductName();
        double price = productService.getProductById(product.getId()).getPrice();

        Cart cart = new Cart(id,productName,price);

        Cart c = cartRepository.save(cart);

        return "redirect:/cart";

    }

 */



    /*
    @PostMapping ("/addToCart")
    public String addToCart(@ModelAttribute("product") Product product){

        System.out.println(" product is coming ");

        // hon lazem el id taba3 el User
        String number = MessageListener.messageUserId;
        int id = Integer.parseInt(number);
        String productName = productService.getProductById(product.getId()).getProductName();
        double price = productService.getProductById(product.getId()).getPrice();

        Cart cart = new Cart(id,productName,price);

      //  Cart c = cartRepository.save(cart);
        cartRepository.save(cart);

        return "redirect:/cart";

    }

     */

}



/*

private RestTemplate restTemplate;


    @PostMapping("/cart/project/{projectId}")
    public void addToCart(@PathVariable String projectId) {
        // 1. Senden Sie eine Anfrage an den Project-Service, um das Projektobjekt abzurufen
        String projectServiceUrl = "http://project-service";
        ResponseEntity<Product> response = restTemplate.getForEntity(
                projectServiceUrl + "/projects/" + projectId, Product.class);

        // 2. Speichern Sie das Projektobjekt in der Datenbank des Cart-Services
        if (response.getStatusCode() == HttpStatus.OK) {
            Product project = response.getBody();
            // Speichern Sie das Projektobjekt in der Cart-Service-Datenbank
            // ...
        } else {
            // Fehlerbehandlung, wenn das Projektobjekt nicht abgerufen werden kann
            // ...
        }
    }

 */
