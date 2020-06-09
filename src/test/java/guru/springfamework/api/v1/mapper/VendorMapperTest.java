package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import junit.framework.TestCase;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class VendorMapperTest extends TestCase {

    private static final String NAME = "Name";

    public void testVendorToVendorDTO() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME);

        VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(vendor);

        assertThat(vendorDTO.getName(), equalTo(NAME));
    }

    public void testVendorDtoToVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = VendorMapper.INSTANCE.vendorDTOToVendor(vendorDTO);

        assertThat(vendorDTO.getName(), equalTo(NAME));
    }

}