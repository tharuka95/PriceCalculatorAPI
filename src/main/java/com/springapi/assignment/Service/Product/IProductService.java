package com.springapi.assignment.Service.Product;

import com.springapi.assignment.Models.Model.ProductTable;
import com.springapi.assignment.Models.Model.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Products> getProducts();
    Optional<Products> getAProduct(Integer id);
    ArrayList<ProductTable> getPriceTable(Integer id);
}
