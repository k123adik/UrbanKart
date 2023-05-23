package com.example.UrbanKart.service.Impl;

import com.example.UrbanKart.Enum.Category;
import com.example.UrbanKart.dto.RequestDto.ProductRequestDto;
import com.example.UrbanKart.dto.ResponseDto.ProductResponseDto;
import com.example.UrbanKart.exception.SellerNotFoundException;
import com.example.UrbanKart.model.Product;
import com.example.UrbanKart.model.Seller;
import com.example.UrbanKart.repository.ProductRepository;
import com.example.UrbanKart.repository.SellerRepository;
import com.example.UrbanKart.service.ProductService;
import com.example.UrbanKart.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {

        //check seller exist or not
        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmailId());
        if(seller == null){
            throw new SellerNotFoundException("Enter Valid EmailId!");
        }

        //dto to entity
        Product product = ProductTransformer.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        //save to product list in seller class
        seller.getProducts().add(product);

        //save product
        Seller savedSeller = sellerRepository.save(seller);//this will save both seller and product
        Product savedProduct = savedSeller.getProducts().get(savedSeller.getProducts().size()-1);

        //prepare responseDto
        return ProductTransformer.productToProductResponseDto(savedProduct);
    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price) {

        List<Product> products = productRepository.findByCategoryAndPrice(category, price);

        //prepare a list of dto
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products){
            productResponseDtos.add(ProductTransformer.productToProductResponseDto(product));
        }
        return productResponseDtos;
    }
}
