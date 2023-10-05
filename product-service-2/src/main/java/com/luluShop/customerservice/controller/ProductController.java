package com.luluShop.customerservice.controller;


import com.luluShop.customerservice.FileUploadUtil;
import com.luluShop.customerservice.cart.Cart;
import com.luluShop.customerservice.entity.Product;
import com.luluShop.customerservice.orders.Order;
import com.luluShop.customerservice.rmq.MessageListener;
import com.luluShop.customerservice.service.CartService;
import com.luluShop.customerservice.service.OrderService;
import com.luluShop.customerservice.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/products")
    public String Project1(Model model, @Param("keyword") String keyword) {

        if (MessageListener.messageEmail.equals("admin@admin")){
            return "Admin";
        }
        List<Product> listProduct = productService.getAllProducts(keyword);

        model.addAttribute("listProduct", listProduct);
        model.addAttribute("keyword", keyword);

        return "productsHome";
    }

    @GetMapping("/showNewProductForm")
    public String Project2(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "Add_product";
    }

    @RequestMapping("/showEditProduct")
    public String Project3(Model model, @Param("keyword") String keyword) {
        List<Product> listProduct = productService.getAllProducts(keyword);

        model.addAttribute("listProduct", listProduct);
        model.addAttribute("keyword", keyword);

        return "Products-AdminSpace";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateImage(@PathVariable(value = "id") Integer id, Model model) {
        Product product = productService.getProductById(id);

        model.addAttribute("product", product);
        return "Edit_product";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") Integer id) {

        this.productService.deleteProductBYId(id);
        return "redirect:/";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        int pageSize = 3;

        Page<Product> page = productService.findPageinated(pageNo, pageSize, sortField, sortDir);
        List<Product> listProduct = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "dsc" : "asc");

        model.addAttribute("listProduct", listProduct);
        return "productsHome";

    }


    @GetMapping("/cart")
    public String getCart(Model model){

        List<Cart> list = cartService.getAllCartItems();
        List<Cart> tmpList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String tempId = list.get(i).getReferenceOfUserId();
            if (tempId != null && tempId.equals(MessageListener.messageUserId)) {
                tmpList.add(list.get(i));
            }
        }

        model.addAttribute("cart", tmpList);

        return "cart";
    }

    private void clearCart(String userId){
        List<Cart> list = cartService.getAllCartItems();
        for (int i = 0; i < list.size(); i++) {
            String tempId = list.get(i).getReferenceOfUserId();
            if (tempId != null && tempId.equals(MessageListener.messageUserId)) {
                cartService.deleteProductBYId(list.get(i).getId());
            }
        }
    }

    @PostMapping("/saveProduct")
    public RedirectView saveProduct(@ModelAttribute("product") Product product,
                                    @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        product.setPhoto((fileName));

        Product saveProduct = productService.saveProduct(product);

        String uploadDir = "product-photos/" + saveProduct.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/", true);
    }

    @RequestMapping("/cart/{id}")
    public String addToCart(@PathVariable("id") int id ){
        Product p = productService.getProductById(id);
        p.setRef(MessageListener.messageUserId);

        Cart c = new Cart(p.getId(), p.getProductName(), p.getPrice(), p.getRef());
        cartService.saveItemsInCart(c);
        return "redirect:/cart";
    }

    @GetMapping("/deleteProductFromCart/{id}")
    public String deleteProductFromCart(@PathVariable(value = "id") Integer id) {

        this.cartService.deleteProductBYId(id);
        return "redirect:/cart";
    }

    @GetMapping("/admin")
    public String goToAdminPage(Model model){

        if (MessageListener.messageEmail.equals("admin@admin")){
            return "Admin";
        }else{
            return "productsHome";
        }
    }

    @GetMapping("/pay")
    public String goToPaymentPage(Model model){
        return "/payment";
    }


    @GetMapping("/addOrder")
    public String addOrder(Model model){

        int userId = Integer.parseInt(MessageListener.messageUserId);

        List<Cart> list = cartService.getAllCartItems();
        List<Cart> tmpList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String tempId = list.get(i).getReferenceOfUserId();
            if (tempId != null && tempId.equals(MessageListener.messageUserId)) {
                tmpList.add(list.get(i));
            }
        }

        String invoice = tmpList.toString();
        Order order = new Order();
        order.setUserId(userId);
        order.setInvoice(invoice);

        orderService.saveItems(order);
        clearCart(MessageListener.messageUserId);

        return "/order-success";
    }


    @GetMapping("/getOrders")
    public String getOrders(Model model){
        int userId = Integer.parseInt(MessageListener.messageUserId);
        List<Order> orders = orderService.getAllCartItems();
        List<Order> tempOrders = new ArrayList<>();

        for (int i=0; i < orders.size(); i++){
            if(orders.get(i).getUserId() == userId){
                tempOrders.add(orders.get(i));
            }
        }

        model.addAttribute("order", tempOrders);
        return "/orders";
    }

}



