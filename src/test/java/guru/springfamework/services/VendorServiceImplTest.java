package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    VendorService vendorService;

    @Mock
    private VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository);
    }

    @Test
    public void getAllVendors() {
        // given
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("V1");

        Vendor vendor2 = new Vendor();
        vendor1.setId(2L);
        vendor1.setName("V2");

        // when
        when(vendorRepository.findAll()).thenReturn(Lists.list(vendor1,vendor2));

        List<VendorDTO> vendors = vendorService.getAllVendors();

        // then
        assertThat(vendors.size(), equalTo(2));
        assertThat(vendors.get(0).getName(), equalTo(vendor1.getName()));
        assertThat(vendors.get(1).getName(), equalTo(vendor2.getName()));
    }

    @Test
    public void getVendorById() {
        // given
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("V1");

        // when
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor1));

        VendorDTO vendor = vendorService.getVendorById(1L);

        // then
        assertThat(vendor.getName(), equalTo(vendor1.getName()));
    }

    @Test
    public void createNewVendor() {
        // given
        Vendor vendor1 = new Vendor();
        vendor1.setName("V1");

        // when
        when(vendorRepository.save(any())).thenReturn(vendor1);

        VendorDTO vendor = vendorService.createNewVendor(VendorMapper.INSTANCE.vendorToVendorDTO(vendor1));

        // then
        assertThat(vendor.getName(), equalTo(vendor1.getName()));
    }

    @Test
    public void saveVendorByDTO() {
        // given
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("V1");

        // when
        when(vendorRepository.save(any())).thenReturn(vendor1);

        VendorDTO vendor = vendorService.saveVendorByDTO(
                vendor1.getId(), VendorMapper.INSTANCE.vendorToVendorDTO(vendor1));

        // then
        assertThat(vendor.getName(), equalTo(vendor1.getName()));
    }

    @Test
    public void patchVendor() {
    }

    @Test
    public void deleteVendorById() {
    }
}