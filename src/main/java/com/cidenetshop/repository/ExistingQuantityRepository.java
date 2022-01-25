package com.cidenetshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cidenetshop.model.entity.ExistingQuantity;
import com.cidenetshop.model.embeddable.ExistingQuantityKey;

@Repository
public interface ExistingQuantityRepository extends CrudRepository<ExistingQuantity, ExistingQuantityKey> {

	@Modifying
	@Query(value = "update existing_quantity set existing_quantity.existing_quantity = :quantity  WHERE existing_quantity.id_product = :idProduct and existing_quantity.id_size = :idSize;", nativeQuery = true)
	void updateAfterBuySQL(@Param("quantity") Integer quantity, @Param("idProduct") Long idProduct,
			@Param("idSize") Integer idSize);

	@Modifying
	@Query("update ExistingQuantity stock set stock.existingQuantity = :quantity where stock.product.id = :idProduct and stock.size.id = :idSize ")
	void updateAfterBuyJPQL(@Param("quantity") Integer quantity, @Param("idProduct") Long idProduct,
			@Param("idSize") Integer idSize);

	Optional<ExistingQuantity> findByProductIdAndSizeId(Long idProduct, Long idSize);

}
