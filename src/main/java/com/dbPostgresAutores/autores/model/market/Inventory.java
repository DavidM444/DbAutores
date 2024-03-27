package com.dbPostgresAutores.autores.model.market;

import com.dbPostgresAutores.autores.model.Film;
import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import com.dbPostgresAutores.autores.model.manage.Store;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "inventory")
public class Inventory extends BaseUpdate {
    @OneToMany
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private List<Film>  filmId = new LinkedList<>();
    @OneToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store storeId;

    public Inventory(Film film, Store store) {
        super();
        this.filmId.add(film);
        this.storeId = store;
    }
    public  Inventory(){}
}
