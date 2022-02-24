package com.cidenetshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.dto.DeleteExistingQuantityDTO;
import com.cidenetshop.model.dto.GetExistingQuantityDTO;
import com.cidenetshop.model.dto.NewExistingQuantityDTO;
import com.cidenetshop.model.entity.ExistingQuantity;
import com.cidenetshop.model.entity.Product;
import com.cidenetshop.model.entity.Size;
import com.cidenetshop.repository.ExistingQuantityRepository;
import com.cidenetshop.service.api.ExistingQuantityServiceAPI;
import com.cidenetshop.service.api.ProductServiceAPI;
import com.cidenetshop.service.api.SizeServiceAPI;

@Service
public class ExistingQuantityServiceImpl implements ExistingQuantityServiceAPI {

	private final ExistingQuantityRepository existingQuantityRepository;
	private final SizeServiceAPI sizeServiceAPI;
	private final ProductServiceAPI productServiceAPI;
	private final ModelMapper modelMapper;

	@Autowired
	public ExistingQuantityServiceImpl(ExistingQuantityRepository existingQuantityRepository,
			SizeServiceAPI sizeServiceAPI, ProductServiceAPI productServiceAPI, ModelMapper modelMapper) {
		super();
		this.existingQuantityRepository = existingQuantityRepository;
		this.sizeServiceAPI = sizeServiceAPI;
		this.productServiceAPI = productServiceAPI;
		this.modelMapper = modelMapper;

	}

	private GetExistingQuantityDTO convertToDTO(ExistingQuantity existingQuantity) throws Exception {

		try {
			GetExistingQuantityDTO getExistingQuantityDTO = modelMapper.map(existingQuantity,
					GetExistingQuantityDTO.class);
			return getExistingQuantityDTO;

		} catch (Exception e) {
			throw new Exception("Don't convert to DTO");
		}

	}

	@Override
	public ExistingQuantity findByProductIdAndSizeId(Long idProduct, Long idSize) throws Exception {
		Optional<ExistingQuantity> existingQuantity = existingQuantityRepository.findByProductIdAndSizeId(idProduct,
				idSize);
		if (existingQuantity.isEmpty())
			throw new Exception("Don't found ExistingQuantity whit idProduct: " + idProduct + " idSize:  " + idSize);

		if (existingQuantity.isPresent()) {
			return existingQuantity.get();
		}

		return null;
	}

	@Override
	public ExistingQuantity findByProductIdAndShortText(Long idProduct, String shortText) throws Exception {

		Size size = sizeServiceAPI.findByShortText(shortText);

		return findByProductIdAndSizeId(idProduct, size.getId());
	}

	@Override
	public void saveExistingQuantity(NewExistingQuantityDTO newExistingQuantityDTO) throws Exception {

		if (findByIdProductAndShortText(newExistingQuantityDTO.getIdProduct(), newExistingQuantityDTO.getShortText())
				.isPresent())
			throw new Exception("That ExistingQuantity already exists");

		if (newExistingQuantityDTO.getQuantity().equals(null) || newExistingQuantityDTO.getQuantity() <= 0)
			throw new Exception("ExistingQuantity value incorrect");

		Product product = productServiceAPI.findById(newExistingQuantityDTO.getIdProduct());

		Size size = sizeServiceAPI.findByShortText(newExistingQuantityDTO.getShortText());

		ExistingQuantity newExistingQuantity = new ExistingQuantity();

		newExistingQuantity.setProduct(product);

		newExistingQuantity.setSize(size);

		newExistingQuantity.setExistingQuantity(newExistingQuantityDTO.getQuantity());

		existingQuantityRepository.save(newExistingQuantity);

	}

	@Transactional
	@Override
	public void updateStock(NewExistingQuantityDTO updateExistingQuantityDTO) throws Exception {

		if (updateExistingQuantityDTO.getQuantity() < 0 || updateExistingQuantityDTO.getQuantity().equals(null))
			throw new Exception("Quantity is invalid");

		ExistingQuantity existingQuantity = findByProductIdAndShortText(updateExistingQuantityDTO.getIdProduct(),
				updateExistingQuantityDTO.getShortText());

		existingQuantity.setExistingQuantity(updateExistingQuantityDTO.getQuantity());

	}

	@Override
	public List<GetExistingQuantityDTO> findByIdProduct(Long idProduct) {

		List<GetExistingQuantityDTO> getDtos = new ArrayList<GetExistingQuantityDTO>();

		List<ExistingQuantity> existingQuantitys = existingQuantityRepository.findByProductId(idProduct);

		existingQuantitys.forEach(obj -> {
			try {
				getDtos.add(convertToDTO(obj));
			} catch (Exception e) {

				e.printStackTrace();
			}
		});

		return getDtos;
	}

	@Override
	public Optional<ExistingQuantity> findByIdProductAndShortText(Long idProduct, String shortText) throws Exception {

		Size size = sizeServiceAPI.findByShortText(shortText);

		Optional<ExistingQuantity> eOptional = existingQuantityRepository.findByProductIdAndSizeId(idProduct,
				size.getId());

		return eOptional;
	}

	@Override
	public void deleteExistingQuantity(DeleteExistingQuantityDTO deleteExistingQuantityDTO) throws Exception {

		Optional<ExistingQuantity> eOptional = findByIdProductAndShortText(deleteExistingQuantityDTO.getIdProduct(),
				deleteExistingQuantityDTO.getShortText());

		if (eOptional.isEmpty())
			throw new Exception("Don't found");

		existingQuantityRepository.delete(eOptional.get());

	}

}
