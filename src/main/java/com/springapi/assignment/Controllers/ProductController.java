package com.springapi.assignment.Controllers;

import com.springapi.assignment.Models.Common.Response;
import com.springapi.assignment.Models.Model.ProductTable;
import com.springapi.assignment.Models.Model.Products;
import com.springapi.assignment.Service.Product.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "get-products")
    public Response getProducts()
    {
        List<Products> productsList = productService.getProducts();
        if(productsList != null) {
            return Response.ok().setPayload(productsList);
        } else {
            return Response.notFound();
        }
    }

    @GetMapping(path = "get-product")
    public Response getAProduct(Integer id)
    {
        if(id == null){
            return Response.badRequest();
        } else {
            Optional<Products> productsList = productService.getAProduct(id);
            if(productsList != null) {
                return Response.ok().setPayload(productsList);
            } else {
                return Response.notFound();
            }
        }
    }

    @GetMapping(path = "get-price-table/{id}")
    public Response getPriceTable(@PathVariable(value = "id") Integer id)
    {
        if(id == null){
            return Response.badRequest();
        } else {
            ArrayList<ProductTable> productsList = productService.getPriceTable(id);
            if(!productsList.isEmpty()) {
                return Response.ok().setPayload(productsList);
            } else {
                return Response.notFound();
            }

        }
    }
}
