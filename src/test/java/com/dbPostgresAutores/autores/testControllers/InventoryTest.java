package com.dbPostgresAutores.autores.testControllers;

import com.dbPostgresAutores.autores.model.Category;
import com.dbPostgresAutores.autores.model.Film;
import com.dbPostgresAutores.autores.model.Language;
import com.dbPostgresAutores.autores.model.actorEnt.Actor;
import com.dbPostgresAutores.autores.model.dtos.AddressDto;
import com.dbPostgresAutores.autores.model.dtos.FilmDto;
import com.dbPostgresAutores.autores.model.dtos.StaffDto;
import com.dbPostgresAutores.autores.model.manage.Staff;
import com.dbPostgresAutores.autores.model.manage.Store;
import com.dbPostgresAutores.autores.model.market.Inventory;
import com.dbPostgresAutores.autores.model.market.InventoryRepository;
import com.dbPostgresAutores.autores.model.place.Address;
import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.model.place.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.Year;

@DataJpaTest //AutoconfigureTestDatabase requiere install H2 database dependency and test scope
public class InventoryTest {
    private final InventoryRepository inventoryRepository;

    private Film film;
    private Store store;

    @Autowired
    public InventoryTest(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @BeforeEach
    public void setUp(){
        Language language = new Language();
        language.setName("Spanish");

        Category category = new Category();
        category.setName("action");

        Actor actor = new Actor();
        actor.setFirstName("Antonio");
        actor.setLastName("Lara");

        FilmDto filmDto = new FilmDto("Academy Dinosaur",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                Year.of(2007),1, (short) 6,0.99, (short) 86,2,
                "G","{Trailers,'Deleted Scenes'}",
                "'academic':1 'battle':15 'canadian':20 'dinosaur':2 'drama':5 'epic':4 'feminist':8 'mad':11 'must':14 'rocky':21 " +
                        "'scientist':12 'teacher':17",
                1,1);

        film = new Film(filmDto,language,category,actor);
        //Mockito.when(filmRepository.findById(Mockito.any())).thenReturn(Optional.of(film)); //test the repository, not the controller.
        //Store Object
        City city = new City("Medellin",new Country("Colombia"));
        AddressDto addressDto = new AddressDto("47 MySakila","Boyacá","Alberta",
                1,"543333","5765767657");
        Address address = new Address(addressDto,city);

        Staff staff = new Staff(new StaffDto("Mike","Hillyer",1,"mike23@gmail.com",
                3,true,"Mike","8scs88cs7dsc8csa8778dc","The Bell"),address);
        store = new Store(address,staff);
    }


    @Test
    void saveInventory(){
        Inventory inventory = new Inventory(film,store);
        Inventory inventoryResponse = inventoryRepository.save(inventory);

        Assertions.assertNotNull(inventoryResponse);
        Assertions.assertEquals(inventoryResponse.getStoreId().getManagerStaffId().getEmail(),inventory.getStoreId().getManagerStaffId().getEmail());
        Assertions.assertEquals(inventoryResponse.getFilmId().get(0).getReleaseYear(),inventory.getFilmId().get(0).getReleaseYear());
        Assertions.assertTrue(inventoryResponse.getFilmId().contains(film));
        Assertions.assertInstanceOf(Inventory.class,inventoryResponse);
        Assertions.assertSame(inventory,inventoryResponse,"Similar Objects");
    }
}
