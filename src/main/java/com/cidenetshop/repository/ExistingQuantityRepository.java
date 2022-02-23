package com.cidenetshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cidenetshop.model.embeddable.ExistingQuantityKey;
import com.cidenetshop.model.entity.ExistingQuantity;

@Repository
public interface ExistingQuantityRepository extends CrudRepository<ExistingQuantity, ExistingQuantityKey> {

	Optional<ExistingQuantity> findByProductIdAndSizeId(Long idProduct, Long idSize);

	List<ExistingQuantity> findByProductId(Long idProduct);
}
