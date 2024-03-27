package com.dbPostgresAutores.autores.controller;

import com.dbPostgresAutores.autores.model.Film;
import com.dbPostgresAutores.autores.model.dtos.*;
import com.dbPostgresAutores.autores.model.manage.Staff;
import com.dbPostgresAutores.autores.model.manage.StaffRepository;
import com.dbPostgresAutores.autores.model.manage.Store;
import com.dbPostgresAutores.autores.model.manage.StoreRepository;
import com.dbPostgresAutores.autores.model.market.*;
import com.dbPostgresAutores.autores.model.place.Address;
import com.dbPostgresAutores.autores.model.place.AddressRepository;
import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.services.repository.CityRepository;
import com.dbPostgresAutores.autores.services.repository.FilmRepository;
import org.springframework.http.ResponseEntity;
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
    private final CustomerRepository customerRepository;
    private final InventoryRepository inventoryRepository;
    private final FilmRepository filmRepository;
    private final RentalRepository rentalRepository;
    private final PaymentRepository paymentRepository;

    public Controller2(AddressRepository addressRepository, CityRepository cityRepository, StaffRepository staffRepository, StoreRepository storeRepository, CustomerRepository customerRepository, InventoryRepository inventoryRepository, FilmRepository filmRepository, RentalRepository rentalRepository, PaymentRepository paymentRepository){
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.staffRepository = staffRepository;
        this.storeRepository = storeRepository;
        this.customerRepository = customerRepository;
        this.inventoryRepository = inventoryRepository;
        this.filmRepository = filmRepository;
        this.rentalRepository = rentalRepository;
        this.paymentRepository = paymentRepository;
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
    /**
     * Customer Method save
     */

    @PostMapping("/customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody CustomerDto customerDto){
        Optional<Address> opAddress = addressRepository.findById(customerDto.addressId());
        Address address = opAddress.orElse(null);
        if(address ==null){
          throw  new RuntimeException("Object Address can not be empty");
        }
        Customer customer= new Customer(customerDto,address);
        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/inventory")
    public ResponseEntity<Inventory> saveInventory(@RequestBody InventoryDto inventoryDto){
        System.out.println("data inventory "+inventoryDto.toString() + " inv "+inventoryDto);
        Optional<Store> opStore = storeRepository.findById(inventoryDto.storeId());
        Store store = opStore.orElse(null);
        Optional<Film> opFilm = filmRepository.findById(inventoryDto.filmId());
        Film film = opFilm.orElse(null);

        System.out.println("entitis "+ store +" "+film.toString());
        Inventory inv = new Inventory(film,store);
        Inventory inventory = inventoryRepository.save(inv);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/rental")
    public ResponseEntity<Rental> saveRental(@RequestBody RentalDto rentalDto){
        Inventory inventory = inventoryRepository.findById(rentalDto.inventoryId()).orElse(null);
        Customer customer = customerRepository.findById(rentalDto.customerId()).orElse(null);
        Staff staff = staffRepository.findById(rentalDto.staffId()).orElse(null);

        Rental rental = new Rental(rentalDto.rentalRate(),rentalDto.returnDate(),inventory,customer,staff);
        Rental saveRental = rentalRepository.save(rental);
        return ResponseEntity.ok(saveRental);
    }

    @PostMapping("/payment")
    public ResponseEntity<Payment> savePayment (@RequestBody PaymentDto paymentDto){
        Customer customer = customerRepository.findById(paymentDto.customerId()).orElse(null);
        Staff staff = staffRepository.findById(paymentDto.staffId()).orElse(null);
        Rental rental = rentalRepository.findById(paymentDto.rentalId()).orElse(null);
        Payment payment = paymentRepository.save(new Payment(customer, staff, rental,paymentDto.amount(),paymentDto.paymentDate()));
        return ResponseEntity.ok(payment);

    }
}
