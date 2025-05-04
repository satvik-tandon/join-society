package edu.dbms.joinsociety.service.customer.impl;

import edu.dbms.joinsociety.convertors.DataConvertor;
import edu.dbms.joinsociety.dto.ProductQuantityDTO;
import edu.dbms.joinsociety.dto.CartDTO;
import edu.dbms.joinsociety.dto.CartEntryDTO;
import edu.dbms.joinsociety.dto.ProductDTO;
import edu.dbms.joinsociety.models.Cart;
import edu.dbms.joinsociety.models.Customer;
import edu.dbms.joinsociety.models.Product;
import edu.dbms.joinsociety.repositories.CartRepository;
import edu.dbms.joinsociety.repositories.ProductRepository;
import edu.dbms.joinsociety.service.customer.CartService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("cartService")
public class DefaultCartService implements CartService {

    @Resource(name = "cartRepository")
    private CartRepository cartRepository;

    @Resource
    private ProductRepository productRepository;

    @Resource(name = "productConvertor")
    private DataConvertor productConvertor;

    @Autowired
    private HttpSession httpSession;

    @Resource(name = "cartConvertor")
    private DataConvertor cartConvertor;

    @Override
    public CartDTO getOrCreateCart() {
        CartDTO customerCartDTO = null;
        if (Objects.isNull(httpSession.getAttribute("sessionCart"))) {
            final Customer customer = (Customer) httpSession.getAttribute("customer");
            if (Objects.nonNull(customer)) {
                final List<Cart> customerCartSearchResult = cartRepository.getCartByCustomer(customer);
                if (CollectionUtils.isNotEmpty(customerCartSearchResult)) {
                    final Cart customerCart = customerCartSearchResult.get(0);
                    if (Objects.nonNull(customerCart)) {
                        customerCartDTO = (CartDTO) cartConvertor.toDTO(customerCart);
                    }
                } else {
                    customerCartDTO = createCartForCustomer(customer);
                }
            } else {
                customerCartDTO = createAnonymousCart();
            }
            httpSession.setAttribute("sessionCart", customerCartDTO);
        } else {
            customerCartDTO = (CartDTO) httpSession.getAttribute("sessionCart");
        }
        return customerCartDTO;
    }

    private CartDTO createAnonymousCart() {
        final Cart newCart = new Cart();
        populateBasicCartData(newCart);
        return (CartDTO) cartConvertor.toDTO(newCart);
    }

    private CartDTO createCartForCustomer(final Customer customer) {
        final Cart newCart = new Cart();
        newCart.setCustomer(customer);
        populateBasicCartData(newCart);
        cartRepository.save(newCart);
        return (CartDTO) cartConvertor.toDTO(newCart);
    }

    private void populateBasicCartData(final Cart newCart) {
        newCart.setCartTotal(0.0);
        newCart.setCreatedAt(LocalDateTime.now());
        newCart.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public CartDTO addProductToCart(final ProductQuantityDTO productQuantityDTO) {
        final CartDTO cartDTO = getOrCreateCart();
        updateCartEntries(productQuantityDTO, cartDTO);
        updateCartTotal(cartDTO);
        return cartDTO;
    }

    @Override
    public CartDTO updateProductInCart(ProductQuantityDTO updateCartQuantityDTO) {
        final CartDTO cartDTO = getOrCreateCart();
        updateCartEntries(updateCartQuantityDTO, cartDTO);
        updateCartTotal(cartDTO);
        return cartDTO;
    }

    @Override
    public CartDTO mergeAnonymousCartWithCustomerCart() {
        final Customer customer = (Customer) httpSession.getAttribute("customer");
        CartDTO sessionCartDTO = null;
        if (Objects.nonNull(httpSession.getAttribute("sessionCart"))) {
            sessionCartDTO = (CartDTO) httpSession.getAttribute("sessionCart");
        }
        final Cart customerCart = cartRepository.getOneByCustomer(customer);
        if (Objects.nonNull(customerCart)) {

        }
        return null;
    }

    private void updateCartTotal(final CartDTO cartDTO) {
        double cartTotal = 0;
        for (CartEntryDTO cartEntryDTO : cartDTO.getCartEntriesDTO()) {
            cartTotal +=
                    cartEntryDTO.getProduct().getDiscountedPrice() * cartEntryDTO.getQuantity();
        }
        cartDTO.setCartTotal(BigDecimal.valueOf(cartTotal));
    }

    private void updateCartEntries(final ProductQuantityDTO productQuantityDTO, final CartDTO cartDTO) {
        if (CollectionUtils.isEmpty(cartDTO.getCartEntriesDTO())) {
            final List<CartEntryDTO> cartEntryDTOS = new ArrayList<>();
            cartEntryDTOS.add(populateCartEntryData(productQuantityDTO));
            cartDTO.setCartEntriesDTO(cartEntryDTOS);
        } else {
            final List<CartEntryDTO> cartEntriesDTO = cartDTO.getCartEntriesDTO();
            CartEntryDTO cartEntryToUpdate = null;
            for (CartEntryDTO cartEntryDTO : cartEntriesDTO) {
                if (cartEntryDTO.getProduct().getProductId().equals(productQuantityDTO.getProductId())) {
                    cartEntryToUpdate = cartEntryDTO;
                    break;
                }
            }
            if (Objects.nonNull(cartEntryToUpdate)) {
                cartEntryToUpdate.setQuantity(productQuantityDTO.getQuantity());
            } else {
                CartEntryDTO cartEntryDTO = populateCartEntryData(productQuantityDTO);
                cartDTO.getCartEntriesDTO().add(cartEntryDTO);
            }
        }
    }

    private CartEntryDTO populateCartEntryData(ProductQuantityDTO productQuantityDTO) {
        CartEntryDTO cartEntryDTO = new CartEntryDTO();
        cartEntryDTO.setQuantity(productQuantityDTO.getQuantity());
        final Product product = productRepository.findOneById(productQuantityDTO.getProductId());
        cartEntryDTO.setProduct((ProductDTO) productConvertor.toDTO(product));
        return cartEntryDTO;
    }
}
