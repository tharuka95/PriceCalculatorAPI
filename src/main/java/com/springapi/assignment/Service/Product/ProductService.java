package com.springapi.assignment.Service.Product;

import com.springapi.assignment.Models.Model.ProductTable;
import com.springapi.assignment.Models.Model.Products;
import com.springapi.assignment.Repository.Product.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private final ProductsRepository productRepository;

    @Autowired
    public ProductService(ProductsRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Products> getProducts()
    {
        List<Products> productPageList = productRepository.findAll();
        return productPageList;
    }


    @Override
    public Optional<Products> getAProduct(Integer id) {
        Optional<Products> product = productRepository.findById(id);
        return product;
    }

    @Override
    public ArrayList<ProductTable> getPriceTable(Integer id) {
        ArrayList<ProductTable> products= new ArrayList<ProductTable>();
        Double totalPrice = 0.0;
        Optional<Products> optional = getAProduct(id);
        if(optional.isPresent()){
            Products product = optional.get();
            for (int i = 1; i <= 50; i++) {
                Integer unitsInCarton = product.getUnitsPerCarton();
                if(i < unitsInCarton) {
                    totalPrice = i * (product.getUnitPrice()* 130 / 100);
                    ProductTable item = setArrayForArrayList( product.getProductName(), product.getUnitsPerCarton(),product.getCartonPrice(),i,totalPrice);
                    products.add(item);
                } else {
                    Integer boxCount = i / unitsInCarton;
                    Integer itemCount = i% unitsInCarton;
                    if (boxCount >= 3) {
                        totalPrice = (product.getCartonPrice() * 90 / 100) * boxCount;
                    } else {
                        totalPrice = product.getCartonPrice() * boxCount;
                    }
                    totalPrice += itemCount * (product.getUnitPrice()* 130 / 100);
                    ProductTable item = setArrayForArrayList( product.getProductName(), product.getUnitsPerCarton(),product.getCartonPrice(),i,totalPrice);
                    products.add(item);
                }
            }
        }
        return products;
    }

    private ProductTable setArrayForArrayList(String productName, Integer unitsPerCarton, Double cartonPrice,Integer units, Double totalPrice){
        ProductTable productPrice = new ProductTable();
        productPrice.setProductName(productName);
        productPrice.setUnitsPerCarton(unitsPerCarton);
        productPrice.setCartonPrice(cartonPrice);
        productPrice.setNumberOfUnits(units);
        productPrice.setTotalPrice(totalPrice);
        return productPrice;
    }

}
