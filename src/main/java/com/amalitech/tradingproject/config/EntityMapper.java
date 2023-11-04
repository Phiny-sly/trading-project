package com.amalitech.tradingproject.config;

import com.amalitech.tradingproject.dto.OrderDto;
import com.amalitech.tradingproject.dto.ProductDto;
import com.amalitech.tradingproject.dto.ProductLineDto;
import com.amalitech.tradingproject.dto.UserDto;
import com.amalitech.tradingproject.model.Order;
import com.amalitech.tradingproject.model.Product;
import com.amalitech.tradingproject.model.ProductLine;
import com.amalitech.tradingproject.model.User;
import com.amalitech.tradingproject.payload.ProductPayload;
import com.amalitech.tradingproject.payload.UserPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Component
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "id", target = "id")
    UserDto convertToUserDto(User user);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "password", target = "password")
    User convertToUser(UserPayload userPayload);

    void updateUserDetails(@MappingTarget User user, UserPayload userPayload);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", expression = "java(order.getUser().getId())")
    @Mapping(target = "email", expression = "java(order.getUser().getEmail())")
    @Mapping(target = "listOfProductLines", source = "listOfProductLines")
    OrderDto convertToOrderDto(Order order);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productName", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "stock", source = "stock")
    ProductDto convertToProductDto(Product product);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "stock", source = "stock")
    Product convertToProduct(ProductPayload productPayload);

    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "productName", expression = "java(productLine.getProduct().getName())")
    @Mapping(target = "unitPrice", expression = "java(productLine.getProduct().getPrice())")
    @Mapping(target = "productId", expression = "java(productLine.getProduct().getId())")
    ProductLineDto convertToProductLineDto(ProductLine productLine);

    void updateProductDetails(@MappingTarget Product product, ProductPayload productPayload);
}
