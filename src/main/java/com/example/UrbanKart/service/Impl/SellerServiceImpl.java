package com.example.UrbanKart.service.Impl;

import com.example.UrbanKart.Enum.Category;
import com.example.UrbanKart.dto.RequestDto.SellerRequestDto;
import com.example.UrbanKart.dto.ResponseDto.SellerResponseDto;
import com.example.UrbanKart.model.Product;
import com.example.UrbanKart.model.Seller;
import com.example.UrbanKart.repository.ProductRepository;
import com.example.UrbanKart.repository.SellerRepository;
import com.example.UrbanKart.service.SellerService;
import com.example.UrbanKart.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

        //dto to entity
        Seller seller = SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);

        //save to db
        Seller savedSeller = sellerRepository.save(seller);

        //entity to responseDto
        SellerResponseDto sellerResponseDto = SellerTransformer.sellerToSellerResponseDto(savedSeller);
        return sellerResponseDto;
    }

    @Override
    public List<SellerResponseDto> allSellersOfParticularCategory(Category category) {

        List<Product> list = productRepository.findByCategory(category);

        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();

        for(Product product : list){
            sellerResponseDtos.add(SellerTransformer.sellerToSellerResponseDto(product.getSeller()));
        }
        return sellerResponseDtos;
    }

    //*******
    @Override
    public List<SellerResponseDto> sellerWithHighestNoOfProducts() {

        List<Seller> sellers = sellerRepository.getAllSellers();

        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();
        int maxSize = 0;
        for(Seller seller : sellers){
            if(seller.getProducts().size() > maxSize){
                maxSize = seller.getProducts().size();
            }
        }
        for(Seller seller : sellers){
            if (seller.getProducts().size() == maxSize){
                sellerResponseDtos.add(SellerTransformer.sellerToSellerResponseDto(seller));
            }
        }
        return sellerResponseDtos;
    }

    @Override
    public List<SellerResponseDto> sellerWithLowestNoOfProducts() {

        List<Seller> sellers = sellerRepository.getAllSellers();

        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();
        int minSize = Integer.MAX_VALUE;
        for(Seller seller : sellers){
            if(seller.getProducts().size() < minSize){
                minSize = seller.getProducts().size();
            }
        }
        for(Seller seller : sellers){
            if (seller.getProducts().size() == minSize){
                sellerResponseDtos.add(SellerTransformer.sellerToSellerResponseDto(seller));
            }
        }
        return sellerResponseDtos;
    }

    @Override
    public List<SellerResponseDto> sellerSellingCostliestProduct() {

        List<Product> products = productRepository.findAllProducts();

        int price = Integer.MIN_VALUE;

        for(Product product : products){
           if(product.getPrice() > price){
               price = product.getPrice();
           }
        }
        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();
        for(Product product : products){
            if(product.getPrice() == price){
                sellerResponseDtos.add(SellerTransformer.sellerToSellerResponseDto(product.getSeller()));
            }
        }
        return sellerResponseDtos;
    }

    @Override
    public List<SellerResponseDto> sellerSellingCheapestProduct() {

        List<Product> products = productRepository.findAllProducts();

        int price = Integer.MAX_VALUE;

        for(Product product : products){
            if(product.getPrice() < price){
                price = product.getPrice();
            }
        }
        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();
        for(Product product : products){
            if(product.getPrice() == price){
                sellerResponseDtos.add(SellerTransformer.sellerToSellerResponseDto(product.getSeller()));
            }
        }
        return sellerResponseDtos;
    }
}
