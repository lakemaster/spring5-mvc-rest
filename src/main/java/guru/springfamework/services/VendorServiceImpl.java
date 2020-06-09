package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll().stream().map(vendor -> {
            VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(vendor);
            vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
            return vendorDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id).map(vendor -> {
            VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(vendor);
            vendorDTO.setVendorUrl(getVendorUrl(id));
            return vendorDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveVendorDTO(VendorMapper.INSTANCE.vendorDTOToVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = VendorMapper.INSTANCE.vendorDTOToVendor(vendorDTO);
        return saveVendorDTO(vendor);
    }

    private VendorDTO saveVendorDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO savedVendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(savedVendor);
        savedVendorDTO.setVendorUrl(getVendorUrl(savedVendor.getId()));
        return savedVendorDTO;
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return null;
    }

    @Override
    public void deleteVendorById(Long id) {

    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
}
