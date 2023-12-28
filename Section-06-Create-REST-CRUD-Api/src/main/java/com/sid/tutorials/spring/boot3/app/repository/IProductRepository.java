/**
 *
 */
package com.sid.tutorials.spring.boot3.app.repository;

import java.util.List;

import com.sid.tutorials.spring.boot3.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * @author kunmu
 */
@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

    public List<Product> findByName(String name);

    @Query(value = "Select p from product p where product_name like %:name% and price>=:price", nativeQuery = true)
    public List<Product> findByNameLikeAndPrice(@Param("name") String name, @Param("price") double price);

}
