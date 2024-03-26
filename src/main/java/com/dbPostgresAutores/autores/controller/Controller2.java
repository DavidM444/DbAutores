package com.dbPostgresAutores.autores.controller;

import com.dbPostgresAutores.autores.model.dtos.AddressDto;
import com.dbPostgresAutores.autores.model.dtos.StaffDto;
import com.dbPostgresAutores.autores.model.dtos.StoreDto;
import com.dbPostgresAutores.autores.model.manage.Staff;
import com.dbPostgresAutores.autores.model.manage.StaffRepository;
import com.dbPostgresAutores.autores.model.manage.Store;
import com.dbPostgresAutores.autores.model.manage.StoreRepository;
import com.dbPostgresAutores.autores.model.place.Address;
import com.dbPostgresAutores.autores.model.place.AddressRepository;
import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.services.repository.CityRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hello")
public class Controller2 {

    public final AddressRepository addressRepository;
    public final CityRepository cityRepository;
    private final StaffRepository staffRepository;
    private final StoreRepository storeRepository;

    public Controller2(AddressRepository addressRepository, CityRepository cityRepository, StaffRepository staffRepository, StoreRepository storeRepository){
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.staffRepository = staffRepository;
        this.storeRepository = storeRepository;
    }

    @PostMapping("/address")
    public String saveAddress(@RequestBody AddressDto addressDto){
        System.out.println("dto address "+addressDto);
        Optional<City> city1 = cityRepository.findById(addressDto.cityId());
        City city = city1.orElse(null);
        Address address = addressRepository.save(new Address(addressDto,city));
        return "address save "+address.toString();
    }

    @PostMapping("/staff")
    public String saveStaff( @RequestBody StaffDto staffDto){

        System.out.println("staff dto "+ staffDto);
        Optional<Address> opAddress = addressRepository.findById(staffDto.addressId());
        Address address = opAddress.orElse(null);
        Staff staff = staffRepository.save(new Staff(staffDto, address));
        return "staff save "+staff;
    }

    @PostMapping("/store")
    public String saveStore( @RequestBody StoreDto storeDto){

        Optional<Staff> opStaff = staffRepository.findById(storeDto.managerStaffId());
        Staff staff = opStaff.orElse(null);


        Optional<Address> opAddress = addressRepository.findById(storeDto.addressId());
        Address address = opAddress.orElse(null);

        Store store = storeRepository.save(new Store(address,staff));
        return "staff save "+store;
    }
}
